import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

// 导入 axios
import axios from 'axios'
// 全局配置 axios
axios.defaults.baseURL = 'http://localhost:3000'
Vue.prototype.$axios = axios

// 导入 element-ui
import ElementUI from 'element-ui'
// 导入 element-ui 的样式表
import 'element-ui/lib/theme-chalk/index.css'
//  安装ElementUI 
Vue.use(ElementUI)


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
