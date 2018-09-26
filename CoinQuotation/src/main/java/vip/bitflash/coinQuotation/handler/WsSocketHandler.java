package vip.bitflash.coinQuotation.handler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import vip.bitflash.coinQuotation.utils.HttpClientUtil;

/**
 * 
 * 
 * @author soso
 * @date 2018年9月24日 下午8:04:31
 */

@Component
public class WsSocketHandler extends TextWebSocketHandler {
	private static long count = 0;
	private static HashMap<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	private static ArrayList<WebSocketSession> users = new ArrayList<>();;
	private static Logger logger = LoggerFactory.getLogger(WsSocketHandler.class);
	private static HashMap<String, String> JsonMap = new HashMap<String, String>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// Collection<WebSocketSession> sessions = sessionMap.values();
		// for (WebSocketSession ws : sessions) {// 广播
		// ws.sendMessage(message);
		// }
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("用户:" + session.getRemoteAddress() + "连接成功");
		long now = System.currentTimeMillis();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat def = new DecimalFormat("0.00");
		
		NumberFormat nf = NumberFormat.getNumberInstance();  
        nf.setMaximumFractionDigits(2);
        
		// 获取火币BTC行情
		String url = "http://api.coindog.com/api/v1/tick/HUOBIPRO:BTCUSDT?unit=usdt";
		String resultForUSDT = HttpClientUtil.doGet(url);
		url = "http://api.coindog.com/api/v1/tick/HUOBIPRO:BTCUSDT?unit=cny";
		String resultForCNY = HttpClientUtil.doGet(url);
		try {
			JSONObject jsonObjectForUSDT = JSON.parseObject(resultForUSDT);
			JSONObject jsonObjectForCNY = JSON.parseObject(resultForCNY);
			JsonMap.put("type", "btc");
			JsonMap.put("status", "success");
			JsonMap.put("CNY", def.format(jsonObjectForCNY.getDoubleValue("close")));
			JsonMap.put("USDT", def.format(jsonObjectForUSDT.getDoubleValue("close")));
			JsonMap.put("Degree", def.format(jsonObjectForUSDT.getDoubleValue("degree")));
			JsonMap.put("Date", df.format(now));
			logger.info("[" + df.format(now) + "] 获取火币BTC行情，状态:成功" + JSON.toJSONString(JsonMap));
		} catch (JSONException e) {
			JsonMap.put("status", "failed");
			JsonMap.put("type", "btc");
			logger.info("[" + df.format(now) + "] 获取火币BTC行情，状态:失败");
		}
		session.sendMessage(new TextMessage(JSON.toJSONString(JsonMap).getBytes()));

		// 获取火币ETH行情
		url = "http://api.coindog.com/api/v1/tick/HUOBIPRO:ETHUSDT?unit=usdt";
		resultForUSDT = HttpClientUtil.doGet(url);
		url = "http://api.coindog.com/api/v1/tick/HUOBIPRO:ETHUSDT?unit=cny";
		resultForCNY = HttpClientUtil.doGet(url);
		try {
			JSONObject jsonObjectForUSDT = JSON.parseObject(resultForUSDT);
			JSONObject jsonObjectForCNY = JSON.parseObject(resultForCNY);
			JsonMap.put("type", "eth");
			JsonMap.put("status", "success");
			JsonMap.put("CNY", def.format(jsonObjectForCNY.getDoubleValue("close")));
			JsonMap.put("USDT", def.format(jsonObjectForUSDT.getDoubleValue("close")));
			JsonMap.put("Degree", def.format(jsonObjectForUSDT.getDoubleValue("degree")));
			JsonMap.put("Date", df.format(now));
			logger.info("[" + df.format(now) + "] 获取火币ETH行情，状态:成功" + JSON.toJSONString(JsonMap));
		} catch (JSONException e) {
			JsonMap.put("status", "failed");
			JsonMap.put("type", "eth");
			logger.info("[" + df.format(now) + "] 获取火币ETH行情，状态:失败");
		}
		session.sendMessage(new TextMessage(JSON.toJSONString(JsonMap).getBytes()));
		users.add(session);

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// sessionMap.remove(session.getPrincipal().getName());
		logger.info("用户断开连接");
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
		super.afterConnectionClosed(session, status);
	}

	/**
	 * 发送消息
	 */
	// public static void sendMessage(String username, String message) throws
	// IOException {
	// sendMessage(Arrays.asList(username), Arrays.asList(message));
	// }

	/**
	 * 发送消息
	 */
	// public static void sendMessage(Collection<String> acceptorList, String
	// message) throws IOException {
	// sendMessage(acceptorList, Arrays.asList(message));
	// }

	/**
	 * 群发
	 */
	public static void sendMessage(String message) throws IOException {
		if (users != null) {
			for (WebSocketSession user : users) {
				try {
					if (user.isOpen()) {
						user.sendMessage(new TextMessage(message.getBytes()));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 发送消息，p2p 群发都支持
	 */
	// public static void sendMessage(Collection<String> acceptorList,
	// Collection<String> msgList) throws IOException {
	// if (acceptorList != null && msgList != null) {
	// for (String acceptor : acceptorList) {
	// WebSocketSession session = sessionMap.get(acceptor);
	// if (session != null) {
	// for (String msg : msgList) {
	// session.sendMessage(new TextMessage(msg.getBytes()));
	// }
	// }
	// }
	// }
	// }
}