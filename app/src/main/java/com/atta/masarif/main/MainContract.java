package com.atta.masarif.main;

import com.atta.masarif.model.Payment;

import java.util.ArrayList;

public interface MainContract {

    interface View{

        void showMessage(String error);

        void showRecyclerView(ArrayList<Payment> payments);
    }

    interface Presenter{
        void getPayments() ;

    }
}
