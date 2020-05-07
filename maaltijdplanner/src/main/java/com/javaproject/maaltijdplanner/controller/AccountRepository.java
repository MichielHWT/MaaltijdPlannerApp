package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepository extends CrudRepository<Account, Long> {
}
