package waed.dev.ps.Screens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.PrisonersCardsAdapter;
import waed.dev.ps.Models.PrisonerCard;
import waed.dev.ps.R;
import waed.dev.ps.databinding.ActivityCardsBinding;

public class CardsActivity extends AppCompatActivity {
    ActivityCardsBinding binding;
    PrisonersCardsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<PrisonerCard> models = new ArrayList<>();
        models.add(new PrisonerCard("1", R.drawable.temp_card_1, "محمد الحلبي", "30/12/2016", "Kill 5 terrorists", "Gaza, Palestine"));
        models.add(new PrisonerCard("2", R.drawable.temp_card_2, "خضر عدنان", "30/12/2016", "Kill 5 terrorists", "Gaza, Palestine"));
        models.add(new PrisonerCard("3", R.drawable.temp_card_3, "أحمد رزق الزهار", "30/12/2016", "Kill 5 terrorists", "Gaza, Palestine"));
        models.add(new PrisonerCard("4", R.drawable.temp_card_4, "وليد دقة", "30/12/2016", "Kill 5 terrorists", "Gaza, Palestine"));

        adapter = new PrisonersCardsAdapter(models);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        binding.prisonersCardsRecyclerView.setLayoutManager(manager);
        binding.prisonersCardsRecyclerView.setAdapter(adapter);
        binding.prisonersCardsRecyclerView.setHasFixedSize(true);
    }
}