package com.atta.masarif.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("password") String password
    );


    @GET("get_categories")
    Call<Categories> getCategories(
    );

    @GET("get_payments")
    Call<Payments> getPayments(
    );


    //the signin call
    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("name") String name,
            @Field("password") String password
    );



    @FormUrlEncoded
    @POST("add_payment")
    Call<Result> addPayment(
            @Field("user_id") int userId,
            @Field("trans_name") String transNme,
            @Field("trans_date") String transDate,
            @Field("trans_details") String transDetails,
            @Field("trans_amount") String transAmount,
            @Field("trans_category") String transCategory
    );
}
