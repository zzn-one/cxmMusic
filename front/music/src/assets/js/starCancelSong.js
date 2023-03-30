
import axios from 'axios'

//取消收藏歌曲
async function starCancel(songs, account) {
    const resp = await axios({
        method: "delete",
        url: "/starNumber/song/cancel/" + account,
        data: songs
    })
    return resp
}

export default starCancel