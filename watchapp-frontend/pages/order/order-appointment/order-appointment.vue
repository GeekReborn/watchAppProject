<template>
	<view class="container">
		<order-detail-shop :shop='shop'></order-detail-shop>
		<view class="form-group">
			<view class="form-container ">
				<view class="form-label">姓名</view>
				<input class="uni-input theme-color" v-model="form.customerName" name="username" placeholder="请在这输入姓名" />
			</view>
			<view class="form-container border-top">
				<view class="form-label">电话</view>
				<input class="uni-input theme-color" v-model="form.customerPhone" name="phone" type="number" placeholder="请在这输入联系电话" />
			</view>
			<view class="form-container no-height border-top"></view>
			<custom-select title="品牌" height="45" width="600" titleWidth="200" @selectId="getSelectId" ref="watchBrandId"
			 textAlign="left" placeholderText="请选择品牌" :list="brand" iconflag></custom-select>
			<view class="form-container border-top">
				<view class="form-label">表身号</view>
				<input class="uni-input theme-color" name="watchBodyNo" v-model="form.watchBodyNo" placeholder="在这输入表身号码" />
			</view>
		</view>
		<view class="form-group">
			<custom-select title="预约时间" textAlign="left" titleWidth="148" type="startDate" width="600" height="45" ref="appointmentDate"
			 :defaultdate="defaultDate" iconflag></custom-select>
		</view>
		<view class="form-group">
			<view class="form-container ">
				<view class="form-label">问题描述</view>
			</view>
			<textarea v-model="form.customerProfile" name="problemDescText" :style="'width: 600rpx;height:auto;'" class="problem-desc theme-color"
			 @blur="blurEventHandle" auto-height maxlength="255" placeholder="请输入问题描述" placeholder-class="placeholder-text"
			 @focus="focusEventHandle"></textarea>
			<view class="form-container">
				<sunui-upimg @change="uploadImage" :upload_auto="true" :upload_count="1" ref="upload"></sunui-upimg>
			</view>
		</view>

		<button type="primary" class="booking-btn" @click="orderClickHandle">确认预约</button>
	</view>
</template>

<script>
	import orderDetailShop from '@/components/order-detail-shop/order-detail-shop.vue'
	import customSelect from "@/components/custom-select/custom-select.vue"
	import sunuiUpimg from '@/components/sunui-upimg/sunui-upimg.vue'
	export default {
		components: {
			orderDetailShop,
			customSelect,
			sunuiUpimg
		},
		data() {
			return {
				shop: {
					id: 0,
					name: '',
					locationtext: '',
				},
				order: {
					orderNo: '112211222'
				},
				form: {
					customerName: '',
					customerPhone: '',
					watchBrandId: 0,
					watchBodyNo: '',
					reservationDatetime: '',
					customerProfile: '',
					beforeRepairPhoto: '-'
				},
				defaultDate: "",
				brand: [],
			}
		},
		onLoad(param) {
			console.log('order-appointment...onLoad(param)...')
			this.shop = param.shop ? JSON.parse(param.shop) : this.shop
			this.initPageData()
		},
		watch: {
			// customerName(val){
			// 	console.log('watch customerName', val)
			// },
			form: {
				handler: function(val, oldval) {
					console.log('watch form', val)
					var that = this;
					this.$nextTick(() => {
						var form = that.form;
						form.customerName = that.verification(val.customerName);
						form.customerPhone = that.verification(val.customerPhone);
						form.watchBrandId = that.verification(val.watchBrandId);
						form.watchBodyNo = that.verification(val.watchBodyNo);
						that.form = form;
					})
				},
				deep: true //对象内部的属性监听，也叫深度监听
			},
		},
		methods: {
			verification (content) {
				var val = content.toString();
				var ranges = [
					'\ud83c[\udf00-\udfff]',
					'\ud83d[\udc00-\ude4f]',
					'\ud83d[\ude80-\udeff]'
				];
				return val.replace(new RegExp(ranges.join('|'), 'g'), '');
			},
			initPageData(option) {
				console.log('initPageData...')
				this.initBrandList()
				this.initFormData()
				this.initDefaultDate();
			},
			getSelectId(res) {
				console.log('getSelectId...', res)
				//获取子模块中选择的品牌
				this.form.watchBrandId = res;
			},
			initBrandList() {
				console.log('initBrandList...')
				this.brand = this.$dict.getSelectors('brand_select');
			},
			initFormData() {
				var form = this.form;
				var user = this.$userInfo.getUserInfo();
				console.log('user', user)
				form.customerName = user.username;
				form.customerPhone = user.phone;
				this.form = form;
			},
			initDefaultDate() {
				var date = new Date()
				var year = date.getFullYear();
				var month = date.getMonth();
				var day = date.getDate();
				var hour = date.getHours();
				var dateText = year + "-" + (month + 1) + "-" + day + " " + hour + ":00";
				this.defaultDate = dateText;
				// customizeRequest.hiddenLoading()
			},
			focusEventHandle(e) {},
			blurEventHandle(e) {},
			orderClickHandle(e) {
				if (!this.checkOrderFormData()) {
					return false;
				}
				var that = this;
				uni.showModal({
					title: '提示',
					content: '确定预约该店铺？',
					success: function(res) {
						if (!res.confirm) {
							return false;
						}
						var form = that.form;
						form.reservationDatetime = that.$refs.appointmentDate.defaultDate ||
							that.defaultDate;
						form.reservationDatetime += ":00";
						form.customerId = that.$userInfo.getUserInfo().id;
						form.shopId = that.shop.id;
						form.orderName = that.shop.name + " 维修单";
						that.form = form;
						console.log(that.form)
						that.$login.checkTokenValidity().then((res) => {
							that.$api.apiPost({
								url: `/api/order/appointment/create`,
								data: that.form
							}).then((res) => {
								console.log('请求ok！', res)
								that.$msg.showMessage(res.data.msg || "已为您生成预约订单")
								let order = res.data.data;
								that.$websocket.sendData({
									orderId: order.id,
									orderStep: order.orderStep,
									notifyShopId:order.shopId
								})
								that.toOrderDetail(order.id, order.orderNo, order.orderStep, order.shopId)
							}).catch((res) => {
								console.log('请求fail！', res)
								that.$msg.showMessage(res.data.msg || "请求失败！")
							})
						})
					},
				})
			},
			toOrderDetail(orderId, orderNo, orderStep, shopId) {
				uni.redirectTo({
					url: `/pages/order/order-info/order-info?orderId=${orderId}&shopId=${shopId}&orderNo=${orderNo}&orderStep=${orderStep}`
				})
			},
			checkOrderFormData() {
				console.log('checkOrderFormData！')
				var form = this.form;
				if (!form.customerName) {
					this.$msg.showMessage('请输入姓名');
					return false;
				} else if (!form.customerPhone || form.customerPhone.length != 11) {
					this.$msg.showMessage('手机号长度有误');
					return false;
				} else if (!RegExp(/^\d{11}$/).test(form.customerPhone)) {
					this.$msg.showMessage('手机号格式不正确');
					return false;
				} else if (form.watchBrandId < 0) {
					this.$msg.showMessage('请选择手表类型');
					return false;
				} else if (!form.watchBodyNo) {
					this.$msg.showMessage('请输入表身号');
					return false;
				} else if (RegExp(/[\u4E00-\u9FA5]/g).test(form.watchBodyNo)) {
					this.$msg.showMessage('表身号不能输入汉字');
					return false;
				} else if (!form.customerProfile) {
					this.$msg.showMessage('请输入问题描述');
					return false;
				} 
				// else if (!form.beforeRepairPhoto) {
				// 	this.$msg.showMessage('请拍照记录手表问题');
				// 	return false;
				// }
				return true;
			},
			uploadImage(e) {
				console.log(e)
				this.form.beforeRepairPhoto = e[0]
			}
		}
	}
</script>

<style>
	.form-group {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		font-size: 32rpx;
		margin-top: 20rpx;
		background-color: #FFFFFF;
	}

	.form-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		align-items: center;
		width: 80%;
		height: 50rpx;
		padding: 20rpx;
	}

	.form-container.no-height {
		height: 1rpx;
		padding-top: 0;
		padding-bottom: 0;
		margin-top: 0;
		margin-bottom: 0;
	}

	.form-label {
		color: #333;
	}

	.form-content {
		color: #009245;
	}

	.border-top {
		border-top: #dddddd 1px solid;
	}

	.booking-btn {
		position: fixed;
		bottom: 0;
		width: 100%;
		height: 120rpx;
		line-height: 120rpx;
		border-radius: 0;
		border: 0;
	}

	button[type=primary] {
		color: #fff;
		background-color: #009245;
	}

	.uni-input {
		width: 75%;
		margin-left: 10rpx;
	}
</style>
