package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService {
    @Autowired
    AccountRepository ar;

    public Iterable<Account> getAccountTable(){
        return ar.findAll();
    }

    public Account getAccountByName(String accountName){
        Iterable<Account> allAccounts = ar.findAll();
        for (Account account : allAccounts){
            if(account.getName().equals(accountName)){
                System.out.println(account.getName());
                return account;
            }
        }
        return null;
    }

    public void addNewAccount(Account newAccount){
        System.out.println("Added " + newAccount.getName());
        ar.save(newAccount);
    }
}
