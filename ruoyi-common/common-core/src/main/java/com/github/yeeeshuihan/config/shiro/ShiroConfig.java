package com.github.yeeeshuihan.config.shiro;

import com.github.yeeeshuihan.config.shiro.filters.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.*;

/**
 * @author yeeeshuihan
 * @date 2026/1/10 19:57
 * @description
 */
@Configuration
public class ShiroConfig {
//    //配置Filter
//    @Autowired
//    private IsbgBaseConfig isbgBaseConfig;

//    public static final List<String> ANON_URL_LIST = Arrays.asList(
//            "/system/getPlatformInfo",
//            "/system/randomImage/**",
//            "/system/randomImage",
//            "/system/checkCaptcha",
//            "/system/loginPolicy/queryCache",
//            "/system/getRsaPublicKey",
//            "/system/login",
//            "/system/doubleLogin",
//            "/system/ssoLogin",
//            "/system/mssCtcOauthRedirect",
//            "/system/mssCtcOauthLogin",
//            "/system/phoneLogin",
//            "/system/accessToken",
//            "/system/logout",
//            "/system/getEncryptedString",
//            "/system/sendCode",
//            "/system/product/ssoLoginAuth",
//            "/system/user/resetPasswordUser",
//            "/system/mssSync/syncData",
//            "/system/fourUser/sync/**",
//            "/system/common/static/**",
//            "/system/common/pdf/**",
//            "/system/websocket/**",
//            "/task/fatigue/**",
//            "/error",
//            "/generic/**",
//            "/doc.html",
//            "/**/*.js",
//            "/**/*.css",
//            "/**/*.html",
//            "/**/*.svg",
//            "/**/*.pdf",
//            "/**/*.jpg",
//            "/**/*.png",
//            "/**/*.gif",
//            "/**/*.ico",
//            "/**/*.ttf",
//            "/**/*.woff",
//            "/**/*.woff2",
//            "/druid/**",
//            "/v3/api-docs/**",
//            "/es8/**",
//            "/swagger-ui.html",
//            "/swagger**/**",
//            "/webjars/**",
//            "/v2/**"
//    );

//    /**
//     * Filter Chain定义说明
//     * <p>
//     * 1、一个URL可以配置多个Filter，使用逗号分隔
//     * 2、当设置多个过滤器时，全部验证通过，才视为通过
//     * 3、部分过滤器可指定参数，如perms，roles
//     */
//    @Bean("shiroFilterFactoryBean")
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        CustomShiroFilterFactoryBean shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 拦截器
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        //支持yml方式，配置拦截排除
//        if (isbgBaseConfig != null && isbgBaseConfig.getExcludeShiroUrls() != null && isbgBaseConfig.getExcludeShiroUrls().size() > 0) {
//            for (String url : isbgBaseConfig.getExcludeShiroUrls()) {
//                filterChainDefinitionMap.put(url, "anon");
//            }
//        }
//        //配置不会被拦截的链接 顺序判断
//        for (String url : ANON_URL_LIST) {
//            filterChainDefinitionMap.put(url, "anon");
//        }
//        // 添加自己的过滤器并且取名为jwt
//        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
//        filterMap.put("jwt", new JwtFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);
//        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
//        filterChainDefinitionMap.put("/**", "jwt");
//
//        // 未授权界面返回JSON
//        shiroFilterFactoryBean.setUnauthorizedUrl("/system/common/403");
//        shiroFilterFactoryBean.setLoginUrl("/system/common/403");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
    static final List<String> ANON_URL_LIST = Arrays.asList(
            "/system/getPlatformInfo",
            "/system/randomImage/**",
            "/system/randomImage",
            "/system/checkCaptcha",
            "/system/loginPolicy/queryCache",
            "/system/getRsaPublicKey",
            "/system/login",
            "/system/doubleLogin",
            "/system/ssoLogin",
            "/system/mssCtcOauthRedirect",
            "/system/mssCtcOauthLogin"
    );

//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
////        Map<String, Filter> filters = new HashMap<>();
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        for (String url : ANON_URL_LIST) {
//            filterChainDefinitionMap.put(url, "anon");
//        }
//        //Jwt Filter
//        shiroFilterFactoryBean.getFilters().put("jwt", new JwtFilter());
//        filterChainDefinitionMap.put("/**", "jwt");
//    }

//    @Bean("securityManager")
//    public DefaultWebSecurityManager securityManager(ShiroRealm myRealm, IRedisManager iRedisManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myRealm);
//        /*
//         * 关闭shiro自带的session，详情见文档
//         * http://shiro.apache.org/session-management.html#SessionManagement-
//         * StatelessApplications%28Sessionless%29
//         */
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);
//        //自定义缓存实现,使用redis
//        securityManager.setCacheManager(redisCacheManager(iRedisManager));
//        return securityManager;
//    }

//    /**
//     * 下面的代码是添加注解支持
//     *
//     * @return
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
//        defaultAdvisorAutoProxyCreator.setAdvisorBeanNamePrefix("_no_advisor");
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//
//    /**
//     * cacheManager 缓存 redis实现
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    public RedisCacheManager redisCacheManager(IRedisManager redisManager) {
//        log.info("===============创建缓存管理器RedisCacheManager");
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager);
//        //redis中针对不同用户缓存(此处的userId需要对应user实体中的userId字段,用于唯一标识)
//        redisCacheManager.setPrincipalIdFieldName("userId");
//        //用户权限信息缓存时间
//        redisCacheManager.setExpire(200000);
//        return redisCacheManager;
//    }
//
//    /**
//     * 配置shiro redisManager
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    @Bean
//    public IRedisManager redisManager(RedisConnectionFactory redisConnectionFactory) {
//        log.info("===============创建RedisManager,连接Redis..");
//        //复用Spring RedisConnectionFactory
//        return new ShiroCacheRedisManager(redisConnectionFactory);
//    }
}
