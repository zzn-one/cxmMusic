import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/singer',
    name: 'singer',
    component: () => import('../views/Singer.vue')
  },
  {
    path: '/song',
    name: 'song',
    component: () => import('../views/Song.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
