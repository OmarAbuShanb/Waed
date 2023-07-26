package waed.dev.ps.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import waed.dev.ps.Models.Book;
import waed.dev.ps.Utils.UtilsGeneral;
import waed.dev.ps.databinding.ItemBooksBinding;

public class PrisonerBooksAdapter extends RecyclerView.Adapter<PrisonerBooksAdapter.viewHolder> {
    private ArrayList<Book> books;
    private PrisonerBooksListListener prisonerBooksListListener;

    public void setPrisonerBooksListListener(PrisonerBooksListListener prisonerBooksListListener) {
        this.prisonerBooksListListener = prisonerBooksListListener;
    }

    public PrisonerBooksAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
//        notifyItemRangeInserted(0, books.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBooksBinding binding
                = ItemBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Book model = books.get(position);
        holder.bind(model);
        holder.setPrisonerBooksListListener(prisonerBooksListListener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        private final ItemBooksBinding binding;
        private final Context context;

        private PrisonerBooksListListener prisonerBooksListListener;

        protected void setPrisonerBooksListListener(PrisonerBooksListListener prisonerBooksListListener) {
            this.prisonerBooksListListener = prisonerBooksListListener;
        }

        public viewHolder(@NonNull ItemBooksBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
        }

        void bind(Book model) {
            binding.bookName.setText(model.getName());
            binding.bookAuthor.setText(model.getAuthor());

            UtilsGeneral.getInstance()
                    .loadImage(context, model.getImageUrl())
                    .into(binding.bookImage);

            binding.buBookCard.setOnClickListener(v ->
                    prisonerBooksListListener.onClickItemListener(model));
        }
    }

    public interface PrisonerBooksListListener {
        void onClickItemListener(Book model);
    }
}
