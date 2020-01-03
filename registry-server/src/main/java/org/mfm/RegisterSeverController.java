package org.mfm;

public class RegisterSeverController {

    private Registry registry = Registry.getInstance();

    /**
     * 服务注册
     *
     * @param registerRequest
     * @return
     */
    public RegisterResponse registry(RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        try {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setServiceName(registerRequest.getServiceName());
            serviceInstance.setHostname(registerRequest.getHostname());
            serviceInstance.setIp(registerRequest.getIp());
            serviceInstance.setPort(registerRequest.getPort());
            serviceInstance
                .setServiceInstanceId(registerRequest.getServiceInstanceId());

            this.registry.register(serviceInstance);

            registerResponse.setStatus(RegisterResponse.SUCCESS);
        } catch (Exception e) {
            registerResponse.setStatus(RegisterResponse.FAILURE);
            e.printStackTrace();
        }
        return registerResponse;

    }

    /**
     * 服务注册
     *
     * @param heartbeatRequest
     * @return
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) {
        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        try {
            ServiceInstance serviceInstance = this.registry.getServiceInstance(
                heartbeatRequest.getServiceName(),
                heartbeatRequest.getServiceInstanceId());
            serviceInstance.renew();

            heartbeatResponse.setStatus(RegisterResponse.SUCCESS);
        } catch (Exception e) {
            heartbeatResponse.setStatus(RegisterResponse.FAILURE);
            e.printStackTrace();
        }
        return heartbeatResponse;

    }

}
