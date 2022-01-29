<template>
	<view v-if="type == 'select'" class="picker-container" :style="'height:'+(height*2)+'rpx;width:'+width+'rpx;'">
		<view :class="bottomLine?'picker-box bottom-line':'picker-box '" :style="'height:'+((bottomLine)?(height*2 - 2):(height*2))+'rpx'">
			<view class="picker-title element-height" :style="'width:'+titleWidth+'rpx;'">
				{{title}} 
				<view v-if="required" class="required">*</view>
			</view> 
			<view class="picker-select-box full-width full-height"  @tap="onSelectHandle">
				<custom-picker @onConfirm="onConfirmHandle" @onCancel="onCancelHandle" @onChange="onChangeHandle" :themeColor="themeColor" :datas="list" ref="custompicker" >
					
				</custom-picker>
				<view class="full-height picker-selected-value " :style="'line-height:'+(height*2)+'rpx;color:'+themeColor+';text-align:'+textAlign+';'" :class="textAlign=='left'? 'text-align-left' : ''">{{list[defaultValue].dictValue}}</view>
				<!-- <picker class="full-width full-height" @change="bindPickerChangeHandle" :value="index" :range="list">

					<view class="full-height picker-selected-value theme-color" :style="'line-height:'+(height*2)+'rpx'">{{list[index]}}</view>
				</picker> -->
			</view>
			<view class="watch-icons icon-down element-height picker-icon" @tap="onSelectHandle" v-if="iconflag"></view>
		</view>
	</view>
	<view v-else-if="type == 'input'" class="picker-container" :style="'height:'+(height*2)+'rpx'">
		<view :class="bottomLine?'picker-box bottom-line':'picker-box '" :style="'height:'+((bottomLine)?(height*2 - 2):(height*2))+'rpx'">
			<view class="picker-title element-height" :style="'width:'+titleWidth+'rpx;'">
				{{title}}
				<view v-if="required" class="required">*</view>
			</view> 
			<view class="picker-select-box full-width full-height" >
				<input class="full-height picker-input " v-model="defaultText" name="defaultText" @input="inputHandle" :style="'color:'+themeColor+';text-align:'+textAlign+';'" :placeholder="placeholderText" placeholder-class="picker-input-placeholder" :disabled="disabled" />
			</view>
		</view> 
	</view>
	<view v-else-if="type == 'date'" class="picker-container" :style="'height:'+(height*2)+'rpx;width:'+width+'rpx;'">
		<view :class="bottomLine?'picker-box bottom-line':'picker-box '" :style="'height:'+((bottomLine)?(height*2 - 2):(height*2))+'rpx'">
			<view class="picker-title element-height" :style="'width:'+titleWidth+'rpx;'">
				{{title}}
				<view v-if="required" class="required">*</view>
			</view> 
			<view class="picker-select-box full-width full-height" @tap="onSelectHandle">
				<!-- <picker mode="date" class="full-width full-height" @change="bindPickerChangeHandle" :value="date" :start="startDate" :end="endDate" fields="day">				
					<view class="full-height picker-selected-value theme-color" :style="'line-height:'+(height*2)+'rpx;padding-right: 10rpx;'">{{date}}</view>
				</picker> -->
				<custom-picker mode="date" :datetype="datetype" @onConfirm="onConfirmHandle" @onCancel="onCancelHandle" @onChange="onChangeHandle" :themeColor="themeColor" ref="custompicker" >
					
				</custom-picker>
				<view class="full-height picker-selected-value theme-color" :style="'line-height:'+(height*2)+'rpx;padding-right: 10rpx;text-align:'+textAlign+';'">{{defaultDate == '' ?defaultdate:defaultDate}}</view>
			</view>
			<view class="watch-icons icon-down element-height picker-icon" @tap="onSelectHandle" v-if="iconflag"></view>
		</view> 
	</view>
	<view v-else-if="type == 'text'" class="picker-container" style="min-height: 100rpx;">
		<view :class="bottomLine?'picker-box-text bottom-line':'picker-box-text '" style="min-height: 100rpx;">
			<view class="picker-title element-height ">
				{{title}}
			</view> 
			<view class="picker-select-box-text full-height picker-input" >
				<view class="theme-color picker-text-value text-value" :style="'max-width:'+(width==670?'400':width)+'rpx;text-align:'+textAlign">{{defaulttext}}</view>
			</view>
		</view> 
	</view>
	<view v-else-if="type == 'startDate'" class="picker-container" :style="'height:'+(height*2)+'rpx;width:'+width+'rpx;'">
		<view :class="bottomLine?'picker-box bottom-line':'picker-box '" :style="'height:'+((bottomLine)?(height*2 - 2):(height*2))+'rpx'">
			<view class="picker-title element-height" :style="'width:'+titleWidth+'rpx;'">
				{{title}}
				<view v-if="required" class="required">*</view>
			</view> 
			<view class="picker-select-box full-width full-height" @tap="onSelectHandle">
				<!-- <picker mode="date" class="full-width full-height" @change="bindPickerChangeHandle" :value="date" :start="startDate" :end="endDate" fields="day">				
					<view class="full-height picker-selected-value theme-color" :style="'line-height:'+(height*2)+'rpx;padding-right: 10rpx;'">{{date}}</view>
				</picker> -->
				<custom-date mode="date" datetype="hour" @onConfirm="onConfirmHandle" @onCancel="onCancelHandle" @onChange="onChangeHandle" :themeColor="themeColor" ref="custompicker" >
					
				</custom-date>
				<view class="full-height picker-selected-value theme-color" :style="'line-height:'+(height*2)+'rpx;padding-right: 10rpx;text-align:'+textAlign+';'">{{defaultDate==''?defaultdate:defaultDate}}</view>
			</view>
			<view class="watch-icons icon-down element-height picker-icon" @tap="onSelectHandle" v-if="iconflag"></view>
		</view> 
	</view>
</template>

<script>
	/*
	*注意数据格式
	* [{
            "id":2,
            "dictKey":"insurance_select",
            "dictValue":"是否保价",
            "dictParent":"selector",
            "seq":null,
            "parentId":1,
            "children":[
                {
                    "id":3,
                    "dictKey":"0",
                    "dictValue":"是",
                    "dictParent":"insurance_select",
                    "seq":1,
                    "parentId":2,
                    "children":[

                    ]
                },
                {
                    "id":4,
                    "dictKey":"1",
                    "dictValue":"否",
                    "dictParent":"insurance_select",
                    "seq":2,
                    "parentId":2,
                    "children":[

                    ]
                }
            ]
        }]
	* 
	*/
	import customPicker from "@/components/custom-picker/custom-picker.vue"
	import customDate from "@/components/custom-date/custom-date.vue"
	export default {
		components:{
			customPicker,
			customDate
		},
		props:{
			width:{
				type:Number,
				default:670
			},
			height:{
				type:Number,
				default:40
			},
			inputWidth:{
				type:Number,
				default:40
			},
			titleWidth:{
				type:Number,
				default:220
			},
			title:{
				type:String,
				default:"当前选择"
			},
			list:{
				type:Array,
				default:undefined
			},
			bottomLine:{
				type:Boolean,
				default:false
			},
			iconflag:{
				type:Boolean,
				default:false
			},
			type:{
				type:String,
				default:'select'
			},
			placeholderText:{
				type:String,
				defalt:''
			},
			defaultvalue:{
				type:Number,
				defalt:0
			},
			defaulttext:{
				type:String,
				defalt:''
			},
			defaultdate:{
				type:String,
				defalt:''
			},
			disabled:{
				type:Boolean,
				default:false
			},
			themeColor:{
				type:String,
				default:'#009245'
			},
			textAlign:{
				type:String,
				default:'right'
			},
			datetype:{
				type:String,
				default:'day'
			},
			required:{
				type:Boolean,
				default:false
			}
			
		},
		data() {
			const currentDate = this.getDate({
				format: true
			})
			return {
				defaultDate:this.defaultdate,
				defaultValue:this.defaultvalue,
				defaultText:this.defaulttext,
				date: currentDate,
			};
		},
		
		computed: {
			startDate() {
				return this.getDate('start');
			},
			endDate() {
				return this.getDate('end');
			}
		},
		methods:{
			onSelectHandle:function(e){
				this.$refs.custompicker.show(this.defaultValue)
			},
			onConfirmHandle:function(e){
				if(this.type === "select"){
					this.defaultValue = e.index
					this.$emit("selectId",e.index);
				}else if(this.type === 'date'){
					this.defaultDate = e.date
				}else if(this.type === 'startDate'){
					this.defaultDate = e.date
				}
			},
			onCancelHandle:function(){
				this.$refs.custompicker.hide()
			},
			onChangeHandle:function(e){
				console.log(e)
			},
			bindPickerChangeHandle:function(event){
				this.index = event.detail.value
			},
			inputHandle:function(e){
				this.defaultText = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();

				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			verification: function(e){
				var val = e
				var ranges = [
					'\ud83c[\udf00-\udfff]', 
					'\ud83d[\udc00-\ude4f]', 
					'\ud83d[\ude80-\udeff]'
				];
				return val.replace(new RegExp(ranges.join('|'), 'g'), '');
			},
		},
		watch: {
		  defaultText(val) {
			this.$nextTick(() => {
			  this.defaultText = this.verification(val)
			})
		  }
		}
	}
</script>

<style>
.picker-selected-value,.picker-title,.picker-input-placeholder,.picker-text-value{
	font-size: 32rpx;
}
.element-height{
	align-items: center;
	display: flex;
}
.full-width{
	width: 100%;
}
.full-height{
	height: 100%;
}
.text-align-left{
	margin-left: -50rpx;
}
.picker-container {
	background-color: #FFFFFF;
	position: relative;
	width: 670rpx;
	flex-direction: column;
	padding: 0 40rpx;
}
.picker-title{
	height: 100%;
	text-align: left;
}
.picker-content{
	max-width: 500rpx;
	height: 100%;
	line-height: 80rpx;
	text-align: right;
}
.picker-input{
	text-align: right;
	padding-right: 16rpx;
}
.picker-input-placeholder{
	color:#ccc;
}
.picker-select-box{
	flex: 1;
}
.picker-select-box-text{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-end;
}
.picker-selected-value{
	text-align: right;
	padding-right: 40rpx;
}
.picker-box{
	display: flex;
}
.picker-box-text{
	display: flex;
	flex-wrap: nowrap;
	justify-content: space-between;
	align-items: center;
}
.bottom-line{
	border-bottom:2rpx solid #f6f6f6;
}
.picker-icon{
	width: 50rpx;
	font-size: 18rpx;
	text-align: center;
}
.required{
	color: #ff0000;
	font-size: 32rpx;
	display: inline-block;
}
</style>
