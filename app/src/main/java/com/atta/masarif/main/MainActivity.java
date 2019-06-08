package com.atta.masarif.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.atta.masarif.R;
import com.atta.masarif.model.Payment;
import com.atta.masarif.model.PaymentsAdapter;
import com.atta.masarif.newpayment.NewPaymentActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    Button newPaymentBtn;

    MainPresenter mainPresenter;

    RecyclerView recyclerView;

    PaymentsAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newPaymentBtn = findViewById(R.id.button);
        newPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPaymentActivity.class);
                startActivity(intent);
            }
        });



        mainPresenter = new MainPresenter(this, this);

        recyclerView = findViewById(R.id.recycler_view);

        mainPresenter.getPayments();

        final SwipeRefreshLayout mySwipeRefreshLayout = findViewById(R.id.refresh_swipe);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mainPresenter.getPayments();
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
    }

    @Override
    public void showMessage(String error) {

        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecyclerView(ArrayList<Payment> payments) {

        myAdapter = new PaymentsAdapter(this, payments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

    }

}
