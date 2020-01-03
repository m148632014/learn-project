package org.mfm;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务注册组件
 *
 * @author MengFanmao
 * @since 2020年1月3日
 */
public class Registry {
    /**
     * 服务注册组件单例
     */
    private static Registry instance = new Registry();
    /**
     * 服务注册表
     */
    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

    /**
     * 私有化构造函数，保证单例
     */
    private Registry() {

    }

    /**
     * 服务注册组件单例创建
     *
     * @return
     */
    public static Registry getInstance() {
        return Registry.instance;
    }

    /**
     * 获取整个注册表
     *
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return this.registry;
    }

    /**
     * 从注册表移除服务实例
     *
     * @param serviceName
     * @param serviceInstanceId
     * @return
     */
    public ServiceInstance remove(String serviceName,
            String serviceInstanceId) {
        System.out.println("服务实例【" + serviceInstanceId + "】，从注册表进行了摘除");
        return this.registry.get(serviceName).remove(serviceInstanceId);
    }

    /**
     * 服务注册
     *
     * @param serviceInstance
     */
    public void register(ServiceInstance serviceInstance) {
        Map<String, ServiceInstance> serviceInstanceMap = this.registry
            .get(serviceInstance.getServiceName());
        if (serviceInstanceMap == null) {
            serviceInstanceMap = new HashMap<>();
            serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),
                serviceInstance);
            this.registry.put(serviceInstance.getServiceName(),
                serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),
            serviceInstance);
        System.out.println("服务实例【" + serviceInstance.getServiceName() + "】进行了注册"
            + serviceInstance);

    }

    /**
     * 获取服务实例信息
     *
     * @param serviceName
     * @param serviceInstanceId
     * @return
     */
    public ServiceInstance getServiceInstance(String serviceName,
            String serviceInstanceId) {
        return this.registry.get(serviceName).get(serviceInstanceId);
    }

}
