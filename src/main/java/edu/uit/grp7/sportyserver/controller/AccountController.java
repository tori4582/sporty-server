package edu.uit.grp7.sportyserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uit.grp7.sportyserver.models.req.LoginRequest;
import edu.uit.grp7.sportyserver.repository.AccountRepository;
import edu.uit.grp7.sportyserver.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody final LoginRequest loginRequest) throws JsonProcessingException {

        log.info("Login requested: " + new ObjectMapper().writeValueAsString(loginRequest));

        if (accountService.login(loginRequest)) {
            log.info("Authorized client");
            return ResponseEntity.ok().build();
        } else {
            log.warn("Request contains invalid login information");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("/login-oauth2")
    public ResponseEntity loginOAuth2() {
        log.info("Login OAuth2 requested");
        return ResponseEntity.ok().build();
    }
}

