package com.tec.fernandoalberto.hackaton_web_servicie_prueba;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoint {
    @GET(Constantes_web_service.GET_REPORTE)
    Call<Object> ObtenerReportes();
}
