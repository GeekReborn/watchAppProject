<template>
	<view class="container">
		<view class="res-tittle">
			<view class="shop-name">{{shop.name}}</view>
			<view class="shop-adress-container">
				<view class="watch-icons icon-location 	landmark"></view>
				<view class="shop-adress">{{shop.locationText}}</view>
			</view>
		</view>
		<view class="order-info-default" v-if="showCtrl.default" >
			<view class="order-container">
				<view class="order-label-container ">
					<view class="order-label">订单号</view>
					<view class="order-content">{{order.orderNo}}</view>
				</view>
				<view class="order-label-container border-top" v-if="showCtrl.orderDetail" @click="getOrderDetail">
					<view class="order-label">订单详情</view>
					<view class="order-content-icon watch-icons icon-you"></view>
				</view>
			</view>
			<view class="order-container" v-if="showCtrl.orderQR">
				<view class="qrimg">
					<tki-qrcode @onDone="qrcodeDone" cid="qrcode1" ref="qrcode"
					:val="order.orderQrCode||QR.defaultVal" :size="QR.size" :unit="QR.unit" 
					:background="QR.background" :foreground="QR.foreground" :pdground="QR.pdground" 
					:icon="QR.icon" :iconSize="QR.iconsize" :lv="QR.lv" :onval="QR.onval" :loadMake="QR.loadMake" 
					:usingComponents="true" @result="qrR" />
				</view>
				<view class="qrcode-prompt">到店时请出示二维码</view>
			</view>
			<view class="order-container schedule-container">
				<view class="order-label-container">
					<view class="order-label">维修进度</view>
					<view class="order-content">{{orderStepText}}</view>
				</view>
				<view class="timeline">
					<uni-steps :options="timelines" direction="column" />
				</view>
			</view>
			<view class="appointment-action" >
				<button type="primary" class="btn " v-if="showCtrl.appointmentCancel" @click="cancelAppointment">取消预约</button>
				<button type="primary" class="btn " v-if="showCtrl.repairComplete" @click="completeRepair">维修完成</button>
				<button type="primary" class="btn " v-if="showCtrl.scanCodeTakeWatch" @click="scanClickHandle">扫码取表</button>
				<button type="primary" class="btn " v-if="showCtrl.submitRating" @click="submitRatingHandle">去评价</button>
			</view>
		</view>
		<view class="order-info-appointment" v-if="showCtrl.appointment">
			<view class="order-container">
				<view class="order-label-container ">
					<view class="order-label">姓名</view>
					<view class="order-content">{{order.customerName}}</view>
				</view>
				<view class="order-label-container border-top ">
					<view class="order-label">电话</view>
					<view class="order-content">{{order.customerPhone}}</view>
				</view>
				<view class="order-label-container border-top ">
					<view class="order-label">品牌</view>
					<view class="order-content">{{order.watchBrand}}</view>
				</view>
				<view class="order-label-container border-top ">
					<view class="order-label">表身号</view>
					<view class="order-content">{{order.watchBodyNo}}</view>
				</view>
			</view>
			<view class="order-container">
				<view class="order-label-container ">
					<view class="order-label">预约时间</view>
					<view class="order-content">{{order.reservationDatetime}}</view>
				</view>
			</view>
			<view class="image-desc-info">
				<view class="upload-image-box">
					<image-text Title="客户描述" :problemDescText="order.customerProfile" containerWidth="80%" containerPaddingX="80rpx" ></image-text>
				</view>
			</view>
			<view class="appointment-action" v-if="showCtrl.appointmentAccept">
				<button type="default" class="btn btn-half border-top" @click="refuseAppointment">拒绝预约</button>
				<button type="primary" class="btn btn-half btn-right border-top" @click="acceptAppointment">确认预约</button>
			</view>
			<button type="primary"  v-if="showCtrl.appointmentDispose" class="btn  border-top" @click="scanClickHandle">扫码接修</button>
		</view>
		<!-- <view class="bottom-spilt"></view> -->
	</view>
</template>

<script>
	import uniSteps from '@/components/uni-steps/uni-steps.vue';
	import imageText from "@/components/image-text/image-text.vue";
	import tkiQrcode from '@/components/tki-qrcode/tki-qrcode.vue';
	export default {
		components: {
			uniSteps,
			imageText,
			tkiQrcode
		},
		data() {
			return {
				shop: {
					id: 0,
					name: '钟表维修店',
					locationText: '未知',
				},
				order: {
					orderId: 1,
					orderNo: '订单号',
					orderStep: 0,

				},
				orderStepText: '请联系店员',
				timelines: [],
				showCtrl: {
					default: true,
					appointment: false,
					orderDetail: true,
					orderStep: true,
					appointmentAccept: false,
					appointmentCancel: false,
					appointmentDispose:false,
					orderQR: false,
					repairComplete:false,
					navigateHome:false,
					scanCodeTakeWatch:false,
					submitRating: false
				},
				timelinesData: [{
					"orderStepText": "接受预约",
					"createDate": "2020-01-10 16:40:22"
				}, {
					"orderStepText": "预约",
					"createDate": "2019-11-28 10:46:23"
				}],
				QR: {
					defaultVal: '没有二维码', // 要生成的二维码值
					size: 120, // 二维码大小
					unit: 'rpx', // 单位
					background: '#ffffff', // 背景色
					foreground: '#000000', // 前景色
					pdground: '#000000', // 角标色
					icon: '', // 二维码图标
					iconsize: 40, // 二维码图标大小
					lv: 3, // 二维码容错级别 ， 一般不用设置，默认就行
					onval: true, // val值变化时自动重新生成二维码
					loadMake: true, // 组件加载完成后自动生成二维码
					src: '' ,// 二维码生成后的图片地址或base64
				}
				// disabled:false,
				// ifShow: true,
				// masterPhone: '',
			}
		},
		onLoad(param) {
			console.log('param', param)
			this.order.orderNo = param.orderNo;
			this.order.id = param.orderId;
			this.order.orderStep = param.orderStep;
			this.shop.id = param.shopId;
			this.initPageData();
		},
		onShow() {
			this.initPageData();
			this.$websocket.addHandle("orderinfo",this.orderSocketHandle)
		},
		onPullDownRefresh() {
			this.initPageData();
			setTimeout(function() {
				uni.stopPullDownRefresh();
			}, 1000)
		},
		methods: {
			// 初始化页面数据
			initPageData() {
				console.log('initPageData')
				var that = this;
				that.loadShopData();
				that.$login.checkTokenValidity()
					.then((res) => {
						that.loadOrderInfo();
						that.loadOrderSteps();
					})
			},
			// 初始化页面可展示的元素
			initPageShow() {
				console.log('initPageShow')
				this.hideActionBtn();
				var isMaster = this.$userInfo.isMasterOrShopOwner();
				var order = this.order;
				var showCtrl = this.showCtrl;
				if (isMaster & order.orderStep <= 2) {
					this.showAppointmentForm()
				} else {
					this.showDefaultForm()
				} 
				if (isMaster && order.orderStep == 6) {
					showCtrl.repairComplete = true;
				}
				if (isMaster && order.orderStep == 7) {
					showCtrl.scanCodeTakeWatch = true;
				}
				if (!isMaster && order.orderStep == 8) {
					showCtrl.submitRating = true;
				}
				this.showCtrl = showCtrl;
				// this.showAppointmentForm()
			},
			// 初始化默认表单的展示
			showDefaultForm () {
				console.log('showDefaultForm')
				var showCtrl = this.showCtrl;
				var isMaster = this.$userInfo.isMasterOrShopOwner();
				var order = this.order;
				showCtrl.default = true;
				showCtrl.appointment = false;
				showCtrl.appointmentAccept = false;
				showCtrl.appointmentCancel = false;
				showCtrl.appointmentDispose = false;
				if (!isMaster & order.orderStep <= 2) {
					showCtrl.appointmentCancel = true;
				}
				if (!isMaster & (order.orderStep == 7 || order.orderStep == 2)) {
					showCtrl.orderQR = true;
				}
				
			},
			// 初始化预约表单的展示
			showAppointmentForm () {
				console.log('showAppointmentForm...')
				var showCtrl = this.showCtrl;
				var isMaster = this.$userInfo.isMasterOrShopOwner();
				var order = this.order;
				showCtrl.default = false;
				showCtrl.appointment = true;
				showCtrl.appointmentAccept = false;
				showCtrl.appointmentCancel = false;
				showCtrl.appointmentDispose = false;	
				if (isMaster & order.orderStep == 1) {
					showCtrl.appointmentAccept = true;	
				}
				if (isMaster & order.orderStep == 2) {
					showCtrl.appointmentDispose = true;	
				}
				showCtrl.appointmentCancel = false;
				this.showCtrl = showCtrl;
			},
			// 隐藏可操作的按钮
			hideActionBtn(){
				var showCtrl = this.showCtrl;
				var excludes = ['default','appointment','orderDetail','orderStep','orderQR'];
				for(var key in showCtrl) {
					if (excludes.includes(key)) {
						continue;
					}
					showCtrl[key] = false;
				}
				this.showCtrl = showCtrl;
			},
			loadShopData(shopId) {
				console.log('loadShopData')
				var that = this;
				// boolean
				shopId = shopId || that.shop.id;
				that.$api.get({
						url: `/open/shop/${shopId}`
					})
					.then((res) => {
						console.log('成功', res)
						that.shop = res.data.data;
					})
					.catch((res) => {
						console.log('失败')
					})
			},
			// 初始化订单信息
			loadOrderInfo(orderId) {
				console.log('loadOrderInfo')
				var that = this;
				orderId = orderId || that.order.id;
				this.$api.apiGet({
						url: `/api/order/${orderId}`,
					})
					.then((res) => {
						// that.$msg.showMessage("请求ok！", res)
						console.log('请求ok！', res,that.$dict.getValue('order_status',res.data.data.orderStep))
						res.data.data.watchBrand = that.$dict.getSelector('brand_select',res.data.data.watchBrandId);
						that.order = res.data.data;
						that.orderStepText = that.$dict.getValue('order_status',res.data.data.orderStep);
						// 成功加载订单数据后，根据最新数据初始化页面显示
						that.initPageShow();
					})
					.catch((res) => {
						// that.$msg.showMessage(res.data.msg || "请求失败！")
						that.showCtrl.orderDetail = false;
					})
			},
			// 初始化订单进度
			loadOrderSteps(orderId) {
				console.log('loadOrderSteps')
				var that = this;
				orderId = orderId || that.order.id;
				this.$api.apiGet({
						url: `/api/order/${orderId}/steps`,
					})
					.then((res) => {
						console.log('steps请求ok！', res)
						if (!res.data.data || res.data.data.length == 0) {
							return 0;
						}
						// that.orderStepText = res.data.data[0].orderStepText;
						that.timelines = res.data.data;
					})
					.catch((res) => {
						that.$msg.showMessage(res.data.msg || "请求失败！")
					})
			},
			// 查看订单详情表单
			getOrderDetail() {
				console.log('getOrderDetail')
				var that = this;
				let order = this.order;
				let orderInfo = JSON.stringify(order);
				let shopInfo = JSON.stringify(this.shop);
				that.$login.checkTokenValidity()
					.then(() => {
						uni.navigateTo({
							url: `../order-detail-info/order-detail-info?order=${orderInfo}&shop=${shopInfo}`
						})
					})
					.catch((res) => {
						that.$msg.showMessage(res.data.msg || "暂时不能为你打开详情")
					})
			},
			// 二维码回调事件
			qrR:function(res) {
				var QR = this.QR;
				QR.src = res;
				this.QR = QR;
			},
			// 二维码回调事件
			qrcodeDone(){
				this.$refs.qrcode._makeCode()
			},
			// 扫码事件
			scanClickHandle(e) {
				console.log('scanClickHandle...')
				let that = this;
				var isMaster = this.$userInfo.isMasterOrShopOwner();
				if (!isMaster) {
					return 0;
				}
				// 允许从相机和相册扫码
				uni.scanCode({
					success (res) {
						// console.log('条码类型：' + res.scanType);
						// console.log('条码内容：' + res.result);
						var code = res.result;
						that.$msg.showLoading("正在查询订单...")
						that.findOrderByCode(code);
					}
				});
			},
			// 扫码事件-获取相关订单
			findOrderByCode(code) {
				let that = this;
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiGet({
						url: `/api/order/code/${code}`,
					}).then((res) => {
						// that.$msg.showMessage("请求ok！", res)
						console.log('findOrderByCode请求ok！', res)
						that.$msg.hideLoading();
						let order = res.data.data;
						 if (1 == order.orderQrCodeScene && 2 == order.orderStep) {
							that.toOrderPepair(res.data.data); 
						 } else if (2 == order.orderQrCodeScene && 7 == order.orderStep) {
							 that.takeWatch(order);
						 }
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 扫码事件-确认取表逻辑
			takeWatch(order){
				console.log('takeWatch....', order)
				if (!order) {
					return 0;
				}
				// let orderNo = order.orderNo;
				// let watchBodyNo = order.watchBodyNo;
				// let customerPhone = order.customerPhone;
				let content = `订单编号：${order.orderNo}`;
				if (order.customerPhone) {
					content += `\n客户手机：${order.customerPhone}`;
				}
				if (order.watchBodyNo) {
					content += `\n表身号：${order.watchBodyNo}`;
				}
				var that = this;
				uni.showModal({
					title: '确认取表?',
					content,
					success(res) {
						if (!res.confirm) {
							return 0;
						}
						that.takeWatchRequest(order.id);
					}
				})
			},
			// 扫码事件-确认取表请求
			takeWatchRequest (orderId) {
				var that = this;
				var order = this.order;
				that.$msg.showLoading();
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiPost({
						url: `/api/order/${orderId}/takeWacth`,
					}).then((res) => {
						// that.$msg.showMessage("请求ok！", res)
						console.log('takeWatchRequest请求ok！', res)
						that.$msg.hideLoading();
						that.initPageData();
						that.sendSocketMsg(order.id,order.orderStep,0,order.customerId);
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 扫码事件-跳转接修页面
			toOrderPepair(order){
				order = JSON.stringify(order);
				uni.navigateTo({
					url: `/pages/order/order-repair/order-repair?order=${order}`
				})
			},
			// 接受预约
			acceptAppointment (e){
				console.log('acceptAppointment...')
				var order = this.order;
				var orderId = this.order.id;
				var that = this;
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiPost({
						url: `/api/order/${orderId}/appointment/accept`,
					}).then((res) => {
						console.log('acceptAppointment请求ok！', res)
						that.$msg.hideLoading(); 
						that.hideActionBtn();
						that.initPageData();
						that.sendSocketMsg(order.id,order.orderStep,0,order.customerId);
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 拒绝预约
			refuseAppointment (e){
				var order = this.order;
				var orderId = this.order.id;
				var that = this;
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiPost({
						url: `/api/order/${orderId}/appointment/refuse`,
					}).then((res) => {
						console.log('refuseAppointment请求ok！', res)
						that.$msg.hideLoading(); 
						that.hideActionBtn();
						that.initPageData();
						that.sendSocketMsg(order.id,order.orderStep,0,order.customerId);
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 取消预约
			cancelAppointment (e){
				var order = this.order;
				var orderId = this.order.id;
				var that = this;
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiPost({
						url: `/api/order/${orderId}/appointment/cancel`,
					}).then((res) => {
						console.log('cancelAppointment请求ok！', res)
						that.$msg.hideLoading(); 
						that.hideActionBtn();
						that.initPageData();
						that.sendSocketMsg(order.id,order.orderStep,order.shopId,order.customerId);
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 维修完成
			completeRepair (e){
				console.log('completeRepair...', e)
				let shopName = this.shop.name;
				let locationText = this.shop.locationText;
				let orderId = this.order.id;
				var path = '/pages/order/order-complete/order-complete';
				path += `?orderId=${orderId}&shopName=${shopName}&locationText=${locationText}`;
				uni.showModal({
					title: '是否维修完成？',
					success(res) {
						if (res.confirm) {
							uni.navigateTo({
								url: path
							})
						}
					}
				})
				
			},
			submitRatingHandle() {
				let shopId = this.shop.id
				let orderId = this.order.id
				uni.navigateTo({
					url: `/pages/order/order-rating/order-rating?shopId=${shopId}&orderId=${orderId}`
				})
			},
			sendSocketMsg(orderId,orderStep,notifyShopId,notifyCustomerId){
				console.log('sendSocketMsg')
				if (!notifyShopId && !notifyCustomerId) {
					console.log('sendSocketMsg... parameter miss...')
					return 0;
				}
				var data = {
					orderId:orderId,
					orderStep:orderStep,
					notifyShopId:notifyShopId,
					notifyCustomerId: notifyCustomerId
				}
				var isMaster = this.$userInfo.isMasterOrShopOwner();
				if (notifyShopId && !isMaster) {
					delete data.notifyCustomerId;
				} else if (notifyCustomerId && isMaster) {
					delete data.notifyShopId;
				}
				this.$websocket.sendData(data)
			},
			orderSocketHandle(res){
					console.log('orderSocketHandle')
					this.initPageData()
			}
			/* initPageData() {
				console.log('getOrderDetail')
			},
			loadShopData(shopId) {
				console.log('getOrderDetail')
			},
			loadOrderInfo(orderId) {
				console.log('getOrderDetail')
			},
			loadOrderSteps(orderId) {
				console.log('getOrderDetail')
			},
			getOrderDetail() {
				console.log('getOrderDetail')
			}, */
		}
	}
</script>

<style>
	.container {
		background-color: #eee;
	}

	.res-tittle {
		text-align: center;
		background-color: #FFFFFF;
		height: 100rpx;
		padding-top: 20rpx;
		padding-bottom: 10rpx;
	}

	.shop-adress-container {
		align-items: center;
		justify-content: center;
		display: flex;
	}

	.shop-name {
		font-size: 30rpx;
		font-weight: bold;
		margin-bottom: 10rpx;
	}

	.shop-adress {
		font-size: 26rpx;
	}

	.landmark {
		font-size: 26rpx;
		color: #009245;
	}

	.order-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		font-size: 32rpx;
		margin-top: 20rpx;
		background-color: #FFFFFF;
	}

	.timeline {
		width: 86%;
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
		align-items: center;
		padding-bottom: 40rpx;
	}

	.order-label-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		align-items: center;
		width: 80%;
		height: 50rpx;
		padding: 20rpx;
	}

	.order-content {
		color: #009245;
	}

	.order-content-icon {
		color: #009245;
		font-size: 28rpx;
	}

	.border-top {
		border-top: #DDDDDD 1px solid;
	}

	.border-bottom {
		border-bottom: #DDDDDD 1px solid;
	}

	.image-desc-info {}

	/*图片上传*/
	.upload-image-box {
		background-color: #fff;
		margin-top: 20rpx;
	}
	.bottom-spilt {
		margin-top: 160rpx;
		min-height: 1rpx;
	}
	.btn {
		position: fixed;
		bottom: 0;
		width: 100%;
		height: 140rpx;
		line-height: 140rpx;
		border-radius: 0;
		/* border: 0; */
	}
	.btn.btn-half {
		width: 50%;
	}
	.btn.btn-right {
		left: 50%;
	}
	button[type=primary] {
		color: #fff;
		background-color: #009245;
		border-color: #009245;
	}
	button[type=default] {
		color: #666;
		background-color: #fff;
	}
	button::after {
		border: 0;
	}
	
	.qrimg {
		display: flex;
		justify-content: center;
		width: 100%;
		margin-top: 80rpx;
	}
	.qrcode-prompt {
		padding: 20rpx 0rpx;
		font-size: 28rpx;
		color: #808080
	}
	/* .two-bar-codes {
		height: 240rpx;
		width: 240rpx;
		margin: 48rpx -100%;
	}

	.two-bar-codes-container {
		background-color: #FFFFFF;
		text-align: center;
	}

	.two-bar-prompt {
		padding: 20rpx 0rpx;
		font-size: 28rpx;
		color: #808080
	}

	.contacts-phone {
		font-size: 28rpx;
		color: #808080;
		text-align: center;
		line-height: 80rpx;
		background-color: #F8F8F8;
		height: 80rpx;
		display: flex;
		align-items: center;
		justify-content: center;

	}

	

	.qrimg-i {
		margin-top: 80rpx;
	}

	.prompting {
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		background-color: #FFFFFF;
		font-size: 36rpx;
		text-align: center;
		line-height: 100rpx;
		color: #20A05D;
		height: 100rpx;
	}

	.schedule-container {
		margin: 20rpx 0rpx;
		background-color: #FFFFFF;
		width: 100%;
	}

	.schedule-title {
		margin-left: 130rpx;
	}

	.schedule-content {
		margin-right: 160rpx;
	} */
</style>
