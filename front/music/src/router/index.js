import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    //主页
    path: "/home",
    name: 'home',
    component: () => import('@/views/Home.vue'),
    redirect: '/home/musicHall',
    children: [
      {
        // MM音乐
        path: 'mMusic',
        name: 'mMusic',
        redirect: '/home/musicHall'
      },
      {
        //我的音乐
        path: 'userMusic',
        name: 'userMusic',
        component: () => import('@/views/home/UserMusic.vue')
      },
      {
        //搜索的结果页面
        path: 'searchResult',
        name: 'searchResult',
        component: () => import('@/views/home/SearchResult.vue')
      },
      {
        // 音乐馆
        path: 'musicHall',
        name: 'musicHall',
        component: () => import('@/views/home/MusicHall.vue'),
        redirect: '/home/musicHall/hallHome',
        children: [
          {
            //音乐馆首页
            path: 'hallHome',
            name: 'hallHome',
            component: () => import('@/views/home/musicHall/HallHome.vue'),
          },
          {
            //歌手页面
            path: 'singerList',
            name: 'singerList',
            component: () => import('@/views/home/musicHall/SingerList.vue'),
          },
          {
            //歌单分类页面
            path: 'songList',
            name: 'songList',
            component: () => import('@/views/home/musicHall/SongList.vue'),
          },
          {
            //（单个）歌单的简介页面
            path: 'songListDetail',
            name: 'songListDetail',
            component: () => import('@/views/home/musicHall/SongListDetail.vue'),
          },
        ]
      },

    ]
  },
  {
    //播放页
    path: '/playList',
    name: 'playList',
    component: () => import('@/views/PlayList.vue')
  },
  {
    //注册页面
    path: '/userRegister',
    name: 'userRegister',
    component: () => import('@/views/UserRegister.vue')
  }

]

const router = new VueRouter({
  routes
})

export default router
