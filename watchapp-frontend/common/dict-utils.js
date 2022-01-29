import Api from '@/common/api.js'

const DICT_KEY = 'DICT'
 var dictData = {
	"dictKey": "dict",
	"dictValue": "字典",
	"childNode": {
		selector: {
			"dictKey": "selector",
			"dictValue": "选择器",
			"childNode": {}
		},
		order_status: {
			"dictKey": "order_status",
			"dictValue": "订单状态",
			"childNode": {}
		}
	}
};

// 初始化字典数据
function initDictData () {
	uni.setStorageSync(DICT_KEY,dictData);
	console.log('initDictData');
	initSelectorDictData();
	initOrderStatusDictData();
}

// 初始化选择器的字典数据 
function initSelectorDictData() {
	console.log('initSelectorDictData');
	Api.get({
		url: `/open/dict/selector`,
	}).then(res => {
		let dictData = uni.getStorageSync(DICT_KEY);
		dictData.childNode['selector'].childNode = res.data.data;
		uni.setStorageSync(DICT_KEY,dictData);
	});
}
// 初始化订单状态的字典数据 
function initOrderStatusDictData() {
	console.log('initOrderStatusDictData');
	Api.get({
		url: `/open/dict/orderStatus`,
	}).then(res => {
		let dictData = uni.getStorageSync(DICT_KEY);
		dictData.childNode['order_status'].childNode = res.data.data;
		uni.setStorageSync(DICT_KEY,dictData);
	});
}

/**
 * @description 按照顺序获取字典对应键的值 
 * @example 格式如: var dictValue = get('selector','movement_category_select','1')
 * @param {string} ...key  array
 * */
 // es6 可变参数列表
 // es6 slice(statr,[end])
 // 深拷贝 JSON.parse(JSON.stringify(dictData));
 // typeof keyItem === 'undefined'
 // function.apply(this,params); call
function get(...key) {
	var keyCache = key.slice(0);
	var shot = false;
	let dictData = uni.getStorageSync(DICT_KEY);
	var dictCache = JSON.parse(JSON.stringify(dictData));
	while (!shot) {
		var keyItem = keyCache.shift();
		if (typeof keyItem === 'undefined' || keyItem == null) {
			shot = true;
			break;
		}
		dictCache = dictCache.childNode[keyItem] || dictCache;
	}
	return dictCache;
}
/**
 * @description 按照顺序获取字典对应键的值 
 * @example 格式如: var dictValue = get('selector','movement_category_select','1')
 * @param {string} ...key  array
 * */
function getValue(...key) {
	return get.apply(this,key).dictValue;
}
/**
 * @description 按照顺序获取字典对应键的值 
 * @example 格式如: var dictValue = get('selector','movement_category_select','1')
 * @param {string} ...key  array
 * */
function getChild(...key) {
	return get.apply(this,key).childNode;
}
/**
 * @description 按照顺序获取字典中选择器对应键的值 
 * @example 格式如: var dictValue = getSelector('movement_category_select','1')
 * @param {string} ...key 
 * */
function getSelector(...key) {
	key = key || [];
	key.unshift('selector');
	return getValue.apply(this,key);
}
/**
 * @description 按照顺序获取字典中选择器对应键的值 
 * @example 格式如: var dictValue = getSelector('movement_category_select')
 * @param {string} ...key 
 * */
function getSelectors(...key) {
	console.log('getSelectors...')
	key = key || [];
	key.unshift('selector');
	var res = [];
	var content = getChild.apply(this,key);
	if (content && Object.keys(content).length > 0) {
		for(let key in content) {
		   res.push(content[key])
		}
	}
	return res;
}
/**
 * @description 调试打印用的测试方法
 * @param {string} ...key 
 * */
function log(...key) {
	console.log('log..', key)
}

module.exports = {
	initDictData,
	initSelectorDictData,
	get,
	getValue,
	getChild,
	getSelector,
	getSelectors,
	log
}
