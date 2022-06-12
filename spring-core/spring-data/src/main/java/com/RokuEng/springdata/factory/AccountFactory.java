package com.RokuEng.springdata.factory;

import com.RokuEng.springdata.entity.account.Account;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public interface AccountFactory<T extends Account> extends Supplier<T> {
}
