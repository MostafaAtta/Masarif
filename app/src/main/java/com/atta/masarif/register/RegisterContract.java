package com.atta.masarif.register;

import com.atta.masarif.model.User;

public interface RegisterContract {

    interface View{

        void showMessage(String error);

        void showViewError(String view, String error);

        void navigateToMain();

        void setDialog();

        boolean validate(String name, String password, String passwordConfirm);

    }

    interface Presenter{

        void register(User user);



    }
}
