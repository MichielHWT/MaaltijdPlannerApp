package com.javaproject.maaltijdplanner.api;

import com.javaproject.maaltijdplanner.controller.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountEndpoint {
    @Autowired
    AccountService as;
}
