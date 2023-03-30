
import axios from 'axios'

//收藏歌曲
async function star(songs, account) {
    const resp = await axios({
        method: "post",
        url: "/starNumber/song/" + account,
        data: songs
    })
    return resp
}

export default star