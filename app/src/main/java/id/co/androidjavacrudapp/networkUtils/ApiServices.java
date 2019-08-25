package id.co.androidjavacrudapp.networkUtils;

import id.co.androidjavacrudapp.AddDataUser.models.ResponseAddData;
import id.co.androidjavacrudapp.ShowDataUser.models.ResponseShowUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("AddUser")
    Call<ResponseAddData> addDataUser(
            @Field("nama_user")String namaUser,
            @Field("email_user")String emailUser,
            @Field("no_hp_user")String noHpUser,
            @Field("alamat")String alamat
    );

    @GET("ShowDataUser")
    Call<ResponseShowUser> showUserData();
}
