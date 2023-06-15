package com.nabob.conch.geo.bootstrap;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "conch.geo.job")
public class ConchGeoJobProperties {

    private Manager manager;

    public static class Manager {
        /**
         * 核心线程数 (默认为CPU核心数的 2 倍)
         */
        private int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;

        /**
         * 最大线程数
         */
        private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2 + 2;

        /**
         * 非核心线程线程最大空闲时间 unit: s
         */
        private long keepAliveTime = 0L;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public long getKeepAliveTime() {
            return keepAliveTime;
        }

        public void setKeepAliveTime(long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
