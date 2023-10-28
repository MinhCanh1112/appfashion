package com.example.appfashion.View.Search;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfashion.Adapter.SanphamAdapter;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.R;
import com.example.appfashion.View.Home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerTimKiem;
    SanphamAdapter sanphamAdapter;
    List<ModelSanpham> sanphamList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhxa();
        ActionToolBar();
        sanphamList = HomeActivity.sanphams;

    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbar_search);
        recyclerTimKiem = findViewById(R.id.recyclerTimKiem);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) itSearch.getActionView();
        searchView.setIconified(false);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    recyclerTimKiem.setVisibility(View.VISIBLE);
                    LinearLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this,2);
                    recyclerTimKiem.setLayoutManager(layoutManager);
                    sanphamAdapter = new SanphamAdapter(SearchActivity.this,sanphamList);
                    recyclerTimKiem.setAdapter(sanphamAdapter);
                    sanphamAdapter.notifyDataSetChanged();
                    filterList(newText);
                } else {
                    recyclerTimKiem.setVisibility(View.INVISIBLE);
                }
                return true;
            }
        });
        return true;
    }

    private void filterList(String text) {
        List<ModelSanpham> filteredList = new ArrayList<>();
        for(ModelSanpham sanpham : sanphamList){
            if(sanpham.getTensp().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(sanpham);
            }
        }

        if(filteredList.isEmpty()){
//            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }else{
            sanphamAdapter.setFilteredList(filteredList);
        }
    }

}