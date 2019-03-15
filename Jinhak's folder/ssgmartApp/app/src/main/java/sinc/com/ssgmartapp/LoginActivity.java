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
import sinc.com.ssgmartapp.helper.Util;
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
    //현재 로그인 된 유저 정보를 담을 변수
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Util.setGlobalFont(this, getWindow().getDecorView());

        id_editText = findViewById(R.id.login_editText);
        pw_editText = findViewById(R.id.pw_editText);
        loginButton = findViewById(R.id.login_button);

        mService = Common.getUrlService();
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id_editText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디 입력해주세요", Toast.LENGTH_LONG).show();
                } else if (pw_editText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("click", "LoginButton");
                    signIn(id_editText.getText().toString(), pw_editText.getText().toString());
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

                            loginCheck(id_editText.getText().toString(), pw_editText.getText().toString());

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "인증 실패",
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

//    //로그아웃 안했으면, 즉 로그인 되어있으면 자동으로 메인페이지로 이동시키기
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
//            finish();
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
