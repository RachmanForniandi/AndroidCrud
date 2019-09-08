package id.co.androidjavacrudapp.networkUtils;

import id.co.androidjavacrudapp.AddDataUser.models.ResponseAddData;
import id.co.androidjavacrudapp.ShowDataUser.models.ResponseShowUser;
import id.co.androidjavacrudapp.detailDataUser.models.ResponseDeleteData;
import id.co.androidjavacrudapp.editDataUser.models.ResponseEditData;
import id.co.androidjavacrudapp.uploadImage.models.ResponseUploadPicture;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @FormUrlEncoded
    @POST("DeleteDataUser")
    Call<ResponseDeleteData>deleteUser(
      @Field("id_user")String id_user
    );

    @FormUrlEncoded
    @POST("EditDataUser")
    Call<ResponseEditData>EditUser(
            @Field("id_user")String id_user,
            @Field("nama_user")String namaUser,
            @Field("email_user")String emailUser,
            @Field("no_hp_user")String noHpUser,
            @Field("alamat")String alamat
    );

    @Multipart
    @POST("UploadFileImage")
    Call<ResponseUploadPicture>uploadThePict(
            @Part MultipartBody.Part image
            );

}
