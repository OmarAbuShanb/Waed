package waed.dev.ps.Screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import waed.dev.ps.databinding.FragmentContactBinding;

public class Contact extends Fragment {

    public Contact() {
    }

    FragmentContactBinding binding;
    final static String FACEBOOK_URL = "https://www.facebook.com/waed.palestine/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactBinding.inflate(getLayoutInflater());

        binding.facebook.setOnClickListener(view -> openFaceBookUrl());

        return binding.getRoot();
    }

    void openFaceBookUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(FACEBOOK_URL));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}