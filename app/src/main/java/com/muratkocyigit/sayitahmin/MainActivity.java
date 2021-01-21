package com.muratkocyigit.sayitahmin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int sayiTahmin = (int)(Math.random()* 100);
    private int defaultHak = 10;
    private int denemeSayisi = 0;
    private String ipucuYukari = "YUKARI";
    private String ipucuAsagi = "AŞAĞI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtHak = (TextView) this.findViewById(R.id.txtHak);
        EditText tahminInput = (EditText) this.findViewById(R.id.txtTahminInput);
        Button btnTahmin = (Button) this.findViewById(R.id.btnTahmin);
        TextView txtIpucu = (TextView) this.findViewById(R.id.txtIpucu);
        Button btnRefresh = (Button) this.findViewById(R.id.btnRefresh);

        /** Tahmin Kontrol */
        btnTahmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int can = Integer.valueOf(txtHak.getText().toString());
                String tahminStr = tahminInput.getText().toString();
                if (tahminStr.equals("") || tahminStr.isEmpty()) {
                    errorToast("Lütfen Bir Sayı Giriniz");
                } else {
                    int tahmin = Integer.valueOf(tahminStr);
                    denemeSayisi++;
                    //   if (tahmin)
                    if (can >= 1) {
                        if (tahmin == sayiTahmin) {
                            refresh(txtHak, tahminInput, txtIpucu);
                            showToast();
                        } else {
                            if(tahmin > sayiTahmin) {
                                txtIpucu.setText(ipucuAsagi);
                                can--;
                            } else if(tahmin < sayiTahmin) {
                                txtIpucu.setText(ipucuYukari);
                                can--;
                            }
                            txtHak.setText(String.valueOf(can));
                        }
                    } else {
                        errorToast("Hakkınız kalmadığı için oyun yeniden başlatılıyor !");
                        refresh(txtHak, tahminInput, txtIpucu);
                    }
                }

            }
        });

        /** Yeniden Oyna Butonu */
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(txtHak, tahminInput, txtIpucu);
            }
        });




    }

    private void refresh (TextView txtHak, EditText tahminInput, TextView txtIpucu) {
        sayiTahmin = (int)(Math.random()* 100);
        defaultHak = 10;

        txtHak.setText(String.valueOf(defaultHak));
        tahminInput.setText("");
        txtIpucu.setText("");
    }
    public void showToast()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.activity_success,(ViewGroup) findViewById(R.id.toast_root));

        TextView textView = layout.findViewById(R.id.textView);
        ImageView imageView = layout.findViewById(R.id.imageView);

        textView.setText("Tebrikler ! " + denemeSayisi + ". denemede sayıyı buldunuz" );
        //imageView.setImageResource(R.drawable.icon);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        //toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }
    public void errorToast(String message)
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.activity_error,(ViewGroup) findViewById(R.id.toast_root));

        TextView textView = layout.findViewById(R.id.textView);
        ImageView imageView = layout.findViewById(R.id.imageView);

        textView.setText(message);
        //imageView.setImageResource(R.drawable.icon);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        //toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }
}