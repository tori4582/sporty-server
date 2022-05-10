package edu.uit.grp7.sportyserver.controller;

import edu.uit.grp7.sportyserver.models.req.LoginRequest;
import edu.uit.grp7.sportyserver.models.req.ResetPasswordRequest;
import edu.uit.grp7.sportyserver.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity login(
            @RequestBody final LoginRequest loginRequest,
            @RequestParam(name = "oauth2-method") final String oAuth2Method) {

        if (oAuth2Method == null) {
            return (accountService.login(loginRequest))
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!oAuth2Method.equals("firebase")) {
            return ResponseEntity.badRequest().body(new IllegalArgumentException());
        }

        try {
            String grantedToken = accountService.loginWithFireBase(loginRequest);
            return (grantedToken != null)
                    ? ResponseEntity.ok().body(grantedToken)
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getCause() + " : " + e.getMessage());
        }
    }

    @GetMapping("/login-oauth2")
    public ResponseEntity loginOAuth2() {
        log.info("Login OAuth2 requested");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody final LoginRequest signupRequest) {

        String grantedToken = null;

        try {
            log.info("Attempt to create user");
            grantedToken = accountService.signUp(signupRequest);
            return ResponseEntity.ok().body(grantedToken);
        } catch (Exception e) {
            log.warn("Signup failed due to " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody final ResetPasswordRequest resetPasswordRequest) {
        try {
            accountService.resetPassword(resetPasswordRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/check")
    public ResponseEntity checkExistence(@RequestParam(name = "email") final String email) {
        try {
            accountService.checkExistence(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}

