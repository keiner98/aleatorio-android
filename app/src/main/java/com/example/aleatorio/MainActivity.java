package com.example.aleatorio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button puntoMedio, pendiente, cuadrante;
    private EditText x1, x2, y1, y2;
    public TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x1 = findViewById(R.id.x1);
        x2 = findViewById(R.id.x2);
        y1 = findViewById(R.id.y1);
        y2 = findViewById(R.id.y2);
        puntoMedio = findViewById(R.id.pm);
        pendiente = findViewById(R.id.p);
        cuadrante = findViewById(R.id.c);
        puntoMedio.setOnClickListener(this);
        pendiente.setOnClickListener(this);
        cuadrante.setOnClickListener(this);
        result = findViewById(R.id.resultado);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.aleatorio:
                    x1.setText(random() + "");
                    x2.setText(random() + "");
                    y1.setText(random() + "");
                    y2.setText(random() + "");
                    break;
                case R.id.ditancia:
                    if (TextUtils.isEmpty(x1.getText().toString()) || TextUtils.isEmpty(x2.getText().toString())
                    || TextUtils.isEmpty(y1.getText().toString()) || TextUtils.isEmpty(y2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_LONG).show();
                    }
                    else {
                        float x01 = Float.parseFloat(x1.getText().toString());
                        float x02 = Float.parseFloat(x2.getText().toString());
                        float y01 = Float.parseFloat(y1.getText().toString());
                        float y02 = Float.parseFloat(y2.getText().toString());
                        result.setText(distancia(x01, x02, y01, y02) + "");
                    }
                    break;
            }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(x1.getText().toString()) || TextUtils.isEmpty(x2.getText().toString())
           || TextUtils.isEmpty(y1.getText().toString()) || TextUtils.isEmpty(y2.getText().toString())) {
           Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_LONG).show();
        }
        else {
            float x01 = Float.parseFloat(x1.getText().toString());
            float x02 = Float.parseFloat(x2.getText().toString());
            float y01 = Float.parseFloat(y1.getText().toString());
            float y02 = Float.parseFloat(y2.getText().toString());
            switch (v.getId()){
                case R.id.pm:
                    result.setText("(" + CalMedio(x01, x02) + "," + CalMedio(y01, y02) + ")");
                    break;
                case  R.id.p:
                    result.setText(CalPendiente(x01,x02,y01,y02)+"");
                    break;
                case R.id.c:
                    result.setText(CalCuadrante(x01,x02)+"");
            }
        }
    }

    public int random() {
        int menor = -50;
        int mayor = 50;
        final int r = (int) (Math.random() * (menor - mayor + 1) + mayor);
        return r;
    }

    public float CalMedio(float a, float b) {
        float r = (a + b) / 2;
        return r;
    }

    public float CalPendiente(float a1, float a2, float b1, float b2) {
        float r = (b2 - b1) / (a2 - a1);
        return r;
    }

    public int CalCuadrante(float a,float b){
        if (a == 0 || b == 0) return 0;
        else if (a > 0 && b > 0) return 1;
        else if (a < 0 && b > 0) return 2;
        else if (a < 0 && b < 0) return 3;
        else return 4;
    }

    public int distancia(float x1, float x2, float y1, float y2){
        return (int) Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

}