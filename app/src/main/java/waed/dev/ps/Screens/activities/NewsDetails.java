package waed.dev.ps.Screens.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import waed.dev.ps.databinding.ActivityNewsDetailsBinding;

public class NewsDetails extends AppCompatActivity {
    private ActivityNewsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.newsImage.setImageResource(getIntent().getIntExtra("news_image", 0));
        binding.newsTitle.setText(getIntent().getStringExtra("news_title"));
        binding.newsDetails.setText(getIntent().getStringExtra("news_details"));
    }
}