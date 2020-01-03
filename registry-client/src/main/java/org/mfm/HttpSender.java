package org.mfm;

public class HttpSender {

    public RegisterResponse registry(RegisterRequest registerRequest) {
        System.out.println("服务实例【" + registerRequest.getServiceInstanceId()
            + "】发送了注册请求....." + registerRequest);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus(RegisterResponse.SUCCESS);
        return registerResponse;
    }

    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) {
        System.out.println("服务实例【" + heartbeatRequest.getServiceInstanceId()
            + "】发送了心跳....." + heartbeatRequest);
        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(HeartbeatResponse.SUCCESS);
        return response;
    }
}
