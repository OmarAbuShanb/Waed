package waed.dev.ps.Screens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import waed.dev.ps.R;
import waed.dev.ps.Screens.activities.SupportActivity;
import waed.dev.ps.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {
    private FragmentMoreBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListeners();
    }

    private void setupListeners() {
        binding.notifications.setOnClickListener(view -> {
            boolean isChecked = binding.switchNotifications.isChecked();
            binding.switchNotifications.setChecked(!isChecked);
        });

        binding.switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getActivity(), R.string.notifications_are_enabled, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.notifications_are_disabled, Toast.LENGTH_SHORT).show();
            }
        });

        binding.support.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), SupportActivity.class));
        });

        binding.aboutUs.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), AboutUsFragment.class));
        });
    }
}