package shenkar.koruApps.PackChetApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenActivity extends AppCompatActivity {
    private static final int SIGN_IN_REQUEST_CODE = 111;
    private static String loggedInUserName = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);
        intent = new Intent(this,shenkar.koruApps.PackChetApp.GroupsActivity.class);
        Intent intent = new Intent(this,GroupsActivity.class);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser temp = firebaseAuth.getCurrentUser();
        if (firebaseAuth.getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(), SIGN_IN_REQUEST_CODE);
        } else {
            // User is already signed in, show list of messages
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in successful!", Toast.LENGTH_LONG).show();
                startActivity(intent);


            } else {
                Toast.makeText(this, "Sign in failed, please try again later", Toast.LENGTH_LONG).show();

                // Close the app
                finish();
            }
        }
    }

    public static String getLoggedInUserName() {
        return loggedInUserName;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FirebaseAuth.getInstance().getCurrentUser() !=null){
            startActivity(intent);
        }
    }
}
