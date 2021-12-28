import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Projects from '@/views/Projects'
import Interface from '@/views/Interface'
import Cases from '@/views/Cases'
import EditCase from '@/views/EditCase'
import Page404 from '@/views/Page404'
import req from '../api/req'

Vue.use(VueRouter)

// vue路由是从上往下匹配
const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect: "/project",
    children: [
      {
        path: '/project',
        name: 'project',
        component: Projects
      },
      {
        path: '/interface',
        name: 'interface',
        component: Interface
      },
      {
        path: '/cases',
        name: 'cases',
        component: Cases
      },
      {
        path: '/cases/edit',
        name: 'editcase',
        component: EditCase
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/*',
    name: '404',
    component: Page404
  }
]

const router = new VueRouter({
  routes
})

// 导航守卫
router.beforeEach(async (to, from, next) => {
  if (to.path === '/login') {
    // 鉴别token是否有效
    const u = window.sessionStorage.getItem('username')
    const r = await req.tokenVeri({ username: u })
    if (r.status === 200 && r.data.msg) {
      next()
    } else {
      next('/login')
    }
  } else {
    next('/login') 
  }
})

export default router
