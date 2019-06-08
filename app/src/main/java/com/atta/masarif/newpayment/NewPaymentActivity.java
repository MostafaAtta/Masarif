package com.atta.masarif.newpayment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.atta.masarif.R;
import com.atta.masarif.main.MainActivity;
import com.atta.masarif.model.Payment;
import com.atta.masarif.model.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewPaymentActivity extends AppCompatActivity implements View.OnClickListener, NewPaymentContract.View, AdapterView.OnItemSelectedListener {

    EditText nameEditText, dateEditText, detailsEditText, amountEditText;

    Calendar myCalendar;

    SimpleDateFormat dateFormat;

    DatePickerDialog.OnDateSetListener dateListener;

    String paymentDate, category;

    Button addPayment;

    Spinner categoriesSpinner;

    List<String> categoriesArray;

    ArrayAdapter<String> categoriesAdapter;
    // National ID, password edit text

    NewPaymentPresenter newPaymentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment);

        dateEditText = findViewById(R.id.date);
        addPayment = findViewById(R.id.add);
        addPayment.setOnClickListener(this);


        nameEditText = findViewById(R.id.name);
        detailsEditText = findViewById(R.id.details);
        amountEditText = findViewById(R.id.amount);

        dateFormat = new SimpleDateFormat("E, dd MMM", Locale.US);
        paymentDate = dateFormat.format(new Date());
        dateEditText.setText(paymentDate);
        myCalendar = Calendar.getInstance();

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM", Locale.US);

                paymentDate = sdf.format(myCalendar.getTime());
                dateEditText.setText(paymentDate);

            }
        };

        dateEditText.setOnClickListener(this);

        categoriesSpinner = findViewById(R.id.categories);

        newPaymentPresenter = new NewPaymentPresenter(this);

        newPaymentPresenter.getCategories();


    }

    @Override
    public void onClick(View v) {

        if (v == dateEditText){
            new DatePickerDialog(this, dateListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }else if (v == addPayment){

            if (!validate(nameEditText.getText().toString(), detailsEditText.getText().toString(),
                    amountEditText.getText().toString(), category)) {
                addPayment.setEnabled(true);
                return;
            }
            newPaymentPresenter.addPayment(new Payment(SessionManager.getInstance(this).getUserId(), nameEditText.getText().toString(), detailsEditText.getText().toString(),
                    paymentDate, amountEditText.getText().toString(), category));
        }
    }

    @Override
    public void showViewError(String view, String error) {

        int id = getResources().getIdentifier(view, "id", this.getPackageName());
        EditText editText = findViewById(id);
        editText.setError(error);
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void setCategories(List<String> categories) {

        categoriesArray = new ArrayList<>();


        categoriesArray.add("select payment category");

        categoriesArray.addAll(categories);


        categoriesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categoriesArray);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(categoriesAdapter);
        categoriesSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void moveToMain() {
        Intent intent = new Intent(NewPaymentActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position != 0){
            category = categoriesArray.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean validate(String name, String details, String amount, String category) {
        boolean valid = true;



        if (name.isEmpty()) {

            showViewError("name","Enter Transaction name");
            valid = false;
        } else {

            showViewError("name",null);
        }


        if (details.isEmpty()) {

            showViewError("details","Enter Transaction details");
            valid = false;
        } else {

            showViewError("details",null);
        }


        if (amount.isEmpty()) {

            showViewError("amount","Enter Transaction amount");
            valid = false;
        } else {

            showViewError("amount",null);
        }

        if (category == null) {

            showMessage("Enter Transaction category");
            valid = false;
        }



        return valid;
    }
}
