package com.tongzhu.welfare.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 创建分布式锁模板实例的工厂Bean
 */
public class DistributedLockFactoryBean implements FactoryBean<DistributedLockTemplate> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private LockInstanceMode mode;

    private DistributedLockTemplate distributedLockTemplate;

    private RedissonClient redisson;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @PostConstruct
    public void init() {
       /* Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redssionProperties.getAddress())
                .setTimeout(redssionProperties.getTimeout())
                .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());
        redisson=Redisson.create(config);*/

        Config config=new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port)
                .setPassword(password)
                .setConnectionPoolSize(4)
                .setConnectionMinimumIdleSize(2);
        //config.useSingleServer().setAddress("redis://106.15.202.236:6379");
        redisson=Redisson.create(config);
        logger.info("成功连接Redis Server:{}:{}",host,port);
    }

    @PreDestroy
    public void destroy() {
        logger.debug("销毁分布式锁模板");
        redisson.shutdown();
    }

    @Override
    public DistributedLockTemplate getObject() throws Exception {
        switch (mode) {
            case SINGLE:
                distributedLockTemplate = new SingleDistributedLockTemplate(redisson);
                break;
        }
        return distributedLockTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return DistributedLockTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setMode(String mode) {
        if (mode==null||mode.length()<=0||mode.equals("")) {
            throw new IllegalArgumentException("未找到dlm.redisson.mode配置项");
        }
        this.mode = LockInstanceMode.parse(mode);
        if (this.mode == null) {
            throw new IllegalArgumentException("不支持的分布式锁模式");
        }
    }

    private enum LockInstanceMode {
        SINGLE;
        public static LockInstanceMode parse(String name) {
            for (LockInstanceMode modeIns : LockInstanceMode.values()) {
                if (modeIns.name().equals(name.toUpperCase())) {
                    return modeIns;
                }
            }
            return null;
        }
    }
}
