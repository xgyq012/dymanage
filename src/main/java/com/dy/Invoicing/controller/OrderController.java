package com.dy.Invoicing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/index")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/first")
    public String index(){


        return "base/index";
    }

}
