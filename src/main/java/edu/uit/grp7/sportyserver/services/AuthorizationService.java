package edu.uit.grp7.sportyserver.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static edu.uit.grp7.sportyserver.common.AuthorizationHelpers.getBearerToken;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationService {

    private FirebaseAuth firebaseAuth;

    public FirebaseToken verifyToken(HttpServletRequest request) throws FirebaseAuthException {
        FirebaseToken decodedToken = firebaseAuth.verifyIdToken(getBearerToken(request));
        return decodedToken;
    }

}
