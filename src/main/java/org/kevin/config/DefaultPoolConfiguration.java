package org.kevin.config;

import javafx.util.Duration;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author Kevin.Zng
 * @date 2022/11/25 00:59
 */
public class DefaultPoolConfiguration {
    public static GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(2);
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxTotal(5);
        poolConfig.setMaxWaitMillis(50000);
        poolConfig.setTimeBetweenEvictionRunsMillis(50000);

        return poolConfig;
    }
}
