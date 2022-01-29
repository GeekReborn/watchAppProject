<template>
	<view>
		<form @submit="addMaster">
			<view class="form-container">
				<text>师傅手机号：</text>
				<input name="phone" placeholder="请输入手机号" />
			</view>
			<button form-type="submit" class="add-btn">添加师傅</button>
		</form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				shopId: 0
			}
		},
		onLoad(options) {
			this.shopId = options.shopId
		},
		methods: {
			addMaster(e) {
				let that = this
				uni.showModal({
					title: '是否要添加该师傅？',
					success(confirm) {
						if (confirm.confirm) {
							let phone = e.detail.value.phone
							that.$api.apiPost({
								url: '/api/masterManage/add',
								data: {
									phone: phone,
									shopId: that.shopId
								}
							})
							.then((res) => {
								uni.navigateBack()
								uni.showToast({
									icon: 'none',
									title: '添加维修师傅成功'
								})
							})
						}
					}
				})
			}
		}
	}
</script>

<style>
	.form-container {
		padding: 40rpx;
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
