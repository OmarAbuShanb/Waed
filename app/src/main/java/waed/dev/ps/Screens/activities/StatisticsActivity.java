package waed.dev.ps.Screens.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import waed.dev.ps.databinding.ActivityStatisticsBinding;

public class StatisticsActivity extends AppCompatActivity {
    private ActivityStatisticsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}