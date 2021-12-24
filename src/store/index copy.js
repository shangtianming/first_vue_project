import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // 用来定义全局的数据(多组件共用的数据) 不能双向绑定，修改只能通过mutations
  state: {
  },
  // 定义全局的计算属性
  getters: {
  },
  // 定义全局的方法（不能定义异步的方法），也只有这里能修改state中的数据
  mutations: {
  },
  // 定义全局的异步方法（比如请求接口获取全局数据的方法），只能通过操作mutations来修改state
  actions: {
  },
  modules: {
  }
})
