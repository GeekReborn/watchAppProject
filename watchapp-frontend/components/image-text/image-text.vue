<template>
	<view>
		<view class="problem-container" 
		:style="'width:'+containerWidth+';padding-left:'+(containerPaddingLeft||containerPaddingX)+';padding-right:'+(containerPaddingRight||containerPaddingX)" 
		scroll-y="true">
			<view class="problem-title" :style="width!=690?'width: '+width+'rpx;padding-left: 30rpx;':''">{{Title}}</view>
			<view class="problem-desc theme-color" :style="width!=690?'width: '+width+'rpx;padding-left: 30rpx;':''">{{problemDescText}}</view>
		</view>
		<view class="uploader-list list-pd">
			<view class="uploader-list-cell cell-pd">
				<view class="uploader-container">
					<view class="uploader-body">
						<view class="uploader-files" :style="width!=690?'width: '+width+'rpx;padding-left: 30rpx;':''">
							<block v-if="images == ''">
								<block v-for="(images,index) in imageList" :key="index">
									<view class="uploader-file">
										<image class="uploader-img" :src="images" :data-src="images" @tap="previewImage"></image>
									</view>
								</block>
							</block>
							<block v-else>
								<view class="uploader-file">
									<image class="uploader-img" :src="images" :data-src="images" @tap="previewImage"></image>
								</view>
							</block>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'choose/previewImage',
				sourceTypeIndex: 2,
				sourceType: ['album', 'camera'],
				sizeType: ['original '], //默认全部原图，如果大于2M再进行压缩
				count: 1,
				uploadPaths: [],
				iamgesDomain: this.imagesDomain
			};
		},
		props: {
			containerWidth: {
				type: String,
				default: '90%'
			},
			containerPaddingX: {
				type: String,
				default: '40rpx'
			},
			containerPaddingLeft: {
				type: String,
				default: ''
			},
			containerPaddingRight: {
				type: String,
				default: ''
			},
			width: {
				type: Number,
				default: 690
			},
			Title: {
				type: String,
				default: ''
			},
			imageList: {
				type: Array,
				default: undefined
			},
			images: {
				type: String,
				default: ''
			},
			placeholder: {
				type: String,
				default: '请写下内容描述'
			},
			problemDescText: {
				type: String,
				default: ''
			},
			autoOpenLogin: {
				//在上传登录时遇到登录失效情况是否自动打开登录页面
				type: Boolean,
				default: false
			},
			uploadScene: {
				//上传场景 ：
				//RESERVATION_SCENCE：1，
				// ORDER_PROCESSING_SCENCE：2  
				// ORDER_FINISH_SCENCE：3  
				// EVALUATION_SCENCE：4 
				// DEFAULT_SECENCE：5
				type: Number,
				default: 5
			},
			compressImageSize: {
				//当图片大于该值时进行压缩，默认2MB  ，单位：MB
				type: Number,
				default: 2
			}
		},
		methods: {
			previewImage: function(e) {
				var current = e.target.dataset.src
				var temArray = []
				if (this.images == '') {
					for (var index in this.imageList) {
						temArray.push(this.imageList[index])
					}
				} else {
					temArray.push(this.images)
				}
				console.log(current)
				uni.previewImage({
					current: current,
					urls: temArray
				})

			}
		}
	}
</script>

<style>
	/*问题描述*/
	.problem-container {
		/* display: flex; */
		/* flex-direction: column; */
		/* flex-wrap: wrap; */
		/* justify-content: start; */
		/* align-items: center; */
		width: 90%;
		min-height: 170rpx;
		background-color: #fff;
		padding: 20rpx 40rpx 0 40rpx;
	}

	.problem-title {
		font-size: 32rpx;
	}

	.problem-desc {
		font-size: 32rpx;
		min-height: 110rpx;
		margin-top: 20rpx;
	}

	.placeholder-text {
		color: #bbb;
	}

	/*文件上传*/
	.cell-pd {
		padding: 20rpx 30rpx;
	}

	.uploader-list {
		background-color: #FFFFFF;
		position: relative;
		width: 100%;
		display: -webkit-box;
		display: -webkit-flex;
		display: -ms-flexbox;
		display: flex;
		-webkit-box-orient: vertical;
		-webkit-box-direction: normal;
		-webkit-flex-direction: column;
		-ms-flex-direction: column;
		flex-direction: column;
	}

	.uploader-list-cell {
		position: relative;
		display: -webkit-box;
		display: -webkit-flex;
		display: -ms-flexbox;
		display: flex;
		-webkit-box-orient: horizontal;
		-webkit-box-direction: normal;
		-webkit-flex-direction: row;
		-ms-flex-direction: row;
		flex-direction: row;
		-webkit-box-pack: justify;
		-webkit-justify-content: space-between;
		-ms-flex-pack: justify;
		justify-content: space-between;
		-webkit-box-align: center;
		-webkit-align-items: center;
		-ms-flex-align: center;
		align-items: center;
	}

	.uploader-container {
		flex: 1;
		flex-direction: column;
	}

	.uploader-info {
		color: #B2B2B2;
	}

	.uploader-files {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
	}

	.uploader-file {
		margin: 10rpx;
		width: 150rpx;
		height: 150rpx;
	}

	.uploader-img {
		display: block;
		width: 100%;
		height: 100%;
	}

	.uploader-input-box {
		position: relative;
		margin: 10rpx;
		width: 150rpx;
		height: 150rpx;
		border: 2rpx solid #D9D9D9;
	}

	.uploader-input-box:before,
	.uploader-input-box:after {
		content: " ";
		position: absolute;
		top: 50%;
		left: 50%;
		-webkit-transform: translate(-50%, -50%);
		transform: translate(-50%, -50%);
		background-color: #D9D9D9;
	}

	.uploader-input-box:before {
		width: 4rpx;
		height: 79rpx;
	}

	.uploader-input-box:after {
		width: 79rpx;
		height: 4rpx;
	}

	.uploader-input-box:active {
		border-color: #999999;
	}

	.uploader-input-box:active:before,
	.uploader-input-box:active:after {
		background-color: #999999;
	}

	.uploader-input {
		position: absolute;
		z-index: 1;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		opacity: 0;
	}

	.uploader-title {
		font-size: 30rpx;
	}
</style>
