<template>
	<view class="container">
		<view class="img-banner" :style="{backgroundImage: 'url('+shopInfo.shopImage+')'}"></view>
		<view class="shop-info">
			<view class="shop-name-info">
				<text class="shop-name">{{shopInfo.name}}</text>
				<text class="shop-volume">接单量 {{shopInfo.orderCount || 0}}</text>
			</view>
			<view class="shop-contact-info" @click="makePhoneCall" :data-phone="shopInfo.shopContact">
				<text class="info-label">电话</text>
				<text class="info-content">{{shopInfo.shopContact}}</text>
				<view class="watch-icons icon-contact"></view>
			</view>
			<view class="shop-contact-info" @click="goToMap(shopInfo.longitude, shopInfo.latitude)">
				<text class="info-label">地址</text>
				<text class="info-content">{{shopInfo.locationText}}</text>
				<view class="watch-icons icon-location"></view>
			</view>
		</view>
		<view class="shop-rating">
			<text class="rating-title">用户评价({{pager.count}})</text>
			<view class="shop-rating-tags">
				<uni-tag class="tag-diy" :text="`全部(${filterCount[0]||0})`" :inverted="currentFilter != 0" circle="true"
					type="success" @click="changeTag(0)"></uni-tag>
				<uni-tag class="tag-diy" :text="`好评(${filterCount[1]||0})`" :inverted="currentFilter != 1" circle="true"
					type="success" @click="changeTag(1)"></uni-tag>
				<uni-tag class="tag-diy" :text="`差评(${filterCount[2]||0})`" :inverted="currentFilter != 2" circle="true"
					type="success" @click="changeTag(2)"></uni-tag>
				<uni-tag class="tag-diy" :text="`有图(${filterCount[3]||0})`" :inverted="currentFilter != 3" circle="true"
					type="success" @click="changeTag(3)"></uni-tag>
				<uni-tag class="tag-diy" :text="`无图(${filterCount[4]||0})`" :inverted="currentFilter != 4" circle="true"
					type="success" @click="changeTag(4)"></uni-tag>
			</view>

			<view class="evaluation-list">
				<block v-for="(rateItem) in rateList" :key="rateItem.id">
					<rate-item :rate-item="rateItem"></rate-item>
				</block>
				<uni-load-more :status="loadMoreStatus" showIcon="true" :content-text="loadMoreText"></uni-load-more>
			</view>
		</view>
		<button type="primary" class="booking-btn" @click="orderClickHandle">预约</button>
	</view>

</template>

<script>
	import {
		uniTag
	} from "@dcloudio/uni-ui"
	import rateItem from "@/components/rate-item/rate-item.vue"
	import {
		uniLoadMore
	} from '@dcloudio/uni-ui'
	export default {
		components: {
			uniTag,
			rateItem,
			uniLoadMore
		},
		data() {
			return {
				shopInfo: {},
				currentFilter: 0,
				pager: {
					currentPage: 0,
					pageSize: 15,
					count: 0,
					totalPage: 1
				},
				filterCount: {},
				rateList: [],
				loadMoreStatus: 'more',
				loadMoreText: {
					contentdown: '上拉显示更多',
					contentrefresh: '正在加载...',
					contentnomore: '没有更多数据了'
				},
				rateData: [{
					"id": 27,
					"orderId": 284,
					"star": 2,
					"comment": "还OK啦",
					"repairItemPhoto": "http://lc-3uxiaoex.cn-n1.lcfile.com/508dcf39f0ed06fdfb9a/IMG_3152.jpeg",
					"createDatetime": "2019-09-04 17:20:043",
					"createBy": 38,
					"avatarUrl": "https://wx.qlogo.cn/mmopen/vi_32/1yzTKJKIfurhDI29RqibEicNOoH0WiaCuKb6jWppVu4uzWovO0d1ICAwuW4rB4zfUxVvGHfNuxXLHu44t3yBkgbicQ/132",
					"username": "Bee",
					"shopId": 7
				}, {
					"id": 26,
					"orderId": 283,
					"star": 1,
					"comment": "差",
					"repairItemPhoto": null,
					"createDatetime": "2019-09-04 16:53:058",
					"createBy": 37,
					"avatarUrl": "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOLwsnM32ibwcmgnRhXRzMVlUiaFYkEFQFbYLRic17QA9KMSJJicab42r3cmkEbs7nmm18Y14M8MDGqg/132",
					"username": "吴义增",
					"shopId": 7
				}, {
					"id": 25,
					"orderId": 280,
					"star": 5,
					"comment": "不错",
					"repairItemPhoto": "http://lc-3uxiaoex.cn-n1.lcfile.com/508dcf39f0ed06fdfb9a/IMG_3152.jpeg",
					"createDatetime": "2019-09-04 15:17:059",
					"createBy": 39,
					"avatarUrl": "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJOLwsnM32ibwcmgnRhXRzMVlUiaFYkEFQFbYLRic17QA9KMSJJicab42r3cmkEbs7nmm18Y14M8MDGqg/132",
					"username": "吴义增",
					"shopId": 7
				}, {
					"id": 24,
					"orderId": 274,
					"star": 3,
					"comment": "一般般",
					"repairItemPhoto": null,
					"createDatetime": "2019-09-04 10:44:035",
					"createBy": 37,
					"avatarUrl": "https://wx.qlogo.cn/mmopen/vi_32/1yzTKJKIfurhDI29RqibEicNOoH0WiaCuKb6jWppVu4uzWovO0d1ICAwuW4rB4zfUxVvGHfNuxXLHu44t3yBkgbicQ/132",
					"username": "Bee",
					"shopId": 7
				}, {
					"id": 23,
					"orderId": 277,
					"star": 5,
					"comment": "环境特别好，师傅人也很好，不错",
					"repairItemPhoto": "http://lc-3uxiaoex.cn-n1.lcfile.com/508dcf39f0ed06fdfb9a/IMG_3152.jpeg",
					"createDatetime": "2019-09-04 10:33:025",
					"createBy": 36,
					"avatarUrl": "https://wx.qlogo.cn/mmopen/vi_32/1yzTKJKIfurhDI29RqibEicNOoH0WiaCuKb6jWppVu4uzWovO0d1ICAwuW4rB4zfUxVvGHfNuxXLHu44t3yBkgbicQ/132",
					"username": "Bee",
					"shopId": 7
				}]
			}
		},
		onLoad(param) {
			let shopId = param.shopId
			this.loadShopDetail(shopId)
			this.loadEvaluation(shopId)
			this.initEvaluationFilterCount(shopId)
		},
		onReachBottom(event) {
			console.log('onReachBottom...')
			if (this.pager.currentPage >= (this.pager.totalPage - 1)) {
				console.log('no data')
				this.loadMoreStatus = 'noMore';
				return false;
			}
			if (this.pager.currentPage < this.pager.totalPage) {
				++this.pager.currentPage;
			}
			this.loadEvaluation()
		},
		methods: {
			loadShopDetail(shopId) {
				let that = this
				that.$api.get({
						url: '/open/shop/' + shopId
					})
					.then((res) => {
						that.shopInfo = res.data.data
					})
					.catch((res) => {
						this.$requestUtils.getErrorMessage('获取店铺信息失败。')
					})
			},
			goToMap(longitude, latitude) {
				console.log(longitude + '------' + latitude)
				uni.navigateTo({
					url: `../shop-location-map/shop-location-map?longitude=${longitude}&latitude=${latitude}`
				})
			},
			makePhoneCall(event) {
				console.log('makePhoneCall')
				var phoneNumber = event.currentTarget.dataset.phone
				uni.makePhoneCall({
					phoneNumber: phoneNumber, //仅为示例
					fail(res) {
						uni.showToast({
							icon: 'none',
							title: "呼叫失败，请稍后再试或联系管理员"
						})
					}
				});
			},
			initEvaluationFilterCount(shopId) {
				let that = this;
				that.$api.get({
						url: `/open/shop/${shopId}/evaluations/count`,
					})
					.then((res) => {
						console.log('res', res)
						debugger
						that.filterCount = res.data.data;
					})
					.catch((res) => {
						console.log('获取店铺信息失败。', res)
					})
			},

			orderClickHandle(event) {
				console.log('orderClickHandle...')
			},
			changeTag(currentTag) {
				if (this.currentFilter == currentTag) {
					return 0;
				}
				this.currentFilter = currentTag;
				this.pager.currentPage = 0;
				this.pager.totalPage = 1;
				this.loadEvaluation()
			},


			loadEvaluation(shopId) {
				shopId = shopId || this.shopInfo.id;
				let that = this;
				that.$api.get({
						url: `/open/shop/${shopId}/evaluations`,
						data: {
							filter: that.currentFilter,
							page: that.pager.currentPage,
							size: that.pager.pageSize,
						}
					})
					.then((res) => {
						debugger
						if (that.pager.currentPage == 0) {
							this.rateList = res.data.data;
							// that.rateList = res.data.data;
						} else {
							that.rateList = that.rateList.concat(res.data.data);
						}
						that.pager.count = res.data.count;
						if (res.data.count && res.data.count > 0) {
							that.pager.totalPage = Math.ceil(res.data.count / that.pager.pageSize)

						} else {
							that.pager.totalPage = 1;
						}
						if (that.pager.totalPage == 1) {
							that.loadMoreStatus = 'noMore';
						}
					})
					.catch((res) => {
						console.log('获取店铺信息失败。', res)
						this.pager.count = 0;
						this.pager.totalPage = 1;
					})
			}

		}
	}
</script>

<style>
	.watch-icons {
		color: #009245;
	}

	.container {
		width: 100%;
		display: flex;
		flex-direction: column;
		background-color: #eee;
	}

	.img-banner {
		width: 100%;
		height: 300rpx;
		background-image: url('http://lc-3uxiAoex.cn-n1.lcfile.com/1dbacee741ba8eafff1a/banner-14.jpg');
		background-size: cover;
		background-position: center;
	}

	.shop-info {
		padding: 0 60rpx;
		margin-top: 20rpx;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		background-color: #ffffff;
	}

	.shop-name-info {
		width: 100%;
		height: 90rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}

	.shop-name {
		font-size: 32rpx;
		font-weight: bold;
		color: #333333;
	}

	.shop-volume {
		font-size: 28rpx;
		color: #999999;
	}

	.shop-contact-info {
		width: 100%;
		height: 90rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		border-top: 1px solid #eeeeee;
	}

	.info-label {
		width: 80rpx;
		color: #008d29;
		font-size: 32rpx;
	}

	.info-content {
		font-size: 28rpx;
		flex: 1;
	}

	.booking-btn {
		position: fixed;
		bottom: 0;
		width: 100%;
		height: 100rpx;
		border-radius: 0;
		border: 0;
	}

	button[type=primary] {
		color: #fff;
		background-color: #008d29;
	}

	.shop-rating {
		padding: 20rpx 60rpx;
		margin-top: 20rpx;
		display: flex;
		flex-direction: column;
		background-color: #ffffff;
		align-items: flex-start;
	}

	.rating-title {
		font-size: 32rpx;
		line-height: 2em;
		color: #008D29;
	}

	.shop-rating-tags {
		margin-top: 20rpx;
		display: flex;
		flex-direction: row;
		align-items: flex-start;
		flex-wrap: wrap;
	}

	.tag-diy {
		padding-right: 20rpx;
		padding-bottom: 20rpx;
	}

	.evaluation-list {
		width: 100%;
		padding-bottom: 120rpx;
	}
</style>
