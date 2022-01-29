<template>
	<view class="shop-item" @click="openShopDetail(info.id)">
		<view class="shop-icon" :style="'background-image: url(' + info.shopImage + ');'"></view>
		<view class="shop-info">
		   <view class="shop-name">{{info.name}}</view>
		   <view class="shop-selector-info">
			   <text>好评率 {{info.favorableRate+"%  "}}</text><text> 距离 {{distance}} km</text>
		   </view>
		</view>
		<view class="btn-book" @click.stop="orderEventHandle(info.id)" >
		   <text>预约</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'shopItem',
		props: ['info', 'distance'],
		methods: {
			openShopDetail(shopId) {
				console.log('openShopDetail',shopId)
				uni.navigateTo({
					url: `/pages/index/shop-detail/shop-detail?shopId=${shopId}`,
				})
			},
			orderEventHandle (shopId){
				console.log('orderEventHandle shopId',shopId)
				this.$emit('order-event',this.info)
			}
		}
	}
</script>

<style>
	.shop-item {
		height: 160rpx;
		padding: 20rpx 20rpx 20rpx 0rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		border-radius: 10rpx;
		background-color: #ffffff;
		border-bottom: 1px solid #f2f2f2;
	}
	.shop-icon {
		width: 120rpx;
		height: 120rpx;
		background-size: auto 120rpx;
		background-position: center;
	}
	.shop-info {
		padding: 0 20rpx;
		display: flex;
		flex-direction: column;
		flex: 1;
		justify-content: center;
		align-items: flex-start;
	}
	.shop-name {
		font-size: 36rpx;
		color: #666666;
	}
	.shop-selector-info {
		font-size: 28rpx;
		color: #008d29;
	}
	.btn-book {
		text-align: center;
		width: 100rpx;
		line-height: 50rpx;
		border-radius: 50rpx;
		border: 1px solid #008d29;
		color: #008d29;
	}
</style>
