/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.zj.content.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO:类功能介绍
 * @version 2020-11-2317:03:32
 * @author zhouzj
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    
    @RequestMapping(value = "/getStr", method = RequestMethod.GET)
    public String getStr() {
        
        return "hello world";
    }
    
}
