package pril.oa_system.shiro;

import java.net.Authenticator;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import pril.oa_system.pojo.User;
import pril.oa_system.service.UserService;
import org.apache.shiro.authc.*;

import javax.annotation.Resource;

public class MyRealm extends AuthenticatingRealm{
    @Resource
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(usernamePasswordToken.getUsername());
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }

}
