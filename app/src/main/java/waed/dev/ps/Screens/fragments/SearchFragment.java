package waed.dev.ps.Screens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.PrisonerBooksAdapter;
import waed.dev.ps.Adapters.PrisonerCardAdapter;
import waed.dev.ps.Models.Book;
import waed.dev.ps.Models.PrisonerCard;
import waed.dev.ps.Screens.activities.PdfActivity;
import waed.dev.ps.Screens.activities.PrisonerDetailsActivity;
import waed.dev.ps.databinding.FragmentSearchBinding;
import waed.dev.ps.firebase.controller.FirebaseController;

public class SearchFragment extends Fragment implements TextWatcher,
        PrisonerBooksAdapter.PrisonerBooksListListener,
        PrisonerCardAdapter.PrisonersCardsListListener {

    private FragmentSearchBinding binding;

    private FirebaseController firebaseController;
    private PrisonerBooksAdapter prisonerBooksAdapter;
    private PrisonerCardAdapter prisonerCardAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        firebaseController = FirebaseController.getInstance();
        binding.etSearchView.addTextChangedListener(this);

        setupPrisonerCardsAdapter();
        setupBooksAdapter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            searchPrisoners(String.valueOf(s));
            searchBooks(String.valueOf(s));
        } else {
            updateBooksAdapter(new ArrayList<>());
            updatePrisonersCardsAdapter(new ArrayList<>());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void searchBooks(String bookName) {
        firebaseController.searchBooks(bookName, new FirebaseController.GetBooksCallback() {
            @Override
            public void onSuccess(ArrayList<Book> books) {
                updateBooksAdapter(books);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    private void searchPrisoners(String prisonerName) {
        firebaseController.searchPrisoners(prisonerName, new FirebaseController.GetPrisonerCardsCallback() {
            @Override
            public void onSuccess(ArrayList<PrisonerCard> prisonerCards) {
                updatePrisonersCardsAdapter(prisonerCards);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    private void setupBooksAdapter() {
        prisonerBooksAdapter = new PrisonerBooksAdapter(new ArrayList<>());
        binding.recyclerBooksSearch.setAdapter(prisonerBooksAdapter);
        binding.recyclerBooksSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerBooksSearch.setHasFixedSize(true);
    }

    private void updateBooksAdapter(ArrayList<Book> models) {
        prisonerBooksAdapter.setBooks(models);
        prisonerBooksAdapter.setPrisonerBooksListListener(this);
    }

    @Override
    public void onClickItemListener(Book model) {
        startActivity(new Intent(getContext(), PdfActivity.class));
    }

    private void setupPrisonerCardsAdapter() {
        prisonerCardAdapter = new PrisonerCardAdapter(new ArrayList<>());
        binding.recyclerPrisonersSearch.setAdapter(prisonerCardAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false
        );
        binding.recyclerPrisonersSearch.setLayoutManager(manager);
        binding.recyclerPrisonersSearch.setHasFixedSize(true);
    }

    private void updatePrisonersCardsAdapter(ArrayList<PrisonerCard> models) {
        prisonerCardAdapter.setPrisonerCards(models);
        prisonerCardAdapter.setNewsListCallback(this);
    }

    @Override
    public void onClickItemListener(PrisonerCard model) {
        Intent intent = new Intent(getContext(), PrisonerDetailsActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
}