package com.mvp.conjunto.config;

/*import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
*/
//@Configuration
public class FirebaseConfig {

  //  @Bean
  /*  public FirebaseApp initializeFirebase() throws IOException {


        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");

        if (serviceAccount == null) {
            throw new IOException("No se encontró el archivo serviceAccountKey.json en resources.");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Verificar si Firebase ya está inicializado
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        if (firebaseApps.isEmpty()) {
            return FirebaseApp.initializeApp(options);
        } else {
            return firebaseApps.get(0); // Retorna la instancia existente
        }
    }*/
}
