package pe.gob.sunat.microservices.curso.orders.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderServiceClient {
  @POST("v1/orders")
  Call<Order> create(@Body Order customer);

  @GET("v1/orders/{id}")
  Call<Order> get(@Path("id") Long id);
}


