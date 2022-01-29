function showMessage(msg) {
	return new Promise((resolve, reject) => {
		uni.showToast({
			title: msg,
			icon: 'none',
			duration: 2000,
			success: resolve(),
			fail: reject()
		})
	})
}

function showLoading(msg) {
	return new Promise((resolve, reject) => {
		uni.showLoading({
			title: msg,
			mask: true,
			success: resolve(),
			fail: reject()
		})
	})
}
function hideLoading() {
	uni.hideLoading()
}

module.exports = {
	showMessage,
	showLoading,
	hideLoading
}
