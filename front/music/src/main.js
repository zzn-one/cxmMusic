import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

/*导入 axios---------------------------------------------------------------------------------------------- */
import axios from 'axios'
// 全局配置 axios
axios.defaults.baseURL = 'http://localhost:10000'
Vue.prototype.$axios = axios

//axios请求拦截器
axios.interceptors.request.use(config => {
  //jwt
  const token = localStorage.getItem("token")
  if (token !== null) {
    //为请求头设置jwt
    config.headers.Authorization = token
  }
  return config
})

//axios响应拦截器
axios.interceptors.response.use(response => {

  //如果jwt异常，则删除本地jwt
  if (response.data.code === 401) {
    localStorage.removeItem("token")
  }
  return response
})




/* element-ui----------------------------------------------------------------------------------------------*/
import ElementUI from 'element-ui'
//  element-ui 的样式表
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

/* moment 时间格式工具 */
import moment from 'moment'
Vue.prototype.$moment = moment

/* token解析 */
import token from '@/assets/js/token';
Vue.prototype.$token = token


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
