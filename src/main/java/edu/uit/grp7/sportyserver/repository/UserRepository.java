package edu.uit.grp7.sportyserver.repository;

import com.google.cloud.firestore.Firestore;
import edu.uit.grp7.sportyserver.models.PracticeGroup;
import edu.uit.grp7.sportyserver.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends FirebaseRepository<User> {

    private final Firestore firestoreDb;

    public UserRepository(
            @Autowired Firestore firestoreDb,
            @Value("${mapping.firebase.collections.user}") final String collectionName) {
        super(firestoreDb.collection(collectionName));
        this.firestoreDb = firestoreDb;
    }

}
