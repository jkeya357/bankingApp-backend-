package com.nzimbu_yeno.nzimbu.service;

import com.nzimbu_yeno.nzimbu.domain.dto.AccountRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.DeleteAccountRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.DeleteAccountResponse;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    List<Account> getAccount();
    Account createAccount(AccountRequest accountRequest);
    DeleteAccountResponse deleteAccount(DeleteAccountRequest deleteAccountRequest);
}
