package waed.dev.ps.Screens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.NewsAdapter;
import waed.dev.ps.Models.News;
import waed.dev.ps.Screens.activities.BooksActivity;
import waed.dev.ps.Screens.activities.CardsActivity;
import waed.dev.ps.Screens.activities.DesignsActivity;
import waed.dev.ps.Screens.activities.NewsDetails;
import waed.dev.ps.Screens.activities.StatisticsActivity;
import waed.dev.ps.databinding.FragmentHomeBinding;
import waed.dev.ps.firebase.controller.FirebaseController;

public class HomeFragment extends Fragment implements NewsAdapter.NewsListListener {
    private FragmentHomeBinding binding;

    private FirebaseController firebaseController;

    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        firebaseController = FirebaseController.getInstance();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.newsShimmerView.startShimmer();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        setupListener();
        argentAddValueEventListener();
        setupNewsAdapter();
        getNews();
    }

    private void argentAddValueEventListener() {
        binding.importantNews.setText("جار الاتصال");
        firebaseController.argentAddValueEventListener(new FirebaseController.GetUrgentCallback() {
            @Override
            public void onSuccess(String newsText) {
                binding.importantNews.setText(newsText);
                new Handler().postDelayed(() -> binding.importantNews.setSelected(true), 1500);
            }

            @Override
            public void onFailure(String errorMessage) {
                binding.importantNews.setText("حدث خطأ ما");
            }
        });
    }

    private void getNews() {
        binding.progressNews.setVisibility(View.VISIBLE);
        //.. following the MVC Concept by fetching the data within the controller
        firebaseController.getNews(new FirebaseController.GetNewsCallback() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                // remove the shimmer
                removeShimmer();
                // progress
                binding.progressNews.setVisibility(View.GONE);
                updateNewsAdapter(news);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    private void removeShimmer() {
        binding.newsShimmerView.stopShimmer();
        binding.newsShimmerView.setVisibility(View.GONE);
    }

    private void setupNewsAdapter() {
        newsAdapter = new NewsAdapter(new ArrayList<>());
        binding.newsRecyclerView.setAdapter(newsAdapter);
        LinearLayoutManager manager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        binding.newsRecyclerView.setLayoutManager(manager);
        binding.newsRecyclerView.setHasFixedSize(true);
    }

    private void updateNewsAdapter(ArrayList<News> models) {
        newsAdapter.setNews(models);
        newsAdapter.setNewsListCallback(this);
    }

    private void setupListener() {
        binding.statisticsCard.setOnClickListener(view ->
                startActivity(new Intent(getActivity(), StatisticsActivity.class)));

        binding.booksCard.setOnClickListener(view ->
                startActivity(new Intent(getActivity(), BooksActivity.class)));

        binding.cardsCard.setOnClickListener(view ->
                startActivity(new Intent(getActivity(), CardsActivity.class)));

        binding.postersCard.setOnClickListener(view ->
                startActivity(new Intent(getActivity(), DesignsActivity.class)));
    }

    @Override
    public void onClickItemListener(News model) {
        Intent detailsIntent = new Intent(getActivity(), NewsDetails.class);
        detailsIntent.putExtra("news_image", model.getImageUrl());
        detailsIntent.putExtra("news_title", model.getTitle());
        detailsIntent.putExtra("news_details", model.getDetails());
        requireContext().startActivity(detailsIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        firebaseController.argentRemoveEventListener();
    }
}