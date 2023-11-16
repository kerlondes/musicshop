package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class orderactivity extends AppCompatActivity {
    String[] addresses={"fruzpla@gmail.com"};
    String subject = "Order from Music Shop";
    String emailtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderactivity);
        Intent receivedOrderIntent =  getIntent();
        String userName = receivedOrderIntent.getStringExtra("UserNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity",0);
        double Price = receivedOrderIntent.getDoubleExtra("Price",0);
        double OrderPrice = receivedOrderIntent.getDoubleExtra("OrderPrice",0);
        emailtext ="UserName: "+userName+"\n"
                +"GoodsName: "+goodsName+"\n"
                +"Quantity: "+quantity+"\n"
                +"Price: "+Price+"\n"
                +"OrderPrice: "+OrderPrice;
        TextView orderTextView = findViewById(R.id.orderTextViev);
        orderTextView.setText(emailtext);
    }

    public void SubmitOrder(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailtext);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }
    }
}