<template>
	<view class="my-content">
		<view class="user-info-container">
			<!-- 未登录时，显示登录 -->
			<view v-if="!userInfo.loginStatus" class="iconfont icon-ren user-avatar" @click="goToLogin"></view>
			<view v-if="!userInfo.loginStatus" class="username-info" @click="goToLogin">
				<view class="login">登录</view>
			</view>
			
			<!-- 登录时，显示用户信息 -->
			<view v-if="userInfo.loginStatus" class="user-avatar">
				<image :src="userInfo.avatarUrl"></image>
			</view>
			<view v-if="userInfo.loginStatus" class="username-info">
				<view class="username">{{userInfo.username}}</view>
				<view>{{phone}}</view>
			</view>
			
		</view>
		<view class="menu-list-container">
			<view v-if="isShopOwner" class="menu-list-item menu-border" @click="openMasterManage">
				<view class="iconfont icon-renshu" style="color:#009245;font-size:40rpx;width:40rpx;"></view>
				<view class="menu-title">店铺人员管理</view>
				<view class="iconfont icon-you menu-arrow"></view>
			</view>
			<view v-if="isShopOwner" class="menu-list-item menu-border" @click="openFinance">
				<view class="iconfont icon-zhangdan" style="color:#009245;font-size:40rpx;width:40rpx;"></view>
				<view class="menu-title">店铺账单</view>
				<view class="iconfont icon-you menu-arrow"></view>
			</view>
		</view>
		<button v-if="userInfo.loginStatus" @click="confirmLogout" style="margin-top: 20rpx;">退出登录</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isShopOwner: false,
				userInfo: {
					loginStatus: false,
				}
			}
		},
		computed: {
			phone() {
				let phone = this.userInfo.phone
				if(phone == null){
					return phone = '**********';
				}else{
					return phone.substring(0, 3) + '****' + phone.substring(7);
				}
			}
		},
		created() {
			this.updateUserInfo()
			
		},
		methods: {
			openFinance() {
				uni.navigateTo({
					url: '/pages/my/finance/finance'
				})
			},
			goToLogin() {
				this.$login.openLoginPage()
			},
			updateUserInfo() {
				let userInfo = this.$userInfo.getUserInfo()
				if (userInfo) {
					this.userInfo = userInfo
				} else {
					this.userInfo = {
						loginStatus: false,
						phone: '',
						username: ''
					}
				}
				// 判断是否为店长
				let roles = this.userInfo.roles;
				this.isShopOwner = false;
				if (roles) {
					for (let i = 0; i < roles.length; i ++) {
						let role = roles[i];
						if (role.name == 'ROLE_SHOPOWNER') {
							this.isShopOwner = true;
						}
					}
				}
			},
			openMasterManage() {
				uni.navigateTo({
					url: '/pages/my/master-manage/master-manage'
				})
			},
			confirmLogout() {
				let that = this
				uni.showModal({
					title: '退出登录',
					content: '是否确定要退出登录？',
					success(res) {
						if (res.confirm) {
							that.logout()
						}
					}
				})
			},
			logout() {
				let that = this
				let userInfo = uni.getStorageSync('LOGIN_USER_INFO')
				that.$api.apiGet({
					url: '/logout'
				})
				.then(() => {
					// if (res.statusCode == 200) {
						// uni.removeStorageSync('LOGIN_USER_INFO')
					that.$userInfo.removeUserInfo()
					that.$emit('logout')
					that.updateUserInfo()
					uni.showToast({
						title: '退出登录成功'
					})
					// }
				})
				.catch(() => {
					uni.showToast({
						title: '退出登录失败'
					})
				})
				
			}
		}
	}
</script>

<style>
	.my-content {
		background-color: #f2f2f2;
	}
	.user-info-container {
		height: 120rpx;
		padding: 40rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		background-color: #ffffff;
	}
	.user-avatar {
		height: 120rpx;
		font-size: 120rpx;
	}
	
	.user-avatar image {
		width: 120rpx;
		height: 120rpx;
		border-radius: 100%;
	}
	
	.username-info {
		padding-left: 40rpx;
		display: flex;
		flex-direction: column;
	}
	
	.username-info .username {
		font-size: 36rpx;
		font-weight: bold;
	}
	.username-info .login {
		font-size: 50rpx;
	}
	
	.menu-list-container {
		margin-top: 20rpx;
		background-color: #ffffff;
	}
	.menu-list-item {
		padding: 40rpx 0;
		margin: 0 40rpx;
		font-size: 36rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	.menu-border {
		border-bottom: 1px solid #f2f2f2;
	}
	.menu-title {
		padding-left: 20rpx;
		color: #333333;
		flex-grow: 1;
	}
	.menu-arrow {
		width: 40rpx;
	}
</style>
