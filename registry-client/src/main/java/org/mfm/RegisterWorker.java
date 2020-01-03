package org.mfm;

public class RegisterWorker extends Thread {

    public static final String SERVICE_NAME = "inventory-service";

    public static final String IP = "inventory-service";

    public static final String HOSTNAME = "inventory-service";

    public static final Integer PORT = 9000;

    private HttpSender httpSender;

    private Boolean finishedRegister;

    private String serviceInstanceId;

    public RegisterWorker(String serviceInstanceId) {
        this.httpSender = new HttpSender();
        this.finishedRegister = false;
        this.serviceInstanceId = serviceInstanceId;
    }

    @Override
    public void run() {

        while (!this.finishedRegister) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setServiceName(RegisterWorker.SERVICE_NAME);
            registerRequest.setHostname(RegisterWorker.HOSTNAME);
            registerRequest.setIp(RegisterWorker.IP);
            registerRequest.setPort(RegisterWorker.PORT);
            registerRequest.setServiceInstanceId(this.serviceInstanceId);

            RegisterResponse response = this.httpSender
                .registry(registerRequest);

            System.out.println("服务实例【" + registerRequest.getServiceInstanceId()
                + "】注册成功，状态：" + response.getStatus());
            if (RegisterResponse.SUCCESS.equals(response.getStatus())) {
                this.finishedRegister = true;
            } else {
                return;
            }
        }

        if (this.finishedRegister) {
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setServiceInstanceId(this.serviceInstanceId);
            while (true) {
                try {
                    HeartbeatResponse heartbeatResponse = this.httpSender
                        .heartbeat(heartbeatRequest);
                    System.out.println("服务实例【"
                        + heartbeatRequest.getServiceInstanceId() + "发送心跳，心跳结果："
                        + heartbeatResponse.getStatus() + ".......");
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
