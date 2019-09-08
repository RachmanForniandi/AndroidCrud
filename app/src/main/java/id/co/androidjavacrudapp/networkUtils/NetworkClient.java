package id.co.androidjavacrudapp.networkUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    public static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient client = new OkHttpClient.Builder()
           .addInterceptor(logging)
            .build();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.115/servercrud/index.php/ApiController/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static ApiServices service = retrofit.create(ApiServices.class);
}
