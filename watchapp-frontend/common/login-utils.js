import Api from '@/common/api.js'
import UserInfoUtils from '@/common/user-info-utils.js'
import WebSocketUtils from '@/common/websocket-utils.js'

const CLIENT_ID = 'wxmp'
const CLIENT_SECRET = '123456'

function checkTokenValidity() {
	var userInfo = UserInfoUtils.getUserInfo()
	return new Promise((resolve, reject) => {
		if (userInfo && userInfo.expiresIn && userInfo.lastLoginDatetime) {
			console.log('expiresIn check')
			let currentDatetime = new Date().getTime()
			let lastLoginDatetime = new Date(userInfo.lastLoginDatetime).getTime()
			
			if (currentDatetime - (userInfo.expiresIn * 1000) > lastLoginDatetime) {
				console.log('token expire')
				// openLoginPage()
				// reject(refreshAccessToken())
				refreshAccessToken()
				.then((res) => {
					UserInfoUtils.updateUserInfo(res.data)
					console.log('refreshAccessToken success')
					// setTimeout(function(){
						// WebSocketUtils.reconnet()
					// },1000)
					resolve()
				})
				.catch((res) => {
					console.log('refreshAccessToken fail')
					reject()
				})
			} else {
				resolve()
			}
			
		} else {
			openLoginPage()
			reject()
		}
	})
}

function refreshAccessToken() {
	let refreshToken = UserInfoUtils.getUserInfo().refreshToken
	// 字符ES6 撇号``, 引号''
	// /oauth/token？
	return Api.post({
		url: `/oauth/token?grant_type=refresh_token&refresh_token=${refreshToken}&client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}`,
		isLocalResult: false
	})
		
}

function openLoginPage() {
	uni.navigateTo({
		url: '/pages/my/login/login'
	})
}

var LoginUtils = {
	checkTokenValidity,
	openLoginPage
}

module.exports = LoginUtils