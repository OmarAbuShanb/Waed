package waed.dev.ps.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.News;
import waed.dev.ps.Utils.UtilsGeneral;
import waed.dev.ps.databinding.NewsItemBinding;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<News> news;

    private NewsListListener newsListListener;

    public void setNewsListCallback(NewsListListener newsListListener) {
        this.newsListListener = newsListListener;
    }

    public NewsAdapter(ArrayList<News> news) {
        this.news = news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
        notifyItemRangeInserted(0, news.size());
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding binding =
                NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News model = news.get(position);
        holder.bind(model);

        holder.setNewsListCallback(newsListListener);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;
        private final Context context;
        private NewsListListener newsListListener;

        protected void setNewsListCallback(NewsListListener newsListListener) {
            this.newsListListener = newsListListener;
        }

        public NewsViewHolder(@NonNull NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        void bind(News model) {
            binding.newsTitle.setText(model.getTitle());

            UtilsGeneral.getInstance()
                    .loadImage(context, model.getImageUrl())
                    .into(binding.ivNews);

            binding.buNewsCard.setOnClickListener(v -> newsListListener.onClickItemListener(model));
        }
    }

    public interface NewsListListener {
        void onClickItemListener(News model);
    }
}
