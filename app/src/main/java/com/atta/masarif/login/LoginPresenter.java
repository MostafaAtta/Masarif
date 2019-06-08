package com.atta.masarif.login;

import android.content.Context;

import com.atta.masarif.model.APIService;
import com.atta.masarif.model.APIUrl;
import com.atta.masarif.model.Result;
import com.atta.masarif.model.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;

    private Context mContext;

    public LoginPresenter(LoginContract.View view, Context context) {

        mView = view;

        mContext = context;
    }

    @Override
    public void login(String email, String password) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.userLogin(email, password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {


                mView.dismissProgressDialog();
                if (!response.body().getError()) {
                    mView.showMessage();
                    SessionManager.getInstance(mContext).createLoginSession(response.body().getUser());
                    mView.navigateToMain();
                } else {

                    mView.showError("Invalid email or password");
                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {


                mView.dismissProgressDialog();

                mView.showError(t.getMessage());



            }
        });

    }

    @Override
    public boolean validate(String userName, String password) {



        boolean valid = true;



        if (userName.isEmpty())
        {
            mView.showError("Invalid userName");
            mView.showViewError("userName","Invalid userName");
            valid = false;

        }else {

            mView.showViewError("userName",null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mView.showError("password must be between 4 and 10 alphanumeric characters");
            mView.showViewError("password","password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mView.showViewError("password",null);
        }



        return valid;
    }


}
