/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vip.bitflash.coinQuotation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import vip.bitflash.coinQuotation.bean.CoinQuotationBean;
import vip.bitflash.coinQuotation.utils.HttpClientUtil;

@CrossOrigin
@RestController
public class QuotationController {

	private static final Logger logger = LoggerFactory.getLogger(QuotationController.class);

	@RequestMapping(value = "/tick/{ticker}", method = RequestMethod.GET)
	public CoinQuotationBean getQuotation(@PathVariable("ticker") String ticker) {
		String url = "http://api.coindog.com/api/v1/tick/" + ticker + "?unit=usdt";
		String resultForUSDT = HttpClientUtil.doGet(url);
		url = "http://api.coindog.com/api/v1/tick/" + ticker + "?unit=cny";
		String resultForCNY = HttpClientUtil.doGet(url);

		JSONObject jsonObjectForUSDT = JSON.parseObject(resultForUSDT);

		JSONObject jsonObjectForCNY = JSON.parseObject(resultForCNY);

		CoinQuotationBean coinQuotationBean = new CoinQuotationBean();
		coinQuotationBean.setCny(jsonObjectForCNY.getString("close"));
		coinQuotationBean.setDegree(jsonObjectForUSDT.getString("degree"));
		coinQuotationBean.setUsdt(jsonObjectForUSDT.getString("close"));

		return coinQuotationBean;
	}
}