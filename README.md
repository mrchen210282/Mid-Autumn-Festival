# springcloud
公司项目

	
**项目结构** 

bitflash-security
		│
		├─bitflash-api      				api模块	| /api
		│		│
		│    	├─index（package）					
		│    	│   └─controller
		│    	│   	├─login(class)				
		│    	│   	│	├─/login				登录/index/login/login
		│    	│   	│	└─/logout				登出/index/login/logout
		│    	│   	│
		│    	│   	└─register					
		│		│			├─/app  				注册(register)/index/register/app
		│		│			└─/web					注册(register2)/index/register/web
		│		│
		│    	├─user							
		│    	│   └─controller
		│    	│   	├─payUrl			
		│    	│   	│	├─/upload 				上传的图片/user/payUrl/upload
		│    	│   	│	├─/message 				获取支付信息(payMessage)/user/payUrl/message
		│    	│   	│	└─/get 					查看收款图片(getPayUrl)/user/payUrl/get
		│		│		│
		│    	│   	├─loginPwd			
		│    	│   	│	├─/update 				修改登录密码(changePassword)/user/loginPwd/update
		│    	│   	│	└─/find 				找回登录密码(changePassword2)/user/loginPwd/find
		│		│		│
		│    	│   	├─nickName			
		│    	│   	│	└─/update		 		修改昵称(updateNickName)/user/nickName/update
		│		│		│
		│    	│   	├─confirm			
		│    	│   	│	├─/upload	 			上传身份证(uploadImg )/user/confirm/upload
		│    	│   	│	└─/success		 		身份认证完成(successUpload)/user/confirm/success
		│    	│   	│
		│    	│   	├─payPwd			
		│    	│   	│	├─/vaildate 			验证旧交易密码(vaildatePwd)/user/payPwd/vaildate
		│    	│   	│	└─/update			 	设置交易密码（addOrUpdatePayPwd）/user/payPwd/update
		│    	│   	│
		│    	│   	├─wallet
		│		│		│	└─/get			 		钱包地址(getWalletToken )/user/wallet/get
		│    	│   	│
		│    	│   	└─account
		│		│			└─/info 				收入+购买+可用资产(accountInfo)/user/account/info
		│		│
		│    	├─level								
		│    	│   └─controller
		│    	│   	├─relation				
		│    	│   	│	└─/get					我的社区(getRelation)/level/relation/get
		│    	│   	│
		│    	│   	└─vip
		│		│			├─/updat		 		加入社区(updateVipLevel)/level/vip/update
		│    	│   		└─/get		 			获取vip等级(getVipLevel)/level/vip/get
		│		│
		│    	├─system							
		│    	│   └─controller
		│    	│   	├─version			
		│    	│   	│	└─/get					版本信息(versionInfo)/system/version/get
		│    	│   	│
		│    	│   	├─verifyCode			
		│    	│   	│	└─/get					获取验证码(getVerifyCode)/system/verifyCode/get
		│    	│   	│
		│    	│   	└─priceRate
		│		│			└─/get					折线图(getWeekPriceRate)/system/priceRate/get
		│		│
		│    	├─usersend							
		│    	│   └─controller
		│    	│   	└─send				
		│    	│   		├─/send					发送/usersend/send
		│    	│   		├─/record				发送记录/usersend/record
		│    	│   		└─/fee					手续费(handingFee)/usersend/fee
		│		│
		│    	├─buy							
		│    	│   └─controller
		│       │		├─list
		│    	│	    │ 	├─/buying				获取交易列表（showBuyMessage）/buy/list/buying
		│    	│	    │ 	└─/order	 			获取订单列表（showBuyMessageOwn）/buy/list/order
		│		│		│
		│       │		├─check
		│    	│	    │ 	├─/buying				显示交易详情(showBuyMessagePage)/buy/check/buying
		│    	│	    │ 	└─/order	 			显示订单详情(orderCheck)/buy/check/order
		│		│		│
		│       │		├─addOrCancelBuy
		│    	│	    │ 	├─/publish				发布(addBuyMessage)/buy/publish
		│    	│	    │ 	├─/recall				撤销(cancel)/buy/recall
		│    	│	    │ 	└─/click				下单(addBuyMessageHistory)/buy/click
		│		│		│
		│       │		├─pendingPay		
		│    	│	    │ 	├─/pay					付款(payMoney)/buy/readyToPay/pay
		│    	│	    │ 	└─/cancel 				取消(recall)/buy/readyToPay/cancel
		│		│		│
		│       │		├─remind
		│    	│	    │ 	├─/remind				催单(reminders)/buy/remind
		│    	│	    │ 	└─/decide 				是否已催单(checkReminders)/buy/remind/decide
		│		│		│
		│       │		├─appeal
		│    	│	    │ 	└─/appeal 				申诉/buy/appeal
		│		│		│
		│       │		└─confirm
		│    	│	     	└─/confirm 				确认(PayCoin)/buy/confirm
		│		│
		│		├─trade								/trade
		│    	│   └─controller
		│       │		├─list
		│    	│     	│	├─/trade				获取交易列表/trade/list/trade
		│    	│     	│	└─/order	 			获取订单列表/trade/list/order
		│		│		│
		│       │		├─check
		│    	│     	│	├─/trade	 			交易页详情(listTrade)/trade/check/trade
		│    	│     	│	├─/click				下单详情(selectById)/trade/check/click
		│    	│     	│	└─/order				订单页详情（viewDetail）/trade/check/order
		│       │		│
		│       │		├─confirm
		│    	│	    │ 	└─/confirm 				确认(purchase)/trade/confirm
		│		│		│
		│       │		├─addOrCancelTrade
		│    	│     	│	├─/publish				发布(saveTrade)/trade/publish
		│    	│     	│	├─/forward 				跳转发布(responseTrade)/trade/publish/forward
		│    	│     	│	├─/click				下单(addLock)/trade/click
		│    	│     	│	├─/forward	 			跳转下单(forwardPay)/trade/click/forward
		│    	│     	│	└─/recall 				撤销(cancelTrade)/trade/click/recall
		│		│		│
		│       │		├─lock
		│    	│     	│	├─/unLock				解锁订单(updateTradeState)/trade/lock/unLock
		│    	│     	│	├─/validate				验证是否锁定(provingState)/trade/lock/validate
		│    	│     	│	└─/update				更新交易订单状态)/trade/lock/update
		│		│		│
		│       │		├─pendingPay		
		│    	│	    │ 	├─/pay					付款(payTrade)/trade/pendingPay/pay
		│    	│	    │ 	└─/cancel 				取消(cancelOrder)/trade/pendingPay/cancel
		│		│		│
		│		│		├─reminder
		│    	│     	│	└─/reminder 			催单(singleMsg)/trade/reminder
		│    	│     	│	└─/validate			 	是否被催单(validateSendMessage)/trade/reminder/validate
		│		│		│
		│       │		└─appeal
		│    	│      		└─/appeal	 			申诉(complaint)trade/appeal
		│		│
		│		├─order		
		│    	│   └─controller
		│       │		├─remind
		│    	│     	│	├─/list					获取申诉列表(List)/order/remind/list
		│    	│     	│	└─/check 				显示申诉详情/order/remind/check
		│		│		│
		│       │		└─success
		│    	│     		├─/list					获取成功列表(selectFinishOrder)/order/success/list
		│    	│     		└─/check 				显示成功详情(viewSuccess)/order/success/check
		│		│
		│		├─verify		
		│    	│   └─controller
		│    	│   	└─validate				
		│    	│   		├─/payPwd				是否设置交易密码/verify/validate/payPwd
		│    	│   		└─/user					用户信息判断(getUserPower)/verify/validate/user
		│		│
		│		├─oauth2
		│    	│   └─controller
		│    	│   	└─oauth2				
		│    	│   		├─/responseCode			返回授权许可码/oauth2/responseCode
		│    	│   		└─/validate				取得用户信息/oauth2/validate
		│		│
		│		├─entity							实体类
		│		│
		│    	└─util								工具
		│
		│
		├─bitflash-model       				数据模块 | /model
		│		│
		│    	├─controller
		│		│
		│    	├─server
		│    	│     └─impl 				修改昵称
		│		│
		│    	├─entity  
		│		│
		│    	├─dao
		│		│
		│    	└─xml
		
		

















