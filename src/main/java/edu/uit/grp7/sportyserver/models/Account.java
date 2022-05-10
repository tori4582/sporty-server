package edu.uit.grp7.sportyserver.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String provider;
    private String accountType;
}
