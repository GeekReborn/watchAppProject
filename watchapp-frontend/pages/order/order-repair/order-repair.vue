<template>
	<view class="container">
		<order-detail-shop :shopid='order.shopId' ref="shopInfo"></order-detail-shop>
		<view class="form-group">
			<custom-select bottom-line title="订单号" required type="input" height="50" disabled themeColor="#666" ref="orderSelect"></custom-select>
			<custom-select bottom-line title="姓名" required type="input" height="50" textalign='left' ref="customerName"></custom-select>
			<custom-select bottom-line title="电话" required type="input" height="50" ref="customerPhone"></custom-select>
			<custom-select bottom-line title="委托寄件" type="input" placeholder-text="在这输入委托寄件信息" height="50" ref="customerShipping"></custom-select>
			<custom-select title="是否保价" height="50" :list="select.insurance_select" ref="insurance_select" iconflag></custom-select>
		</view>
		<view class="form-group">
			<custom-select bottom-line title="品牌" required :height="pickerHeight" :list="select.brand_select" ref="brand_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="系列" :height="pickerHeight" :list="select.series_select" ref="series_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="型号" type="input" placeholder-text="在这输入型号" height="50" :list="select.model_select"
			 ref="model_select"></custom-select>
			<custom-select bottom-line title="购买日期" required datetype="month" type="date" :height="pickerHeight" ref="buy_date"
			 iconflag :defaultdate="defaultDate"></custom-select>
			<custom-select bottom-line title="是否维修过" required :height="pickerHeight" :list="select.isrepair_select" ref="isrepair_select"
			 iconflag></custom-select>
			<custom-select title="购买地点" type="input" :height="pickerHeight" ref="buyPosition" iconflag></custom-select>
		</view>
		<view class="form-group">
			<custom-select bottom-line title="手表功能" required :height="pickerHeight" :list="select.function_select" ref="function_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="机芯类别" required :height="pickerHeight" :list="select.movement_category_select" ref="movement_category_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="表壳材质" required :height="pickerHeight" :list="select.case_material_select" ref="case_material_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="表带材质" required :height="pickerHeight" :list="select.strap_material_select" ref="strap_material_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="表扣材质" required :height="pickerHeight" :list="select.buckle_material_select" ref="buckle_material_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="外观品相" required :height="pickerHeight" :list="select.appearance_select" ref="appearance_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="送修部件" required :height="pickerHeight" :list="select.repair_parts_select" ref="repair_parts_select"
			 iconflag></custom-select>
			<custom-select bottom-line title="表及按钮情况" required type="input" placeholder-text="在这输入情况描述" :height="pickerHeight"
			 ref="watch_button_input"></custom-select>
		</view>
		<view class="form-group">
			<image-text Title="客户描述" ref="customerDescImageTtext" required :problem-desc-text="order.customerProfile"
			 :image-list="order.beforeRepairPhoto"></image-text>
		</view>
		<view class="form-group">
			<upload-image required title="正面及问题描述(只能上传1张图片)" upload_id="positiveItem" @text-input="uploadText" @change="uploadImage"
			 :title_style="uploadStyle" :textarea_style="uploadStyle" :upload_style="uploadStyle" placeholder="在这写下手表正面的问题" ref="uploadImagePositive">
			 </upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="背面及问题描述(只能上传1张图片)" upload_id="backItem" @text-input="uploadText" @change="uploadImage"
			 :title_style="uploadStyle" :textarea_style="uploadStyle" :upload_style="uploadStyle" placeholder="在这写下手表背面的问题" ref="uploadImageBack">
			 </upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="侧面及问题描述(只能上传1张图片)" upload_id="sideItem" @text-input="uploadText" @change="uploadImage"
			 :title_style="uploadStyle" :textarea_style="uploadStyle" :upload_style="uploadStyle" placeholder="在这写下手表侧面的问题" ref="uploadImageSide">
			 </upload-image>
		</view>
		<view class="form-group">
			<upload-image required title="故障面及问题描述(只能上传1张图片)" upload_id="problem" @text-input="uploadText" @change="uploadImage"
			 :title_style="uploadStyle" :textarea_style="uploadStyle" :upload_style="uploadStyle" placeholder="在这写下手表故障面的问题" 
			 ref="uploadImageMalfunction">
			 </upload-image>
		</view>
		<view class="form-group">
			<view class="form-container full-width">
				<view class="price-label">我的报价<label style="color: #FF0000;">*</label></view>
				<input class="price-input" type="digit" v-model:value="order.payPrice" placeholder="在这输入你的报价" placeholder-style="color:#bbb;" />
				<view class="price-unit">元</view>
			</view>
		</view>
		<view class="tip">注:手表真伪，本店概不负责。</view>
		<view class="bottom-spilt"></view>
		<button type="primary" class="btn" @click="orderClickHandle">确认接修</button>
	</view>
</template>

<script>
	import orderDetailShop from '@/components/order-detail-shop/order-detail-shop.vue'
	import customSelect from "@/components/custom-select/custom-select.vue"
	import sunuiUpimg from '@/components/sunui-upimg/sunui-upimg.vue'
	import imageText from "@/components/image-text/image-text.vue";
	import uploadImage from "@/components/upload-image/upload-image.vue";
	export default {
		components: {
			orderDetailShop,
			customSelect,
			sunuiUpimg,
			imageText,
			uploadImage
		},
		data() {
			return {
				order: {
					agreeRepair: 0,
					archiveStatus: 0,
					backItemDesc: 0,
					backItemPhoto: 0,
					beforeRepairPhoto: "http://lc-3uxiAoex.cn-n1.lcfile.com/167ecbad8c1fbeb7365b.jpg",
					caseMaterial: 0,
					claspMaterial: 0,
					createBy: 0,
					createDatetime: "0000-0-0 00:00:00",
					customerId: 0,
					customerName: "-",
					customerPhone: "-",
					customerProfile: "-",
					customerSignature: 0,
					delStatus: 0,
					entrustedMail: "",
					id: 0,
					insure: 0,
					masterId: 0,
					orderName: "维修单",
					orderNo: "0000",
					orderQrCode: "10002",
					orderStep: 0,
					orderStepText: "暂无",
					payPrice: 0,
					positiveItemDesc: 0,
					positiveItemPhoto: 0,
					problemDescription: 0,
					problemPhoto: 0,
					purchaseDate: 0,
					purchasePlace: 0,
					remarks: 0,
					repair: 0,
					repairParts: 0,
					reservationDatetime: "0000-0-0 00:00:00",
					shopId: 0,
					sideItemDesc: 0,
					sideItemPhoto: 0,
					tablehandleButton: 0,
					watchBodyNo: "-",
					watchBrandId: 0,
					watchExterior: 0,
					watchFunction: 0,
					watchMaterial: 0,
					watchModel: 0,
					watchMovement: 0,
					watchSerialNumber: 0,
					watchSerie: 0,
					watchStrapNumber: 0,
					watchbandMaterial: 0
				},
				select: {
					insurance_select: [],
					brand_select: [],
					series_select: [],
					model_select: [],
					isrepair_select: [],
					function_select: [],
					movement_category_select: [],
					case_material_select: [],
					strap_material_select: [],
					buckle_material_select: [],
					appearance_select: [],
					repair_parts_select: [],
					table_button_select: [],
				},
				pickerHeight: 40,
				uploadStyle: 'width:90%;height:auto;padding-left:40rpx;padding-right:40rpx',
				payPrice: '',
				disabled: false
			}
		},
		onLoad(param) {
			console.log('param', param)
			this.order = JSON.parse(param.order);
			this.initPageData();
		},
		methods: {
			initPageData() {
				console.log('initPageData')
				this.initFormInput()
				this.initFormSelect()
			},
			initFormInput() {
				console.log('initFormInput')
				this.$refs.orderSelect.defaultText = this.order.orderNo
				this.$refs.customerName.defaultText = this.order.customerName
				this.$refs.customerPhone.defaultText = this.order.customerPhone
				this.$refs.customerShipping.defaultText = this.order.entrustedMail
				var customerPhoto = []
				customerPhoto.push(this.order.beforeRepairPhoto)
				this.order.beforeRepairPhoto = customerPhoto;
				this.payPrice = this.order.payPrice
				this.orderStep = this.order.orderStep
			},
			initFormSelect() {
				console.log('initFormSelect')
				// console.log('initFormSelect......',this.$dict.getSelectors('brand_select'))
				//select option 
				var that = this;
				var select = {
					insurance_select: that.$dict.getSelectors('insurance_select'), //['是', '否'],
					brand_select: that.$dict.getSelectors('brand_select'),
					series_select: that.$dict.getSelectors('series_select'),
					model_select: that.$dict.getSelectors('model_select'),
					isrepair_select: that.$dict.getSelectors('isrepair_select'),
					function_select: that.$dict.getSelectors('function_select'),
					movement_category_select: that.$dict.getSelectors('movement_category_select'),
					case_material_select: that.$dict.getSelectors('case_material_select'),
					strap_material_select: that.$dict.getSelectors('strap_material_select'),
					buckle_material_select: that.$dict.getSelectors('buckle_material_select'),
					appearance_select: that.$dict.getSelectors('appearance_select'),
					repair_parts_select: that.$dict.getSelectors('repair_parts_select'),
					table_button_select: that.$dict.getSelectors('table_button_select'),
				}
				console.log('initFormSelect......', select)
				that.select = select;

			},
			uploadText(e, position) {
				console.log('uploadText', e, position)
				position += 'Desc';
				position == 'problemDesc' ? position += 'ription' : position;
				this.order[position] = e.detail.value
			},
			uploadImage(e, position) {
				// console.log('uploadImage', e, position, this.order[position])
				position += 'Photo';
				this.order[position] = e[0]
			},
			orderClickHandle(e) {
				// console.log('orderClickHandle', e)
				if (this.disabled) {
					return 0;
				}
				// this.disabled = true;
				var postData = this.getFormData();
				console.log('postData...', postData)
				var pass = this.checkFormData(postData);
				if (!pass) {
					this.disabled = false;
					return 0;
				}
				console.log('check pass...')
				var that = this;
				var orderId = this.order.id;
				that.$login.checkTokenValidity().then((res) => {
					// debugger
					that.$api.apiPost({
						url: `/api/order/${orderId}/repair/accept`,
						data: postData
					}).then((res) => {
						console.log('请求ok！', res)
						that.$msg.showMessage(res.data.msg || "接修成功")
						that.disabled = false;
						let order = res.data.data;
						that.$websocket.sendData({
							orderId: order.id,
							orderStep: order.orderStep,
							// notifyShopId:order.shopId,
							notifyCustomerId: order.customerId
						})
						that.toOrderDetail(order.id, order.orderNo, order.orderStep, order.shopId)
					}).catch((res) => {
						// debugger
						console.log('请求fail！', res)
						that.$msg.showMessage(res.data.msg || "请求失败！")
						that.disabled = false;
					})
				})
			},
			toOrderDetail(orderId, orderNo, orderStep, shopId) {
				uni.redirectTo({
					url: `/pages/order/order-info/order-info?orderId=${orderId}&shopId=${shopId}&orderNo=${orderNo}&orderStep=${orderStep}`
				})
			},
			checkFormData(data) {
				console.log('checkFormData...')
				// if (!data.customerName) {
				// 	this.$msg.showMessage("客户姓名不能为空")
				// 	return false;
				// } else if (!data.customerPhone) {
				// 	this.$msg.showMessage("客户手机号不能为空")
				// 	return false;
				// } else if (!RegExp(/^\d{11}$/).test(data.customerPhone)) {
				// 	this.$msg.showMessage("客户手机号不正确")
				// 	return false;
				// } else if (data.purchaseTime == "0-01 00:00:00" || data.purchaseTime == "-01 00:00:00") {
				// 	this.$msg.showMessage("购买日期不能为空")
				// 	return false;
				// } else if (data.tablehandleButton == "") {
				// 	this.$msg.showMessage("表把按钮情况不能为空")
				// 	return false;
				// } else if (!data.positiveItemPhoto || !data.positiveItemDesc) {
				// 	this.$msg.showMessage("正面图片或者正面问题描述不能为空")
				// 	return false;
				// } else if (!data.backItemPhoto || !data.backItemDesc) {
				// 	this.$msg.showMessage("背面图片或者背面问题描述不能为空")
				// 	return false;
				// } else if (!data.sideItemPhoto || !data.sideItemDesc) {
				// 	this.$msg.showMessage("侧面图片或者侧面问题描述不能为空")
				// 	return false;
				// } else if (!data.problemPhoto || !data.problemDescription) {
				// 	this.$msg.showMessage("故障面图片或者故障问题描述不能为空")
				// 	return false;
				// } else if (!data.payPrice) {
				// 	this.$msg.showMessage("我的报价不能为空")
				// 	return false;
				// } else if (!RegExp(/(^(([1-9]\d*)|([0-9]\d*\.\d?[1-9]{1}))$)/).test(data.payPrice)) {
				// 	this.$msg.showMessage("请输入正确的价格")
				// 	return false;
				// }
				return true;
			},
			getFormData: function() {
				var that = this;
				// get order
				// var order = this.order;
				var order = {};
				// get input value

				order.caseMaterial = this.$refs.case_material_select.defaultValue
				order.claspMaterial = this.$refs.buckle_material_select.defaultValue
				order.customerName = this.$refs.customerName.defaultText
				order.customerPhone = this.$refs.customerPhone.defaultText
				order.entrustedMail = this.$refs.customerShipping.defaultText
				order.insure = this.$refs.insurance_select.defaultValue
				order.purchaseDate = that.$refs.buy_date.defaultDate || that.$refs.buy_date.defaultdate
				// debugger
				order.purchaseDate = order.purchaseDate + "-01 00:00:00";
				console.log('order.purchaseDate: -------- ' + order.purchaseDate)
				order.purchasePlace = this.$refs.buyPosition.defaultText
				order.repair = this.$refs.isrepair_select.defaultValue
				order.repairParts = this.$refs.repair_parts_select.defaultValue
				order.tablehandleButton = this.$refs.watch_button_input.defaultText
				order.watchBrandId = this.$refs.brand_select.defaultValue
				order.watchExterior = this.$refs.appearance_select.defaultValue
				order.watchFunction = this.$refs.function_select.defaultValue
				order.watchModel = this.$refs.model_select.defaultText
				order.watchMovement = this.$refs.movement_category_select.defaultValue
				order.watchSerie = this.$refs.series_select.defaultValue
				order.watchbandMaterial = this.$refs.strap_material_select.defaultValue
				order.beforeRepairPhoto = this.order.beforeRepairPhoto[0]
				order.masterId = this.$userInfo.getUserInfo().id || 0;
				order.orderNo = this.order.orderNo;
				order.id = this.order.id;
				order.backItemDesc = this.order.backItemDesc;
				order.backItemPhoto = this.order.backItemPhoto;
				order.positiveItemDesc = this.order.positiveItemDesc;
				order.positiveItemPhoto = this.order.positiveItemPhoto;
				order.problemDescription = this.order.problemDescription;
				order.problemPhoto = this.order.problemPhoto;
				order.sideItemDesc = this.order.sideItemDesc;
				order.sideItemPhoto = this.order.sideItemPhoto;
				order.payPrice = this.order.payPrice;
				return order;
			},
		}
	}
</script>

<style>
	.problem {
		margin-top: 30rpx;
	}

	.bottom-spilt {
		margin-top: 160rpx;
		min-height: 1rpx;
	}

	.price-label {
		width: 150rpx;
		font-weight: bold;
	}

	.price-input {
		width: 360rpx;
		text-align: right;
		font-size: 30rpx;
		padding-right: 20rpx;
	}

	.price-unit {
		width: 100rpx;
	}

	.tip {
		height: 100rpx;
		line-height: 100rpx;
		padding: 0 40rpx;
		color: #e8462f;
		font-size: 30rpx;
		/* margin-bottom:140rpx; */
	}
</style>
