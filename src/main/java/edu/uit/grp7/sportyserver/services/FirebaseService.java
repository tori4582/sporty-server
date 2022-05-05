package edu.uit.grp7.sportyserver.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
@RequiredArgsConstructor
public class FirebaseService {

    private final String firebaseUrl;
    private final Resource credentialsFileResource;

    @SneakyThrows
    public void initialize() {
        log.info("Establishing connection to Firebase: " + firebaseUrl);
        var fireBaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentialsFileResource.getInputStream()))
                .setDatabaseUrl(firebaseUrl)
                .build();

        FirebaseApp.initializeApp(fireBaseOptions);
        log.info("Firebase service initialized");
    }


}
