package waed.dev.ps.Screens.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import waed.dev.ps.databinding.ActivityPrisonerDetailsBinding;

public class PrisonerDetailsActivity extends AppCompatActivity {

    private ActivityPrisonerDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrisonerDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int image = getIntent().getIntExtra("prisoner_image", 0);
        String name = getIntent().getStringExtra("prisoner_name");
        String date = getIntent().getStringExtra("prisoner_arrest_date");
        String judgment = getIntent().getStringExtra("prisoner_judgment");
        String living = getIntent().getStringExtra("prisoner_living");

        binding.imageView.setImageResource(image);
        binding.nameView.setText(name);
        binding.dateOfArrestView.setText(date);
        binding.judgmentView.setText(judgment);
        binding.livingView.setText(living);
    }
}