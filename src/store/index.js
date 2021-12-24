import Vue from 'vue'
import Vuex from 'vuex'
import req from '../api/req.js'

Vue.use(Vuex)

export default new Vuex.Store({
  // 用来定义全局的数据(多组件共用的数据) 不能双向绑定，修改只能通过mutations
  state: {
    projects: ['1', '2', '3'],
    name: 'vuex中的name字段',
    a: 2,
    b: 5
  },
  // 定义全局的计算属性
  getters: {
    c(state) {
      return state.a * state.b
    }
  },
  // 定义全局的方法（不能定义异步的方法），也只有这里能修改state中的数据
  mutations: {
    addNum(state) {
      state.a++
    },
    // 修改state.projects的方法，给下面异步方法用
    updateProjects(state, data) {
      state.projects = data
    }
  },
  // 定义全局的异步方法（比如请求接口获取全局数据的方法），只能通过操作mutations来修改state
  actions: {
    async getProjects(content) {
      const r = await req.getProjects()
      // console.log(r.data.results)
      content.commit('updateProjects', r.data.results)
    }
  },
  modules: {
  }
})
