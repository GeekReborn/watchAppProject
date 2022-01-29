<template>
	<view>
		<view class="page-body">
			<view class="page-section page-section-gap">
				<map style="width: 100vw; height: 100vh;"
					:latitude="latitude" :longitude="longitude" 
					:markers="covers" :scale="scale" :polyline="polyline"
					show-location="true">
				</map>
				<button class="btn-control" @tap="addScale" style="bottom: 150rpx; right: 0;">+</button>
				<button class="btn-control" @tap="substractScale" style="bottom: 50rpx; right: 0;">-</button>
			</view>
		</view>
	</view>
</template>

<script>
	import MapDistanceUtils from '@/common/map-distance-utils.js'
	export default {
		data() {
			return {
				scale: 16,
				title: 'map',
				latitude: 22.274562,
				longitude: 113.566532,
				covers: [{
					latitude: 22.279407,
					longitude: 113.567648,
					iconPath: '../../../static/images/position.png',
					label: {
						content: '距离店铺：1.2km',
						color: '#ff0000',
						bgColor: '#ffffff',
						borderRadius: 5,
						padding: 10,
						textAlign: 'center'
					}
				}],
				polyline: [{
					points: [
						{
							latitude: 22.274562,
							longitude: 113.566532
						},
						{
							latitude: 22.279407,
							longitude: 113.567648
						}
					],
					color: '#ff0000',
					width: 2,
					arrowLine: true
				}]
			}
		},
		methods: {
			addScale() {
				if (this.scale < 18) {
					this.scale = this.scale + 1;
				}
			},
			substractScale() {
				if (this.scale > 5) {
					this.scale = this.scale - 1;
				}
			}
		},
		onLoad(param) {
			var that = this;
			that.covers[0].latitude = param.latitude;
			that.covers[0].longitude = param.longitude;
			that.polyline[0].points[1].latitude = param.latitude;
			that.polyline[0].points[1].longitude = param.longitude;
			uni.authorize({
			    scope: 'scope.userLocation',
			    success() {
			        uni.getLocation({
			            type: 'gcj02',
			            success: function (res) {
			        		that.latitude = res.latitude;
			        		that.longitude = res.longitude;
							that.polyline[0].points[0].latitude = res.latitude;
							that.polyline[0].points[0].longitude = res.longitude;
							var distance = MapDistanceUtils.getDistance(res.latitude, res.longitude, 
								that.polyline[0].points[1].latitude, that.polyline[0].points[1].longitude);
							that.covers[0].label.content = `距离店铺：${distance}km`;
			                console.log('当前位置的经度：' + res.longitude);
			                console.log('当前位置的纬度：' + res.latitude);
			            }
			        });
			    }
			})
			
		}
	}
</script>

<style>
	.btn-control {
		width: 80rpx;
		height: 100rpx;
		z-index: 10000;
		position: fixed; 
	}
</style>
