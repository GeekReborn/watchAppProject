import Vue from 'vue'
import App from './App'
import UserInfo from '@/common/user-info-utils.js'
import Api from '@/common/api.js'
import LoginUtils from '@/common/login-utils.js'
import MessageUtils from '@/common/message-utils.js'
import DictUtils from '@/common/dict-utils.js'
import AV from '@/common/3d-parts/av-weapp-min.js'
import WebSocketUtils from '@/common/websocket-utils.js'

Api.defaultSetting.baseUrl = 'http://localhost:90'
WebSocketUtils.webSocketSetting.domain = 'ws://localhost:90/websocket/';
// WebSocketUtils.webSocketSetting.domain = 'wss://emoker.utools.club/websocket/';
// Api.defaultSetting.baseUrl = 'http://emoker.utools.club'
// ws ==> http
// wss ==> https

AV.init({
	// appId: 'O7uW3C5twXED3J6582L9hifo-gzGzoHsz',
	// appKey: 'eA5pOdESnG1PkA2qt6wKQkv9',
	// serverURLs: 'https://o7uw3c5t.lc-cn-n1-shared.com',
	appId: 'UubPz7b2SoPjk8X22Mtn9xud-gzGzoHsz',
	appKey: '1o9L1KE0BPEXsKdhLQXYTBvX',
	serverURLs: 'https://uubpz7b2.lc-cn-n1-shared.com'
})

Vue.config.productionTip = false
Vue.prototype.$userInfo = UserInfo
Vue.prototype.$api = Api
Vue.prototype.$login = LoginUtils
Vue.prototype.$msg = MessageUtils
Vue.prototype.$dict = DictUtils
Vue.prototype.$av = AV
Vue.prototype.$websocket = WebSocketUtils

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
