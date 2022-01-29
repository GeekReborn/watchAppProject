<template>
	<view>
		<view class="order-base-info split-line">
			<custom-select bottom-line title="订单号" type="text" width="500" :defaulttext="order.orderNo"></custom-select>
			<custom-select bottom-line title="订单状态" type="text" width="500" :defaulttext="order.orderStepText"></custom-select>
		</view>
		<view class="order-base-info split-line">
			<custom-select bottom-line title="姓名" type="text" width="500" textalign='left' :defaulttext="order.customerName"></custom-select>
			<custom-select bottom-line title="电话" type="text" width="500" :defaulttext="order.customerPhone"></custom-select>
			<custom-select bottom-line title="委托寄件" type="text" textAlign="left" width="500" :defaulttext="order.entrustedMail"></custom-select>
			<custom-select title="是否保价" type="text" width="500" :defaulttext="order.insure?'是':'否'"></custom-select>
		</view>
		<view class="">
			<custom-select bottom-line title="品牌" width="500" type="text" :defaulttext="order.watchBrandId"></custom-select>
			<custom-select bottom-line title="系列" width="500" type="text" :defaulttext="order.watchSerie"></custom-select>
			<custom-select bottom-line title="型号" width="500" type="text" :defaulttext="order.watchModel"></custom-select>
			<custom-select bottom-line title="购买日期" width="500" type="text" :defaulttext="order.purchaseDate"></custom-select>
			<custom-select bottom-line title="是否维修过" width="400" type="text" :defaulttext="order.repair"></custom-select>
			<custom-select bottom-line title="购买地点" width="500" type="text" :defaulttext="order.purchasePlace"></custom-select>
			<custom-select bottom-line title="手表功能" width="500" type="text" :defaulttext="order.watchFunction"></custom-select>
			<custom-select bottom-line title="机芯类别" width="500" type="text" :defaulttext="order.watchMovement"></custom-select>
			<custom-select bottom-line title="表壳材质" width="500" type="text" :defaulttext="order.caseMaterial"></custom-select>
			<custom-select bottom-line title="表带材质" width="500" type="text" :defaulttext="order.watchbandMaterial"></custom-select>
			<custom-select bottom-line title="表扣材质" width="500" type="text" :defaulttext="order.claspMaterial"></custom-select>
			<custom-select bottom-line title="外观品相" width="500" type="text" :defaulttext="order.watchExterior"></custom-select>
			<custom-select bottom-line title="送修部件" width="500" type="text" :defaulttext="order.repairParts"></custom-select>
			<custom-select bottom-line title="表及按钮情况" width="450" type="text" :defaulttext="order.tablehandleButton"></custom-select>
		</view>
	</view>
</template>

<script>
	import customSelect from "@/components/custom-select/custom-select.vue"
	export default {
		name:"orderDetail",
		props:['order'],
		components: {
			customSelect
		},
		data() {
			return {};
		}
	}
</script>

<style>
/*
	地理位置
	*/
	.res-tittle {
		text-align: center;
		background-color: #FFFFFF;
		height: 100rpx;
		margin-bottom: 20rpx;
	}

	.shop-adress-container {
		align-items: center;
		justify-content: center;
		display: flex;
	}

	.shop-name {
		font-size: 30rpx;
		font-weight: bold;
	}

	.shop-adress {
		font-size: 26rpx;
	}


	.problem {
		margin-top: 30rpx;
	}

	/*问题描述*/
	.problem-container {
		width: 100%;
		min-height: 170rpx;
		background-color: #fff;
		padding: 20rpx 40rpx 0 40rpx;
	}

	.problem-title {
		font-size: 32rpx;
	}

	.problem-desc {
		font-size: 30rpx;
		width: 690rpx;
		min-height: 110rpx;
		margin-top: 20rpx;
	}

	.placeholder-text {
		color: #bbb;
	}

	/*图片上传*/
	.upload-image-box {
		margin-top: 20rpx;
	}

	/*报价*/
	.price-container {
		background-color: #fff;
		height: 100rpx;
		padding: 0 40rpx;
		margin-top: 20rpx;
	}

	.price-label,
	.price-unit {
		display: inline-block;
		font-size: 32rpx;
		height: 100rpx;
		line-height: 100rpx;
		float: left;
	}
	.price-input{
		display: inline-block;
		font-size: 32rpx;
		height: 100rpx;
		line-height: 100rpx;
		float: left;
		text-align: right;
	}
	.price-label {
		width: 150rpx;
		font-weight: bold;
	}

	.price-input {
		width: 450rpx;
		margin-right: 10rpx;
	}

	.price-unit {
		width: 60rpx;
	}

	.tip {
		height: 100rpx;
		line-height: 100rpx;
		padding: 0 40rpx;
		color: #e8462f;
		font-size: 30rpx;
	}

	/*按钮*/
	.cancel-btn,
	.confirm-btn {
		width: 50%;
		height: 140rpx;
		line-height: 140rpx;
		text-align: center;
		font-size: 32rpx;
		display: inline-block;
	}

	.cancel-btn {
		color: #666;
		background-color: #fff;
	}

	.confirm-btn {
		color: #fff;
		background-color: #009245;
	}

	.order-state {
		padding-top: 20rpx;
		margin-left: 40rpx;
		background-color: #FFFFFF;
		font-size: 32rpx;
	}

	.timeline-container {
		background: #FFFFFF;
		margin-top: 20rpx;
	}

	.timeline {}

	.two-bar-codes-container {
		margin-top: 20rpx;
		height: 460rpx;
		background-color: #FFFFFF;
		text-align: center;
	}
</style>
