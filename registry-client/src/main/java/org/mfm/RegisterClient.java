package org.mfm;

import java.util.UUID;

public class RegisterClient {

    private String serviceInstanceId;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
    }

    public void start() {
        new RegisterWorker(this.serviceInstanceId).start();
    }
}
