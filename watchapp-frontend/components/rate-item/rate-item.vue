<template>
	<view class="user-rating-container">
		<view class="rating-info-container">
			<image class="user-avatar" :src="rateItem.avatarUrl"></image>
			<view class="rating-info">
				<view class="user-name">{{rateItem.username}}</view>
				<view class="user-rating">
					<text class="user-rating-time">{{rateItem.createDatetime}} </text>
					<uni-rate max="5" :value="rateItem.star" size="20" active-color="#008d29" disabled="true"></uni-rate>
				</view>
			</view>
		</view>
		<view class="rating-content">{{rateItem.comment}}</view>
		<image class="rating-photo" :src="rateItem.repairItemPhoto" v-if="rateItem.repairItemPhoto" 
				:data-photo="rateItem.repairItemPhoto" mode="aspectFill" @tap="showImage"></image>
	</view>
</template>

<script>
	import {uniRate} from "@dcloudio/uni-ui"
	export default {
		components: {
			uniRate
		},
		props: [
			'rateItem'
		],
		data() {
			return {}
		},
		methods: {
			showImage(event) {
				let photo = event.currentTarget.dataset.photo
				uni.previewImage({
					urls: [photo]
				})
			}
		}
	}
</script>

<style>
	.user-rating-container {
		padding-top: 20rpx;
		padding-bottom: 20rpx;
		width: 100%;
		display: flex;
		flex-direction: column;
		border-bottom: 1px dashed #eeeeee;
	}
	.rating-info-container {
		display: flex;
		flex-direction: row;
	}
	.user-avatar {
		width: 80rpx;
		height: 80rpx;
		border-radius: 80rpx;
	}
	.rating-info {
		padding-left: 20rpx;
	}
	.user-rating {
		display: flex;
		flex-direction: row;
		align-items: center;
		font-size: 24rpx;
		color: #999999;
	}
	.user-rating-time {
		padding-right: 30rpx;
	}
	.uni-rate {
		margin-left: 30rpx;
	}
	.rating-content {
		padding-top: 20rpx;
	}
	.rating-photo {
		padding-top: 20rpx;
		width: 100rpx;
		height: 100rpx;
	}
</style>
