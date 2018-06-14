package com.tec.fernandoalberto.hackaton_web_servicie_prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt1;
    ArrayList<Datos_Reporte> Datos;
    ListView Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1= findViewById(R.id.txt1);
        Lista= findViewById(R.id.Lista);
        ObtenerReportes();
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
                txt1.setText("");
                JSONObject json = null;
                try {
                    json = new JSONObject(gsonCadena);

                JSONArray jArray = json.getJSONArray("Datos");


                for(int i=0; i<jArray.length(); i++){
                    JSONObject json_data = jArray.getJSONObject(i);
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
        ArrayAdapter<Datos_Reporte> adapter= new ArrayAdapter<>(this, R.layout.lista_objeto, Datos);
        Lista.setAdapter(adapter);
    }
}
