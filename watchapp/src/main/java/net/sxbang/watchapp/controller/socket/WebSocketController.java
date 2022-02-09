package net.sxbang.watchapp.controller.socket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.model.SysShopUser;
import net.sxbang.watchapp.model.SysStationNotice;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.service.SocketService;
import net.sxbang.watchapp.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

// 端点
@ServerEndpoint("/websocket/{userId}/{access_token}")
@Component
@Slf4j
public class WebSocketController implements Constants {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketController> webSocketSetCustomer = new CopyOnWriteArraySet<WebSocketController>();
    private static CopyOnWriteArraySet<WebSocketController> webSocketSetMaster = new CopyOnWriteArraySet<WebSocketController>();

    //concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象。key值是用户id
    private static ConcurrentHashMap<Integer, WebSocketController> webSocketMap = new ConcurrentHashMap<>();


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private static SocketService socketService;

    @Autowired
    public void setSocketService(SocketService socketService) {
        WebSocketController.socketService = socketService;
    }

    private String accessToken;
    private Authentication authentication;
    private Integer shopId;
    private Integer userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId, @PathParam("access_token") String accessToken) throws IOException {
        System.out.println("...socket onopen...|" + accessToken + "|...onlineCout: " + onlineCount);
        this.authentication = RoleUtils.getAuthentication(accessToken);
        // 确认用户是否存在
        SysUser user = socketService.getUserByAuthentication(this.authentication, userId);
        if (null == user) {
            sendMessage(generatorMsg(-1, null, "连接失败"));
            return;
        }
        // 处理需要保存的数据
        // userId 和 shopId用于推送时比对用户
        this.userId = user.getId();
        SysShopUser shopUser = socketService.findShopIdByAuthentication(this.authentication, this.userId);
        if (null != shopUser) {
            this.shopId = shopUser.getShopId();
        }
        this.session = session;
        this.accessToken = accessToken;
        log.info("...data...userId:" + this.userId + "...shopid:" + this.shopId + "...this.accessToken" + this.accessToken);
        // 添加到socket维护队列里
        webSocketMap.put(this.userId, this);
        // 发送消息通知连接成功
        sendMessage(generatorMsg(0, null, "连接成功"));
        onlineCount.incrementAndGet();
        log.info("...webSocketMap: " + webSocketMap.size());
    }

    /** 连接建立成功调用的方法 */
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("...socket onMessage...webSocketMap: " + webSocketMap.size()+"...msg: " + message);
        // 把json字符串转换成json对象
        JSONObject messageObj = JSONObject.parseObject(message);
        // 如果message里不存在type属性，判断为无效消息
        if (!messageObj.containsKey("type")) {
            return;
        }
        Integer type = (Integer) messageObj.get("type");
        // 如果message的type属性不为 1，判断为无效消息
        if (!SOCKET_MSG_TYPE_USER.equals(type)) {
            return;
        }
        // 如果message里不存在data属性，判断为无效消息
        if (!messageObj.containsKey("data")) {
            return;
        }
        if (null == this.authentication) {
            this.authentication = RoleUtils.getAuthentication(accessToken);
        }
        // 把消息保存到数据库并转换成SysStationNotice对象
        SysStationNotice sysStationNotice = socketService.saveNotice(messageObj.get("data").toString(), this.authentication);
        // sysStationNotice 为null说明消息保存失败，终止业务
        if (null == sysStationNotice) {
            return;
        }
        Integer customerId = sysStationNotice.getNotifyCustomerId();
        Collection targets = webSocketMap.values();
        Integer shopId = sysStationNotice.getNotifyShopId();
        // 生成消息文本
        String msg = generatorMsg(1, sysStationNotice, null);
        sendMassMessage(targets, msg, customerId, shopId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("...socket onClose......onlineCout: " + onlineCount);
        if (0 < onlineCount.get()) {
            onlineCount.decrementAndGet();
        }
        webSocketMap.remove(this.userId, this);
//        log.info("webSocketSetCustomer: "+webSocketSetCustomer.size()+"..|..webSocketSetMaster: "+webSocketSetMaster.size()+"..|..webSocketMap: "+webSocketMap.size());
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("...socket onError...|" + accessToken + "|...onlineCout: " + onlineCount);
//        log.info("..|..webSocketMap: "+webSocketMap.size());
        if (0 < onlineCount.get()) {
            onlineCount.decrementAndGet();
        }
        webSocketMap.remove(this.userId);
        error.printStackTrace();
//        log.info("..|..webSocketMap: "+webSocketMap.size());
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
//        log.info("session is null?" + (this.session == null) + "...msg: " + message);
        // 如果当前session为空或者session已经关闭，则放弃发送消息并打印日志
        if (null == this.session || !this.session.isOpen()) {
            log.info("session is null or session is not open...");
            return;
        }
        this.session.getBasicRemote().sendText(message);
    }

    /*
     * 群发消息
     * */
    public void sendMassMessage(Collection<WebSocketController> webSocketSet, String message, Integer userId, Integer shopId) throws IOException {
        log.info("...sendMassMessage...webSocketSet:" + webSocketSet.size() + " ... message: " + message);
        for (WebSocketController item : webSocketSet) {
            // 如果item为null则跳过
            if (null == item) {
                continue;
            }
//            log.info("webSocketSet -- item.shopId:" + item.shopId + ",userId:" + item.userId + ",param-shopId:" + shopId + ",param-useriD:" + userId);
            // 当存在需要通知的shopID时，与item的shopId比对，一致则发送消息
            if (shopId != null && !shopId.equals(0) && shopId.equals(item.shopId)) {
                item.sendMessage(message);
                // 否则，说明需要被通知的一般消费者，所以通过userId进行比对，，一致则发送消息
            } else if (userId != null && !userId.equals(0) && userId.equals(item.userId)) {
                item.sendMessage(message);
            }
        }
    }

    /**
     * 按照固定格式生成消息文本
     * @param type 消息类型
     * @param obj  数据
     * @param tips 额外的消息提示
     */
    String generatorMsg(int type, Object obj, String tips) {
        StringBuffer msg = new StringBuffer();
        msg.append("{\"type\":");
        msg.append(type);
        if (null != obj) {
            msg.append(",\"data\":");
            msg.append(JSONObject.toJSONString(obj));
        }
        if (null != tips) {
            msg.append(",\"msg\":\"");
            msg.append(tips);
            msg.append("\"");
        }
        msg.append("}");
        return msg.toString();
    }
}
