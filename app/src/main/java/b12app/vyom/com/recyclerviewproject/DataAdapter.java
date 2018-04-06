package b12app.vyom.com.recyclerviewproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    List<ContactsData> myContactsDataList;
    Context context;

    public DataAdapter(List<ContactsData> myContactsDataList, Context context) {
        this.myContactsDataList = myContactsDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_format,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ContactsData contactsData = myContactsDataList.get(position);

        holder.tvName.setText(contactsData.name);
        holder.tvAddress.setText(contactsData.address);
        holder.tvMobile.setText(contactsData.mobile);

    }

    @Override
    public int getItemCount() {
        return myContactsDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvAddress, tvMobile;



        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvMobile = itemView.findViewById(R.id.tvPhone);
        }
    }
}
