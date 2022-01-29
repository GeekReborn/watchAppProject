<template>
	<view class="content">
		<view class="info-panel">
			<view class="panel-title">店长</view>
			<view class="user-info-container">
				<view class="user-avatar">
					<image :src="shopOwner.avatarUrl"></image>
				</view>
				<view class="username-info">
					<view class="username">{{shopOwner.username}}</view>
					<view>{{shopOwner.phone}}</view>
				</view>
			</view>
		</view>
		<view class="info-panel">
			<view class="panel-title">所有维修师傅</view>
			<uni-swipe-action>
			    <uni-swipe-action-item v-for="(item, index) in shopMasters" :options="options" @click="deleteMaster($event, index)">
			        <view class="user-info-container">
			        	<view class="user-avatar">
			        		<image :src="item.avatarUrl"></image>
			        	</view>
			        	<view class="username-info">
			        		<view class="username">{{item.username}}</view>
			        		<view>{{item.phone}}</view>
			        	</view>
			        </view>
			    </uni-swipe-action-item>
			</uni-swipe-action>
		</view>
		<button class="add-btn" @click="addMaster">添加师傅</button>
	</view>
</template>

<script>
	import {uniSwipeAction, uniSwipeActionItem} from '@dcloudio/uni-ui'
	export default {
		components: {uniSwipeAction, uniSwipeActionItem},
		data() {
			return {
				shopId: 0,
				shopOwner: {
					avatarUrl: '',
					username: '',
					phone: ''
				},
				options: [{
					text: '删除',
					style: {
						backgroundColor: 'rgb(255,58,49)'
					}
				}],
				shopMasters: []
			}
		},
		onLoad() {
			let userInfo = this.$userInfo.getUserInfo()
			this.shopOwner.avatarUrl = userInfo.avatarUrl
			this.shopOwner.username = userInfo.username
			this.shopOwner.phone = userInfo.phone
			
			this.getShop()
		},
		onShow() {
			this.showList()
		},
		methods: {
			showList() {
				let that = this
				that.$api.apiGet({
					url: '/api/masterManage/list'
				})
				.then((res) => {
					that.shopMasters = res.data.data
				})
			},
			getShop() {
				let that = this
				that.$api.apiGet({
					url: '/api/masterManage/getShop'
				})
				.then((res) => {							
					that.shopId = res.data.data.shopId
				})
			},
			deleteMaster(e, index) {
				let that = this
				uni.showModal({
					title: '是否要删除该师傅？',
					success(confirm) {
						if (confirm.confirm) {
							let shopMaster = that.shopMasters[index]
							that.$api.apiPost({
								url: '/api/masterManage/del',
								data: {
									userId: shopMaster.userId,
									shopId: shopMaster.shopId
								}
							})
							.then((res) => {
								that.showList()
								uni.showToast({
									icon: 'none',
									title: '删除维修师傅成功'
								})
							})
						}
					}
				})
			},
			addMaster() {
				uni.navigateTo({
					url: '/pages/my/add-master/add-master?shopId=' + this.shopId
				})
			}
		}
	}
</script>

<style>
	.content {
		background-color: #f2f2f2;
	}
	.info-panel {
		margin-top: 20rpx;
		background-color: #ffffff;
	}
	.panel-title {
		padding-top: 20rpx;
		padding-left: 40rpx;
		color: #333333;
		font-size: 36rpx;
		font-weight: bold;
	}
	.user-info-container {
		height: 120rpx;
		padding: 40rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		background-color: #ffffff;
	}
	
	.user-avatar image {
		width: 120rpx;
		height: 120rpx;
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
		font-size: 36rpx;
		font-weight: bold;
	}
	.add-btn {
		width: 100%;
		position: fixed;
		bottom: 0;
		background-color: #009245;
		color: #FFFFFF;
		border-radius: 0;
	}
</style>
