const LOGIN_USER_INFO = 'LOGIN_USER_INFO'

function updateUserInfo(refreshInfo) {
	let userInfo = getUserInfo()
	userInfo.accessToken = refreshInfo['access_token']
	userInfo.expiresIn = refreshInfo['expires_in']
	userInfo.lastLoginDatetime = new Date()
	uni.setStorageSync(LOGIN_USER_INFO, userInfo)
}

function setUserInfo(userInfo) {
	uni.setStorageSync(LOGIN_USER_INFO, userInfo)
}

function getUserInfo() {
	return uni.getStorageSync(LOGIN_USER_INFO)
}

function getAccessToken() {
	let userInfo = getUserInfo()
	if (userInfo) {
		return userInfo.accessToken
	}
 	return null
}

function removeUserInfo() {
	uni.removeStorageSync(LOGIN_USER_INFO)
}

function isMasterOrShopOwner() {
	let userInfo = getUserInfo()
	let roles = userInfo.roles;
	if (roles) {
		for (let i = 0; i < roles.length; i ++) {
			let role = roles[i];
			if (role.name == 'ROLE_MASTER' || role.name == 'ROLE_SHOPOWNER') {
				return true;
			}
		}
	}
	return false;
}

function updateLoginStatus(loginStatus) {
	let userInfo = getUserInfo()
	userInfo['loginStatus'] = loginStatus
	uni.setStorageSync(LOGIN_USER_INFO, userInfo)
}
module.exports = {
	updateUserInfo,
	setUserInfo,
	getUserInfo,
	getAccessToken,
	removeUserInfo,
	isMasterOrShopOwner,
	updateLoginStatus
}