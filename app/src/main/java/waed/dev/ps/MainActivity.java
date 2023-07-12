package waed.dev.ps;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;

import com.google.firebase.messaging.FirebaseMessaging;

import waed.dev.ps.Screens.Contact;
import waed.dev.ps.Screens.Home;
import waed.dev.ps.Screens.More;
import waed.dev.ps.Screens.Search;
import waed.dev.ps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;

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

        Home home = new Home();
        Search search = new Search();
        Contact contact = new Contact();
        More more = new More();

        sitFragment(home, R.string.home);

        binding.bottomVanView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                sitFragment(home, R.string.home);
            } else if (item.getItemId() == R.id.nav_search) {
                sitFragment(search, R.string.search);
            } else if (item.getItemId() == R.id.nav_contact) {
                sitFragment(contact, R.string.connect_with_us);
            } else {
                // item.getItemId() == R.id.nav_more
                sitFragment(more, R.string.more);
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