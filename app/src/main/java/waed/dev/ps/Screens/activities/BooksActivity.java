package waed.dev.ps.Screens.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.BooksAdapter;
import waed.dev.ps.Models.Book;
import waed.dev.ps.R;
import waed.dev.ps.databinding.ActivityBooksBinding;

public class BooksActivity extends AppCompatActivity {
    private ActivityBooksBinding binding;

    BooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));
        books.add(new Book("hbjtr4", R.drawable.test_image_book, "العاصي", "سائد سلامة"));

        booksAdapter = new BooksAdapter(books);
        binding.booksRecyclerView.setAdapter(booksAdapter);
        binding.booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.booksRecyclerView.setHasFixedSize(true);
    }
}