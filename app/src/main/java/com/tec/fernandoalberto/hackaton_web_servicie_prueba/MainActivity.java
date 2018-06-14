package com.tec.fernandoalberto.hackaton_web_servicie_prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1= findViewById(R.id.txt1);
        ObtenerReportes();
    }

    public void ObtenerReportes(){
        final Retrofit.Builder builder= new Retrofit.Builder().baseUrl(Constantes_web_service.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();
        EndPoint endPoint= retrofit.create(EndPoint.class);
        Call call= endPoint.ObtenerReportes();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String gsonCadena= new Gson().toJson(response.body());
                txt1.setText(gsonCadena);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                txt1.setText(t.getMessage());
            }
        });
    }
}
