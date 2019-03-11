package sinc.com.ssgmartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sinc.com.ssgmartapp.helper.Common;
import sinc.com.ssgmartapp.remote.RequestService;

/**
 * 로그인
 */
public class LoginActivity extends AppCompatActivity {

    private EditText id_editText;
    private EditText pw_editText;
    private Button loginButton;
    RequestService mService;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id_editText = findViewById(R.id.login_editText);
        pw_editText = findViewById(R.id.pw_editText);
        loginButton = findViewById(R.id.login_button);

        mService = Common.getLoginService();
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(id_editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "아이디 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else if(pw_editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호 입력해주세요", Toast.LENGTH_LONG).show();
                }else{
                    loginCheck(id_editText.getText().toString(), pw_editText.getText().toString());
                }
            }
        });


    }

    private void signIn(String email, String password) {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                            loginCheck(id_editText.getText().toString(), pw_editText.getText().toString());

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void loginCheck(String username, String password) {

        mService.userLogin(username, password).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "개인 정보 여부 일치하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }


}
