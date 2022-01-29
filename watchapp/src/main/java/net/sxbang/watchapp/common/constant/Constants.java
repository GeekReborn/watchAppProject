package net.sxbang.watchapp.common.constant;

public interface Constants {

    // 店铺查询排序标识
    String SHOP_ORDER_DISTANCE = "distance";
    String SHOP_ORDER_FAVORABLE_RATE = "favorable_rate";

    /* 订单查询 order query */
    String ORDER_FILTER_ALL = "all";
    String ORDER_FILTER_BOOKED = "booked";
    String ORDER_FILTER_WORKING = "working";
    String ORDER_FILTER_COMPLETEED = "completed";

    /* 订单状态 */
    int ORDER_STATUS_1 = 1;        //	预约
    int ORDER_STATUS_2 = 2;        //	接受预约
    int ORDER_STATUS_3 = 3;        //	鉴定
    int ORDER_STATUS_4 = 4;        //	用户确认报价
    int ORDER_STATUS_5 = 5;        //	师傅确认
    int ORDER_STATUS_6 = 6;        //	维修中
    int ORDER_STATUS_7 = 7;        //	维修完成
    int ORDER_STATUS_8 = 8;        //	取表完成
    int ORDER_STATUS_9 = 9;        //	拒绝预约
    int ORDER_STATUS_10 = 10;        //	取消预约
    int ORDER_STATUS_11 = 11;        //	取消订单
    int ORDER_STATUS_12 = 12;        //	已评价

    /* 二维码使用场景 */
    int QRCODE_SCENE_APPOINTMENT = 1; // 预约二维码
    int QRCODE_SCENE_TAKE = 2; // 取表二维码

    /* role id */
    int ROLE_ADMIN_KEY = 1; //管理员
    int ROLE_MASTER_KEY = 2;    //维修师傅
    int ROLE_CUSTOMER_KEY = 3;  //顾客
    int ROLE_SHOPOWNER_KEY = 4; //店长

    Integer SOCKET_MSG_TYPE_SYSTEM = 0; // 系统发送的socket消息
    Integer SOCKET_MSG_TYPE_USER = 1; // 用户发送的socket消息

}
