package id.co.androidjavacrudapp.ShowDataUser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.androidjavacrudapp.R;
import id.co.androidjavacrudapp.ShowDataUser.models.DataItemShowUser;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserHolder> {
    Context context;
    List<DataItemShowUser> dataItemList;

    public UserDataAdapter(Context context,List<DataItemShowUser> dataItemList ) {
        this.context = context;
        this.dataItemList = dataItemList;

    }

    @NonNull
    @Override
    public UserDataAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataAdapter.UserHolder holder, int position) {
        final DataItemShowUser dataItemShowUser = dataItemList.get(position);

        holder.tvNameUser.setText(dataItemShowUser.getNamaUser());
        holder.tvEmailUser.setText(dataItemShowUser.getEmailUser());
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }


    public class UserHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_user) TextView tvNameUser;
        @BindView(R.id.tv_email_user) TextView tvEmailUser;

        public UserHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
