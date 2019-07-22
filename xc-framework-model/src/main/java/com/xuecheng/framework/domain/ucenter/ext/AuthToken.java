package com.xuecheng.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author 98050
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {

    /**
     * 访问token
     */
    String accessToken;
    /**
     * 刷新token
     */
    String refreshToken;
    /**
     * jti令牌
     */
    String jti;


}
