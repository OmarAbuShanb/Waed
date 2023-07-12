package waed.dev.ps.Screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import waed.dev.ps.R;
import waed.dev.ps.databinding.FragmentSearchBinding;

public class Search extends Fragment {
    FragmentSearchBinding binding;

    public Search() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}