package com.cxm.cxmmusic.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @create 2023-04-01 15:51
 */
@Configuration
public class SocketIOConfig {
    @Value("${socketIO.host}")
    private String host;

    @Value("${socketIO.port}")
    private Integer port;

    @Value("${socketIO.bossCount}")
    private int bossCount;

    @Value("${socketIO.workCount}")
    private int workCount;

    @Value("${socketIO.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${socketIO.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${socketIO.pingTimeout}")
    private int pingTimeout;

    @Value("${socketIO.pingInterval}")
    private int pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        return new SocketIOServer(config);
    }

    //这个对象是用来扫描socketIO的注解，比如 @OnConnect、@OnEvent
    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }
}



