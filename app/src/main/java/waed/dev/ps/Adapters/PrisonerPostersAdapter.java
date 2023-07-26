package waed.dev.ps.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.Poster;
import waed.dev.ps.Utils.UtilsGeneral;
import waed.dev.ps.databinding.ItemPosterBinding;


public class PrisonerPostersAdapter extends RecyclerView.Adapter<PrisonerPostersAdapter.PosterViewHolder> {
    private ArrayList<Poster> posters;

    public PrisonerPostersAdapter(ArrayList<Poster> posters) {
        this.posters = posters;
    }

    public void setPosters(ArrayList<Poster> posters) {
        this.posters = posters;
        notifyItemRangeInserted(0, posters.size());
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPosterBinding binding
                = ItemPosterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PosterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        Poster poster = posters.get(position);
        holder.bind(poster);
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    static class PosterViewHolder extends RecyclerView.ViewHolder {
        private final ItemPosterBinding binding;
        private final Context context;

        public PosterViewHolder(ItemPosterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        public void bind(Poster poster) {
            UtilsGeneral.getInstance()
                    .loadImage(context, poster.getImageUrl())
                    .fitCenter()
                    .into(binding.ivPoster);
        }
    }
}