<template>
	<view class="container">
		<order-detail-shop :shop='shop'></order-detail-shop>
		<view class="split-line">
			<order-detail-form :order='order'></order-detail-form>
		</view>
		<view class="image-desc-info">
			<image-text Title="客户描述" :problem-desc-text="order.customerProfile" :images="order.beforeRepairPhoto"  ></image-text>
			<view class="upload-image-box">
				<image-text Title="正面及问题描述(至少上传1张图片)" :problemDescText="order.positiveItemDesc" :images="order.positiveItemPhoto" ></image-text>
			</view>
			<view class="upload-image-box">
				<image-text Title="背面及问题描述(至少上传1张图片)" :problemDescText="order.backItemDesc" :images="order.backItemPhoto" ></image-text>
			</view>
			<view class="upload-image-box">
				<image-text Title="侧面及问题描述(至少上传1张图片)" :problemDescText="order.sideItemDesc" :images="order.sideItemPhoto"  ></image-text>
			</view>
			<view class="upload-image-box">
				<image-text Title="故障面及问题描述(至少上传1张图片)" :problemDescText="order.problemDescription" :images="order.problemPhoto"  ></image-text>
			</view>
		</view>
		<view v-if="order.orderStep >= 4" class="order-info-panel">
			<view>店铺报价</view>
			<view class="info-value">￥{{order.payPrice}}</view>
		</view>
		<!-- 用户确认报价阶段 -->
		<view v-if="order.orderStep == 4 && !isMasterOrShopOwner">
			<order-detail-confirm-price @signed="signStatusHandle" ref="orderConfirm"></order-detail-confirm-price>
		</view>
		<!-- 客户操作 -->
		<cover-view v-if="order.orderStep == 4 && !isMasterOrShopOwner" class="operation-container">
			<button class="cancel-btn" @click="cancelOrder" >取消订单</button>
			<button class="confirm-btn" @click="confirmToAgreePricing">￥{{order.payPrice}} 同意并确认报价</button>
		</cover-view>
		<!-- 店铺操作 -->
		<cover-view v-if="order.orderStep >= 4 && order.orderStep <= 5 && isMasterOrShopOwner" class="operation-container">
			<button class="cancel-btn" @click="cancelOrder" >取消订单</button>
			<button class="confirm-btn" @click="confirmPayed" :disabled="!order.agreeRepair">确认已支付</button>
		</cover-view>
		
		<!-- 取消订单 -->
		<cover-view v-if="order.orderStep == 5 && !isMasterOrShopOwner" class="operation-container">
			<button class="cancel-btn-lg" @click="cancelOrder" >取消订单</button>
		</cover-view>
	</view>
</template>

<script>
	import orderDetailShop from '@/components/order-detail-shop/order-detail-shop.vue'
	import orderDetailForm from '@/components/order-detail-form/order-detail-form.vue'
	import orderDetailConfirmPrice from '@/components/order-detail-confirm-price/order-detail-confirm-price.vue'
	import imageText from "@/components/image-text/image-text.vue";
	export default {
		components:{orderDetailShop,orderDetailForm,imageText,orderDetailConfirmPrice},
		data() {
			return {
				shop:{
					shopname:'',
					locationtext:'',
				},
				order:{
					customerSignature: '',
					agreeRepair: false
				},
				// isSigned: false,
				userInfo: {},
				isMasterOrShopOwner: false
			}
		},
		onLoad(param) {
			if (param.order) {
				this.order = JSON.parse(param.order);
				console.log('order',this.order)
			}
			if (param.shop) {
				this.shop = JSON.parse(param.shop);
				console.log('order',this.shop)
			}
			this.userInfo = this.$userInfo.getUserInfo()
			this.isMasterOrShopOwner = this.$userInfo.isMasterOrShopOwner()
			this.initPageData();
		},
		onPullDownRefresh() {
			console.log('onPullDownRefresh...')
			var that = this;
			that.$login.checkTokenValidity()
			.then(()=>{
				that.loadOrderInfo();
			}).catch((res)=>{
				console.log('catch...',res)
			})
			setTimeout(function() {
				uni.stopPullDownRefresh();
			}, 1000)
		},
		methods: {
			initPageData(){
				this.setSelectorValue();
				// this.setImageInfo();
			},
			loadOrderInfo(orderId) {
				console.log('loadOrderInfo')
				var that = this;
				orderId = orderId || that.order.id;
				this.$api.apiGet({
						url: `/api/order/${orderId}`,
					})
					.then((res) => {
						// that.$msg.showMessage("请求ok！", res)
						console.log('请求ok！', res)
						that.order = res.data.data;
						that.setSelectorValue();
						// that.setImageInfo();
					})
					.catch((res) => {
						that.$msg.showMessage(res.data.msg || "请求失败！")
						that.showCtrl.orderDetail = false;
					})
			},
			
			setSelectorValue(order){
				var that = this;
				// this.order.purchaseDate = this.order.purchaseDate.substr(0,10);
				console.log('setSelectorValue')
				var dicrval = that.$dict.getSelector('strap_material_select',0)
				console.log('dicrval',dicrval)
				order = order || that.order;
				console.log('order...',JSON.parse(JSON.stringify(order)))
				// 品牌 watch_brand_id watchBrandId
				order.watchBrandId = that.$dict.getSelector('brand_select',order.watchBrandId);
				// 手表功能 watch_function watchFunction
				order.watchFunction = that.$dict.getSelector('function_select',order.watchFunction);
				// 机芯类别 watch_movement watchMovement
				order.watchMovement = that.$dict.getSelector('movement_category_select',order.watchMovement);
				// 表带材质 watchband_material watchbandMaterial
				order.watchbandMaterial = that.$dict.getSelector('strap_material_select',order.watchbandMaterial);
				// 表壳材质 case_material caseMaterial
				order.caseMaterial = that.$dict.getSelector('case_material_select',order.caseMaterial);
				// 表扣材质 clasp_material claspMaterial
				order.claspMaterial = that.$dict.getSelector('buckle_material_select',order.claspMaterial);
				// 外观品相 watch_exterior watchExterior
				order.watchExterior = that.$dict.getSelector('appearance_select',order.watchExterior);
				// 维修部件 repair_parts repairParts
				order.repairParts = that.$dict.getSelector('repair_parts_select',order.repairParts);
				// 是否保价 is_insure insure
				order.insure = that.$dict.getSelector('insurance_select', (order.insure?1:0));
				// 是否维修过 is_repair repair
				order.repair = that.$dict.getSelector('isrepair_select', order.repair?1:0);
				console.log('order...',order)
				that.order = order;
			},
			/* setImageInfo(order){
				order = order || this.order;
				// customerPhoto 
				var customerPhoto = [];
				customerPhoto.push(orderInfo.beforeRepairPhoto);
				orderInfo.beforeRepairPhoto = customerPhoto;
				// imagePositive
				var imagePositive = [];
				imagePositive.push(orderInfo.positiveItemPhoto);
				orderInfo.positiveItemPhoto = imagePositive;
				// imageBack
				var imageBack = [];
				imageBack.push(orderInfo.backItemPhoto);
				orderInfo.backItemPhoto = imageBack;
				// imageSide
				var imageSide = [];
				imageSide.push(orderInfo.sideItemPhoto);
				orderInfo.sideItemPhoto = imageSide;
				// imageMalfunction
				var imageMalfunction = [];
				imageMalfunction.push(orderInfo.problemPhoto);
				orderInfo.problemPhoto = imageMalfunction;
				this.order = order;
			} */
			/* ,setImageArchiveInfo(orderArchive){
				// imagePositiveItem
				var imagePositiveItem = [];
				imagePositiveItem.push(orderInfo.positiveItemPhoto);
				orderInfo.positiveItemPhoto = imagePositiveItem;
				// imageBackItem
				var imageBackItem = [];
				imageBackItem.push(orderInfo.backItemPhoto);
				orderInfo.backItemPhoto = imageBackItem;
				// imageSideItem
				var imageSideItem = [];
				imageSideItem.push(orderInfo.sideItemPhoto);
				orderInfo.sideItemPhoto = imageSideItem;
				// imageProblem
				var imageProblem = [];
				imageProblem.push(orderInfo.problemPhoto);
				orderInfo.problemPhoto = imageProblem;
				// imageItem
				var imageItem = [];
				imageItem.push(orderInfo.itemPhoto);
				orderInfo.itemPhoto = imageItem;
				// imageRepair
				var imageRepair = [];
				imageRepair.push(orderInfo.repairPhoto);
				repairPhoto.beforeRepairPhoto = imageRepair;
			} */
			
			signStatusHandle() {
				// this.isSigned = true
				this.order.customerSignature = this.$refs.orderConfirm.getImg()
				this.order.agreeRepair = true
			},
			confirmToAgreePricing() {
				var that = this
				that.$login.checkTokenValidity().then((login) => {
					if (!that.order.agreeRepair) {
						uni.showToast({
							title: '签名未上传完成，请稍等或重新签名！',
							icon: 'none'
						})
						return 
					}
					console.log('拿出来的：' + that.order.customerSignature)
					var orderId = that.order.id
					that.$api.apiPost({
						url: `/api/order/${orderId}/repair/confirm`,
						data: {
							customerSignature: that.order.customerSignature,
							isAgreeRepair: that.order.agreeRepair
						}
					})
					.then((res) => {
						uni.showToast({
							title: '已确认报价！',
							icon: 'none'
						})
						that.$websocket.sendData({
							orderId: that.order.id,
							orderStep: that.order.orderStep,
							notifyShopId:that.order.shopId,
							// notifyCustomerId: order.customerId
						})
						uni.navigateBack()
					})
					.catch((res) => {
						uni.showToast({
							title: '确认报价失败！',
							icon: 'none'
						})
					})
				})
			},
			confirmPayed() {
				var that = this
				
				that.$login.checkTokenValidity().then((login) => {
					uni.showModal({
						title: '是否已支付？',
						cancelText: '未支付',
						confirmText: '已支付',
						success(result) {
							if (result.confirm) {
								var orderId = that.order.id
								that.$api.apiPost({
									url: `/api/order/${orderId}/repair/doubleConfirm`
								})
								.then((res) => {
									uni.showToast({
										title: '已确认支付！',
										icon: 'none'
									})
									that.$websocket.sendData({
										orderId: that.order.id,
										orderStep: that.order.orderStep,
										// notifyShopId:that.order.shopId,
										notifyCustomerId: that.order.customerId
									})
									uni.navigateBack()
								})
								.catch((res) => {
									uni.showToast({
										title: '确认支付失败！',
										icon: 'none'
									})
								})
							}
						}
					})
					
				})
			},
			cancelOrder() {
				var that = this
				that.$login.checkTokenValidity().then((login) => {
					uni.showModal({
						title: '是否要取消订单？',
						cancelText: '不取消',
						confirmText: '确认取消',
						success(result) {
							if (result.confirm) {
								var orderId = that.order.id
								that.$api.apiPost({
									url: `/api/order/${orderId}/repair/cancel`
								})
								.then((res) => {
									uni.showToast({
										title: '已取消订单！',
										icon: 'none'
									})
									that.$websocket.sendData({
										orderId: that.order.id,
										orderStep: that.order.orderStep,
										notifyShopId:that.order.shopId,
										// notifyCustomerId: order.customerId
									})
									uni.navigateBack()
								})
								.catch((res) => {
									uni.showToast({
										title: '取消订单失败！',
										icon: 'none'
									})
								})
							}
						}
					})
					
				})
			}
		} // methods end
		
	}
</script>

<style>
	.container {
		padding-bottom: 140rpx;
	}
	.order-info-panel {
		margin-top: 20rpx;
		padding: 20rpx 40rpx;
		display: flex;
		justify-content: space-between;
		background-color: #ffffff;
	}
	.info-value {
		color: #009245;
	}

	/*图片上传*/
	.upload-image-box {
		margin-top: 20rpx;
	}
	.operation-container{
		position:fixed;
		bottom:0;
		width:100%;
		line-height: 0rpx;
		z-index:100;
	}
	.cancel-btn{
		width:40%;
		height:140rpx;
		line-height: 140rpx;
		text-align: center;
		font-size: 32rpx;
		color:#666;
		background-color: #fff;
		border-top: #EEEEEE 1px solid;
		border-radius: 0rpx ;
		display: inline-block;
		z-index: 999;
	}
	.cancel-btn-lg {
		width: 100%;
		height:140rpx;
		line-height: 140rpx;
		text-align: center;
		font-size: 32rpx;
		color:#fff;
		background-color: #009245;
		border-top: #EEEEEE 1px solid;
		border-radius: 0rpx ;
		display: inline-block;
		z-index: 999;
	}
	.confirm-btn{
		width:60%;
		height:140rpx;
		line-height: 140rpx;
		text-align: center;
		font-size: 32rpx;
		color:#fff;
		background-color: #009245;
		border-top: #EEEEEE 1px solid;
		border-radius: 0rpx ;
		display: inline-block;
		z-index: 999;
		
	}
</style>
