package edu.uit.grp7.sportyserver;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import edu.uit.grp7.sportyserver.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FirebaseService firebaseService(
            @Value("${infrastructure.services.firebase.url}") String url,
            @Value("${infrastructure.services.firebase.credentials-file-name}") String secret) {

        FirebaseService firebaseService = new FirebaseService(url, new ClassPathResource(secret));
        firebaseService.initialize();
        return firebaseService;
    }

    @Bean
    public Firestore firestoreDb() {
        return FirestoreClient.getFirestore();
    }

    @Bean
    public FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Bean
    public BCryptPasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public BCrypt bcrypt() {
//        return new BCrypt();
//    }

}