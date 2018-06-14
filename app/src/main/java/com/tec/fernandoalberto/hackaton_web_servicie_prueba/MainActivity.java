package com.tec.fernandoalberto.hackaton_web_servicie_prueba;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt1, txtTitulo;
    ArrayList<Datos_Reporte> Datos;
    ProgressBar progressBar;
    public static int SegundosStock= 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        txtTitulo = findViewById(R.id.titulo);
        Datos = new ArrayList<Datos_Reporte>();
        progressBar = findViewById(R.id.progressBar);
        ObtenerReportes();
        progressBar.setVisibility(View.GONE);;
        CuentaRegresiva(SegundosStock);
}

    public void CuentaRegresiva(final int Segundos) {
        new CountDownTimer(Segundos, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //Toast.makeText(MainActivity.this, "Acabó", Toast.LENGTH_SHORT).show();

                ObtenerReportes();
                CuentaRegresiva(Segundos);
            }
        }.start();

    }

    public void ObtenerReportes(){
        Datos.clear();
        final Retrofit.Builder builder= new Retrofit.Builder().baseUrl(Constantes_web_service.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();
        EndPoint endPoint= retrofit.create(EndPoint.class);
        Call call= endPoint.ObtenerReportes();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String gsonCadena= new Gson().toJson(response.body());
                txtTitulo.setText("Proyecto Hackaton");
                txt1.setText(gsonCadena);
                JSONObject json = null;
                try {
                    json = new JSONObject(gsonCadena);

                JSONArray jArray = json.getJSONArray("Datos");


                for(int i=0; i<jArray.length(); i++){
                    JSONObject json_data = jArray.getJSONObject(i);

                    txt1.setText("Humedad: " + json_data.getString("Humedad") +
                                "\nRevoluciones por minuto: " + json_data.getString("RevolucionesMinuto") +
                                "\nFecha: " + json_data.getString("Fecha") +
                                "\nHora: " + json_data.getString("Hora"));
                    Datos.add(new Datos_Reporte(json_data.getString("Humedad"),json_data.getString("RevolucionesMinuto"),json_data.getString("Fecha"),json_data.getString("Hora")));
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                txt1.setText(t.getMessage());
            }
        });
    }
}
