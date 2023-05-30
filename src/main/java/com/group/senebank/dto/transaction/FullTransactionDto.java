package com.group.senebank.dto.transaction;

import com.group.senebank.dto.account.FullAccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FullTransactionDto {
    private int id;
    private String payload;
    private FullAccountDto sourceAccount;
    private FullAccountDto targetAccount;
}
