import UserInfoUtils from '@/common/user-info-utils.js'
const REQUEST_METHOD = {
	GET: 'GET',
	POST: 'POST',
	PUT: 'PUT',
	DELETE: 'DELETE'
}

var defaultSetting = {
	baseUrl: '',
	url: '',
	data: {},
	method: REQUEST_METHOD.GET,
	header: {
		'content-type': 'application/x-www-form-urlencoded'
	},
	dataType: 'json',
	isLocalResult: true,		// RestResult, statusCode == 200 && data.code == 200
	isApiRequest: false			// true: ?access_token=xx, false: /
}

function request(options) {
	options = initOptions(options)
	return sendRequest(options)
}

function get(options) {
	options.method = REQUEST_METHOD.GET
	return request(options)
}

function post(options) {
	options.method = REQUEST_METHOD.POST
	return request(options)
}


function apiGet(options) {
	options.isApiRequest = true
	return get(options)
}

function apiPost(options) {
	options.isApiRequest = true
	return post(options)
}

function sendRequest(options) {
	return new Promise(async (resolve, reject) => {
		var [error, res] = await uni.request(options)
		if ((options.isLocalResult && checkLocalResponseSuccess(res))
			|| (!options.isLocalResult && checkResponseSuccess(res))) {
			console.log('request sucess')
			resolve(res)
		} else {
			console.log('request fail')
			reject(res)
		}
	})
}

function initOptions(options) {
	if (options) {
		options.baseUrl = options.baseUrl || defaultSetting.baseUrl
		// url: /login, baseUrl: null, defaultSetting.baseUrl = http://localhost:90
		// 自动拼接baseUrl, http://localhost:90/login
		options.url = (options.baseUrl ? options.baseUrl : '') 
					+ (options.url ? options.url : defaultSetting.url);
	
		options.method = options.method || defaultSetting.method;
		options.header = options.header || defaultSetting.header;
		options.dataType = options.dataType || defaultSetting.dataType;
		options.data = options.data || defaultSetting.data;
		options.isLocalResult = (typeof options.isLocalResult != 'undefined') ? options.isLocalResult : defaultSetting.isLocalResult;
		options.isApiRequest = (typeof options.isApiRequest != 'undefined') ? options.isApiRequest : defaultSetting.isApiRequest;
		// 自动携带access_token参数
		if (options.isApiRequest) {
			options.data['access_token'] = UserInfoUtils.getAccessToken()
		} else {
			delete options.data['access_token']
		}
		// 
		// if (!options.baseUrl) {
		// 	options.baseUrl = defaultSetting.baseUrl
		// }
	}
	return options
}

function checkLocalResponseSuccess(res) {
	// statusCode == 200 && data.code == 200
	return checkResponseSuccess(res) && res.data && res.data.code == 200
}

function checkResponseSuccess(res) {
	// statusCode == 200
	return res && res.statusCode == 200
}

module.exports = {
	defaultSetting,
	request,
	get,
	post,
	apiGet,
	apiPost,
	checkLocalResponseSuccess,
	checkResponseSuccess
}