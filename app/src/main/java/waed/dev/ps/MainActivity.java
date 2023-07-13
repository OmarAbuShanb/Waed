package waed.dev.ps;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;

import com.google.firebase.messaging.FirebaseMessaging;

import waed.dev.ps.Screens.fragments.ContactFragment;
import waed.dev.ps.Screens.fragments.HomeFragment;
import waed.dev.ps.Screens.fragments.MoreFragment;
import waed.dev.ps.Screens.fragments.SearchFragment;
import waed.dev.ps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseMessaging.getInstance()
                .subscribeToTopic("/topics/news")
                .addOnCompleteListener(task -> {
                    System.out.println(task.isSuccessful());
                    String msg = "Done";
                    if (!task.isSuccessful()) {
                        msg = "Failed";
                    }
                    Log.e(TAG, "onCreate: " + msg);
                });

        binding.bottomVanView.getMenu()
                .findItem(R.id.nav_home)
                .setChecked(true);

        HomeFragment homeFragment = new HomeFragment();
        SearchFragment searchFragment = new SearchFragment();
        ContactFragment contactFragment = new ContactFragment();
        MoreFragment moreFragment = new MoreFragment();

        sitFragment(homeFragment, R.string.homeFragment);

        binding.bottomVanView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                sitFragment(homeFragment, R.string.homeFragment);
            } else if (item.getItemId() == R.id.nav_search) {
                sitFragment(searchFragment, R.string.searchFragment);
            } else if (item.getItemId() == R.id.nav_contact) {
                sitFragment(contactFragment, R.string.connect_with_us);
            } else {
                // item.getItemId() == R.id.nav_more
                sitFragment(moreFragment, R.string.moreFragment);
            }
            return true;
        });
    }

    void sitFragment(Fragment fragment, int resIdAppBarTitle) {
        binding.headContentName.setText(getString(resIdAppBarTitle));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content_screens, fragment)
                .commit();
    }
}