package waed.dev.ps.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.PrisonerCard;
import waed.dev.ps.Screens.PrisonerDetailsActivity;
import waed.dev.ps.databinding.PrisonerCardItemBinding;

public class PrisonersCardsAdapter extends RecyclerView.Adapter<PrisonersCardsAdapter.viewHolder> {
    ArrayList<PrisonerCard> prisonerCards;

    public PrisonersCardsAdapter(ArrayList<PrisonerCard> prisonerCards) {
        this.prisonerCards = prisonerCards;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PrisonerCardItemBinding binding
                = PrisonerCardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PrisonerCard model = prisonerCards.get(position);
        holder.bind(model);
//        holder.itemView.setOnClickListener = binding.getRoot().setOnClickListener
    }

    @Override
    public int getItemCount() {
        return prisonerCards.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        private final PrisonerCardItemBinding binding;
        private final Context context;

        public viewHolder(@NonNull PrisonerCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        void bind(PrisonerCard model) {
            binding.prisonerImage.setImageResource(model.getImageUrl());
            binding.prisonerName.setText(model.getName());

            binding.getRoot().setOnClickListener(v -> {
                Intent details = new Intent(context, PrisonerDetailsActivity.class);
                details.putExtra("prisoner_image", model.getImageUrl());
                details.putExtra("prisoner_name", model.getName());
                details.putExtra("prisoner_arrest_date", model.getDateOfArrest());
                details.putExtra("prisoner_judgment", model.getJudgment());
                details.putExtra("prisoner_living", model.getLiving());
                context.startActivity(details);
            });
        }
    }
}
