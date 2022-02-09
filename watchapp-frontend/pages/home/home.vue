<template>
	<view class="content">
		<view class="banner-container">
			<swiper class="banner" indicator-dots="true" autoplay="true">
				<swiper-item v-for="(item) in bannerList" :key="item.id">
					<image class="banner-image" :src="item.bannerUrl" mode="scaleToFill"></image>
				</swiper-item>
			</swiper>
		</view>
		
		<view class="shop-container">
			<view class="shop-header">
				<view class="shop-title">热门店铺</view>
				<view class="shop-selector">
					<!-- <view class="btn-selector btn-selector-active" :class="divClass" @click="switchSelector" data-order="distance"> -->
					<view class="btn-selector" :class="{'btn-selector-active': order=='distance'}" @click="switchSelector" data-order="distance">
						<text>距离</text>
					</view>
					<view class="btn-selector" :class="{'btn-selector-active': order=='favorable_rate'}" @click="switchSelector" data-order="favorable_rate">
						<text>好评</text>
					</view>
				</view>
			</view>
			<view class="shop-list">
				<shop-item v-for="(item) in shopList" @order-event="orderShop" :key="item.id"  :info="item" :distance="item.distance | distanceFilter"></shop-item>
			</view>
			<view style="height:150rpx;text-align:center;">
				<!-- <uni-load-more></uni-load-more> -->
				<uni-load-more :status="loadMoreStatus" showIcon="true" :content-text="loadMoreText" ></uni-load-more>
			</view>
		</view>
		<!-- <navigator url="/pages/my/login/login"><button>去登录</button></navigator> -->
		<!-- <button @click="neetToLogin">需要登录权限的功能</button> -->
	</view>
</template>

<script>
	import ShopItem from '@/components/shop-item/shop-item'
	import {uniLoadMore} from '@dcloudio/uni-ui'
	export default {
		components: {
			ShopItem,
			uniLoadMore
		},
		data() {
			return {
				isActive:true,
				bannerList: [
					// {
					// 	id: 1,
					// 	bannerUrl: 'https://xjwatchimage.sxbang.net/banner/banner1.jpg'
				],
				order: 'distance',
				location: {
					latitude: 0,
					longitude: 0
				},
				shopListPager: {
					currentPage: 1,
					pageSize: 3,
					count: 0,
					totalPage: 1
				},
				shopList: [],
				loadMoreStatus: 'more',
				loadMoreText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				}
			}
		},
		created() {
			this.initLocation()
			this.loadBanner()
			this.loadShopList()
		},
		methods: {
			orderShop(shop){
				console.log('orderShop......', shop)
				// var that = this;
				// debugger
				this.$login.checkTokenValidity()
				.then(() => {
					// console.log('权限没问题，开始做')
					shop = JSON.stringify(shop)
					// debugger
					uni.navigateTo({
						url: `../order/order-appointment/order-appointment?shop=${shop}`
					})
				})
				.catch(() => {
					// console.log('没权限，做不了')
					this.$msg.showToast("请先登录")
				})
			},
			neetToLogin() {
				var that = this;
				this.$login.checkTokenValidity()
				.then(() => {
					console.log('权限没问题，开始做')
					that.$websocket.sendData({
						orderId:309,
						orderStep:1,
						notifyShopId:5,
						// notifyCustomerId: 59
					})
				})
				.catch(() => {
					console.log('没权限，做不了')
				})
			},
			loadBanner() {
				let that = this
				that.$api.get({
					url: '/open/banner/list'
				})
				.then((res) => {
					that.bannerList = res.data.data
					console.log('成功')
				})
				.catch((res) => {
					console.log('失败')
				})
			},
			initLocation() {
				// debugger
				var that = this;
				uni.authorize({
				    scope: 'scope.userLocation',
				    success() {
				        uni.getLocation({
				            type: 'gcj02',
							isHighAccuracy:true,
				            success: function (res) {
				        		that.location.latitude = res.latitude;
				        		that.location.longitude = res.longitude;
								// debugger
								console.log('my location:' + res.latitude + ',' + res.longitude)
								that.loadShopList(that.shopListPager.currentPage)
				            }
				        });
				    }
				})
			},
			loadShopList(currentPage) {
				console.log('go to loadShopList')
				let that = this;
				// debugger
				that.loadMoreStatus = 'loading';
				// setTimeout(function() {
					// console.log('延迟5秒来看一下加载中的效果')
					
				// }, 2000)
				that.$api.get({
					url: '/open/shop/list',
					data: {
						order: that.order,
						latitude: that.location.latitude,
						longitude: that.location.longitude,
						page: currentPage - 1,
						size: that.shopListPager.pageSize
					}
				})
				.then((resp) => {
					if (currentPage > 1) {
						that.shopList = that.shopList.concat(resp.data.data)
					} else {
						that.shopList = resp.data.data
					}
					that.shopListPager.count = resp.data.count
					that.shopListPager.totalPage = resp.data.totalPage
					if (that.shopListPager.currentPage < that.shopListPager.totalPage) {
						that.loadMoreStatus = 'more';
					} else {
						that.loadMoreStatus = 'noMore';
					}
				})
				.catch((err) => {
					that.loadMoreStatus = 'more';
				})
			},
			onLoadShopListByPage() {
				let that = this;
				let currentPage = that.shopListPager.currentPage;
				let totalPage = that.shopListPager.totalPage;
				if (currentPage < totalPage) {
					currentPage += 1;
					that.shopListPager.currentPage = currentPage;
					that.loadShopList(currentPage)
				}
			},
			switchSelector(event, key) {
				// debugger
				let order = event.currentTarget.dataset.order;
				this.order = order;
				this.shopListPager.currentPage = 1;
				this.shopListPager.count = 0;
				this.shopListPager.totalPage = 1;
				this.loadShopList(this.shopListPager.currentPage)
			}
		},
		filters: {
			distanceFilter(value) {
				return Number(value / 1000).toFixed(1)
			}
		}
	}
</script>

<style>

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.banner {
		height: 300rpx;
	}
	.banner-container {
		width: 100%;
		height: 300rpx;
	}
	.banner-image {
		width: 100%;
		height: 300rpx;
	}
	.shop-container {
		width: 100%;
		padding: 50rpx 0;
		display: flex;
		flex-direction: column;
	}
	.shop-header {
		padding: 0 50rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}
	.shop-title {
		font-size: 40rpx;
		color: #333333;
	}
	.shop-selector {
		width: 260rpx;
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: center;
	}
	.btn-selector {
		width: 100rpx;
		text-align: center;
	}
	.btn-selector-active {
		line-height: 50rpx;
		border-radius: 50rpx;
		background-color: #008d29;
		color: #ffffff;
	}
	.shop-list {
		padding: 0 50rpx;
		display: flex;
		flex-direction: column;
	}
</style>
