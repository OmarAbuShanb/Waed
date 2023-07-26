package waed.dev.ps.Screens.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import waed.dev.ps.databinding.ActivityStatisticsBinding;
import waed.dev.ps.firebase.controller.FirebaseController;

public class StatisticsActivity extends AppCompatActivity {
    private ActivityStatisticsBinding binding;

    private FirebaseController firebaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        firebaseController = FirebaseController.getInstance();
        getStatistics();
    }

    private void getStatistics() {
        binding.scrollContent.setVisibility(View.GONE);
        binding.progressStatistics.setVisibility(View.VISIBLE);

        firebaseController.getStatistics(new FirebaseController.GetStatisticsCallback() {
            @Override
            public void onSuccess(Map<String, String> statistics) {
                putData(statistics);

                binding.scrollContent.setVisibility(View.VISIBLE);
                binding.progressStatistics.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    private void putData(Map<String, String> statistics) {
        binding.tvNumOfMartyrsOfCaptiveMovement.setText(statistics.get("numberOfMartyrsOfCaptiveMovement"));
        binding.tvNumOfChildCaptives.setText(statistics.get("numberOfChildCaptives"));
        binding.tvNumOfGirlPrisonerInsidePrisons.setText(statistics.get("numberOfGirlPrisonerInsidePrisons"));
        binding.tvNumOfSickPrisoner.setText(statistics.get("numberOfSickPrisoner"));
        binding.tvNumOfPrisonersWhoSpentMoreThan30Scholars.setText(statistics.get("numberOfPrisonersWhoSpentMoreThan30Scholars"));
        binding.tvNumOfPrisonerSentencedToLife.setText(statistics.get("numberOfPrisonerSentencedToLife"));
        binding.tvNumOfPrisonerInsidePrisons.setText(statistics.get("numberOfPrisonerInsidePrisons"));
        binding.tvNumOfAdministrativeDetention.setText(statistics.get("numberOfAdministrativeDetention"));
    }
}