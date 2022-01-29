<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			this.$dict.initDictData();
			
		},
		onShow: function() {
			console.log('App Show')
			this.$login.checkTokenValidity().then((res)=>{
				this.$websocket.connet();
			})
			var that = this;
			this.$websocket.addHandle('notice', function(res){
				console.log('..res..',res)
				uni.showToast({
					icon:'none',
					title: '您有订单发生变化'
				})
			})
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import url("static/fonts/iconfont.css");
	@import url("static/iconfont/iconfont.wxss");
	.text-center{
		text-align: center;
	}
	.theme-color{
		color: #009245;
	}
	/* page {
		background-color: #eee;
	} */
	.container {
		background-color: #eee;
	}
	/* form */
	.form-group {
		/* display: flex; */
		/* flex-wrap: wrap; */
		/* justify-content: center; */
		/* font-size: 32rpx; */
		margin-top: 20rpx;
		background-color: #FFFFFF;
	}
	.form-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		align-items: center;
		width: 80%;
		height: 80rpx;
		max-height: auto;
		padding: 20rpx;
		padding-left: 40rpx;
		padding-right: 40rpx;
	}
	.form-container.full-width {
		width: 100%;
	}
	.form-container.no-height {
		height: 1rpx;
		padding-top: 0;
		padding-bottom: 0;
		margin-top: 0;
		margin-bottom: 0;
	}
	.form-label {
		color: #333;
	}
	
	.form-content {
		color: #009245;
	}
	.form-required {
		color: #ff0000;
		font-size: 32rpx;
		display: inline-block;
	}
	.uni-input {
		width: 75%;
		margin-left: 10rpx;
	}
	.split-line {
		margin-top: 20rpx;
	}
	.border-top {
		border-top: #dddddd 1px solid;
	}
	.bottom-spilt {
		margin-top: 160rpx;
		min-height: 1rpx;
	}
	
	/* button */
	.btn {
		position: fixed;
		bottom: 0;
		width: 100%;
		height: 140rpx;
		line-height: 140rpx;
		border-radius: 0;
		border: 0;
		z-index: 999;
	}
	.btn.btn-half {
		width: 50%;
	}
	.btn.btn-right {
		left: 50%;
	}
	button[type=primary] {
		color: #fff;
		background-color: #009245;
	}
	button[type=default] {
		color: #666;
		background-color: #fff;
	}
	button::after {
		border: 0;
	}
</style>
