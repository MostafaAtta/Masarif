package com.atta.masarif.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atta.masarif.R;

import java.util.List;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.MyViewHolder> {

    private Context mContext;

    private List<Payment> payments;

    public PaymentsAdapter(Context mContext, List<Payment> payments) {
        this.mContext = mContext;
        this.payments = payments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Payment payment = payments.get(i) ;

        final int id = payment.getId();
        final String name = payment.getName();

        final String date = payment.getDate();
        final String amount = payment.getAmount();

        myViewHolder.paymentName.setText(name);
        myViewHolder.paymentDate.setText(date);
        myViewHolder.paymentAmount.setText(amount);

        if (i % 2 != 0){
            myViewHolder.linearLayout.setBackgroundResource(R.drawable.myrect2);
        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(mContext, NewAddressActivity.class);
                intent.putExtra("payment", payment);
                mContext.startActivity(intent);*/
            }
        });

    }


    @Override
    public int getItemCount() {
        return payments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView paymentName, paymentDate, paymentAmount;

        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentName = itemView.findViewById(R.id.trans_name);
            paymentDate = itemView.findViewById(R.id.trans_date);
            paymentAmount = itemView.findViewById(R.id.trans_amount);

            linearLayout = itemView.findViewById(R.id.view_background);
        }
    }

}
