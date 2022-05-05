package edu.uit.grp7.sportyserver.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fullname;
    private String accountType;
}
