/*
 * Copyright 2012-2014 the original author or authors.
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

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * test
 * 
 * @author soso
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = QuotationController.class)
public class QuotationControllertests {
	private static final Logger logger = LoggerFactory.getLogger(QuotationControllertests.class);

	@Test
	public void contextLoads() {
	}

	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

	@Autowired
	private WebApplicationContext wac; // 注入WebApplicationContext

	@Before // 在测试开始前初始化工作
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testHome() throws Exception {
		 String responseString  = mockMvc
				.perform(MockMvcRequestBuilders.get("/tick/HUOBIPRO:BTCUSDT")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.log()).andReturn().getResponse().getContentAsString();// 返回执行请求的结果


	}

}
