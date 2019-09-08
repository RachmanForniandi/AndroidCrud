package id.co.androidjavacrudapp.ShowDataUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.androidjavacrudapp.AddDataUser.AddDataUserActivity;
import id.co.androidjavacrudapp.R;
import id.co.androidjavacrudapp.ShowDataUser.adapter.UserDataAdapter;
import id.co.androidjavacrudapp.ShowDataUser.models.DataItemShowUser;
import id.co.androidjavacrudapp.ShowDataUser.models.ResponseShowUser;
import id.co.androidjavacrudapp.detailDataUser.DetailDataActivity;
import id.co.androidjavacrudapp.networkUtils.NetworkClient;
import id.co.androidjavacrudapp.uploadImage.UploadImageActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDataUserActivity extends AppCompatActivity {

    @BindView(R.id.btn_navigate_add_user) Button btnNavigateAdd;
    @BindView(R.id.btn_to_upload_data)Button btnToUploadData;
    @BindView(R.id.rv_list_user) RecyclerView rvListUser;

    private List<DataItemShowUser>dataItemList;
    UserDataAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_user);
        dataItemList = new ArrayList<>();

        ButterKnife.bind(this);

        getListDataUser();
    }

    private void getListDataUser() {
        NetworkClient.service.showUserData().enqueue(new Callback<ResponseShowUser>() {
            @Override
            public void onResponse(Call<ResponseShowUser> call, Response<ResponseShowUser> response) {

                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    Boolean status = response.body().isStatus();
                    if (status) {
                        dataItemList = response.body().getData();
                        rvListUser.setLayoutManager(new LinearLayoutManager(ShowDataUserActivity.this, LinearLayoutManager.VERTICAL, false));
                        userAdapter = new UserDataAdapter(ShowDataUserActivity.this, dataItemList, new UserDataAdapter.onItemClick() {
                            @Override
                            public void item(DataItemShowUser data) {
                                Intent intent = new Intent(ShowDataUserActivity.this, DetailDataActivity.class);
                                intent.putExtra("data_user", data);
                                startActivity(intent);
                            }
                        });
                        rvListUser.setAdapter(userAdapter);
                    } else {
                        Toast.makeText(ShowDataUserActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseShowUser> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @OnClick({R.id.btn_navigate_add_user,R.id.btn_to_upload_data})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btn_navigate_add_user:
                moveToAddData();
                break;

            case R.id.btn_to_upload_data:
                toTheUploadData();
                break;
        }

    }

    private void toTheUploadData() {
        startActivity(new Intent(ShowDataUserActivity.this, UploadImageActivity.class));
    }

    private void moveToAddData() {
        startActivity(new Intent(ShowDataUserActivity.this, AddDataUserActivity.class));
    }
}
