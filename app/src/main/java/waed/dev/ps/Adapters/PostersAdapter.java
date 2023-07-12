package waed.dev.ps.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.databinding.ItemPosterBinding;


public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.PosterViewHolder> {
    final ArrayList<Integer> postersImage;

    public PostersAdapter(ArrayList<Integer> postersImage) {
        this.postersImage = postersImage;
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
        holder.bind(postersImage.get(position));
    }

    @Override
    public int getItemCount() {
        return postersImage.size();
    }

    static class PosterViewHolder extends RecyclerView.ViewHolder {
        private final ItemPosterBinding binding;
        private final Context context;

        public PosterViewHolder(ItemPosterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        public void bind(Integer integer) {
            binding.ivPoster.setImageResource(integer);
        }
    }
}