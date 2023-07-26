package waed.dev.ps.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.PrisonerCard;
import waed.dev.ps.Utils.UtilsGeneral;
import waed.dev.ps.databinding.PrisonerCardItemBinding;

public class PrisonerCardAdapter extends RecyclerView.Adapter<PrisonerCardAdapter.viewHolder> {
    private ArrayList<PrisonerCard> prisonerCards;
    private PrisonersCardsListListener prisonersCardsListListener;

    public void setNewsListCallback(PrisonersCardsListListener prisonersCardsListListener) {
        this.prisonersCardsListListener = prisonersCardsListListener;
    }

    public PrisonerCardAdapter(ArrayList<PrisonerCard> prisonerCards) {
        this.prisonerCards = prisonerCards;
    }

    public void setPrisonerCards(ArrayList<PrisonerCard> prisonerCards) {
        this.prisonerCards = prisonerCards;
//        notifyItemRangeInserted(0, prisonerCards.size() - 1);
        notifyDataSetChanged();
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

        holder.setNewsListCallback(prisonersCardsListListener);
    }

    @Override
    public int getItemCount() {
        return prisonerCards.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        private final PrisonerCardItemBinding binding;
        private final Context context;

        private PrisonersCardsListListener prisonersCardsListListener;

        protected void setNewsListCallback(PrisonersCardsListListener prisonersCardsListListener) {
            this.prisonersCardsListListener = prisonersCardsListListener;
        }

        public viewHolder(@NonNull PrisonerCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        void bind(PrisonerCard model) {
            binding.prisonerName.setText(model.getName());

            UtilsGeneral.getInstance()
                    .loadImage(context, model.getImageUrl())
                    .into(binding.prisonerImage);

            binding.prisonerCard.setOnClickListener(v ->
                    prisonersCardsListListener.onClickItemListener(model));
        }
    }

    public interface PrisonersCardsListListener {
        void onClickItemListener(PrisonerCard model);
    }
}
