<template>
	<view class="form-group-container" :style="style">
		<view class="form-container title-container" :style="title_style">
			<view class="form-label">{{title}}
				<view v-if="required" class="form-required">*</view>
			</view>
		</view>
		<view class="form-container textarea-container" :style="textarea_style">
			<textarea v-model="customerProfile" name="problemDescText" class="problem-desc theme-color" @blur="blurEventHandle"
			 auto-height :maxlength="maxlength" :placeholder="placeholder" placeholder-class="placeholder-text" @focus="focusEventHandle"
			 @input="inputHandle"></textarea>
		</view>
		<view class="form-container upload-container" :style="upload_style">
			<sunui-upimg @change="uploadImage" :upload_auto="upload_auto" :upload_count="upload_count" ref="upload"></sunui-upimg>
		</view>
	</view>
</template>

<script>
	import sunuiUpimg from '@/components/sunui-upimg/sunui-upimg.vue'
	export default {
		components: {
			sunuiUpimg
		},
		props: {
			title: {
				type: String,
				default: ''
			},
			style: {
				type: String,
				default: ''
			},
			title_style: {
				type: String,
				default: ''
			},
			textarea_style: {
				type: String,
				default: ''
			},
			upload_style: {
				type: String,
				default: ''
			},
			placeholder: {
				type: String,
				default: '请输入问题描述'
			},
			maxlength: {
				type: Number,
				default: 255
			},
			upload_count: {
				type: Number,
				default: 1
			},
			upload_auto: {
				type: Boolean,
				default: true
			},
			upload_id: {
				type: String,
				default: "0"
			},
			required: {
				type: Boolean,
				default: false
			}
		},
		data() {
			return {
				customerProfile: ''
			};
		},
		methods: {
			focusEventHandle(e) {
				this.$emit('text-focus', e.value)
			},
			blurEventHandle(e) {
				this.$emit('text-blur', e.value)
			},
			inputHandle(e) {
				console.log('inputHandle...', e)
				this.$emit('text-input', e, this.upload_id)
			},
			uploadImage(e) {
				this.$emit('change', e, this.upload_id)
			}
		}
	}
</script>

<style>
	.form-group-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		/* font-size: 32rpx; */
		/* margin-top: 20rpx; */
		background-color: #FFFFFF;
	}

	.form-container {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		align-items: center;
		width: 80%;
		height: 50rpx;
		padding: 20rpx;
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

	.title-container,
	.upload-container,
	.textarea-container {
		width:90%;
		height:auto;
		padding-left:40rpx;
		padding-right:40rpx
	}
</style>
