<template>
	<view class="container">
		<view class="rate-container">
			<view class="score-container">
				<text style="font-size:80rpx;font-weight:300;color:#008d29;">{{star}}</text>
				<text style="font-size: 40rpx;color:#008d29;"> 分</text>
			</view>
			<text class="score-type">{{scoreType}}</text>
			<uni-rate class="rate" max="5" :value="star" @change="rateChange" size="30" active-color="#008d29"></uni-rate>
		</view>
		<view class="description-container">
			<textarea @input="commentHandle" class="rate-desc" placeholder="维修效果怎么样？师傅态度如何？写下你的感受吧!"></textarea>
			<sunui-upimg @change="uploadImage" :upload_auto="true" :upload_count="1"></sunui-upimg>
		</view>
		<view class="btn-container">
			<button class="btn-rate" @click="commitRate">提交评价</button>
		</view>
	</view>
</template>

<script>
	import sunuiUpimg from '@/components/sunui-upimg/sunui-upimg.vue'
	import {uniRate} from "@dcloudio/uni-ui"
	export default {
		components: {sunuiUpimg, uniRate},
		data() {
			return {
				repairItemPhoto: '',
				star: 1,
				scoreType: '非常不好',
				comment: '',
				shopId: '',
				orderId: '',
				scoreTypeList: ['非常不好','不好','一般','好','非常好']
			}
		},
		onLoad(options) {
			this.orderId = options.orderId
			this.shopId = options.shopId
		},
		methods: {
			uploadImage(e) {
				console.log(e)
				this.repairItemPhoto = e[0]
			},
			rateChange(e) {
				let score = e.value
				this.star = score
				this.scoreType = this.scoreTypeList[score - 1]
			},
			commentHandle(e) {
				this.comment = e.detail.value
				console.log('comment :  ---- ' + this.comment)
			},
			commitRate() {
				let that = this
				let orderId = that.orderId
				that.$login.checkTokenValidity().then((login) => {
					that.$api.apiPost({
						url: `/api/order/${orderId}/evaluation`,
						data: {
							star: that.star,
							comment: that.comment,
							shopId: that.shopId,
							repairItemPhoto: that.repairItemPhoto
						}
					})
					.then((res) => {
						uni.showToast({
							title: '感谢你的评价！',
							icon: 'none'
						})
						that.$websocket.sendData({
							orderId: that.orderId,
							orderStep: 12,
							notifyShopId:that.shopId,
							// notifyCustomerId: order.customerId
						})
						uni.navigateBack()
					})
					.catch((res) => {
						uni.showToast({
							title: '提交评价失败！',
							icon: 'none'
						})
					})
				})
			}
		}
	}
</script>

<style>
	.rate-container {
		padding: 40rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		background-color: #FFFFFF;
	}
	.description-container {
		height: 300rpx;
		padding: 40rpx;
		margin-top: 20rpx;
		background-color: #FFFFFF;
	}
	.score-type {
		margin: 20rpx 0;
		color: #999;
	}
	.rate {
		margin: 20rpx 0;
	}
	.rate-desc {
		width: 100%;
		height: 160rpx;
	}
	.btn-container {
		width: 100%;
		position: fixed;
		bottom: 0;
	}
	.btn-rate {
		background-color: #008d29;
		color: #FFFFFF;
		border-radius: 0;
	}
</style>
