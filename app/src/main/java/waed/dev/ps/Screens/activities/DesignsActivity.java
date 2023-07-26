package waed.dev.ps.Screens.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import waed.dev.ps.Adapters.PrisonerPostersAdapter;
import waed.dev.ps.Models.Poster;
import waed.dev.ps.Utils.RotationPageTransformer;
import waed.dev.ps.databinding.ActivityDesignsBinding;
import waed.dev.ps.firebase.controller.FirebaseController;

public class DesignsActivity extends AppCompatActivity {
    private ActivityDesignsBinding binding;

    private FirebaseController firebaseController;
    private PrisonerPostersAdapter prisonerPostersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDesignsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        firebaseController = FirebaseController.getInstance();
        setupPrisonerPostersAdapter();
        getPosters();
    }

    private void setupPrisonerPostersAdapter() {
        prisonerPostersAdapter = new PrisonerPostersAdapter(new ArrayList<>());
        binding.postersPager.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        binding.postersPager.setPageTransformer(new RotationPageTransformer(160));
        binding.postersPager.setAdapter(prisonerPostersAdapter);
    }

    private void getPosters() {
        binding.progressPrisonersPosters.setVisibility(View.VISIBLE);
        firebaseController.getPosters(new FirebaseController.GetPostersCallback() {
            @Override
            public void onSuccess(ArrayList<Poster> posters) {
                binding.progressPrisonersPosters.setVisibility(View.GONE);
                prisonerPostersAdapter.setPosters(posters);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}