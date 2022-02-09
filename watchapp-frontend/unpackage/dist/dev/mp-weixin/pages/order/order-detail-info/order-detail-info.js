(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["pages/order/order-detail-info/order-detail-info"],{

/***/ 89:
/*!*************************************************************************************************************************************************!*\
  !*** F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/main.js?{"page":"pages%2Forder%2Forder-detail-info%2Forder-detail-info"} ***!
  \*************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(createPage) {__webpack_require__(/*! uni-pages */ 5);
var _vue = _interopRequireDefault(__webpack_require__(/*! vue */ 3));
var _orderDetailInfo = _interopRequireDefault(__webpack_require__(/*! ./pages/order/order-detail-info/order-detail-info.vue */ 90));function _interopRequireDefault(obj) {return obj && obj.__esModule ? obj : { default: obj };}wx.__webpack_require_UNI_MP_PLUGIN__ = __webpack_require__;
createPage(_orderDetailInfo.default);
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/index.js */ 1)["createPage"]))

/***/ }),

/***/ 90:
/*!****************************************************************************************************************************!*\
  !*** F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue ***!
  \****************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./order-detail-info.vue?vue&type=template&id=66164246& */ 91);
/* harmony import */ var _order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./order-detail-info.vue?vue&type=script&lang=js& */ 93);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));
/* harmony import */ var _order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./order-detail-info.vue?vue&type=style&index=0&lang=css& */ 95);
/* harmony import */ var _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js */ 11);

var renderjs





/* normalize component */

var component = Object(_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_3__["default"])(
  _order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__["default"],
  _order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["render"],
  _order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"],
  false,
  null,
  null,
  null,
  false,
  _order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["components"],
  renderjs
)

component.options.__file = "pages/order/order-detail-info/order-detail-info.vue"
/* harmony default export */ __webpack_exports__["default"] = (component.exports);

/***/ }),

/***/ 91:
/*!***********************************************************************************************************************************************************!*\
  !*** F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue?vue&type=template&id=66164246& ***!
  \***********************************************************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_16_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--16-0!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!./order-detail-info.vue?vue&type=template&id=66164246& */ 92);
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_16_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_16_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_16_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["recyclableRender"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "components", function() { return _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_16_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_template_id_66164246___WEBPACK_IMPORTED_MODULE_0__["components"]; });



/***/ }),

/***/ 92:
/*!***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--16-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue?vue&type=template&id=66164246& ***!
  \***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "render", function() { return render; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return staticRenderFns; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return recyclableRender; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "components", function() { return components; });
var components
try {
  components = {
    orderDetailShop: function() {
      return __webpack_require__.e(/*! import() | components/order-detail-shop/order-detail-shop */ "components/order-detail-shop/order-detail-shop").then(__webpack_require__.bind(null, /*! @/components/order-detail-shop/order-detail-shop.vue */ 208))
    },
    orderDetailForm: function() {
      return __webpack_require__.e(/*! import() | components/order-detail-form/order-detail-form */ "components/order-detail-form/order-detail-form").then(__webpack_require__.bind(null, /*! @/components/order-detail-form/order-detail-form.vue */ 215))
    },
    imageText: function() {
      return __webpack_require__.e(/*! import() | components/image-text/image-text */ "components/image-text/image-text").then(__webpack_require__.bind(null, /*! @/components/image-text/image-text.vue */ 201))
    },
    orderDetailConfirmPrice: function() {
      return __webpack_require__.e(/*! import() | components/order-detail-confirm-price/order-detail-confirm-price */ "components/order-detail-confirm-price/order-detail-confirm-price").then(__webpack_require__.bind(null, /*! @/components/order-detail-confirm-price/order-detail-confirm-price.vue */ 222))
    }
  }
} catch (e) {
  if (
    e.message.indexOf("Cannot find module") !== -1 &&
    e.message.indexOf(".vue") !== -1
  ) {
    console.error(e.message)
    console.error("1. 排查组件名称拼写是否正确")
    console.error(
      "2. 排查组件是否符合 easycom 规范，文档：https://uniapp.dcloud.net.cn/collocation/pages?id=easycom"
    )
    console.error(
      "3. 若组件不符合 easycom 规范，需手动引入，并在 components 中注册该组件"
    )
  } else {
    throw e
  }
}
var render = function() {
  var _vm = this
  var _h = _vm.$createElement
  var _c = _vm._self._c || _h
}
var recyclableRender = false
var staticRenderFns = []
render._withStripped = true



/***/ }),

/***/ 93:
/*!*****************************************************************************************************************************************************!*\
  !*** F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue?vue&type=script&lang=js& ***!
  \*****************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/babel-loader/lib!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--12-1!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!./order-detail-info.vue?vue&type=script&lang=js& */ 94);
/* harmony import */ var _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ 94:
/*!************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--12-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue?vue&type=script&lang=js& ***!
  \************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(uni) {Object.defineProperty(exports, "__esModule", { value: true });exports.default = void 0;var orderDetailShop = function orderDetailShop() {__webpack_require__.e(/*! require.ensure | components/order-detail-shop/order-detail-shop */ "components/order-detail-shop/order-detail-shop").then((function () {return resolve(__webpack_require__(/*! @/components/order-detail-shop/order-detail-shop.vue */ 208));}).bind(null, __webpack_require__)).catch(__webpack_require__.oe);};var orderDetailForm = function orderDetailForm() {__webpack_require__.e(/*! require.ensure | components/order-detail-form/order-detail-form */ "components/order-detail-form/order-detail-form").then((function () {return resolve(__webpack_require__(/*! @/components/order-detail-form/order-detail-form.vue */ 215));}).bind(null, __webpack_require__)).catch(__webpack_require__.oe);};var orderDetailConfirmPrice = function orderDetailConfirmPrice() {__webpack_require__.e(/*! require.ensure | components/order-detail-confirm-price/order-detail-confirm-price */ "components/order-detail-confirm-price/order-detail-confirm-price").then((function () {return resolve(__webpack_require__(/*! @/components/order-detail-confirm-price/order-detail-confirm-price.vue */ 222));}).bind(null, __webpack_require__)).catch(__webpack_require__.oe);};var imageText = function imageText() {__webpack_require__.e(/*! require.ensure | components/image-text/image-text */ "components/image-text/image-text").then((function () {return resolve(__webpack_require__(/*! @/components/image-text/image-text.vue */ 201));}).bind(null, __webpack_require__)).catch(__webpack_require__.oe);};var _default =



















































{
  components: { orderDetailShop: orderDetailShop, orderDetailForm: orderDetailForm, imageText: imageText, orderDetailConfirmPrice: orderDetailConfirmPrice },
  data: function data() {
    return {
      shop: {
        shopname: '',
        locationtext: '' },

      order: {
        customerSignature: '',
        agreeRepair: false },

      // isSigned: false,
      userInfo: {},
      isMasterOrShopOwner: false };

  },
  onLoad: function onLoad(param) {
    if (param.order) {
      this.order = JSON.parse(param.order);
      console.log('order', this.order);
    }
    if (param.shop) {
      this.shop = JSON.parse(param.shop);
      console.log('order', this.shop);
    }
    this.userInfo = this.$userInfo.getUserInfo();
    this.isMasterOrShopOwner = this.$userInfo.isMasterOrShopOwner();
    this.initPageData();
  },
  onPullDownRefresh: function onPullDownRefresh() {
    console.log('onPullDownRefresh...');
    var that = this;
    that.$login.checkTokenValidity().
    then(function () {
      that.loadOrderInfo();
    }).catch(function (res) {
      console.log('catch...', res);
    });
    setTimeout(function () {
      uni.stopPullDownRefresh();
    }, 1000);
  },
  methods: {
    initPageData: function initPageData() {
      this.setSelectorValue();
      // this.setImageInfo();
    },
    loadOrderInfo: function loadOrderInfo(orderId) {
      console.log('loadOrderInfo');
      var that = this;
      orderId = orderId || that.order.id;
      this.$api.apiGet({
        url: "/api/order/".concat(orderId) }).

      then(function (res) {
        // that.$msg.showMessage("请求ok！", res)
        console.log('请求ok！', res);
        that.order = res.data.data;
        that.setSelectorValue();
        // that.setImageInfo();
      }).
      catch(function (res) {
        that.$msg.showMessage(res.data.msg || "请求失败！");
        that.showCtrl.orderDetail = false;
      });
    },

    setSelectorValue: function setSelectorValue(order) {
      var that = this;
      // this.order.purchaseDate = this.order.purchaseDate.substr(0,10);
      console.log('setSelectorValue');
      var dicrval = that.$dict.getSelector('strap_material_select', 0);
      console.log('dicrval', dicrval);
      order = order || that.order;
      console.log('order...', JSON.parse(JSON.stringify(order)));
      // 品牌 watch_brand_id watchBrandId
      order.watchBrandId = that.$dict.getSelector('brand_select', order.watchBrandId);
      // 手表功能 watch_function watchFunction
      order.watchFunction = that.$dict.getSelector('function_select', order.watchFunction);
      // 机芯类别 watch_movement watchMovement
      order.watchMovement = that.$dict.getSelector('movement_category_select', order.watchMovement);
      // 表带材质 watchband_material watchbandMaterial
      order.watchbandMaterial = that.$dict.getSelector('strap_material_select', order.watchbandMaterial);
      // 表壳材质 case_material caseMaterial
      order.caseMaterial = that.$dict.getSelector('case_material_select', order.caseMaterial);
      // 表扣材质 clasp_material claspMaterial
      order.claspMaterial = that.$dict.getSelector('buckle_material_select', order.claspMaterial);
      // 外观品相 watch_exterior watchExterior
      order.watchExterior = that.$dict.getSelector('appearance_select', order.watchExterior);
      // 维修部件 repair_parts repairParts
      order.repairParts = that.$dict.getSelector('repair_parts_select', order.repairParts);
      // 是否保价 is_insure insure
      order.insure = that.$dict.getSelector('insurance_select', order.insure ? 1 : 0);
      // 是否维修过 is_repair repair
      order.repair = that.$dict.getSelector('isrepair_select', order.repair ? 1 : 0);
      console.log('order...', order);
      that.order = order;
    },
    /* setImageInfo(order){
       	order = order || this.order;
       	// customerPhoto 
       	var customerPhoto = [];
       	customerPhoto.push(orderInfo.beforeRepairPhoto);
       	orderInfo.beforeRepairPhoto = customerPhoto;
       	// imagePositive
       	var imagePositive = [];
       	imagePositive.push(orderInfo.positiveItemPhoto);
       	orderInfo.positiveItemPhoto = imagePositive;
       	// imageBack
       	var imageBack = [];
       	imageBack.push(orderInfo.backItemPhoto);
       	orderInfo.backItemPhoto = imageBack;
       	// imageSide
       	var imageSide = [];
       	imageSide.push(orderInfo.sideItemPhoto);
       	orderInfo.sideItemPhoto = imageSide;
       	// imageMalfunction
       	var imageMalfunction = [];
       	imageMalfunction.push(orderInfo.problemPhoto);
       	orderInfo.problemPhoto = imageMalfunction;
       	this.order = order;
       } */
    /* ,setImageArchiveInfo(orderArchive){
            	// imagePositiveItem
            	var imagePositiveItem = [];
            	imagePositiveItem.push(orderInfo.positiveItemPhoto);
            	orderInfo.positiveItemPhoto = imagePositiveItem;
            	// imageBackItem
            	var imageBackItem = [];
            	imageBackItem.push(orderInfo.backItemPhoto);
            	orderInfo.backItemPhoto = imageBackItem;
            	// imageSideItem
            	var imageSideItem = [];
            	imageSideItem.push(orderInfo.sideItemPhoto);
            	orderInfo.sideItemPhoto = imageSideItem;
            	// imageProblem
            	var imageProblem = [];
            	imageProblem.push(orderInfo.problemPhoto);
            	orderInfo.problemPhoto = imageProblem;
            	// imageItem
            	var imageItem = [];
            	imageItem.push(orderInfo.itemPhoto);
            	orderInfo.itemPhoto = imageItem;
            	// imageRepair
            	var imageRepair = [];
            	imageRepair.push(orderInfo.repairPhoto);
            	repairPhoto.beforeRepairPhoto = imageRepair;
            } */

    signStatusHandle: function signStatusHandle() {
      // this.isSigned = true
      this.order.customerSignature = this.$refs.orderConfirm.getImg();
      this.order.agreeRepair = true;
    },
    confirmToAgreePricing: function confirmToAgreePricing() {
      var that = this;
      that.$login.checkTokenValidity().then(function (login) {
        if (!that.order.agreeRepair) {
          uni.showToast({
            title: '签名未上传完成，请稍等或重新签名！',
            icon: 'none' });

          return;
        }
        console.log('拿出来的：' + that.order.customerSignature);
        var orderId = that.order.id;
        that.$api.apiPost({
          url: "/api/order/".concat(orderId, "/repair/confirm"),
          data: {
            customerSignature: that.order.customerSignature,
            isAgreeRepair: that.order.agreeRepair } }).


        then(function (res) {
          uni.showToast({
            title: '已确认报价！',
            icon: 'none' });

          that.$websocket.sendData({
            orderId: that.order.id,
            orderStep: that.order.orderStep,
            notifyShopId: that.order.shopId
            // notifyCustomerId: order.customerId
          });
          uni.navigateBack();
        }).
        catch(function (res) {
          uni.showToast({
            title: '确认报价失败！',
            icon: 'none' });

        });
      });
    },
    confirmPayed: function confirmPayed() {
      var that = this;

      that.$login.checkTokenValidity().then(function (login) {
        uni.showModal({
          title: '是否已支付？',
          cancelText: '未支付',
          confirmText: '已支付',
          success: function success(result) {
            if (result.confirm) {
              var orderId = that.order.id;
              that.$api.apiPost({
                url: "/api/order/".concat(orderId, "/repair/doubleConfirm") }).

              then(function (res) {
                uni.showToast({
                  title: '已确认支付！',
                  icon: 'none' });

                that.$websocket.sendData({
                  orderId: that.order.id,
                  orderStep: that.order.orderStep,
                  // notifyShopId:that.order.shopId,
                  notifyCustomerId: that.order.customerId });

                uni.navigateBack();
              }).
              catch(function (res) {
                uni.showToast({
                  title: '确认支付失败！',
                  icon: 'none' });

              });
            }
          } });


      });
    },
    cancelOrder: function cancelOrder() {
      var that = this;
      that.$login.checkTokenValidity().then(function (login) {
        uni.showModal({
          title: '是否要取消订单？',
          cancelText: '不取消',
          confirmText: '确认取消',
          success: function success(result) {
            if (result.confirm) {
              var orderId = that.order.id;
              that.$api.apiPost({
                url: "/api/order/".concat(orderId, "/repair/cancel") }).

              then(function (res) {
                uni.showToast({
                  title: '已取消订单！',
                  icon: 'none' });

                that.$websocket.sendData({
                  orderId: that.order.id,
                  orderStep: that.order.orderStep,
                  notifyShopId: that.order.shopId
                  // notifyCustomerId: order.customerId
                });
                uni.navigateBack();
              }).
              catch(function (res) {
                uni.showToast({
                  title: '取消订单失败！',
                  icon: 'none' });

              });
            }
          } });


      });
    } }
  // methods end
};exports.default = _default;
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/index.js */ 1)["default"]))

/***/ }),

/***/ 95:
/*!*************************************************************************************************************************************************************!*\
  !*** F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue?vue&type=style&index=0&lang=css& ***!
  \*************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/mini-css-extract-plugin/dist/loader.js??ref--6-oneOf-1-0!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/css-loader/dist/cjs.js??ref--6-oneOf-1-1!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/stylePostLoader.js!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--6-oneOf-1-2!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/postcss-loader/src??ref--6-oneOf-1-3!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!../../../../../../../../HbuilderX/HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!./order-detail-info.vue?vue&type=style&index=0&lang=css& */ 96);
/* harmony import */ var _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_mini_css_extract_plugin_dist_loader_js_ref_6_oneOf_1_0_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_css_loader_dist_cjs_js_ref_6_oneOf_1_1_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_stylePostLoader_js_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_6_oneOf_1_2_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_6_oneOf_1_3_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_HbuilderX_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_style_js_order_detail_info_vue_vue_type_style_index_0_lang_css___WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ 96:
/*!*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/mini-css-extract-plugin/dist/loader.js??ref--6-oneOf-1-0!./node_modules/css-loader/dist/cjs.js??ref--6-oneOf-1-1!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/stylePostLoader.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--6-oneOf-1-2!./node_modules/postcss-loader/src??ref--6-oneOf-1-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/style.js!F:/ktmp/learn/watchApp20220129/watchAppProject/watchapp-frontend/pages/order/order-detail-info/order-detail-info.vue?vue&type=style&index=0&lang=css& ***!
  \*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

// extracted by mini-css-extract-plugin
    if(false) { var cssReload; }
  

/***/ })

},[[89,"common/runtime","common/vendor"]]]);
//# sourceMappingURL=../../../../.sourcemap/mp-weixin/pages/order/order-detail-info/order-detail-info.js.map