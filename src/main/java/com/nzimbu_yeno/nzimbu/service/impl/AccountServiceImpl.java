package com.nzimbu_yeno.nzimbu.service.impl;

import com.nzimbu_yeno.nzimbu.domain.AccountNumber;
import com.nzimbu_yeno.nzimbu.domain.AccountStatus;
import com.nzimbu_yeno.nzimbu.domain.dto.AccountRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.DeleteAccountRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.DeleteAccountResponse;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import com.nzimbu_yeno.nzimbu.repository.AccountRepository;
import com.nzimbu_yeno.nzimbu.repository.UserRepository;
import com.nzimbu_yeno.nzimbu.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
//    private final AccountNumber accountNumber;

    @Override
    public List<Account> getAccount() {

        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(AccountRequest accountRequest) {

        UUID userId = UUID.fromString(accountRequest.getUserId());
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(foundUser != null){

            Account creteAccount = new Account();

            creteAccount.setUser(foundUser);
            creteAccount.setAccountBalance(accountRequest.getAccountBalance() != null ? accountRequest.getAccountBalance() : BigDecimal.ZERO);
            creteAccount.setAccountStatus(accountRequest.getAccountStatus() != null ? accountRequest.getAccountStatus() : AccountStatus.ACTIVE);
            creteAccount.setAccountType(accountRequest.getAccountType());
            creteAccount.setTransactions(accountRequest.getTransactions());
            creteAccount.setAccountNumber(AccountNumber.generate());

            accountRepository.save(creteAccount);

            return creteAccount;
        }

        return null;
    }

    @Override
    public DeleteAccountResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        Account account = accountRepository.findByAccountNumber(deleteAccountRequest.getAccountNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if(account.getAccountBalance().compareTo(BigDecimal.ZERO) != 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete an account still holding funds, withdraw or transfer all your funds to a secure account then deletion of account can be processed");
        }
        accountRepository.delete(account);
        return new DeleteAccountResponse(
                account.getAccountNumber(),
                "Account deleted successfully"
        ) ;
    }
}
