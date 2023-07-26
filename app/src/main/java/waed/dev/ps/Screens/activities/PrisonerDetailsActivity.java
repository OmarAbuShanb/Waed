package waed.dev.ps.Screens.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import waed.dev.ps.Models.PrisonerCard;
import waed.dev.ps.R;
import waed.dev.ps.Utils.UtilsGeneral;
import waed.dev.ps.databinding.ActivityPrisonerDetailsBinding;

public class PrisonerDetailsActivity extends AppCompatActivity {

    private ActivityPrisonerDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrisonerDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PrisonerCard prisonerCardModel = (PrisonerCard) getIntent().getSerializableExtra("model");
        if (prisonerCardModel != null) {
            putDate(prisonerCardModel);
        }
    }

    private void putDate(PrisonerCard model) {
        UtilsGeneral.getInstance()
                .loadImage(this, model.getImageUrl())
                .into(binding.imageView);

        binding.nameView.setText(model.getName());

        binding.dateOfArrestView.setText(model.getDateOfArrest());
        binding.dateOfArrestView.setTextColor(getResources().getColor(R.color.black));

        binding.judgmentView.setText(model.getJudgment());
        binding.livingView.setText(model.getLiving());
    }
}