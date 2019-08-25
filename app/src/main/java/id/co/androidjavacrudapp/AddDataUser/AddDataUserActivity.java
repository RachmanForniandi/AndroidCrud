package id.co.androidjavacrudapp.AddDataUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.androidjavacrudapp.AddDataUser.models.ResponseAddData;
import id.co.androidjavacrudapp.MainActivity;
import id.co.androidjavacrudapp.R;
import id.co.androidjavacrudapp.ShowDataUser.ShowDataUserActivity;
import id.co.androidjavacrudapp.networkUtils.NetworkClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDataUserActivity extends AppCompatActivity {

    @BindView(R.id.etNamaUser)EditText etNamaUser;
    @BindView(R.id.etAlamatUser) EditText etAlamatUser;
    @BindView(R.id.etEmailUser)EditText etEmailUser;
    @BindView(R.id.etNoContactUser)EditText etNoContactUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_user);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_user)
    public void onClick(){
        actionTambahData();
    }

    private void actionTambahData() {
        String nama = etNamaUser.getText().toString();
        String email = etEmailUser.getText().toString();
        String noContact = etNoContactUser.getText().toString();
        String alamat = etAlamatUser.getText().toString();

        if (nama.isEmpty()){
            etNamaUser.setError("Nama tidak boleh kosong");
            etNamaUser.requestFocus();
        }else if (email.isEmpty()){
            etEmailUser.setError("Email tidak boleh kosong");
            etEmailUser.requestFocus();
        }else if (noContact.isEmpty()){
            etNoContactUser.setError("No Contact tidak boleh kosong");
            etNoContactUser.requestFocus();
        }else if (alamat.isEmpty()){
            etAlamatUser.setError("Alamat tidak boleh kosong");
            etAlamatUser.requestFocus();
        }else {
            NetworkClient.service.addDataUser(nama,email,noContact,alamat).enqueue(new Callback<ResponseAddData>() {
                @Override
                public void onResponse(Call<ResponseAddData> call, Response<ResponseAddData> response) {
                    if (response.isSuccessful()){
                        Boolean status = response.body().isStatus();
                        String message = response.body().getMessage();

                        if (status){
                            startActivity(new Intent(AddDataUserActivity.this, ShowDataUserActivity.class));
                            Toast.makeText(AddDataUserActivity.this, message, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AddDataUserActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseAddData> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

}
