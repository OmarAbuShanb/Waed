package waed.dev.ps.Screens.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import waed.dev.ps.databinding.ActivitySupportBinding;

public class SupportActivity extends AppCompatActivity {
    private ActivitySupportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupListeners();
    }
    private void setupListeners(){
        binding.buttonSend.setOnClickListener(v -> {
            String problem = binding.problemInput.getText().toString().trim();
            if (!problem.isEmpty()) {
                sendEmailMassage(problem);
            } else {
                Toast.makeText(this, "اخبرنا بمشكلتك", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void sendEmailMassage(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"22001177oo@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "الدعم الفني تطبيق حرية");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Intent chooser = Intent.createChooser(intent, null);
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooser);
        finish();
    }
}