package edu.uit.grp7.sportyserver.repository;

import com.google.cloud.firestore.Firestore;
import edu.uit.grp7.sportyserver.models.PracticeGroup;
import edu.uit.grp7.sportyserver.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository extends FirebaseRepository<Test> {

    private final Firestore firestoreDb;

    public TestRepository(
            @Autowired Firestore firestoreDb,
            @Value("${mapping.firebase.collections.test}") final String collectionName) {
        super(firestoreDb.collection(collectionName));
        this.firestoreDb = firestoreDb;
    }

}
