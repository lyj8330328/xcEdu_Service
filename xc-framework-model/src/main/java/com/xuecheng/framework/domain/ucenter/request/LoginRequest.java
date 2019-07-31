package com.xuecheng.framework.domain.ucenter.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;


/**
 * @author 98050
 */
@Data
@ToString
public class LoginRequest extends RequestData {

    String username;
    String password;
    //String verifycode;
}
