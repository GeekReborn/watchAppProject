<template>
	<view class="custom-picker">
		<view :class="{'custom-picker-mask':pickerView}" v-on:click.stop="maskClick" catchtouchmove="true"></view>
		<view class="custom-picker-content" :class="{'custom-picker-view-show':pickerView}" >
			<view class="custom-picker-cc" catchtouchmove>
				<view class="custom-picker-cancel" v-on:click.stop="cancelHandle">取消</view>
				<view class="custom-picker-confirm" v-if="isClick" :style="'color:'+themeColor+';'" v-on:click.stop="confirmHandle">确定</view>
				<view class="custom-picker-confirm" v-if="!isClick" style="color:#888;">确定</view>
			</view>
			<!-- 单列 -->
			<picker-view v-if="mode==='selector' && pickerDatas.length > 0" :value="defaultValue" @pickstart="pickerStarHandle" @pickend="pickerEndHandle" v-on:change.stop="pickerChangeHandle" indicator-class="custom-picker-indicator" :indicator-style="'border-top: 2px solid '+themeColor+';border-bottom: 2px solid '+themeColor+';'" class="custom-picker-view">
			    <block>
			        <picker-view-column>
			            <view class="picker-item" v-for="(item,index) in pickerDatas" :key="item.dictKey">{{item.dictValue}}</view>
			        </picker-view-column>
			    </block>
			</picker-view>
			<!-- 时间 -->
			<picker-view v-if="mode==='date' && pickerDatas != undefined" :value="pickerDatas.defaultSelect"  v-on:change.stop="datePickerChangeHandle" @pickstart="pickerStarHandle" @pickend="pickerEndHandle" indicator-class="custom-picker-indicator" :indicator-style="'border-top: 2px solid '+themeColor+';border-bottom: 2px solid '+themeColor+';'" class="custom-picker-view">
			    <block>
			        <picker-view-column>
			            <view class="picker-item" v-for="(year,index) in pickerDatas.years" :key="index">{{pickerDatas.years[index]}} 年</view>
			        </picker-view-column>
					<picker-view-column v-if="datetype === 'month' || datetype === 'day'">
						<view class="picker-item" v-for="(month,index) in pickerDatas.months" :key="index">{{pickerDatas.months[index]}} 月</view>
					</picker-view-column>
					<picker-view-column v-if="datetype === 'day'">
						<view class="picker-item" v-for="(day,index) in pickerDatas.days" :key="index">{{pickerDatas.days[index]}} 日</view>
					</picker-view-column>
			    </block>
			</picker-view>
		</view>
	</view>
</template>

<script>
	export default {
	    data() {
			return {
				pickerView:false,
				selectValueObject:undefined,
				pickerDatas:undefined,
				oldDates: undefined,
				newDates: undefined,
				currentYear: 1970,
				currentMonth: 1,
				currentDay:1,
				isClick:false
			}
		},
		props:{
			mode:{
				type:String,
				default:'selector'
			},
			themeColor:{
				type:String,
				default:''
			},
			defaultValue:{
				type:Array,
				default:()=>{
					return []
				}
			},
			datas:{
				type:Array,
				default:()=>{
					return []
				}
			},
			startDate:{
				type:String,
				default:'1970-01-01'
			},
			datetype:{
				type:String,
				default:'day'
			}
			
		},
		methods:{
			pickerChangeHandle:function(e){
				this.selectValueObject = e.mp.detail.value[0]
				this.$emit('onChange', e.mp.detail.value[0]);
			},
			pickerStarHandle:function(e){
				this.isClick = false;
			},
			pickerEndHandle:function(e){
				this.isClick = true;
			},
			datePickerChangeHandle:function(e){
				var datePickerValue = e.mp.detail.value
				this.selectValueObject = datePickerValue
				if(datePickerValue[0] != (this.currentYear - new Date(this.startDate).getFullYear())){
					this.pickerDatas.months = this.oldDates.months;
				}else{
					this.pickerDatas.months = this.newDates.months;
				}
				if(datePickerValue[0] == (this.currentYear - new Date(this.startDate).getFullYear()) && datePickerValue[1] == this.currentMonth - 1){
					this.pickerDatas.days = this.newDates.days;
				}else{
					var pickerDatas = this.pickerDatas
					var DayOfMonth = this.getDayOfMonth(pickerDatas.years[datePickerValue[0]],pickerDatas.months[datePickerValue[1]])
					var days = []
					for(var _day = 1;_day <= DayOfMonth;){
						days.push(_day++)
					}
					this.pickerDatas.days = days
				}
			},
			cancelHandle:function(){
				this.$emit("onCancel")
			},
			maskClick:function(){
				this.$emit("onCancel")
			},
			confirmHandle:function(event){
				this.hide()
				if(this.mode == 'selector') {
					var res ={
						index:this.selectValueObject,
						value:this.pickerDatas[this.selectValueObject]
					}
					this.$emit("onConfirm",res)
				}else if(this.mode == 'date'){
					if(this.datetype == 'day'){
						var year = this.pickerDatas.years[this.selectValueObject[0]];
						var month = this.pickerDatas.months[this.selectValueObject[1]];
						var day = this.pickerDatas.days[this.selectValueObject[2]] ;
						if(month == undefined){
							month =  this.pickerDatas.months[this.pickerDatas.months.length-1]
						}
						if(day == undefined){
							day = this.pickerDatas.days[this.pickerDatas.days.length-1]
						}
						var res ={
							dateText: year + '年' + month + '月' + day + '日',
							date: year+ '-' + month + '-' + day ,
							value:this.selectValueObject
						}
						this.$emit("onConfirm",res)
					}else if(this.datetype == 'month'){
						var year = this.pickerDatas.years[this.selectValueObject[0]];
						var month = this.pickerDatas.months[this.selectValueObject[1]];
						if(month == undefined){
							month =  this.pickerDatas.months[this.pickerDatas.months.length-1]
						}
						var res ={
							dateText: year + '年' + month + '月',
							date: year+ '-' + month,
							value:this.selectValueObject
						}
						this.$emit("onConfirm",res)
					}
					
				}
				
			},
			init:function(e){
				if(this.mode == 'selector'){
					this.pickerDatas = this.datas
					this.selectValueObject = e
				}else if(this.mode == 'date'){
					if(this.datas.length > 0){
						this.pickerDatas = datas
					}else if(this.datas.length <= 0){
						this.pickerDatas = this.getDefaultDateData()
					}
					
				}
			},
			show:function(e){
				this.pickerView = true
				this.isClick = false;
				this.init(e)
			},
			hide:function(){
				this.pickerView = false
				this.isClick = false;
			},
			getDefaultDateData:function(startYear){
				var year = [],month=[1,2,3,4,5,6,7,8,9,10,11,12],day = [],endMonth=[],endDay=[]
				var currentDate = new Date()
				var currentYear = currentDate.getFullYear()
				var currentMonth = currentDate.getMonth()+1
				var currentDay = currentDate.getDate()
				this.currentYear = currentYear
				this.currentMonth = currentMonth
				this.currentDay = currentDay
				
				var startYear = this.getStartYear()
				var DayOfMonth = 31
				for(;startYear <= currentYear;){
					year.push(startYear++)
				}
				
				DayOfMonth = this.getDayOfMonth(year[(year.length -1)],currentMonth)
				for(var _day = 1;_day <= DayOfMonth;){
					day.push(_day++)
				}
				
				
				var oldDateData ={
					years:year,
					months:month,
					days:day
				}
				this.oldDates = oldDateData
				
				var newMonth = 1
				while(newMonth<=currentMonth){
					endMonth.push(newMonth++)
				}
				var newDay = 1
				while(newDay<=currentDay){
					endDay.push(newDay++)
				}
				var newDateData = {
					years:year,
					months:endMonth,
					days:endDay
				}
				this.newDates = newDateData
				var defaultDateData ={
					years:year,
					months:endMonth,
					days:endDay,
					defaultSelect:[(year.length -1),(currentMonth - 1),currentDay - 1]
				}
				
				if(this.selectValueObject == undefined){
					this.selectValueObject = defaultDateData.defaultSelect
				}else{
					var thatYear = year;
					var thatMonth = [];
					var thatDay = [];
					if(this.selectValueObject[0] == year.length -1){
						thatMonth = endMonth;
					}else{
						thatMonth = month;
					}
					if(this.selectValueObject[0] == year.length -1 && this.selectValueObject[1] == endMonth.length -1){
						thatDay = endDay;
					}else{
						DayOfMonth = this.getDayOfMonth(year[this.selectValueObject[0]],this.selectValueObject[1]+1)
						for(var _day = 1;_day <= DayOfMonth;){
							thatDay.push(_day++)
						}
					}
					defaultDateData ={
						years:thatYear,
						months:thatMonth,
						days:thatDay,
						defaultSelect:this.selectValueObject
					}
				}
				
				return defaultDateData;
			},
			getStartYear:function(){
				var startDate = new Date(this.startDate)
				var startYear = startDate.getFullYear()
				
				return startYear
			},
			isLeapYear:function(year){
				if (year % 100 == 0 && year % 400 == 0){
					return true;
				}else if (year % 100 != 0 && year % 4==0){
					return true;
				}
				return false;
			},
			getDayOfMonth:function(year,month){
				switch (month){
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
						return 31
					case 4:
					case 6:
					case 9:
					case 11:
						return 30
					case 2:
						if (this.isLeapYear(year)){
							return 29
						}else{
							return 28
						}
				}
			}
		}
	}
</script>

<style>
	.custom-picker-mask {
	    position: fixed;
	    z-index: 1000;
	    top: 0;
	    right: 0;
	    left: 0;
	    bottom: 0;
	    background: rgba(0, 0, 0, 0.6);
	}
	.custom-picker-content{
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		transition: all 0.3s ease;
		transform: translateY(100%);
		z-index: 3000;
	}
	.custom-picker-cc{
		/* display: flex; */
		padding: 18rpx 30rpx;
		background-color: #fff;
		position: relative;
		/* text-align: center; */
		font-size: 32rpx;
	}
	.custom-picker-cancel,.custom-picker-confirm{
		display: inline-block;
		font-size:32rpx;
		padding:10rpx 20rpx; 
		position:relative
	}
	.custom-picker-cancel{
		text-align: left;
		color: #888;
	}
	.custom-picker-confirm{
		text-align: right;
		float:right
	}
	.custom-picker-view-show {
	    transform: translateY(0); 
	}
	.custom-picker-view {
	    position: relative;
	    bottom: 0;
	    left: 0;
	    width: 100%;
	    height: 476rpx;
	    background-color: rgba(255, 255, 255, 1);
	}
	.picker-item {
	    text-align: center;
	    line-height: 80rpx;
	    font-size: 32rpx;
	}
	
	.custom-picker-indicator{
		height:80rpx;
		width: 200rpx;
		margin-left:calc(50% - 100rpx);
	}
</style>
