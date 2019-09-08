package id.co.androidjavacrudapp.uploadImage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.androidjavacrudapp.R;
import id.co.androidjavacrudapp.ShowDataUser.ShowDataUserActivity;
import id.co.androidjavacrudapp.networkUtils.NetworkClient;
import id.co.androidjavacrudapp.uploadImage.models.ResponseUploadPicture;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImageActivity extends AppCompatActivity {

    @BindView(R.id.tv_user_pict)
    TextView tvUserPict;

    @BindView(R.id.btn_camera)
    ImageView btnCamera;

    @BindView(R.id.btn_add_data)
    Button btnAddData;

    Dialog dialog;
    String mediaPath;
    private static final int REQUEST_CAMERA = 1;
    private static final String TAG = "UploadGambarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        ButterKnife.bind(this);

        checkPermissionCamera();
    }

    private void actionDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_capture);

        Button btnOpenCamera = dialog.findViewById(R.id.btn_open_camera);

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,2);
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.dismiss();

        if (requestCode == 2){
            Bitmap dataBitmap = (Bitmap)data.getExtras().get("data");
            Image(dataBitmap,"camera");
        }
    }

    private void Image(Bitmap bitmap,String name){
        File fileDir = getFilesDir();
        File imageFile = new File(fileDir,name + ".jpg");
        mediaPath = imageFile.getPath();
        tvUserPict.setText(mediaPath);


        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionUploadPict(){
        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);

        MultipartBody.Part filePhoto = MultipartBody.Part.createFormData("userfile",file.getName(),requestBody);
        NetworkClient.service.uploadThePict(filePhoto).enqueue(new Callback<ResponseUploadPicture>() {
            @Override
            public void onResponse(Call<ResponseUploadPicture> call, Response<ResponseUploadPicture> response) {
                if (response.isSuccessful()){
                    Boolean status = response.body().isStatus();
                    String message = response.body().getMessage();

                    if (status){
                        Toast.makeText(UploadImageActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UploadImageActivity.this, ShowDataUserActivity.class));
                    }else {
                        Toast.makeText(UploadImageActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUploadPicture> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissionCamera(){
        int permissionCheck = checkSelfPermission(Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"Granted");
        }else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(UploadImageActivity.this,Manifest.permission.CAMERA)){
                Log.d(TAG,"Contacts Permission Required !!");
            }
            ActivityCompat.requestPermissions(UploadImageActivity.this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
        }
    }

    @OnClick({R.id.btn_camera,R.id.btn_add_data})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_camera:
                actionDialog();
                break;

            case R.id.btn_add_data:
                actionUploadPict();
                break;

        }
    }
}
