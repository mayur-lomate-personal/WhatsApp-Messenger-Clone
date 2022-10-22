package com.mayur.websocket.Configuration;

import com.mayur.websocket.Filters.CustomHandShakeHandler;
import com.mayur.websocket.Filters.HandShakeInterceptor;
import com.mayur.websocket.Filters.InputChannelInterceptor;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Value("${broker.hostname}")
    private String borkerHostname;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableStompBrokerRelay("/topic", "/queue")
                .setRelayHost(borkerHostname)
                .setRelayPort(61613)
                .setSystemLogin("admin")
                .setSystemPasscode("admin")
                .setClientLogin("admin")
                .setClientPasscode("admin");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Bean
    public Base32 base32() {
        return new Base32();
    }

    @Autowired
    private HandShakeInterceptor handShakeInterceptor;

    @Autowired
    private DefaultHandshakeHandler defaultHandshakeHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("**").addInterceptors(handShakeInterceptor).setHandshakeHandler(defaultHandshakeHandler).withSockJS();
        registry.addEndpoint("/websocket").setAllowedOrigins("**");
    }



    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new InputChannelInterceptor());
    }
}
