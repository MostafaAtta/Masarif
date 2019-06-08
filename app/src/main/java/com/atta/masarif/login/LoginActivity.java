package com.atta.masarif.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atta.masarif.R;
import com.atta.masarif.main.MainActivity;
import com.atta.masarif.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    ProgressDialog progressDialog;

    // login button
    Button login;

    // National ID, password edit text
    EditText emailText,passwordText;

    TextView newAccount;


    private CheckBox show_hide_password;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setDialog();
        loginPresenter = new LoginPresenter(this, this);

        initiateViews();
    }


    private void initiateViews() {
        // National ID, Password input text
        emailText = findViewById(R.id.userName);
        passwordText = findViewById(R.id.password);

        newAccount = findViewById(R.id.btnRegisterScreen);
        newAccount.setOnClickListener(this);

        // Login button
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        show_hide_password = (CheckBox) findViewById(R.id.show_hide_password);
        show_hide_password.setOnCheckedChangeListener(this);
    }

    @Override
    public void showError(String error) {

        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
        login.setEnabled(true);
    }

    @Override
    public void showViewError(String view, String error) {

        int id = getResources().getIdentifier(view, "id", this.getPackageName());
        EditText editText = (EditText)findViewById(id);
        editText.setError(error);
    }

    @Override
    public void showMessage() {

        Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToMain() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void dismissProgressDialog() {
        if(progressDialog != null || progressDialog.isShowing() ){
            progressDialog.dismiss();
        }
    }

    @Override
    public void navigateToRegister() {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setDialog() {

        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
    }

    @Override
    public void onClick(View view) {
        if(view == login) {
            if (!loginPresenter.validate(emailText.getText().toString().trim(), passwordText.getText().toString())) {
                showError("Invalid Login details");
                return;
            }

            progressDialog.show();
            loginPresenter.login(emailText.getText().toString(),passwordText.getText().toString());
        }else if (view == newAccount){

            navigateToRegister();

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // If it is checked then show password else hide
        // password
        if (isChecked) {

            show_hide_password.setText(R.string.hide_pwd);// change
            // checkbox
            // text

            passwordText.setInputType(InputType.TYPE_CLASS_TEXT);
            passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());// show password
        } else {
            show_hide_password.setText(R.string.show_pwd);// change
            // checkbox
            // text

            passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());// hide password

        }
    }
}
