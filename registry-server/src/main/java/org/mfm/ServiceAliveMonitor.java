package org.mfm;

import java.util.Map;

/**
 * 微服务存活状态后台监控组件
 *
 * @author MengFanmao
 * @since 2020年1月3日
 */
public class ServiceAliveMonitor {

    /**
     * 检查服务是否存活间隔
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

    /**
     * 负责监控微服务存活状态的线程
     */
    private Daemon daemon;

    public ServiceAliveMonitor() {
        this.daemon = new Daemon(); //daemon线程我一般叫后台线程 ，main等其他线程setDaemon默认是false，我一般称为工作线程
        this.daemon.setDaemon(true); //daemon线程不会阻止JVM进程退出的
    }

    /**
     * 启动后台线程
     */
    public void start() {
        this.daemon.start();
    }

    /**
     * 负责监控微服务存活状态的线程
     *
     * @author MengFanmao
     * @since 2020年1月3日
     */
    private class Daemon extends Thread {
        private Registry registry = Registry.getInstance();

        @Override
        public void run() {
            Map<String, Map<String, ServiceInstance>> registryMap = null;
            while (true) {
                try {
                    registryMap = this.registry.getRegistry();
                    for (String serviceName : registryMap.keySet()) {
                        Map<String, ServiceInstance> serviceInstanceMap = registryMap
                            .get(serviceName);
                        for (ServiceInstance serviceInstance : serviceInstanceMap
                            .values()) {
                            if (!serviceInstance.isAlive()) {
                                System.out.println("");
                                this.registry.remove(
                                    serviceInstance.getServiceName(),
                                    serviceInstance.getServiceInstanceId());
                            }

                        }
                    }
                    Thread.sleep(ServiceAliveMonitor.CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
