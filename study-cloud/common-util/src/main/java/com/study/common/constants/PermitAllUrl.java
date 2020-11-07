package com.study.common.constants;

import java.util.*;

/**
 * 需要放开权限的url
 *
 */
public final class PermitAllUrl {

    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {
            "/user/login","/user/logout",
            "/actuator/health", "/actuator/env", "/actuator/metrics/**",
            "/actuator/trace", "/actuator/dump", "/actuator/jolokia",
            "/actuator/info", "/actuator/logfile", "/actuator/refresh",
            "/actuator/flyway", "/actuator/liquibase", "/actuator/heapdump",
            "/actuator/loggers", "/actuator/auditevents", "/actuator/env/PID",
            "/actuator/jolokia/**", "/v2/api-docs/**", "/swagger-ui.html",
            "/swagger-resources/**", "/webjars/**"};

    /**
     * 需要放开权限的url
     *
     * @param urls 自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }

    /**
     * 用来判断 如果 当前的请求 在 放行的请求中存在,(不需要拦截 :true,否则需要拦截:false)
     * @return
     */
    public static boolean hasAutorize(String uri){
        String[] strings = PermitAllUrl.permitAllUrl();
        List<String> list = Arrays.asList(strings);
        return list.contains(uri);
    }

}
