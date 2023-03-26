import Vue from 'vue'
import App from './App.vue'
import router from './router'

import axios from 'axios';
axios.defaults.baseURL = 'http://1.12.54.183:10000'
Vue.prototype.$axios = axios


import elementUI from 'element-ui';
//  element-ui 的样式表
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(elementUI)


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
