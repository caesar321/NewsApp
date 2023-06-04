package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceActivity extends AppCompatActivity {
    private RecyclerView recyclerView_science;
    private RecyclerView recyclerViewr;
    private Toolbar toolbar;
    private   NewsAdapter adapter_science;
   private SearchView searchView;
    private ArrayList<ModalClass> modal;
   private ProgressDialog dialog;
    String Api_Key="732fd52c302c4b04955d6a53138a48a6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        recyclerViewr= findViewById(R.id.recycler_view_science);
        toolbar= findViewById(R.id.tool_bar_science);
        modal= new ArrayList<>();
        searchView= findViewById(R.id.searchView);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading...Please Wait");
        adapter_science= new NewsAdapter(getApplicationContext(),modal);
        recyclerViewr.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewr.hasFixedSize();
        recyclerViewr.setAdapter(adapter_science);
        men();
    }

    private void men() {

            dialog.show();
            RetrofitClass.newsInterface().science("ng",Api_Key,"science")
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            if(response.isSuccessful()&& response.body()!=null){
                                modal.addAll(response.body().getArticles());
                                adapter_science.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {
                            Toast.makeText(ScienceActivity.this, "An error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
