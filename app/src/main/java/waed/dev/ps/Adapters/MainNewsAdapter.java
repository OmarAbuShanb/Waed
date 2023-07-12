package waed.dev.ps.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.News;
import waed.dev.ps.Screens.NewsDetails;
import waed.dev.ps.databinding.NewsItemBinding;

public class MainNewsAdapter extends RecyclerView.Adapter<MainNewsAdapter.newsHolder> {
    ArrayList<News> news;

    public MainNewsAdapter(ArrayList<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public newsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding binding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new newsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull newsHolder holder, int position) {
        News model = news.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    static class newsHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;
        private final Context context;

        public newsHolder(@NonNull NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        void bind(News model) {
            binding.newsTitle.setText(model.getTitle());
            binding.newsImage.setImageResource(model.getImageUrl());

            binding.newsCard.setOnClickListener(v -> {
                Intent details = new Intent(context, NewsDetails.class);
                details.putExtra("news_image", model.getImageUrl());
                details.putExtra("news_title", model.getTitle());
                details.putExtra("news_details", model.getDetails());

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<>(v, ViewCompat.getTransitionName(v));
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs);
                context.startActivity(details, optionsCompat.toBundle());
            });
        }
    }
}
