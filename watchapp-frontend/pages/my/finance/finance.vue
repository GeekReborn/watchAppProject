<template>
	<view class="content">
		<view class="total-container">
			<picker class="month-selector" :range="orderYearMonthList" :value="index" @change="changeYearMonth">
				<view>{{orderYearMonthList[index]}}</view>
			</picker>
			<text>收入：￥{{total}}</text>
		</view>
		<view class="detail-container">
			<view class="order-container" v-for="(item, index) in orderDetail">
				<view class="user-avatar">
					<image :src="item.avatarUrl"></image>
				</view>
				<view class="username-info">
					<view class="username">{{item.username}}</view>
					<view class="datetime">{{item.createDatetime}}</view>
				</view>
				<view class="price-info">
					<view>￥{{item.payPrice}}</view>
				</view>
			</view>
		</view>
		<view class="no-more-container">没有更多订单了！</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				orderYearMonthList: [],
				index: 0,
				total: 0,
				orderDetail: []
			}
		},
		onLoad() {
			this.loadYearMonthList()
		},
		methods: {
			changeYearMonth(e) {
				let index = e.target.value
				this.index = index
				this.loadTotalByYearMonth(this.orderYearMonthList[index])
				this.loadOrderDetailByYearMonth(this.orderYearMonthList[index])
			},
			loadOrderDetailByYearMonth(orderYearMonth) {
				let that = this
				that.$api.apiPost({
					url: '/api/finance/detail',
					data: {
						orderYearMonth: orderYearMonth
					}
				})
				.then((res) => {
					console.log('detail: ', res.data.data)
					that.orderDetail = res.data.data
				})
			},
			loadTotalByYearMonth(orderYearMonth) {
				let that = this
				that.$api.apiPost({
					url: '/api/finance/total',
					data: {
						orderYearMonth: orderYearMonth
					}
				})
				.then((res) => {
					that.total = res.data.data
				})
			},
			loadYearMonthList() {
				let that = this
				that.$api.apiPost({
					url: '/api/finance/month'
				})
				.then((res) => {
					let orderYearMonth = res.data.data
					that.orderYearMonthList = orderYearMonth
					if (that.orderYearMonthList.length > 0) {
						that.index = 0
						that.loadTotalByYearMonth(orderYearMonth[that.index])
						that.loadOrderDetailByYearMonth(orderYearMonth[that.index])
					}
				})
			}
		}
	}
</script>

<style>
	.content {
		background-color: #F2F2F2;
		padding: 30rpx;
		min-height: 100vh;
	}
	.total-container {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}
	.total-container text {
		color: #666666;
		font-size: 28rpx;
	}
	.month-selector {
		width: 180rpx;
		height: 50rpx;
		padding-left: 40rpx;
		background-color: #FFFFFF;
		border-radius: 50rpx;
		line-height: 50rpx;
	}
	.month-selector::after {
		content: '▾';
		position: relative;
		top: -50rpx;
		left: 140rpx;
	}
	.detail-container {
		margin-top: 30rpx;
		border-radius: 10rpx;
		background-color: #FFFFFF;
	}
	.order-container {
		height: 80rpx;
		padding: 40rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		background-color: #ffffff;
		border-radius: 10rpx;
		border-bottom: 1px solid #f2f2f2;
	}
	.user-avatar image {
		width: 80rpx;
		height: 80rpx;
		border-radius: 100%;
		border: 1px soid #f2f2f2;
	}
	
	.username-info {
		padding-left: 40rpx;
		display: flex;
		flex-direction: column;
	}
	
	.username-info .username {
		color: #333333;
		font-size: 28rpx;
	}
	.username-info .datetime {
		color: #999999;
		font-size: 24rpx;
		font-weight: bold;
	}
	.price-info {
		text-align: right;
		flex-grow: 1;
		color: #009245;
	}
	.no-more-container {
		margin-top: 20rpx;
		text-align: center;
		color: #999999;
	}
</style>
