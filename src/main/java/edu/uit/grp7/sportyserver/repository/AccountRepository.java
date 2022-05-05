package edu.uit.grp7.sportyserver.repository;


import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import edu.uit.grp7.sportyserver.services.FirebaseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AccountRepository {

    private final FirebaseService firebaseService;
    private final Firestore firestoreDb;

    private final CollectionReference collection;

    public AccountRepository(
            @Autowired FirebaseService firebaseService,
            @Autowired Firestore firestoreDb,
            @Value("${mappings.firebase.collections.account}") final String collectionName) {
        this.firebaseService = firebaseService;
        this.firestoreDb = firestoreDb;
        this.collection = firestoreDb.collection(collectionName);
    }

    @SneakyThrows
    public Boolean login(String username, String password) {
        var account = collection.whereEqualTo("username", username).whereEqualTo("password", password).get();
        return !account.get().getDocuments().isEmpty();
    }

}
