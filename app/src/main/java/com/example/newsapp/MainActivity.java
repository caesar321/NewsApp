package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
       private RecyclerView recyclerViewr;
       private Toolbar toolbar;
      private   NewsAdapter adapter;
      private   SearchView searchView;
       private ArrayList<ModalClass> modal;
      private   ProgressDialog dialog;
      private AdView adView;
        String Api_Key="732fd52c302c4b04955d6a53138a48a6";
        Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewr= findViewById(R.id.recycler_view);
        toolbar= findViewById(R.id.tool_bar);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        adView= findViewById(R.id.adView);
        AdRequest adRequest= new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        modal= new ArrayList<>();
        searchView= findViewById(R.id.searchView);
        btn1= findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading...Please Wait");
        adapter= new NewsAdapter(getApplicationContext(),modal);
        recyclerViewr.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewr.hasFixedSize();
        recyclerViewr.setAdapter(adapter);
          fetch();
          click();
          searchView.clearFocus();
          searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
              @Override
              public boolean onQueryTextSubmit(String query) {
                  return false;
              }

              @Override
              public boolean onQueryTextChange(String newText) {
                    filterList(newText);
                  return true;
              }
          });
    }

    private void filterList(String text) {
        ArrayList<ModalClass> filteredList = new ArrayList<>();
        for (ModalClass mod : modal) {
            if (mod.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(mod);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No information available", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }
    private void fetch() {
        dialog.show();
        RetrofitClass.newsInterface().news("ng",Api_Key)
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if(response.isSuccessful()&& response.body()!=null){
                            modal.addAll(response.body().getArticles());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error Occured", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void click(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScienceActivity.class);
                startActivity(intent);

            }
        });

    }

}

