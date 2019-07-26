package com.xuecheng.auth.service.impl;

import com.xuecheng.auth.client.UserClient;
import com.xuecheng.auth.service.UserJwt;
import com.xuecheng.framework.domain.ucenter.XcMenu;
import com.xuecheng.framework.domain.ucenter.ext.XcUserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 98050
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final ClientDetailsService clientDetailsService;


    private final UserClient userClient;

    @Autowired
    public UserDetailsServiceImpl(ClientDetailsService clientDetailsService, UserClient userClient) {
        this.clientDetailsService = clientDetailsService;
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //远程调用用户中心服务，根据用户名查询用户
        XcUserExt userExt = this.userClient.getUserExt(username);
        if (userExt == null){
            //返回NULL表示用户不存在，Spring Security会抛出 异常
            return null;
        }
        //从数据库查询用户正确的密码，Spring Security会去对比输入密码的正确性
        String password = userExt.getPassword();


        //用户权限，先暂时不做
//        List<XcMenu> permissions = userExt.getPermissions();
//        List<String> user_permission = new ArrayList<>();
//        permissions.forEach(item-> user_permission.add(item.getCode()));
//        user_permission.add("course_get_baseinfo");
//        user_permission.add("course_find_pic");
//        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");
        String user_permission_string  = "";
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        /**
         * 设置用户id
         * 用户类型
         * 所属企业
         * 用户头像
         */
        userDetails.setId(userExt.getId());
        userDetails.setUtype(userExt.getUtype());
        userDetails.setCompanyId(userExt.getCompanyId());
        userDetails.setName(userExt.getName());
        userDetails.setUserpic(userExt.getUserpic());
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
//                AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }
}
