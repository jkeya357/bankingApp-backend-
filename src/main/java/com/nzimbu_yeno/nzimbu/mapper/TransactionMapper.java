package com.nzimbu_yeno.nzimbu.mapper;

import com.nzimbu_yeno.nzimbu.domain.dto.TransactionRequestDto;
import com.nzimbu_yeno.nzimbu.domain.dto.TransactionsDto;
import com.nzimbu_yeno.nzimbu.domain.dto.TransactionsRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(target = "description", source = "description")
    TransactionsRequest toTransactionsRequest(TransactionRequestDto transactionRequestDto);

    @Mapping(target = "transactionId", source = "id")
    @Mapping(target = "account", source = "account.id")
    TransactionsDto toDto(Transactions transactions);

    List<TransactionsDto> toDtoList(List<Transactions> transactions);
}
