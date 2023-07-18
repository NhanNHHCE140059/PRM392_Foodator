package com.example.apporderfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apporderfood.Domain.FoodDomain;
import com.example.apporderfood.Helper.ManagementCart;
import com.example.apporderfood.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minuBtn, picFood;
    private FoodDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    @SuppressLint("SetTextI18n")
    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int pic = (int) getIntent().getSerializableExtra("picture");
        @SuppressLint("DiscouragedApi") int drawableSourceId = this.getResources().getIdentifier(String.valueOf(pic), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableSourceId)
                .into(picFood);
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder +1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){numberOrder = numberOrder -1;}
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minuBtn = findViewById(R.id.minuBtn);
        picFood = findViewById(R.id.picfood);
    }
}