package waed.dev.ps.Screens.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import waed.dev.ps.Adapters.PostersAdapter;
import waed.dev.ps.R;
import waed.dev.ps.Utils.RotationPageTransformer;
import waed.dev.ps.databinding.ActivityDesignsBinding;

public class DesignsActivity extends AppCompatActivity {
    private ActivityDesignsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDesignsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Integer> postersImage = new ArrayList<>();
        postersImage.add(R.drawable.temp_poster_1);
        postersImage.add(R.drawable.temp_poster_2);
        postersImage.add(R.drawable.temp_poster_3);
        postersImage.add(R.drawable.temp_poster_4);

        binding.postersPager.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        binding.postersPager.setPageTransformer(new RotationPageTransformer(160));
        binding.postersPager.setAdapter(new PostersAdapter(postersImage));
    }
}