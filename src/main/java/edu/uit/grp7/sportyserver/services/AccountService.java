package edu.uit.grp7.sportyserver.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import edu.uit.grp7.sportyserver.models.req.LoginRequest;
import edu.uit.grp7.sportyserver.models.req.ResetPasswordRequest;
import edu.uit.grp7.sportyserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Boolean login(LoginRequest request) {
        return accountRepository.login(request.getUsername(), request.getPassword());
    }

    public String loginWithFireBase(LoginRequest request) throws FirebaseAuthException, ExecutionException, InterruptedException {
        return accountRepository.loginWithFirebase(request.getUsername(), request.getPassword());
    }

    public String signUp(LoginRequest signupRequest) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return accountRepository.signUpWithFireBase(signupRequest.getUsername(), signupRequest.getPassword());
    }

    public void resetPassword(ResetPasswordRequest request) throws FirebaseAuthException, ExecutionException, InterruptedException {
        accountRepository.resetPassword(request.getUsername(), request.getPassword(), request.getNewPassword());
    }

    public Boolean checkExistence(String email) throws FirebaseAuthException {
        return (accountRepository.getUser(email) != null);
    }
}
