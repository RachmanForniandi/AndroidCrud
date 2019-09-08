package id.co.androidjavacrudapp.editDataUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.androidjavacrudapp.R;
import id.co.androidjavacrudapp.ShowDataUser.ShowDataUserActivity;
import id.co.androidjavacrudapp.ShowDataUser.models.DataItemShowUser;
import id.co.androidjavacrudapp.detailDataUser.DetailDataActivity;
import id.co.androidjavacrudapp.editDataUser.models.ResponseEditData;
import id.co.androidjavacrudapp.networkUtils.NetworkClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    @BindView(R.id.et_edit_Nama_User) EditText etEditNamaUser;
    @BindView(R.id.et_edit_Alamat_User) EditText etEditAlamatUser;
    @BindView(R.id.et_edit_Email_User)EditText etEditEmailUser;
    @BindView(R.id.et_edit_No_Contact_User)EditText etEditNoContactUser;
    @BindView(R.id.param_edit_IdUser) TextView paramEditIdUser;
    @BindView(R.id.btn_edit_user) Button btnEditUser;
    DataItemShowUser dataItemShowUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        dataItemShowUser = (DataItemShowUser)getIntent().getSerializableExtra("data_user");

        ButterKnife.bind(this);

        paramEditIdUser.setText(dataItemShowUser.getIdUser());
        etEditNamaUser.setText(dataItemShowUser.getNamaUser());
        etEditEmailUser.setText(dataItemShowUser.getEmailUser());
        etEditNoContactUser.setText(dataItemShowUser.getNoHpUser());
        etEditAlamatUser.setText(dataItemShowUser.getAlamat());

    }

    @OnClick(R.id.btn_edit_user)
    public void onClick(){
        actionEditDataUser();
    }

    private void actionEditDataUser() {
        String id_user = paramEditIdUser.getText().toString();
        String nama = etEditNamaUser.getText().toString();
        String email = etEditEmailUser.getText().toString();
        String no_contact = etEditNoContactUser.getText().toString();
        String alamat = etEditAlamatUser.getText().toString();
        NetworkClient.service.EditUser(id_user,nama,email,no_contact,alamat).enqueue(new Callback<ResponseEditData>() {
            @Override
            public void onResponse(Call<ResponseEditData> call, Response<ResponseEditData> response) {
                if (response.isSuccessful()){
                    String message = response.body().getMessage();
                    Boolean status = response.body().isStatus();

                    if (status){
                        startActivity(new Intent(EditUserActivity.this, ShowDataUserActivity.class));
                        Toast.makeText(EditUserActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(EditUserActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseEditData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
