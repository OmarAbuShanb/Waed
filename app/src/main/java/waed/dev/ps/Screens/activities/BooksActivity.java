package waed.dev.ps.Screens.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.PrisonerBooksAdapter;
import waed.dev.ps.Models.Book;
import waed.dev.ps.databinding.ActivityBooksBinding;
import waed.dev.ps.firebase.controller.FirebaseController;

public class BooksActivity extends AppCompatActivity implements PrisonerBooksAdapter.PrisonerBooksListListener {
    private ActivityBooksBinding binding;

    private FirebaseController firebaseController;
    private PrisonerBooksAdapter prisonerBooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        firebaseController = FirebaseController.getInstance();

        setupBooksAdapter();
        getBooks();
    }

    private void getBooks() {
        binding.progressBooks.setVisibility(View.VISIBLE);
        firebaseController.getBooks(new FirebaseController.GetBooksCallback() {
            @Override
            public void onSuccess(ArrayList<Book> books) {
                binding.progressBooks.setVisibility(View.GONE);
                updateBooksAdapter(books);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    private void setupBooksAdapter() {
        prisonerBooksAdapter = new PrisonerBooksAdapter(new ArrayList<>());
        binding.booksRecyclerView.setAdapter(prisonerBooksAdapter);
        binding.booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.booksRecyclerView.setHasFixedSize(true);
    }

    private void updateBooksAdapter(ArrayList<Book> models) {
        prisonerBooksAdapter.setBooks(models);
        prisonerBooksAdapter.setPrisonerBooksListListener(this);
    }

    @Override
    public void onClickItemListener(Book model) {
        startActivity(new Intent(getBaseContext(), PdfActivity.class));
    }
}