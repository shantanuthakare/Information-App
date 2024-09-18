package com.example.informationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the data list
        dataList = new ArrayList<>();
        loadData();

        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        // Load initial data
        dataList.add(new DataClass("C.Ronaldo", R.string.ronaldo, "CR7", R.drawable.ronaldo));
        dataList.add(new DataClass("Messi", R.string.messi, "LM10", R.drawable.messi));
        dataList.add(new DataClass("Neymar", R.string.neymar, "NJR", R.drawable.neymar));
        dataList.add(new DataClass("Mount", R.string.mount, "M7", R.drawable.mount));
        dataList.add(new DataClass("Benzema", R.string.benzima, "KB9", R.drawable.ben));
    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }

    // Method to show all data
    public void allFilterTapped(View view) {
        adapter.setSearchList(dataList); // Show all data
    }

    // Method to filter and show only CR7 data
    public void allcr7(View view) {
        List<DataClass> filteredList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().equalsIgnoreCase("C.Ronaldo")) {
                filteredList.add(data);
            }
        }
        adapter.setSearchList(filteredList);
    }

    // Method to filter and show only Messi data
    public void messi(View view) {
        List<DataClass> filteredList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().equalsIgnoreCase("Messi")) {
                filteredList.add(data);
            }
        }
        adapter.setSearchList(filteredList);
    }

    // Method to filter and show only KB9 data (Benzema)
    public void KB9(View view) {
        List<DataClass> filteredList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().equalsIgnoreCase("Benzema")) {
                filteredList.add(data);
            }
        }
        adapter.setSearchList(filteredList);
    }
}
