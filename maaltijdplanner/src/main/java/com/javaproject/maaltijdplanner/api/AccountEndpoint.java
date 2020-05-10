package com.javaproject.maaltijdplanner.api;

import com.javaproject.maaltijdplanner.controller.AccountService;
import com.javaproject.maaltijdplanner.domein.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountEndpoint {
    @Autowired
    AccountService as;

    //Maak account aan bij starten applicatie, admin, heeft als enige toegang tot admin pagina

    @GetMapping("/getAccounTable")
    public Iterable<Account> getAccountTable(){
        return as.getAccountTable();
    }

    @GetMapping("/getAccountByName/{accountName}")
    public Account getAccountByName(@PathVariable String accountName){
        return as.getAccountByName(accountName);
    }

    @PostMapping("/addNewAccount")
    public void addNewAccount(@RequestBody Account newAccount){
        as.addNewAccount(newAccount);
    }
}
