package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
int quantity = 0;
Spinner spinner;
ArrayList spinnerArrayList;
ArrayAdapter spinnerAdapter;
HashMap goodsMap;
String goodsName;
double price;
EditText userNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.editTextText);
        createSpinner();
        createMap();
    }


    public void increaseQuantity(View view) {
        quantity=quantity+1;
        TextView textView8 = findViewById(R.id.textView8);
        textView8.setText(""+quantity);
        TextView textView9 = findViewById(R.id.textView9);
        textView9.setText(""+quantity*price);

    }
    public void decreaseQuantity(View view) {
        if (quantity >0){
            quantity=quantity-1;
            TextView textView8 = findViewById(R.id.textView8);
            textView8.setText(""+quantity);

            TextView textView9 = findViewById(R.id.textView9);
            textView9.setText(""+quantity*price);
        }
        else quantity=0;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView textView9 = findViewById(R.id.textView9);
        textView9.setText(""+quantity*price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.git);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.git);
                break;
        }
    }
    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

    }
    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1000.0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addtocart(View view) {
        Order order = new Order();

        order.userName=userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.Price = price;
        order.OrderPrice = quantity*price;
        Intent orderIntent = new Intent(MainActivity.this, orderactivity.class);
        orderIntent.putExtra("UserNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.Price);
        orderIntent.putExtra("OrderPrice", order.OrderPrice);
        startActivity(orderIntent);
    }


}