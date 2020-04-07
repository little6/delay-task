package com.caisl.dt.job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * DemoConfig
 *
 * @author caisl
 * @since 2019-04-24
 */
@Configuration
public class DelayTaskLoadConfig {
    //注册中心
    @Resource
    private ZookeeperRegistryCenter registryCenter;

    public String getDelayTaskLoadJobName() {
        return delayTaskLoadJobName;
    }

    //延迟任务名称
    @Value("${delayTaskLoadJob.name}")
    private String delayTaskLoadJobName;

    //延迟任务cron表达式
    @Value("${delayTaskLoadJob.cron}")
    private String delayTaskLoadJobCron;

    //延迟任务最大分片数
    @Value("${delayTaskLoadJob.shardingTotalCount}")
    private int delayTaskLoadJobShardingTotalCount;

    @Value("${delayTaskLoadJob.shardingItemParameters}")
    private String shardingItemParameters;

    //创建任务调度bean并且调用init初始化方法
    @Bean(initMethod = "init")
    public JobScheduler delayTaskLoadJobScheduler(final DelayTaskLoadJob delayTaskLoadJob) {
        /**
         * delayTaskLoadJob 调度的任务
         * registryCenter  注册中心
         * liteJobConfiguration  配置信息
         */
        return new SpringJobScheduler(delayTaskLoadJob, registryCenter, liteJobConfiguration());
    }

    //定时调度的配置
    private LiteJobConfiguration liteJobConfiguration() {
        return LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(delayTaskLoadJobName, delayTaskLoadJobCron, delayTaskLoadJobShardingTotalCount)
                                .shardingItemParameters(shardingItemParameters).build()
                        , DelayTaskLoadJob.class.getCanonicalName()
                )).overwrite(true).build();
    }

    public String getShardingItemParameters(){
        return this.shardingItemParameters;
    }
}
