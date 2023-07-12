package waed.dev.ps.Screens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import waed.dev.ps.databinding.ActivityStatisticsBinding;

public class StatisticsActivity extends AppCompatActivity {
    ActivityStatisticsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}