import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import req from './api/req'
import store from './store'

// 将引用的对象赋值给Vue自定义属性req
Vue.prototype.$req = req

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

// $mount方法是用来挂载我们的扩展的
