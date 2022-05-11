package edu.uit.grp7.sportyserver.common;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.concurrent.ExecutionException;

public class FirestoreDbHelpers {

    public static DocumentSnapshot getDocumentFromCollection(CollectionReference collection, String documentId) throws ExecutionException, InterruptedException {
        for (var document : collection.listDocuments()) {
            if (document.getId().equals(documentId)) {
                return document.get().get();
            }
        }
        return null;
    }

    public static Object getFieldFromDocument(DocumentSnapshot document, String fieldName) {
        return document.get(fieldName);
    }

    public static Object getFieldFromDocument(CollectionReference collection,String documentId, String fieldName) throws ExecutionException, InterruptedException {
        var document = getDocumentFromCollection(collection, documentId);

        if (document == null) {
            throwNotFound(collection.getPath());
        }

        return getFieldFromDocument(document, fieldName);
    }

    public static NullPointerException throwNotFound(String collectionPath) {
        throw new NullPointerException("Document with given Id not found in collection '" + collectionPath +  "'");
    }


    public static DocumentSnapshot docSnap(CollectionReference collection, String documentId) throws ExecutionException, InterruptedException {
        var document = getDocumentFromCollection(collection, documentId);
        if (document == null) {
            throw new IllegalArgumentException("Given document Id is not found in collection '" + collection.getPath() + "'");
        }

        return document;
    }

    public static DocumentReference docRef(CollectionReference collection, String documentId) throws ExecutionException, InterruptedException {
        return docSnap(collection, documentId).getReference();
    }

    public static DocumentReference docRef(DocumentSnapshot document) {
        return document.getReference();
    }

}