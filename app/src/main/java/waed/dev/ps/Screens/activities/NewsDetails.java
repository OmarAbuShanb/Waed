package waed.dev.ps.Screens.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import waed.dev.ps.Utils.UtilsGeneral;
import waed.dev.ps.databinding.ActivityNewsDetailsBinding;

public class NewsDetails extends AppCompatActivity {
    private ActivityNewsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UtilsGeneral.getInstance()
                .loadImage(this, Objects.requireNonNull(getIntent().getStringExtra("news_image")))
                .into(binding.newsImage);

        binding.newsTitle.setText(getIntent().getStringExtra("news_title"));
        binding.newsDetails.setText(getIntent().getStringExtra("news_details"));
    }
}