package org.mfm;

import lombok.Data;

@Data
public class RegisterResponse {

    public static String SUCCESS = "SUCCESS";

    public static String FAILURE = "FAILURE";

    private String status;

}
