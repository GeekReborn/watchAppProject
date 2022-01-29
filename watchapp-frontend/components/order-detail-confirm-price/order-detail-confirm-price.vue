<template>
	<view class="content">
		<text>请签名确认同意报价</text>
		<view class="imgs" v-if="img != ''">
			<image class="img" :src="img" mode="widthFix"></image>
		</view>
		<button type="primary" @tap="doss">点击签名</button>
		<catSignature canvasId="canvas1"  @close="close" @save="save" :visible="isShow" />
	</view>
</template>

<script>
	import catSignature from "@/components/cat-signature/cat-signature.vue"
	export default {
		components: {catSignature},
		data() {
			return {
				img:'',
				isShow: false
			}
		},
		methods: {
			doss(){
				this.isShow = true
			},
			close(){
				this.isShow = false
			},
			save(val){
				var that = this
				that.isShow = false
				that.img = val
				// 创建文件
				var file = new that.$av.File('signature', {
						blob: {
							uri: val	// path中为临时文件，转为字节文件才可以上传
						}
					})
				// 保存文件
				file.save().then(function(file) {
					console.log('文件保存完成,url: ' + file.attributes.url)
					that.img = file.attributes.url	// 上传成功后，返回真实URL，替换掉原来的临时URL
					that.$emit('signed')
				}, function(error) {
					console.log('文件保存失败!')
					uni.showToast({
						title: '文件上传失败!',
						icon: 'none'
					})
				})
			},
			getImg() {
				return this.img
			}
		}
	}
</script>

<style>
	.content {
		margin-top: 20rpx;
		padding: 20rpx 40rpx;
		background-color: #FFFFFF;
	}
	.imgs{
		width: 100%; 
		height: 360rpx;
		display: flex;
		margin: 0 auto;
		flex-wrap: wrap;
	}
	.imgs img{
		width: 100%; 
		height: 100%;
	}
</style>
