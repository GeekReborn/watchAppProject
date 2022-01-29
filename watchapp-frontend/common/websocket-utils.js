import Api from '@/common/api.js'
import UserInfoUtils from '@/common/user-info-utils.js'
// socket设置
var webSocketSetting = {
	// socket地址
	domain: '',
	// socket最大重连次数
	reconnetMax: 3
};
// 心跳间隔时长 单位毫秒
const heartBeatTime = 10000;
// 记录心跳的标志
var heartBeatFlag = 0;
// onMessage需要触发的事件列表
var socketHandles = {
	size:0
};
// socket对象
var socket = null;
// socket重连次数
var reconnetCount = 0;

/** 连接socket方法 
 * @param {obj} option 可选: open,error,close
 */
function connet(option) {
	console.log('socket connet...', webSocketSetting.domain, UserInfoUtils.getAccessToken())
	var userInfo = UserInfoUtils.getUserInfo();
	if (!userInfo) {
		console.log('socket connet failure...accessToken is null...')
		return 0;
	}
	close();
	return new Promise((resolve, reject) => {
		var subPath = userInfo.id + "/" + userInfo.accessToken
		var socketObj = uni.connectSocket({
			url: webSocketSetting.domain + subPath,
			success: (res) => {
				//开启心跳
				console.log("打开websocket链接有效")
				heartBeat()
			},
			fail: (res) => {
				console.log("打开websocket链接无效")
			}
		});
		socket = socketObj;
		// socket打开时的监听事件
		socketOnOpen(option, socketObj, resolve);
		// socket 关闭,出错,推送消息的监听
		socketOnMessage();
		socketOnError(option, reject)
		socketOnClose();
	})
}

function socketOnOpen(option, socketObj, resolve) {
	uni.onSocketOpen((res) => {
		console.log('WebSocket连接已打开...', socket, socketObj);
		resolve(res);
		reconnetCount = 0;
	});
}

function socketOnMessage() {
	console.log('socketOnMessage...')
	uni.onSocketMessage((res) => {
		console.log('socketOnMessage...onSocketMessage...', res,socketHandles)
		
		var resData = JSON.parse(res.data)
		console.log('socketOnMessage...onSocketMessage...res', resData)
		if (!resData || 1 != resData.type) {
			return 0;
		}
		// uni.showToast({
		// 	icon: 'none',
		// 	title:"您有订单发生变化"
		// })
		for (var index in socketHandles) {
			console.log('onSocketMessage socketHandles index...'.index)
			if (index == 'size') {
				continue;
			}
			socketHandles[index](resData.data)
		}
	})
}
function socketOnClose(option, rejectEven) {
	uni.onSocketClose((closeRes) => {
		console.log('onSocketClose...', closeRes)
		if (1006 != closeRes.code) {
			return;
		}
		socket = null;
		reconnetCount = 0;
		reconnet(option).catch((res) => {
			if(rejectEven){
				rejectEven(errorRes)
			}
		})
	})
}
function socketOnError(option, rejectEven) {
	uni.onSocketError((errorRes) => {
		console.log('errorRes...', errorRes)
		reconnet(option).catch((res) => {
			if(rejectEven){
				rejectEven(errorRes)
			}
		})
	});
}

function close() {
	console.log('socket close...', socket)
	if (!socket || socket.readyState != 1) {
		console.log('socket close failure...socket is null...')
		return 0;
	}
	uni.closeSocket({
		success() {
			socket = null;
			heartBeatFlag = 0;
			reconnetCount = 0;
			socketHandles = [];
		}
	})
}

function reconnet(option) {
	console.log('socket reconnet...')
	return new Promise((resolve, reject) => {
		console.log('reconnet reconnetCount: ' + reconnetCount)
		if (reconnetCount >= webSocketSetting.reconnetMax) {
			reject();
			return 0;
		}
		setTimeout(function() {
			console.log('reconnet setTimeout reconnetCount: ' + reconnetCount)
			// debugger
			reconnetCount++;
			connet(option).then((res) => {
				console.log('重连成功...')
				resolve(res)
			}).catch((res) => {
				console.log('重连失败...')
				reconnet(option).catch((res) => {
					reject(res)
				})
			})
		}, 1000)
	})
}

function sendMsg(data) {
	console.log('socket sendMsg...')
	uni.sendSocketMessage({
		data: data
	});
}

function sendData(data) {
	console.log('socket sendData...')
	var dataStr = generateStationNotice(data);
	if (dataStr) {
		sendMsg(dataStr)
	}
}

function generateStationNotice(data) {
	var orderStationNoticeStructure = {
		orderId: 0,
		orderStep: 0,
		isRead: false,
		notifyShopId: 0,
		notifyCustomerId: 0,
		message: "",
		createdDatetime: new Date().getTime(),
		createdBy: 0,
		readDatetime: "",
		readBy: 0,
	}
	if (data.orderId) {
		orderStationNoticeStructure.orderId = data.orderId;
	}
	if (data.orderStep) {
		orderStationNoticeStructure.orderStep = data.orderStep;
	}
	if (data.notifyShopId) {
		orderStationNoticeStructure.notifyShopId = data.notifyShopId;
	}
	if (data.notifyCustomerId) {
		orderStationNoticeStructure.notifyCustomerId = data.notifyCustomerId;
	}
	return JSON.stringify({
		data: orderStationNoticeStructure,
		type: 1
	});
}

function heartBeat() {
	console.log("heartBeat...")
	if (!socket || socket.readyState != 1) {
		return 0;
	}
	console.log("heartBeat...心跳包发送...")
	heartBeatFlag = setTimeout(function() {
		sendMsg(JSON.stringify({
			type: 0,
			message: "HeartbeatHandle"
		}));
		heartBeat()
	}, heartBeatTime)
}

function countHandles() {
	console.log("countHandles...len...", socketHandles.length)
	return socketHandles.size;
}

function addHandle(key,func) {
	console.log('addHandle ... func ',func, (typeof func))
	if (typeof func != 'function') {
		console.log('func is not function...')
		return 0;
	}
	socketHandles['size'] += 1;
	socketHandles[key] = func;
}

function deleteHandle(key) {
	if (socketHandles.size <= 0) {
		return 0;
	}
	delete socketHandles[key];
}

function clearHandles() {
	socketHandles = {
		size : 0
	};
}

module.exports = {
	webSocketSetting,
	heartBeat,
	connet,
	close,
	reconnet,
	sendMsg,
	sendData,
	countHandles,
	clearHandles,
	addHandle,
	deleteHandle
};
