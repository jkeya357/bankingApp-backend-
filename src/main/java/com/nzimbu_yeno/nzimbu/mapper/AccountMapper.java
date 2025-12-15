package com.nzimbu_yeno.nzimbu.mapper;

import com.nzimbu_yeno.nzimbu.domain.dto.AccountDto;
import com.nzimbu_yeno.nzimbu.domain.dto.AccountRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.AccountRequestDto;
import com.nzimbu_yeno.nzimbu.domain.dto.DeleteAccountRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TransactionMapper.class})
public interface AccountMapper {

    AccountRequest toAccountRequest(AccountRequestDto accountRequestDto);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "transactions", source = "transactions")
    AccountDto toAccountDto(Account account);
    List<AccountDto> toAccountDtoList(List<Account> account);
    DeleteAccountRequest toAccountRequest(DeleteAccountRequest deleteAccountRequest);
}
