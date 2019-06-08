package com.atta.masarif.newpayment;

import com.atta.masarif.model.APIService;
import com.atta.masarif.model.APIUrl;
import com.atta.masarif.model.Categories;
import com.atta.masarif.model.Payment;
import com.atta.masarif.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPaymentPresenter implements NewPaymentContract.Presenter{

    private NewPaymentContract.View mView;


    public NewPaymentPresenter(NewPaymentContract.View view) {

        mView = view;
    }

    @Override
    public void getCategories() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Categories> call = service.getCategories();

        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {


                mView.dismissProgressDialog();
                if (response.body() != null){
                    if (response.body().getCategories() != null){

                        ArrayList<String> categories = response.body().getCategories();

                        if (categories.size() > 0){

                            mView.setCategories(categories);
                        }

                    }
                }else {
                    mView.showMessage("An error");
                }


            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {


                mView.dismissProgressDialog();

                mView.showMessage(t.getMessage());



            }
        });

    }

    @Override
    public void addPayment(Payment payment) {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.addPayment(
                payment.getUserId(),
                payment.getName(),
                payment.getDate(),
                payment.getDetails(),
                payment.getAmount(),
                payment.getCategory()
                );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {


                mView.dismissProgressDialog();
                if (response.body() != null){

                    mView.showMessage(response.body().getMessage());
                    if (!response.body().getError()){

                        mView.moveToMain();

                    }
                }else {
                    mView.showMessage("An error");
                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {


                mView.dismissProgressDialog();

                mView.showMessage(t.getMessage());



            }
        });
    }


}
