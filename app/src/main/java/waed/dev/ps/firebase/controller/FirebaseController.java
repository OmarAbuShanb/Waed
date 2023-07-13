package waed.dev.ps.firebase.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseController {
    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore database;
    private static volatile FirebaseController Instance;
    private FirebaseController() {
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

    }

    public synchronized static FirebaseController getInstance() {
        if (Instance == null) {
            Instance = new FirebaseController();
        }
        return Instance;
    }


    // authentic
    private FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    private FirebaseAuth getAuth(){
        return firebaseAuth;
    }
    // fire-store

    public FirebaseFirestore getDatabase() {
        return database;
    }
}
