package pril.oa_system.shiro;

import java.net.Authenticator;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import pril.oa_system.pojo.User;
import pril.oa_system.service.UserService;
import org.apache.shiro.authc.*;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm{
    @Resource
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(usernamePasswordToken.getUsername());
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User)principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().getName());
        SimpleAuthorizationInfo s=new SimpleAuthorizationInfo();
        s.setRoles(roles);
        return s;
    }

}
