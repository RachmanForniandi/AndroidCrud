package id.co.androidjavacrudapp.detailDataUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.androidjavacrudapp.R;
import id.co.androidjavacrudapp.ShowDataUser.ShowDataUserActivity;
import id.co.androidjavacrudapp.ShowDataUser.models.DataItemShowUser;
import id.co.androidjavacrudapp.detailDataUser.models.ResponseDeleteData;
import id.co.androidjavacrudapp.editDataUser.EditUserActivity;
import id.co.androidjavacrudapp.networkUtils.NetworkClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDataActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_name) TextView tvDetailName;
    @BindView(R.id.tv_detail_email) TextView tvDetailEmail;
    @BindView(R.id.tv_detail_no_contact) TextView tvDetailNoContact;
    @BindView(R.id.tv_detail_address) TextView tvDetailAddress;
    @BindView(R.id.paramIdUser) TextView paramIdUser;
    @BindView(R.id.btn_hapus) Button btnDelete;
    @BindView(R.id.btn_edit) Button btnEdit;
    DataItemShowUser dataItemShowUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        ButterKnife.bind(this);
        dataItemShowUser = (DataItemShowUser)getIntent().getSerializableExtra("data_user");


        paramIdUser.setText(dataItemShowUser.getIdUser());
        tvDetailName.setText(dataItemShowUser.getNamaUser());
        tvDetailEmail.setText(dataItemShowUser.getEmailUser());
        tvDetailAddress.setText(dataItemShowUser.getAlamat());
    }

    @OnClick({R.id.btn_hapus,R.id.btn_edit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_hapus:
                actionDeleteDataUser();
                break;

            case R.id.btn_edit:
                actionEditDataUser();
                break;

        }
    }

    private void actionEditDataUser() {

        Intent toEdit =new Intent(DetailDataActivity.this, EditUserActivity.class);
        toEdit.putExtra("data_user", dataItemShowUser);
        startActivity(toEdit);

    }

    private void actionDeleteDataUser() {
        String id_user = paramIdUser.getText().toString();
        NetworkClient.service.deleteUser(id_user).enqueue(new Callback<ResponseDeleteData>() {
            @Override
            public void onResponse(Call<ResponseDeleteData> call, Response<ResponseDeleteData> response) {
                if (response.isSuccessful()){
                    String message = response.body().getMessage();
                    Boolean status = response.body().isStatus();

                    if (status){
                        startActivity(new Intent(DetailDataActivity.this, ShowDataUserActivity.class));
                        Toast.makeText(DetailDataActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DetailDataActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
