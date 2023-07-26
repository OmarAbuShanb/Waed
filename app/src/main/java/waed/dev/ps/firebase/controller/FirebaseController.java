package waed.dev.ps.firebase.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import waed.dev.ps.Models.Book;
import waed.dev.ps.Models.News;
import waed.dev.ps.Models.Poster;
import waed.dev.ps.Models.PrisonerCard;

public class FirebaseController {
    private static final String TAG = "FirebaseController";

    private final FirebaseDatabase realTimeDatabase;
    private final FirebaseFirestore fireStoreDatabase;
    private final FirebaseStorage firebaseStorage;
    private final FirebaseMessaging firebaseMessaging;

    private static volatile FirebaseController Instance;

    private FirebaseController() {
        realTimeDatabase = FirebaseDatabase.getInstance();
        fireStoreDatabase = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    public synchronized static FirebaseController getInstance() {
        if (Instance == null) {
            Instance = new FirebaseController();
        }
        return Instance;
    }

    // Realtime
    private DatabaseReference argentRef;
    private ValueEventListener argentEventListener;

    public void argentAddValueEventListener(GetUrgentCallback callback) {
        argentRef = realTimeDatabase.getReference("Urgent").child("newsText");
        argentEventListener = argentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                callback.onSuccess(String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onFailure(error.getMessage());
            }
        });
    }

    public void argentRemoveEventListener() {
        if (argentRef != null && argentEventListener != null) {
            argentRef.removeEventListener(argentEventListener);
        }
    }

    public void getStatistics(GetStatisticsCallback callback) {
        realTimeDatabase.getReference("Statistics")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, String> mapData = new HashMap<>();
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            mapData.put(snapshot.getKey(), String.valueOf(snapshot.getValue()));
                        }
                        callback.onSuccess(mapData);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "getStatistics: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    // FireStore
    public void getNews(GetNewsCallback callback) {
        fireStoreDatabase.collection("News")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<News> news = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            news.add(documentSnapshot.toObject(News.class));
                        }
                        callback.onSuccess(news);
                    }
                }).addOnFailureListener(e -> {
                    Log.e(TAG, "getNews: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    public void getBooks(GetBooksCallback callback) {
        fireStoreDatabase.collection("Books")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<Book> books = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            books.add(documentSnapshot.toObject(Book.class));
                        }
                        callback.onSuccess(books);
                    }
                }).addOnFailureListener(e -> {
                    Log.e(TAG, "getBooks: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    public void getPrisonersCards(GetPrisonerCardsCallback callback) {
        fireStoreDatabase.collection("PrisonersCards")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<PrisonerCard> prisonerCards = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            prisonerCards.add(documentSnapshot.toObject(PrisonerCard.class));
                        }
                        callback.onSuccess(prisonerCards);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "getPrisonersCards: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    public void getPosters(GetPostersCallback callback) {
        fireStoreDatabase.collection("Posters")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<Poster> posters = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            posters.add(documentSnapshot.toObject(Poster.class));
                        }
                        callback.onSuccess(posters);
                    }
                }).addOnFailureListener(e -> {
                    Log.e(TAG, "getPosters: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    public void searchBooks(String bookName, GetBooksCallback callback) {
        fireStoreDatabase.collection("Books")
                .where(Filter.greaterThanOrEqualTo("name", bookName))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<Book> books = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            books.add(documentSnapshot.toObject(Book.class));
                        }
                        callback.onSuccess(books);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "getBooks: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    public void searchPrisoners(String prisonerName, GetPrisonerCardsCallback callback) {
        fireStoreDatabase.collection("PrisonersCards")
                .where(Filter.greaterThanOrEqualTo("name", prisonerName))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<PrisonerCard> prisonerCards = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            prisonerCards.add(documentSnapshot.toObject(PrisonerCard.class));
                        }
                        callback.onSuccess(prisonerCards);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "getBooks: " + e.getMessage());
                    callback.onFailure(e.getMessage());
                });
    }

    public void downloadPdf(String pdfUrl, File destinationFile, DownloadFileCallback callback) {
        StorageReference urlRef = firebaseStorage.getReferenceFromUrl(pdfUrl);
        urlRef.getFile(destinationFile)
                .addOnProgressListener(snapshot -> {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    System.out.println(progress);
                }).addOnSuccessListener(taskSnapshot ->
                        callback.onSuccess(destinationFile.getPath()))
                .addOnFailureListener(exception -> {
                    Log.d(TAG, "downloadPdf: " + exception.getMessage());
                    callback.onFailure(exception.getMessage());
                });
    }

//    private void downloadBookFile(Context context, String docId, String pdfUrl) {
//        File storagePath = new File(context.getFilesDir(), "PDF-Books");
//        if (storagePath.exists() || storagePath.mkdirs()) {
//            final File bookFile = new File(storagePath, docId + ".pdf");
//            downloadPdf(pdfUrl, bookFile);
//        }
//    }

    // Messaging
    public void subscribeToTopic(String topic) {
        firebaseMessaging.subscribeToTopic("/topics/news")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    }
                })
                .addOnFailureListener(e ->
                        Log.e(TAG, "onCreate: " + e.getMessage()));
    }

    public void unsubscribeFromTopic(String topic) {
        firebaseMessaging.unsubscribeFromTopic("/topics/news")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    }
                })
                .addOnFailureListener(e ->
                        Log.e(TAG, "onCreate: " + e.getMessage()));
    }

    /* Interfaces------------------- */

    public interface FirebaseCallback {
        void onSuccess();

        void onFailure(String errorMessage);
    }

    public interface GetUrgentCallback {
        void onSuccess(String newsText);

        void onFailure(String errorMessage);
    }

    public interface GetBooksCallback {
        void onSuccess(ArrayList<Book> books);

        void onFailure(String errorMessage);
    }

    public interface GetNewsCallback {
        void onSuccess(ArrayList<News> news);

        void onFailure(String errorMessage);
    }

    public interface GetPrisonerCardsCallback {
        void onSuccess(ArrayList<PrisonerCard> prisonerCards);

        void onFailure(String errorMessage);
    }

    public interface GetPostersCallback {
        void onSuccess(ArrayList<Poster> posters);

        void onFailure(String errorMessage);
    }

    public interface GetStatisticsCallback {
        void onSuccess(Map<String, String> statistics);

        void onFailure(String errorMessage);
    }

    public interface DownloadFileCallback {
        void onSuccess(String url);

        void onFailure(String errorMessage);
    }
}
