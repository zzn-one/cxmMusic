import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

/*导入 axios---------------------------------------------------------------------------------------------- */
import axios from 'axios'
// 全局配置 axios
axios.defaults.baseURL = 'http://localhost:3000'
Vue.prototype.$axios = axios

/* element-ui----------------------------------------------------------------------------------------------*/
import ElementUI from 'element-ui'
//  element-ui 的样式表
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

/* moment 时间格式工具 */
import moment from 'moment'
Vue.prototype.$moment = moment

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
