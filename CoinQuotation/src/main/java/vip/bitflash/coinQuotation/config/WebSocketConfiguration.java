package vip.bitflash.coinQuotation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import vip.bitflash.coinQuotation.handler.WsSocketHandler;
import vip.bitflash.coinQuotation.interceptor.HandshakeInterceptor;

/**
 * 
 * 
 * @author soso
 * @date 2018年9月24日 下午7:57:37
 */

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	private  String[] origins = {"http://localhos:8080/"};
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new WsSocketHandler(), "/coin/quotation").addInterceptors(new HandshakeInterceptor()).setAllowedOrigins("*");
	}
}
