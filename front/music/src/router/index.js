import Vue from 'vue'
import VueRouter from 'vue-router'
import { Message } from 'element-ui'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '/',
    redirect: '/home',
  },

  {
    //主页
    path: "/home",
    name: 'home',
    component: () => import('@/views/Home.vue'),
    redirect: '/home/musicHall',
    children: [
      //歌单编辑页面
      {
        path: 'songListEdit',
        name: 'songListEdit',
        component: () => import('@/views/home/SongListEdit.vue'),
        redirect: '/home/songListEdit/songTableEdit',
        children: [
          //歌单的歌曲列表
          {
            path: "songTableEdit",
            name: "songTableEdit",
            component: () => import('@/views/home/songListEdit/SongTableEdit.vue')
          },
          //歌单的最近收藏
          {
            path: "starEdit",
            name: "starEdit",
            component: () => import('@/views/home/songListEdit/StarEdit.vue')
          },
          //歌单的评论区
          {
            path: "commentEdit",
            name: "commentEdit",
            component: () => import('@/views/home/songListEdit/CommentEdit.vue')
          },
        ]
      },
      // MM音乐
      {
        path: 'mMusic',
        name: 'mMusic',
        redirect: '/home/musicHall'
      },
      //我的音乐
      {
        path: 'userMusic',
        name: 'userMusic',
        component: () => import('@/views/home/UserMusic.vue'),
        redirect: '/home/userMusic/userSong',
        children: [
          //收藏的歌曲
          {

            path: 'userSong',
            name: 'userSong',
            component: () => import('@/views/home/userMusic/UserSong.vue'),
          },
          //收藏的歌单
          {

            path: 'userSongList',
            name: 'userSongList',
            component: () => import('@/views/home/userMusic/UserSongList.vue'),
          },
        ]
      },
      //搜索的结果页面
      {

        path: 'searchResult',
        name: 'searchResult',
        component: () => import('@/views/home/SearchResult.vue')
      },
      // 音乐馆
      {

        path: 'musicHall',
        name: 'musicHall',
        component: () => import('@/views/home/MusicHall.vue'),
        redirect: '/home/musicHall/hallHome',
        children: [
          //音乐馆首页
          {

            path: 'hallHome',
            name: 'hallHome',
            component: () => import('@/views/home/musicHall/HallHome.vue'),
          },
          //歌手页面
          {

            path: 'singerList',
            name: 'singerList',
            component: () => import('@/views/home/musicHall/SingerList.vue'),
          },
          //歌单分类页面
          {

            path: 'songList',
            name: 'songList',
            component: () => import('@/views/home/musicHall/SongList.vue'),
          },
          //（单个）歌单的简介页面
          {

            path: 'songListDetail',
            name: 'songListDetail',
            component: () => import('@/views/home/musicHall/SongListDetail.vue'),
          },
          //歌手的简介页面
          {

            path: 'singerDetail',
            name: 'singerDetail',
            component: () => import('@/views/home/musicHall/SingerDetail.vue'),
          },


        ]
      },
      //用户中心
      {
        path: 'userMsg',
        name: 'userMsg',
        component: () => import('@/views/home/UserMsg.vue'),
        redirect: "/home/userMsg/personalMsg",
        children: [
          //个人信息
          {
            path: 'personalMsg',
            name: 'personalMsg',
            component: () => import('@/views/home/userMsg/PersonalMsg.vue'),
          },
          //我的歌单
          {
            path: 'personalSongList',
            name: 'personalSongList',
            component: () => import('@/views/home/userMsg/PersonalSongList.vue'),
          },
          //听歌记录
          {
            path: 'historySongs',
            name: 'historySongs',
            component: () => import('@/views/home/userMsg/HistorySongs.vue'),
          }
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

//全局前置守卫：初始化时执行、每次路由切换前执行
router.beforeEach((to, from, next) => {

  //放行首页 注册页
  if (to.fullPath === "/home/musicHall/hallHome"||to.fullPath === "/userRegister") {
    next()
  } else {
    //判断当前是否已经登录
    if (localStorage.getItem("token") !== null) {
      next() //放行 

    } else {
      Message({
        message: "请先登录您的账号",
        type: "info"
      })
    }
  }


})

export default router
