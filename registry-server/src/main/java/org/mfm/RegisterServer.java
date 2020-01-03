package org.mfm;

import java.util.UUID;

public class RegisterServer {

    public static void main(String[] args) throws Exception {
        RegisterSeverController controller = new RegisterSeverController();

        //模拟发起一个服务注册请求
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setHostname("inventory-service-01");
        registerRequest.setIp("192.168.80.11");
        registerRequest.setPort(9000);
        registerRequest.setServiceName("inventory-service");
        registerRequest.setServiceInstanceId(
            UUID.randomUUID().toString().replace("-", ""));

        controller.registry(registerRequest);

        //模拟进行一次心跳
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest
            .setServiceInstanceId(registerRequest.getServiceInstanceId());
        heartbeatRequest.setServiceName(registerRequest.getServiceName());

        controller.heartbeat(heartbeatRequest);

        //模拟微服务存活状态后台监控启动
        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();

        //register-server主要有很多核心工作线程接受和处理register-client发送的请求，默认不是后台线程的话，JVM不会退出的
        //由于没有网络，这里while保证main工作线程不会退出，这样就算其他线程是daemon也不会退出
        while (true) {
            Thread.sleep(30 * 1000);
        }
    }

}
