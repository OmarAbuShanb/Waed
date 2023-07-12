package waed.dev.ps.Screens;

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
import waed.dev.ps.databinding.FragmentMoreBinding;

public class More extends Fragment {
    FragmentMoreBinding binding;

    public More() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(getLayoutInflater());

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
            startActivity(new Intent(getActivity(), Support.class));
        });

        binding.aboutUs.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), AboutUs.class));
        });

        return binding.getRoot();
    }
}