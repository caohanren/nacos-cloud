package com.study.user.controller;

import com.study.common.utils.AppUserUtil;
import com.study.model.user.UserJwt;
import com.study.user.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/user")
public class AppUserController {

    @Resource
    private AppUserService appUserService;

    @GetMapping(value = "/findByUsername")
    public UserJwt findByUsername(@RequestParam("username") String username) {
        return appUserService.findByUsername(username);
    }

    @GetMapping(value = "/findById")
    public  Authentication  findById(@RequestParam("id") Long id) {
        System.out.println("id::::"+id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
        //return appUserService.findByUsername(username);
    }


    @GetMapping("/test")
    @PreAuthorize("hasAuthority('back:menu:save')")
    public String test() {
        System.out.println("进来了没。。。。");
        return "进来了";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('back:menu:save123')")
    public String test2() {
        System.out.println("进不来");
        return "没有有权限";
    }


}
