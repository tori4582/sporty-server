package edu.uit.grp7.sportyserver.repository;


import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.ErrorCode;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import edu.uit.grp7.sportyserver.models.Account;
import edu.uit.grp7.sportyserver.services.FirebaseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class AccountRepository {

    private final FirebaseService firebaseService;
    private final FirebaseAuth firebaseAuth;
    private final Firestore firestoreDb;
    private final CollectionReference collection;

    private final BCryptPasswordEncoder bcryptEncoder;

    public AccountRepository(
            @Autowired FirebaseService firebaseService,
            @Autowired Firestore firestoreDb,
            @Autowired FirebaseAuth firebaseAuth,
            @Value("${mappings.firebase.collections.account}") final String collectionName,
            @Autowired BCryptPasswordEncoder bcryptEncoder) {
        this.firebaseService = firebaseService;
        this.firestoreDb = firestoreDb;
        this.firebaseAuth = firebaseAuth;
        this.collection = firestoreDb.collection(collectionName);
        this.bcryptEncoder = bcryptEncoder;
    }

    @SneakyThrows
    public Boolean login(String username, String password) {
        var account = collection.whereEqualTo("username", username).whereEqualTo("password", password).get();
        return !account.get().getDocuments().isEmpty();
    }

    public UserRecord getUser(String email) throws FirebaseAuthException {
        return firebaseAuth.getUserByEmail(email);
    }

    public String loginWithFirebase(String username, String password) throws FirebaseAuthException, ExecutionException, InterruptedException {

        var userRecord = getUser(username);
        var persistentUser = collection.select("password")
                .whereEqualTo("username", username)
                .get();

        var querySnapshot = persistentUser.get();

        var persistentPassword = querySnapshot.getDocuments().get(0).get("password", String.class);


        if (!bcryptEncoder.matches(password, persistentPassword)) {
            throw new FirebaseAuthException(ErrorCode.UNAUTHENTICATED,
                    "Username or password is not correct",
                    new IllegalArgumentException("Username or password is not correct"),
                    null,
                    AuthErrorCode.USER_NOT_FOUND
            );
        }

        return firebaseAuth.createCustomToken(userRecord.getUid());
    }


    public String signUpWithFireBase(String username, String password) throws ExecutionException, InterruptedException, FirebaseAuthException {

        var encrpytedPassword = bcryptEncoder.encode(password);

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(username);
        createRequest.setPassword(password);

        String createdUserId;

        createdUserId = firebaseAuth.createUserAsync(createRequest).get().getUid();

        collection.add(
            Account.builder()
                .accountType("user")
                .username(username)
                .email(username)
                .provider("firebase")
                .password(encrpytedPassword)
                .build()
        );

        return firebaseAuth.createCustomToken(createdUserId);

    }

    public void resetPassword(String username, String password, String newPassword) throws FirebaseAuthException, ExecutionException, InterruptedException {
        getUser(username);

        String encryptedPassword = bcryptEncoder.encode(newPassword);

        for (var document : collection.listDocuments()) {
            if (document.get().get().get("username", String.class).equals(username)) {
                document.update(Map.of("password", encryptedPassword));
                log.warn("User changed password successfully");
            }
        }
    }
}
