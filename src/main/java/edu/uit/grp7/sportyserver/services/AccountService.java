package edu.uit.grp7.sportyserver.services;

import edu.uit.grp7.sportyserver.models.req.LoginRequest;
import edu.uit.grp7.sportyserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Boolean login(LoginRequest request) {
        return accountRepository.login(request.getUsername(), request.getPassword());
    }

}
