package vip.bitflash.coinQuotation.job;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import vip.bitflash.coinQuotation.handler.WsSocketHandler;
import vip.bitflash.coinQuotation.utils.HttpClientUtil;

/**
 * 
 * 
 * @author soso
 * @date 2018年9月24日 下午7:20:48
 */
@EnableScheduling
@Component
public class CoinQuotationSyncJob {

	private final static Logger logger = LoggerFactory.getLogger(CoinQuotationSyncJob.class);

	// @Autowired
	// private WsSocketHandler wsSocketHandler;

	private static HashMap<String, Object> JsonMap = new HashMap<String, Object>();

	/**
	 * 每天6点到12点，每两分钟获取一次BTC的行情
	 **/
	@Scheduled(cron = "0 0/2 6-23 * * ?")
	public void getCoinQuotationBean() throws Exception {
		long now = System.currentTimeMillis();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		DecimalFormat def = new DecimalFormat("0.00");
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
		} finally {
			WsSocketHandler.sendMessage(JSON.toJSONString(JsonMap));
		}

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
		} finally {
			WsSocketHandler.sendMessage(JSON.toJSONString(JsonMap));
		}
	}
}
