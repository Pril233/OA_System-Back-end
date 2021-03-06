package pril.oa_system.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {
    /**
     * @Bean 声明创建对象  并把对象放在工厂中  相当于bean标签
     * 如果形参类型对应的对象在工厂中有  会自动装配上
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /**
         * 设置安全管理器，将创建的安全管理器放进shiroFilterFactoryBean过滤工厂里面
         */
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        HashMap<String, String> filterMap = new HashMap<>();
        return shiroFilterFactoryBean;
    }
    /**
     * 创建安全管理器,并将自定义的Realm放进去管理器
     * @return
     */
    @Bean
    public DefaultSecurityManager getDefaultSecurityManager(MyRealm myRealm){
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(myRealm);
        return defaultSecurityManager;
    }

    /*@Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(getMyRealm());
        return securityManager;
    }*/

    /**
     * 创建自定义的Realm
     */
    @Bean
    public MyRealm getMyRealm(){
        return new MyRealm();
    }
}
