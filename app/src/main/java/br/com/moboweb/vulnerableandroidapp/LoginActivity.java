package br.com.moboweb.vulnerableandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.com.moboweb.vulnerableandroidapp.api.VulnerableServerApi;
import br.com.moboweb.vulnerableandroidapp.data.LoginModel;
import br.com.moboweb.vulnerableandroidapp.data.MySharedPreferences;
import br.com.moboweb.vulnerableandroidapp.data.UserModel;
import br.com.moboweb.vulnerableandroidapp.event.LoginMessageEvent;
import br.com.moboweb.vulnerableandroidapp.utils.ApplicationUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class LoginActivity extends AppCompatActivity implements Callback<LoginModel> {
    private static final String LOG_TAG = "LoginActivity";
    private Retrofit mRetrofit;
    private MySharedPreferences mSharedPreferences;

    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mSharedPreferences = new MySharedPreferences(getApplicationContext());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApplicationUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void attemptLogin() {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isFieldValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isFieldValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);

            UserModel user = new UserModel(username, password);
            VulnerableServerApi serverApi = mRetrofit.create(VulnerableServerApi.class);
            Call<LoginModel> call = serverApi.userLogin(user);
            call.enqueue(this);
        }
    }

    private boolean isFieldValid(String word) {
        return word.length() > 1;
    }

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginMessageEvent event) {
        Log.d("Teste", "messa event! "+event.getMessage());
        switch(event.getMessage()) {
            case LoginMessageEvent.EVENT_CANCEL:
                showProgress(false);
                break;
            case LoginMessageEvent.EVENT_ON_ERROR:
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                break;
            case LoginMessageEvent.EVENT_ON_SUCCESS:
                Log.d("Teste", "sucesso!!!");
                finish();
                break;
            case LoginMessageEvent.EVENT_POST_EXECUTE:
                showProgress(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
        showProgress(false);
        if(response.isSuccessful()) {
            LoginModel loginObject = response.body();
            mSharedPreferences.saveApplicationToken(loginObject.token);
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Log.e(LOG_TAG, response.message());
        }
    }

    @Override
    public void onFailure(Call<LoginModel> call, Throwable t) {
        t.printStackTrace();
    }


}

