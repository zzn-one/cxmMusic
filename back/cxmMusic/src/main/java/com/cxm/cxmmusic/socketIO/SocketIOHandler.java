package com.cxm.cxmmusic.socketIO;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author
 * @create 2023-04-01 15:53
 */
@Component
@Slf4j
public class SocketIOHandler {
    @Autowired
    private SocketIOServer socketIoServer;//服务端对象

    //项目启动时 ，打开socket服务器
    @PostConstruct
    private void autoStartup() throws Exception {
        try {
            socketIoServer.start();
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("SocketIO服务器启动失败");
        }
    }

    //项目关闭时，关闭socket服务器
    @PreDestroy
    private void autoStop() throws Exception {
        socketIoServer.stop();
    }

    //客户端连接的时候触发
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String account = client.getHandshakeData().getSingleUrlParam("account");
        SocketIOService.clientMap.put(account,client);
        log.info("客户端:" + client.getRemoteAddress() + "  sessionId:" + client.getSessionId() +" account: "+ account+ "已连接");
    }

    //客户端关闭连接时触发
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("客户端:" + client.getSessionId() + "断开连接");
    }

}
