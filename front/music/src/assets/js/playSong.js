
import axios from 'axios'
import router from '../../router'

//播放歌曲
async function play(songs, account) {

    //发送请求，添加歌曲到播放列表
    const resp = await axios({
        method: "put",
        url: "/play/" + account,
        data: songs
    })

    if (resp.data.code === 200) {
        //跳转到播放页
        const { href } = router.resolve({
            name: "playList"
        })

        window.open(href,href)
    }

    return resp
}

export default play