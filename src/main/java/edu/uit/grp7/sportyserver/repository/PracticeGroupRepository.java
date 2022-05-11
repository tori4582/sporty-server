package edu.uit.grp7.sportyserver.repository;

import com.google.cloud.firestore.Firestore;
import edu.uit.grp7.sportyserver.models.PracticeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PracticeGroupRepository extends FirebaseRepository<PracticeGroup> {

    private final Firestore firestoreDb;

    public PracticeGroupRepository(
            @Autowired Firestore firestoreDb,
            @Value("${mapping.firebase.collections.practiceGroup}") final String collectionName) {
        super(firestoreDb.collection(collectionName));
        this.firestoreDb = firestoreDb;
    }

}
