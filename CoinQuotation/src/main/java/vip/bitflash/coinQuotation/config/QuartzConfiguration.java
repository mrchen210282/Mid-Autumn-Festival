package vip.bitflash.coinQuotation.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vip.bitflash.coinQuotation.job.CoinQuotationSyncJob;

/**
 * 
 * 
 * @author soso
 * @date 2018年9月24日 下午7:19:25
 */

@Configuration
public class QuartzConfiguration {

//	private static final int TIME = 2; // 更新频率
//	
//	// JobDetail
//	@Bean
//	public JobDetail coinQuotationSyncJobDetail() {
//		return JobBuilder.newJob(CoinQuotationSyncJob.class).withIdentity("coinQuotationSyncJob").storeDurably()
//				.build();
//	}
//
//	// Trigger
//	@Bean
//	public Trigger coinQuotationSyncTrigger() {
//		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME)
//				.repeatForever();
//		return TriggerBuilder.newTrigger().forJob(coinQuotationSyncJobDetail()).withIdentity("coinQuotationSyncTrigger")
//				.withSchedule(schedBuilder).build();
//	}
}
