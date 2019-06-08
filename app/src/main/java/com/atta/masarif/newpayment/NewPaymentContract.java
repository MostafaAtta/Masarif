package com.atta.masarif.newpayment;

import com.atta.masarif.model.Payment;

import java.util.List;

public interface NewPaymentContract {

    interface View{

        void showViewError(String view, String error);

        void showMessage(String message);

        void setDialog();

        void dismissProgressDialog();

        void setCategories(List<String> categories);

        void moveToMain();

        boolean validate(String name, String details, String amount, String category);
    }

    interface Presenter{

        void getCategories();

        void addPayment(Payment payment);
    }
}
