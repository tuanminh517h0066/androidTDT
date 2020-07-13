package com.example.lab9_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button buttonDown;
    EditText editUrl;
    MyAdapter adapter;
    List<DownloadFile> data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recyclerView);
        editUrl= findViewById(R.id.edit_url);
        buttonDown= findViewById(R.id.buttondown);
        data= new ArrayList<>();

        genMockData();

        adapter= new MyAdapter(MainActivity.this, data);
        adapter= new MyAdapter( this
                ,data);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName= editUrl.getText().toString();
                double fileSize= new Random().nextDouble();
                DownloadFile downloadFile= new DownloadFile(fileName, fileSize,1,0);
                data.add(downloadFile);
                adapter.notifyDataSetChanged();
                DownloadTask task= new DownloadTask();
                task.execute(downloadFile);
            }
        });
    }

    private void genMockData() {

        data.add(new DownloadFile("mydoc.docx", 1.2, 1, 30));
        data.add(new DownloadFile("mymusic.mp3", 1.2, 2, 40));
        data.add(new DownloadFile("myimage.jpg", 1.2, 3, 100));


    }
    private class DownloadTask extends AsyncTask<DownloadFile, DownloadFile, DownloadFile>{

        @Override
        protected DownloadFile doInBackground(DownloadFile... downloadFiles) {
            DownloadFile downloadFile=downloadFiles[0];
            double downLoadSize = 0;
            while (downLoadSize<downloadFile.getSize()){
                try {
                    Thread.sleep(1000);
                    downLoadSize += 0.1;
                    int currentProgress= (int) (100*downLoadSize/downloadFile.getSize());
                    downloadFile.setProgress(currentProgress);
                    publishProgress(downloadFile);
                } catch (InterruptedException e) {
                    downloadFile.setStatus(2);
                    return downloadFile;
                    //e.printStackTrace();

                }

            }
            downloadFile.setStatus(3);
            return downloadFile;
        }

        @Override
        protected void onProgressUpdate(DownloadFile... values) {
            //super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();
        }
    }

}
