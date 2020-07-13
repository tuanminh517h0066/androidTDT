package com.example.lab9_ex1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<DownloadFile> data;
    DownloadFile downloadFile;

    public MyAdapter(Context context, List<DownloadFile> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.list_item,parent,false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtFile.setText(data.get(position).getFileName());
        holder.txtSize.setText( data.get(position).getSize()+ "MB");
        //holder.txtStatus.setText(data.get(position).getStatus()+ "MB");
        holder.imgView.setImageResource(data.get(position).getImgResourceId());
        if(data.get(position).getStatus()==1){
            holder.txtStatus.setVisibility(View.INVISIBLE);
            holder.progressBar.setVisibility(View.VISIBLE);
            holder.progressBar.setProgress(data.get(position).getProgress());

        }
        else if(data.get(position).getStatus()==2){
            holder.txtStatus.setVisibility(View.VISIBLE);
            holder.progressBar.setVisibility(View.INVISIBLE);
            holder.txtStatus.setText("Fail");
            holder.txtStatus.setTextColor(Color.CYAN);

        }
        else{
            holder.txtStatus.setVisibility(View.VISIBLE);
            holder.progressBar.setVisibility(View.INVISIBLE);
            holder.txtStatus.setText("Complete");
            holder.txtStatus.setTextColor(Color.GREEN);

        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtFile;
        TextView txtSize;
        TextView  txtStatus;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView= itemView.findViewById(R.id.imageView);
            txtFile= itemView.findViewById(R.id.text_file_name);
            txtSize= itemView.findViewById(R.id.text_file_size);
            txtStatus= itemView.findViewById(R.id.text_status);
            progressBar= itemView.findViewById(R.id.progressBar);

        }
    }
}
