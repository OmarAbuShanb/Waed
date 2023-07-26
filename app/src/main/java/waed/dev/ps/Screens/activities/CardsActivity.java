package waed.dev.ps.Screens.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.PrisonerCardAdapter;
import waed.dev.ps.Models.PrisonerCard;
import waed.dev.ps.databinding.ActivityCardsBinding;
import waed.dev.ps.firebase.controller.FirebaseController;

public class CardsActivity extends AppCompatActivity
        implements PrisonerCardAdapter.PrisonersCardsListListener {

    private ActivityCardsBinding binding;

    private FirebaseController firebaseController;
    private PrisonerCardAdapter prisonerCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        firebaseController = FirebaseController.getInstance();
        setupPrisonerCardsAdapter();
        getPrisonersCards();
    }

    private void setupPrisonerCardsAdapter() {
        prisonerCardAdapter = new PrisonerCardAdapter(new ArrayList<>());
        binding.prisonersCardsRecyclerView.setAdapter(prisonerCardAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        binding.prisonersCardsRecyclerView.setLayoutManager(manager);
        binding.prisonersCardsRecyclerView.setHasFixedSize(true);
    }

    private void updatePrisonersCardsAdapter(ArrayList<PrisonerCard> models) {
        prisonerCardAdapter.setPrisonerCards(models);
        prisonerCardAdapter.setNewsListCallback(this);
    }

    private void getPrisonersCards() {
        binding.progressPrisonersCards.setVisibility(View.VISIBLE);
        firebaseController.getPrisonersCards(new FirebaseController.GetPrisonerCardsCallback() {
            @Override
            public void onSuccess(ArrayList<PrisonerCard> prisonerCards) {
                binding.progressPrisonersCards.setVisibility(View.GONE);
                updatePrisonersCardsAdapter(prisonerCards);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void onClickItemListener(PrisonerCard model) {
        Intent intent = new Intent(getBaseContext(), PrisonerDetailsActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
}