<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<button open-type="getUserInfo" @getuserinfo="login">登录授权</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			login() {
				
				let that = this
				uni.login({
					success(res) {
						
						console.log('success: ' , res)
						that.$api.post({
							url: '/open/user/wx/login',
							data: {
								code: res.code,
								clientId: "wxmp",
								clientSecret: "123456"
							}
						})
						.then((loginResp) => {
							// if (loginResp.statusCode == 200 && loginResp.data.code == 200) {
								// uni.setStorageSync('LOGIN_USER_INFO', loginResp.data.data)
							that.$userInfo.setUserInfo(loginResp.data.data)
							that.$userInfo.updateLoginStatus(true)
							uni.navigateBack({})
							
							uni.showToast({
								icon: 'none',
								title: '您好，' + loginResp.data.data.username
							})
							that.$websocket.connet()
							console.log('login success: ' , loginResp)
							// } else {
							// 	console.log('login fail in success: ' , loginResp)
							// }
						})	// success
						.catch(function(loginResp) {
							console.log('login fail: ' , loginResp)
						}) // fail
						// /open/user/wx/login
						// uni.request({
						// 	url: '/open/user/wx/login',// http://localhost:90
						// 	method: 'POST',
						// 	header: {
						// 		'content-type': 'application/x-www-form-urlencoded'
						// 	},
						// 	data: {
						// 		code: res.code,
						// 		clientId: "wxmp",
						// 		clientSecret: "123456"
						// 	},
						// 	success(loginResp) {
						// 		if (loginResp.statusCode == 200 && loginResp.data.code == 200) {
						// 			// uni.setStorageSync('LOGIN_USER_INFO', loginResp.data.data)
						// 			that.$userInfo.setUserInfo(loginResp.data.data)
						// 			uni.navigateBack({})
									
						// 			uni.showToast({
						// 				icon: 'none',
						// 				title: '您好，' + loginResp.data.data.username
						// 			})
						// 			console.log('login success: ' + loginResp)
						// 		} else {
						// 			console.log('login fail in success: ' + loginResp)
						// 		}
								
						// 	},
						// 	fail(loginResp) {
								
						// 		console.log('login fail: ' ， loginResp)
						// 	}
						// })
					},
					fail(res) {
						console.log('fail: ' , res)
					}
				})
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
	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
