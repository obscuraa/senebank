package com.group.senebank.dto.account;

import com.group.senebank.dto.users.FullUserDto;
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
public class FullAccountDto {
    private int id;
    private boolean isOverdraft;
    private int balance;
    private FullUserDto user;
}
