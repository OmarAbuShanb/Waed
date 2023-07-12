package waed.dev.ps.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.Book;
import waed.dev.ps.databinding.ItemBooksBinding;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.viewHolder> {
    ArrayList<Book> books;

    public BooksAdapter(ArrayList<Book> books ) {
        this.books = books;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBooksBinding binding =
                ItemBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Book model = books.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        private final ItemBooksBinding binding;
        private final Context context;

        public viewHolder(@NonNull ItemBooksBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        void bind(Book model) {
            binding.bookImage.setImageResource(model.getImageUrl());
            binding.bookName.setText(model.getName());
            binding.bookAuthor.setText(model.getAuthor());
        }
    }
}
