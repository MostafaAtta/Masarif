package com.atta.masarif.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.atta.masarif.R;
import com.atta.masarif.main.MainActivity;
import com.atta.masarif.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterContract.View {



    ProgressDialog progressDialog;
    // login button
    Button register;

    ArrayAdapter<String> locationsAdapter;
    // National ID, password edit text
    EditText passwordText, nameText, confirmPasswordText;


    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setDialog();
        registerPresenter = new RegisterPresenter(this, progressDialog, this);

        initiateViews();
    }

    private void initiateViews() {

        // National ID, Password input text
        passwordText = findViewById(R.id.password);
        nameText = findViewById(R.id.name);
        confirmPasswordText = findViewById(R.id.password_confirm);

        // Register button
        register = findViewById(R.id.btnRegister);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == register) {
            if (!validate(nameText.getText().toString(), passwordText.getText().toString(),
                    confirmPasswordText.getText().toString())) {
                register.setEnabled(true);
                return;
            }


            progressDialog.show();

            //Defining the user object as we need to pass it with the call
            User user = new User(nameText.getText().toString(), passwordText.getText().toString());


            registerPresenter.register(user);

        }
    }

    @Override
    public void showMessage(String error) {

        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showViewError(String view, String error) {

        int id = getResources().getIdentifier(view, "id", this.getPackageName());
        EditText editText = (EditText)findViewById(id);
        editText.setError(error);
    }

    @Override
    public void navigateToMain() {

        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void setDialog() {

        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(RegisterActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating your profile...");
    }

    @Override
    public boolean validate(String name, String password, String passwordConfirm) {
        boolean valid = true;



        if (name.isEmpty()) {

            showViewError("name","Enter your name");
            valid = false;
        } else {

            showViewError("name",null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            showViewError("password","password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (passwordConfirm.isEmpty() || passwordConfirm.length() < 4 || passwordConfirm.length() > 10 ) {

            showViewError("password_confirm","password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (!password.equals(passwordConfirm)){

            showViewError("password","passwords not Matched");
            valid = false;
        }else {
            showViewError("password",null);
            showViewError("password_confirm",null);
        }





        return valid;
    }
}
