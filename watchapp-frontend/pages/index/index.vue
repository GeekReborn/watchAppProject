<template>
	<view class="content">
		<home v-if="currentTabBarIndex == 0" ref="childHome"></home>
		<order v-if="currentTabBarIndex == 1"></order>
		<my v-if="currentTabBarIndex == 2" ref="my" @logout="setMaster"></my>
		<view class="tab-bar">
			<view class="tab-bar-content " :class="{'master-menu': isMaster}">
				<view class="tab-bar-item" v-for="(item, index) in tabBar.list" :key="index" :class="{'tab-active': currentTabBarIndex == index}"
				 @click="switchTab" :data-index="index">
					<image class="icon-tab-bar" :src="currentTabBarIndex === index ? item.selectedIconPath : item.iconPath"></image>
					<view>{{item.name}}
					<image v-if="index == 1 && showOrderDot" mode="scaleToFill" class="new-notice" src="/static/images/icons/dot.png"></image>
					</view>
				</view>
			</view>
			<view class="tab-bar-button" v-show="isMaster">
				<image class="tab-bar-btn-bg" src="/static/images/tabbar/nvg_bar_bg-10.png" mode="scaleToFill"></image>
				<image class="tab-bar-btn-scan" src="/static/images/tabbar/scan-11.svg" 
				@click.stop="scanClickHandle"></image>
			</view>
		</view>
	</view>
</template>

<script>
	import home from '@/pages/home/home'
	import order from '@/pages/order/order'
	import my from '@/pages/my/my'

	export default {
		components: {
			home,
			order,
			my
		},
		data() {
			return {
				title: 'Hello',
				isMaster: false,
				currentTabBarIndex: 0, // 当前页面
				tabBar: {
					"list": [{
							name: "首页",
							nameCode: 'home',
							pagePath: "/pages/index/index",
							iconPath: "/static/images/icons/home.svg",
							selectedIconPath: "/static/images/icons/home_active.svg"
						},
						{
							name: "订单",
							nameCode: 'order',
							pagePath: "/pages/order/order",
							iconPath: "/static/images/icons/order.svg",
							selectedIconPath: "/static/images/icons/order_active.svg"
						},
						{
							name: "我的",
							nameCode: 'my',
							pagePath: "/pages/my/my",
							iconPath: "/static/images/icons/mine.svg",
							selectedIconPath: "/static/images/icons/mine_active.svg"
						}
					]
				},
				showOrderDot: false
			}
		},
		onReachBottom(event) {
			let that = this;
			switch (that.currentTabBarIndex) {
				case 0:
					this.$refs.childHome.onLoadShopListByPage()
					break
				default:
					break;
			}
		},
		onShow() {
			this.setMaster()
			var that = this;
			this.$websocket.addHandle('index',that.orderSocketHandle)
			// debugger
			this.$refs.my.updateUserInfo()
		},
		methods: {
			setMaster() {
				this.isMaster = this.$userInfo.isMasterOrShopOwner()
			},
			// isMasterRole() {
			// 	let userInfo = uni.getStorageSync('LOGIN_USER_INFO')
			// 	let roles = userInfo.roles;
			// 	if (roles) {
			// 		for (let i = 0; i < roles.length; i ++) {
			// 			let role = roles[i];
			// 			if (role.name == 'ROLE_MASTER' || role.name == 'ROLE_SHOPOWNER') {
			// 				return true;
			// 			}
			// 		}

			// 	}
			// 	return false;
			// },
			orderSocketHandle (res) {
				console.log("orderSocketHandle...",res)
				this.showOrderDot = true;
				/* this.$api.apiGet({
					url: `/api/notice/query`,
					data: {
						shopId:shopId ? shopId:0,
						userId:userId ? userId:0
					}
				}) */
			},
			switchTab(event) {
				let data = event.currentTarget.dataset;
				this.currentTabBarIndex = Number(data.index)
				uni.setNavigationBarTitle({
					title: this.tabBar.list[this.currentTabBarIndex].name
				})
			},
			// 扫码事件
			scanClickHandle(e) {
				console.log('scanClickHandle...')
				let that = this;
				var isMaster = this.$userInfo.isMasterOrShopOwner();
				if (!isMaster) {
					return 0;
				}
				// 允许从相机和相册扫码
				uni.scanCode({
					success (res) {
						var code = res.result;
						that.$msg.showLoading("正在查询订单...")
						that.findOrderByCode(code);
					}
				});
			},
			// 扫码事件-获取相关订单
			findOrderByCode(code) {
				let that = this;
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiGet({
						url: `/api/order/code/${code}`,
					}).then((res) => {
						// that.$msg.showMessage("请求ok！", res)
						console.log('findOrderByCode请求ok！', res)
						that.$msg.hideLoading();
						let order = res.data.data;
						 if (1 == order.orderQrCodeScene && 2 == order.orderStep) {
							that.toOrderPepair(res.data.data); 
						 } else if (2 == order.orderQrCodeScene && 7 == order.orderStep) {
							 that.takeWatch(order);
						 }
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 扫码事件-确认取表逻辑
			takeWatch(order){
				console.log('takeWatch....', order)
				if (!order) {
					return 0;
				}
				// 
				let content = `订单编号：${order.orderNo}`;
				if (order.customerPhone) {
					content += `\n客户手机：${order.customerPhone}`;
				}
				if (order.watchBodyNo) {
					content += `\n表身号：${order.watchBodyNo}`;
				}
				var that = this;
				uni.showModal({
					title: '确认取表?',
					content,
					success(res) {
						if (!res.confirm) {
							return 0;
						}
						// 如果点击了 “确认”则发起取表请求
						that.takeWatchRequest(order);
					}
				})
			},
			// 扫码事件-确认取表请求
			takeWatchRequest (order) {
				console.log('takeWatchRequest...', order)
				var that = this;
				that.$msg.showLoading();
				that.$login.checkTokenValidity().then((res) => {
					that.$api.apiPost({
						url: `/api/order/${order.id}/takeWacth`,
					}).then((res) => {
						that.$msg.showMessage("取表成功！", res)
						console.log('takeWatchRequest请求ok！', res)
						that.$msg.hideLoading();
						// 取表成功，跳转到详情页
						that.toOrderDetail(order.id,order.orderNo,order.orderStep,order.shopId)
					}).catch((res) => {
						that.$msg.showMessage("请求失败！" + res.data.msg)
					})
				})
			},
			// 扫码事件-跳转接修页面
			toOrderPepair(order){
				order = JSON.stringify(order);
				uni.navigateTo({
					url: `/pages/order/order-repair/order-repair?order=${order}`
				})
			},
			toOrderDetail (orderId, orderNo, orderStep, shopId) {
				console.log('toOrderDetail...')
				let path = `/pages/order/order-info/order-info?`;
				path += `orderId=${orderId}&shopId=${shopId}&orderNo=${orderNo}&orderStep=${orderStep}`
				uni.redirectTo({
					url: path
				})
			}

		}
	}
</script>

<style>
	.content home,
	.content order,
	.content my {
		width: 100%;
		padding-bottom: 110rpx;
	}

	.tab-bar {
		display: flex;
		flex-direction: row;
		position: fixed;
		bottom: 0;
		height: 110rpx;
		width: 100%;
	}

	.tab-bar-content {
		display: flex;
		flex-direction: row;
		flex: 1;
		border-top: 1px solid #f2f2f2;
		background-color: #ffffff;
	}

	.tab-bar-item {
		display: flex;
		flex: 1;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.tab-bar-button {
		display: flex;
		flex-basis: 30%;
		width: 30%;
	}

	.icon-tab-bar {
		width: 40rpx;
		height: 40rpx;
	}

	.tab-bar-btn-bg {
		width: 100%;
		height: 100%;
	}

	.tab-bar-btn-scan {
		width: 120rpx;
		height: 120rpx;
		position: absolute;
		top: -80rpx;
		right: 7%;
		border-radius: 50%;
	}

	.tab-active {
		color: #009245;
	}
	.new-notice{
		position: absolute;
		top: 16rpx;
		right: unset !important;
		width: 20rpx !important;
		height: 20rpx !important;
	}
</style>
