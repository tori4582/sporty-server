package edu.uit.grp7.sportyserver.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static edu.uit.grp7.sportyserver.common.FirestoreDbHelpers.*;

@RequiredArgsConstructor
public class FirebaseRepository<T> {

    protected final CollectionReference collection;

    public DocumentSnapshot get(String documentId) throws ExecutionException, InterruptedException {
        return getDocumentFromCollection(collection, documentId);
    }

    public Object get(String documentId, String field) throws ExecutionException, InterruptedException {
        return getFieldFromDocument(collection, documentId, field);
    }

    public String addDocument(T value) throws ExecutionException, InterruptedException {
        return collection.add(value).get().getId();
    }

    public void addField(String documentId, String fieldName, Object value) throws ExecutionException, InterruptedException {
        var documentSnapshot = docSnap(collection, documentId);

        if (documentSnapshot.contains(fieldName)) {
            throw new IllegalArgumentException("Field already exists");
        }

        docRef(documentSnapshot).set(Map.of(fieldName, value));
    }

    public void update(String documentId, String fieldName, Object value) throws ExecutionException, InterruptedException {

        var documentSnapshot = docSnap(collection, documentId);

        if (!documentSnapshot.contains(fieldName)) {
            throw new IllegalArgumentException("Field does not exist, an action to create a new field is required");
        }

        docRef(collection, documentId).update(Map.of(fieldName, value));
    }

    public void deleteField(String documentId, String fieldName) throws ExecutionException, InterruptedException {
        update(documentId, fieldName, FieldValue.delete());
    }

    public void deleteDocument(String documentId) throws ExecutionException, InterruptedException {
        docRef(collection, documentId).delete();
    }

}
