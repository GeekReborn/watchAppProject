<template>
	<view class="container">
		<order-detail-shop :shopname="shopName" :locationtext="shopLocation"></order-detail-shop>
		<view class="form-group">
			<upload-image required title="正面照片及描述(只能上传1张图片)" upload_id="positiveItem" @text-input="uploadText" @change="uploadImage"
			 placeholder="在这写下手表正面的描述">
			</upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="背面照片及描述(只能上传1张图片)" upload_id="backItem" @text-input="uploadText" @change="uploadImage"
			 placeholder="在这写下手表背面的描述">
			</upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="侧面照片及描述(只能上传1张图片)" upload_id="sideItem" @text-input="uploadText" @change="uploadImage"
			 placeholder="在这写下手表侧面的描述">
			</upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="故障面照片及描述(只能上传1张图片)" upload_id="problem" @text-input="uploadText" @change="uploadImage"
			 placeholder="在这写下手表故障面的描述">
			</upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="修复面照片及描述(只能上传1张图片)" upload_id="repair" @text-input="uploadText" @change="uploadImage"
			 placeholder="在这写下手表修复面的描述">
			</upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="整表照片及描述(只能上传1张图片)" upload_id="item" @text-input="uploadText" @change="uploadImage"
			 placeholder="在这写下手表整表的描述">
			</upload-image>
		</view>
		<view class="bottom-spilt"></view>
		<button type="primary" class="btn" @click="submitClickHandle">确认留存</button>
	</view>
</template>

<script>
	import orderDetailShop from '@/components/order-detail-shop/order-detail-shop.vue';
	import uploadImage from "@/components/upload-image/upload-image.vue";
	export default {
		components: {
			orderDetailShop,
			uploadImage
		},
		data() {
			return {
				shopName: '-',
				shopLocation: '-',
				disable: false,
				orderId: 0,
				form: {
					positiveItemPhoto: '',
					positiveItemDesc: '',
					backItemPhoto: '',
					backItemDesc: '',
					sideItemPhoto: '',
					sideItemDesc: '',
					problemPhoto: '',
					problemDesc: '',
					itemPhoto: '',
					itemDesc: '',
					repairPhoto: '',
					repairDesc: '',
				}
			}
		},
		onLoad(param) {
			console.log('onLoad(param)...', param)
			this.shopLocation = param.locationText;
			this.shopName = param.shopName;
			this.orderId = param.orderId;
		},
		methods: {
			// 提交留存按钮的方法
			submitClickHandle(e) {
				console.log("submitClickHandle...", this.disable)
				if (this.disable) {
					return 0;
				}
				this.disable = true;
				let form = this.form;
				console.log("submitClickHandle...form...", form)
				let pass = this.checkFormData(form);
				if (!pass) {
					this.disable = false;
					return 0;
				}
				console.log("submitClickHandle...checkFormData...", pass)
				// this.disable = false;
				form.orderId = this.orderId;
				this.submitRequest(this.orderId,form);
			},
			// 	提交请求
			submitRequest(orderId,form){
				var that = this;
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiPost({
						url: `/api/order/${orderId}/repair/complete`,
						data: form
					}).then((res) => {
						console.log('请求ok！', res)
						that.$msg.showMessage(res.data.msg || "维修数据留存成功")
						// that.disabled = false;
						let order = res.data.data;
						that.$websocket.sendData({
							orderId: order.id,
							orderStep: order.orderStep,
							// notifyShopId:order.shopId,
							notifyCustomerId: order.customerId
						})
						that.toOrderDetail(order.id,order.orderNo,order.orderStep,order.shopId)
					}).catch((res) => {
						console.log('请求fail！', res)
						that.$msg.showMessage(res.data.msg || "请求失败！")
						that.disabled = false;
					})
				})
			},
			// 上传组件-文字的输入的监听
			uploadText(e, position) {
				console.log('uploadText', e, position)
				position += 'Desc';
				this.form[position] = e.detail.value
			},
			// 上传组件-图片地址的监听
			uploadImage(e, position) {
				console.log('uploadImage', e, position)
				position += 'Photo';
				this.form[position] = e[0]
			},
			// 检查数据格式
			checkFormData(data) {
				console.log('checkFormData...')
				if (!data.positiveItemPhoto || !data.positiveItemDesc) {
					this.$msg.showMessage("正面图片或者正面问题描述不能为空")
					return false;
				} else if (!data.backItemPhoto || !data.backItemDesc) {
					this.$msg.showMessage("背面图片或者背面问题描述不能为空")
					return false;
				} else if (!data.sideItemPhoto || !data.sideItemDesc) {
					this.$msg.showMessage("侧面图片或者侧面问题描述不能为空")
					return false;
				} else if (!data.problemPhoto || !data.problemDesc) {
					this.$msg.showMessage("故障面图片或者故障问题描述不能为空")
					return false;
				} else if (!data.repairPhoto || !data.repairDesc) {
					this.$msg.showMessage("修复面图片或者修复问题描述不能为空")
					return false;
				} else if (!data.itemPhoto || !data.itemDesc) {
					this.$msg.showMessage("整表图片或者整表问题描述不能为空")
					return false;
				}
				return true;
			},
			toOrderDetail (orderId, orderNo, orderStep, shopId) {
				console.log('toOrderDetail...')
				let path = `/pages/order/order-info/order-info?`;
				path += `orderId=${orderId}&shopId=${shopId}&orderNo=${orderNo}&orderStep=${orderStep}`
				uni.redirectTo({
					url: path
				})
			}
		}
	}
</script>

<style>

</style>
