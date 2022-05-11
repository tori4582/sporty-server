package edu.uit.grp7.sportyserver.repository;

import com.google.cloud.firestore.Firestore;
import edu.uit.grp7.sportyserver.models.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseRepository extends FirebaseRepository<Exercise> {

    private final Firestore firestoreDb;

    public ExerciseRepository(
            @Autowired Firestore firestoreDb,
            @Value("${mapping.firebase.collections.exercise}") final String collectionName) {
        super(firestoreDb.collection(collectionName));
        this.firestoreDb = firestoreDb;
    }

}
