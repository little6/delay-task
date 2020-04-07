package com.caisl.dt.system.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ZookeeperConfig
 * 加载zookeeper配置，创建zookeeper配置中心
 *
 * @author caisl
 * @since 2019-04-24
 */
@Configuration
public class ZookeeperConfig {
    //zk集群地址
    @Value("${zookeeper.serviceLists}")
    private String serviceLists;

    //定时任务的命名空间(zk的一个目录)
    @Value("${zookeeper.namespace}")
    private String nameSpace;

    @Value("${zookeeper.baseSleepTimeMilliseconds}")
    private int baseSleepTimeMilliseconds;

    @Value("${zookeeper.maxSleepTimeMilliseconds}")
    private int maxSleepTimeMilliseconds;

    @Value("${zookeeper.maxRetries}")
    private int maxRetries;

    /**
     * zookeeper 配置
     * 创建zookeeper的注册中心，并调用初始化方法
     *
     * @return
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration configuration = new ZookeeperConfiguration(serviceLists, nameSpace);
        configuration.setBaseSleepTimeMilliseconds(baseSleepTimeMilliseconds);
        configuration.setMaxSleepTimeMilliseconds(maxSleepTimeMilliseconds);
        configuration.setMaxRetries(maxRetries);
        return new ZookeeperRegistryCenter(configuration);
    }
}
