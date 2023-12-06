package com.example.th11;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class Bai1 extends AppCompatActivity {

    int cl = 1;
    LinearLayout ln;
    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        Button btnRandomNum = findViewById(R.id.btn_draw_view);
//        TextView tvTest = findViewById(R.id.tv_test);
        EditText edtNum = findViewById(R.id.edt_num_colm);
        ln =  findViewById(R.id.ln);
        btnRandomNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(edtNum.getText().toString());
                createView(i);
            }
        });
    }
    protected void createView(int num) {

        for (int i= 0;i<num;i++){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.RIGHT;
            Button bttn = new Button(this);
            Random random = new Random();
            int rdNum = random.nextInt(100);
            Math.random();
            bttn.setText(String.valueOf(rdNum));
            bttn.setLayoutParams(params);
            bttn.setTextColor(Color.WHITE);
            bttn.setInputType(InputType.TYPE_CLASS_TEXT);
            bttn.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

            params.gravity = (i % 2 == 0) ? Gravity.RIGHT : Gravity.LEFT;
            bttn.setLayoutParams(params);
            ln.addView(bttn);
        }
    }
}