<template>
	<view class="res-tittle">
		<view class="shop-name">{{shop.name || shopname || shopInfo.name || '店铺名称'}}</view>
		<view class="shop-adress-container">
			<view class="watch-icons icon-location 	landmark"></view>
			<view class="shop-adress">{{shop.locationText || locationtext || shopInfo.locationText || '店铺位置'}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"orderDetailShop",
		props:['shop','shopid','shopname','locationtext'],
		data() {
			return {
				shopInfo:{}
			};
		},
		created() {
			console.log('created')
			if(this.shopid > 0) {
				console.log('created this.shopid')
				this.loadShopData();
			} 
		},
		watch:{
			shopid(val){
				console.log('watch this.shopid')
				if(val > 0) {
					console.log('created this.shopid')
					this.loadShopData(val);
				} 
			}
		},
		methods:{
			loadShopData(shopId) {
				console.log('loadShopData...')
				var that = this;
				// boolean
				shopId = shopId || that.shop.id;
				that.$api.get({
						url: `/open/shop/${shopId}`
					})
					.then((res) => {
						console.log('成功', res)
						that.shopInfo = res.data.data;
					})
					.catch((res) => {
						console.log('失败')
					})
			},
		}
	}
</script>

<style>
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
</style>
