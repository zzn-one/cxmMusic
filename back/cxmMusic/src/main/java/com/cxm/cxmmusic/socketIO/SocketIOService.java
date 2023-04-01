package com.cxm.cxmmusic.socketIO;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SocketIOService {
    //使用ConcurrentMap来存储客户端
    public static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    //播放索引更新事件
    private static final String INDEX_UPDATE = "index_update";

    //播放列表更新事件
    private static final String PLAY_SONG_UPDATE = "play_song_update";

    //播放器的socket前缀
    private static final String PLAYER_PREFIX = "player:";

    //给指定的客户端发送索引更新信息
    public void sendIndexToUser(String account, String index) {
        SocketIOClient client = clientMap.get(account);
        SocketIOClient playerClient = clientMap.get(PLAYER_PREFIX + account);

        if (playerClient != null) {
            playerClient.sendEvent(INDEX_UPDATE, index);
        }
        if (client != null) {
            client.sendEvent(INDEX_UPDATE, index);
        }


    }

    //给指定的客户端发送播放列表更新信息
    public void sendPlaySongToUser(String account, String dataJson) {
        SocketIOClient client = clientMap.get(account);
        SocketIOClient playerClient = clientMap.get(PLAYER_PREFIX + account);

        if (playerClient != null) {
            playerClient.sendEvent(PLAY_SONG_UPDATE, dataJson);
        }
        if (client != null) {
            client.sendEvent(PLAY_SONG_UPDATE, dataJson);
        }
    }

}