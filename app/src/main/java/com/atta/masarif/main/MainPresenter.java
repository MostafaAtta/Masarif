package com.atta.masarif.main;

import android.content.Context;

import com.atta.masarif.model.APIService;
import com.atta.masarif.model.APIUrl;
import com.atta.masarif.model.Payment;
import com.atta.masarif.model.Payments;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    private Context mContext;

    public MainPresenter(MainContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getPayments() {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new User(name, email, password, phone, birthdayString, locationSting);

        //defining the call
        Call<Payments> call = service.getPayments();

        //calling the api
        call.enqueue(new Callback<Payments>() {
            @Override
            public void onResponse(Call<Payments> call, Response<Payments> response) {

                if (response.body() != null){
                    if (response.body().getPayments() != null){

                        ArrayList<Payment> payments = response.body().getPayments();

                        if (payments.size() > 0){

                            mView.showRecyclerView(payments);
                        }else {

                            mView.showMessage("No Payments added");
                        }

                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<Payments> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }

}
