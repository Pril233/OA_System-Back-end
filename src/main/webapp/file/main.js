import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/css/global.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

Vue.use(ElementUI)
Vue.prototype.$http=axios
axios.defaults.baseURL = 'http://127.0.0.1:8888/OA_System'
Vue.config.productionTip = false
axios.interceptors.request.use(config => {
  console.log(config)
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
