package com.shiwangi.retrofitproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.shiwangi.retrofitproject.Adapter.ImageAdapter;
import com.shiwangi.retrofitproject.Model.Image;
import com.shiwangi.retrofitproject.Network.Api;
import com.shiwangi.retrofitproject.Repository.ImageRepository;
import com.shiwangi.retrofitproject.ViewModel.ImageViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    NestedScrollView nestedScrollView;
//    RecyclerView recyclerViewshimmer;
//    ProgressBar progressBar;
//    ShimmerFrameLayout shimmerFrameLayout;
//    ArrayList<MainData> dataArrayList = new ArrayList<MainData>();
//    MainAdapter adapter;

    private ImageViewModel imageViewModel;
    private static final String URL_DATA = "https://picsum.photos/v2/";
    private RecyclerView recyclerView;
    private ImageRepository imageRepository;
    private ImageAdapter imageAdapter;
    private List<Image> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        nestedScrollView = findViewById(R.id.scroll_view);
//        recyclerViewshimmer = findViewById(R.id.recycler_view_shimmer);
//        progressBar = findViewById(R.id.progress_bar);
//        shimmerFrameLayout = findViewById(R.id.shimmer_layout);


        imageAdapter = new ImageAdapter(this, imageList);

        imageList = new ArrayList<>();

        imageRepository = new ImageRepository(getApplication());

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        imageViewModel.getAllImages().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> imageList) {
//                Toast.makeText(MainActivity.this, "Working fine!", Toast.LENGTH_LONG).show();
                imageAdapter.getAllImages(imageList);
                recyclerView.setAdapter(imageAdapter);
                Log.d("main", "onChanged: " + imageList);
            }
        });

        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Image>> call = api.getAllImages();
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if(response.isSuccessful()) {
                    imageRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}