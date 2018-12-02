package com.example.kaptanncek.a19_10_2018_ders2;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    double snc;
    ArrayList engel_listesi=new ArrayList();
    EditText yas,engel_1,engel_2,engel_3,engel_4,engel_5;
    Button hesapla,temizle;
    TextView sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         yas = (EditText)findViewById(R.id.et_yas);
         engel_1 = (EditText)findViewById(R.id.et_engel1);
         engel_2 = (EditText)findViewById(R.id.et_engel2);
         engel_3 = (EditText)findViewById(R.id.et_engel3);
         engel_4 = (EditText)findViewById(R.id.et_engel4);
         engel_5 = (EditText)findViewById(R.id.et_engel5);
         hesapla=(Button)findViewById(R.id.btn_hesapla);
         temizle=(Button)findViewById(R.id.btn_temizle);
         sonuc=(TextView) findViewById(R.id.tv_sonuc);

        temizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engel_listesi.clear();
                String engel1, engel2, engel3, engel4, engel5, Yas;
                Yas = yas.getText().toString();
                engel1 = engel_1.getText().toString();
                engel2 = engel_2.getText().toString();
                engel3 = engel_3.getText().toString();
                engel4 = engel_4.getText().toString();
                engel5 = engel_5.getText().toString();

                if (!"".equals(engel1)) engel_listesi.add(engel1);
                if (!"".equals(engel2)) engel_listesi.add(engel2);
                if (!"".equals(engel3)) engel_listesi.add(engel3);
                if (!"".equals(engel4)) engel_listesi.add(engel4);
                if (!"".equals(engel5)) engel_listesi.add(engel5);
                if (engel_listesi.isEmpty())
                    Toast.makeText(getApplicationContext(), "Engel Orani Girmediniz...", Toast.LENGTH_LONG).show();
                else {
                    if (engel_listesi.size() == 1)
                        sonuc.setText(String.valueOf(engel_listesi.get(0).toString()));
                    else {
                        Collections.sort(engel_listesi, Collections.reverseOrder());
                        double a = 100 - Double.parseDouble(engel_listesi.get(0).toString());
                        double b = (a * Double.parseDouble(engel_listesi.get(1).toString())) / 100;
                        snc = b + Double.parseDouble(engel_listesi.get(0).toString());
                        sonuc.setText(Double.toString(snc));
                        for (int i = 2; i < engel_listesi.size(); i++) {
                            double d = 100 - snc;
                            snc = ((d * Double.parseDouble(engel_listesi.get(i).toString())) / 100) + snc;
                        }
                    }
                }
                if (Integer.parseInt(Yas) >= 60) {
                    double d = 100 - 10;
                    double b= (d*snc);
                    snc=b+snc;
                    sonuc.setText(Double.toString(snc));
                } else sonuc.setText(Double.toString(snc));
            }
            });
        }
    }
