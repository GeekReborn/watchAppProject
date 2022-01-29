<template>
	<view style="display: flex;flex-direction: column;">
		<view id="headerContainer">
			<!-- 搜索框 -->
			<view class="search-container">
				<uni-search-bar :radius="100" placeholder="请输入手机号" style="width:100%;" @confirm="searchValueConfirmHandle"></uni-search-bar>
			</view>
			<!-- tab选择器 -->
			<view class="tab-container">
				<view class="tab-box">
					<view :class="'tab'+ ((currentTab == 0) ? ' tab-active':'')" data-index=0 @click="tabChangeHandle">全部</view>
					<view :class="'tab'+ ((currentTab == 1) ? ' tab-active':'')" data-index=1 @click="tabChangeHandle">预约</view>
					<view :class="'tab'+ ((currentTab == 2) ? ' tab-active':'')" data-index=2 @click="tabChangeHandle">维修</view>
					<view :class="'tab'+ ((currentTab == 3) ? ' tab-active':'')" data-index=3 @click="tabChangeHandle">完成</view>
				</view>
			</view>
		</view>
	
		<!-- 订单显示列表 -->
		<scroll-view class="order-list-container" >
			<block v-for="(order, index) in orderList" :key="index">
				<view class="order-box" :data-id="order.id" :data-order-no="order.orderNo" :data-shop-id="order.shopId" :data-orderstep="order.orderStep" @click="clickOrderHandle" >
					<view class="order-container">
						<image class="shop-logo" mode="aspectFill" :src="order.shopImage"></image>
						<view class="order-info">
							<view class="order-info-box">
								<view class="order-name">{{order.orderName}}</view>
								<view class="order-statu">{{order.orderStepText}}</view>
							</view>
							<view class="order-info-box">
								<view v-if="order.payPrice != null" class="order-price">￥{{order.payPrice}}</view>
								<view v-if="order.payPrice == null" class="order-price">未报价</view>
							</view>
						</view>
					</view>
					<!-- 分割线 -->
					<view class="split-outbox">
						<view class="split-inline"></view>
					</view>
					<!-- 订单底部 -->
					<view class="order-footer">
						<view class="order-no">订单号:{{order.orderNo}}</view>
						<view v-if="order.createDatetime != null" class="order-time">{{order.createDatetime}}</view>
					</view>
				</view>
			</block>
		</scroll-view>
	
		<!-- 底部模拟tabbar高度，用来计算scroll高度 -->
		<view id="footerBox" class="footer-box"></view>
	</view>
</template>

<script>
	import {uniSearchBar} from '@dcloudio/uni-ui'
	export default {
		components: {
			uniSearchBar
		},
		data() {
			return {
				currentTab: 0,
				searchValue: '',
				orderLastTime: 0,
				orderPager: {
					currentPage: 0,
					pageSize: 100,
					count: 0,
					totalPage: 1
				},
				orderList: []
			}
		},
		// 页面组件加载时
		mounted(option) {
			this.initOrderList()
		},
		methods: {
			initOrderList(){
				var that = this
				that.$login.checkTokenValidity()
					.then((res) => {
						that.orderLastTime = new Date().getTime()
						that.orderList = []
						that.loadingOrders()
					})
				
			},
			//search
			searchValueConfirmHandle(event) {
				var that = this
				that.$login.checkTokenValidity()
					.then((res) => {
						that.searchValue = event.value
						that.currentTab = 4
						that.orderList = []
						that.orderLastTime = new Date().getTime()
						that.loadingOrders()
					})
			},
			loadingOrders() {
				var that = this
				var queryUrl = that.currentTab == 4 ? "/api/order/searchOrder" : "/api/order/orders"
				this.$api.apiGet({
					url: queryUrl,
					data: {
						searchValue: that.searchValue,
						orderListType: that.currentTab,
						orderLastTime: that.orderLastTime
					},
				})
				.then((res) => {
					that.orderList = res.data.data
				})
				.catch((res) => {
					that.$msg.showMessage("请求失败！")
				})
			},
			//状态获取
			tabChangeHandle(event){
				var that = this
				that.$login.checkTokenValidity()
					.then(() => {
						that.currentTab = event.currentTarget.dataset.index
						that.orderLastTime = new Date().getTime();
						that.orderList = []
						that.loadingOrders()
					})
			},
			// 订单点击事件
			clickOrderHandle (event) {
				// console.log('event',event)
				let orderId = event.currentTarget.dataset.id;
				let orderNo = event.currentTarget.dataset.orderNo;
				let orderStep = event.currentTarget.dataset.orderstep;
				let shopId = event.currentTarget.dataset.shopId;
				uni.navigateTo({
					url: `/pages/order/order-info/order-info?orderId=${orderId}&shopId=${shopId}&orderNo=${orderNo}&orderStep=${orderStep}`
				})
			}
		}
	}
</script>

<style>
	/* search */
	.search-container{
		display: flex;
		justify-content: center;
		align-items: center;
		padding:30rpx 0 0 0;
		background-color:#fff;
	}
	.search-icon{
		font-size:28rpx;
		display: inline-block;
	}
	.search-box{
		height: 72rpx;
		line-height: 72rpx;
		width: 510rpx;
		margin: auto;
		border-radius: 60rpx;
		border: 1rpx solid #f6f6f6;
		background-color:#f5f5f5;
		padding:0 30rpx;
		font-size:28rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 30rpx;
	}
	.search-input{
		width: 450rpx;
		margin-left: 10rpx;
	}
	/* tab */
	.tab-container{
		background-color: #fff;
	}
	.tab-box{
		width: 640rpx;
		margin:auto;
	}
	.tab{
		width: 70rpx;
		height: 28rpx;
		line-height: 28rpx;
		padding: 24rpx 40rpx;
		text-align: center;
		font-size:32rpx;
		margin:0 5rpx;
		display: inline-block;
	}
	.tab-active{
		color:#009245;
		border-bottom:4rpx solid #009245;
	}
	
	/* order list */
	.order-box{
		position: relative;
	}
	.new-notice{
		position: absolute;
		transform: rotate(180deg);
		right: -4rpx;
		top: 16rpx;
		width: 40rpx !important;
		height: 40rpx !important;
		z-index:1;
	}
	.order-list-container{
		min-height: 280rpx;
	}
	.order-container{
		height: 150rpx;
		background-color: #fff;
		position:relative;
		top: 20rpx;
		margin-bottom: 10rpx;
		display: flex;
	}
	.shop-logo{
		height: 100rpx;
		width: 100rpx;
		margin: 25rpx 30rpx 25rpx 40rpx;
		display: inline-block;
		float: left;
	}
	.order-info{
		display: inline-block;
		float: left;
		width: 500rpx;
		margin: 20rpx 0;
		flex: 1;
		padding-right: 40rpx;
	}
	.order-info-box{
		height: 56rpx;
		line-height: 56rpx;
	}
	.order-name,.order-statu{
		font-size: 32rpx;
		display: inline-block;
	}
	.order-name{
		float: left;
		max-width: 300rpx;
	}
	.order-statu{
		float: right;
		color:#009245;
		height: 56rpx;
		line-height: 56rpx;
		max-width: 200rpx;
	}
	.order-price{
		display: inline-block;
	}
	.order-price{
		
		font-size: 28rpx;
		float: left;
		max-width: 200rpx;
	}
	
	/* split */
	.split-outbox{
		padding: 0 40rpx;
		background-color: #fff;
	}
	.split-inline{
		height: 12rpx;
		border-bottom: 1rpx solid #eee;
	}
	/* order footer */
	.order-footer{
		display: flex;
		background-color: #fff;
		padding: 0 40rpx;
	}
	.order-no{
		height: 80rpx;
		line-height: 80rpx;
		font-size: 24rpx;
		flex: 1;
	}
	.order-time{
		font-size: 24rpx;
		float: right;
		height: 80rpx;
		line-height: 80rpx;
		max-width: 300rpx;
		text-align: right;
	}
	/* 底部加载提示 */
	.loading-tip{
		height: 80rpx;
		line-height: 80rpx;
		font-size: 28rpx;
		text-align: center;
		color: #C0C0C0;
	}
	/* 底部模拟tabBar高度 */
	.footer-box{
		height: 110rpx;
	}
</style>
