package udit.android.twininterviewtask;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import udit.android.twininterviewtask.adapter.MainAdapter;
import udit.android.twininterviewtask.model.CacheFood;
import udit.android.twininterviewtask.model.Food;
import udit.android.twininterviewtask.utils.JSONReader;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    SearchView searchView;
    List<CacheFood> cacheFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
//        etSearch = findViewById(R.id.et_search);
        cacheFoodList = new ArrayList<>();
        mainAdapter = new MainAdapter(cacheFoodList);
        recyclerView = findViewById(R.id.rv_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);
        readJSONFromAssets();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mainAdapter != null) {
                    searchQuery(newText);
                }
                return false;
            }
        });

    }

    private void searchQuery(String newText) {

        String category = "";
        List<CacheFood> cacheFoods = new ArrayList<>();
        List<Food> filteredFoodList = new ArrayList<>();

        if (TextUtils.isEmpty(newText)) {
            mainAdapter.setCacheFood(cacheFoodList);
        } else {
            for (CacheFood cacheFood : cacheFoodList) {
                List<Food> foodList = cacheFood.getFoodList();

                for (Food food : foodList) {
                    if (food.getTitle().contains(newText)) {
                        category = cacheFood.getCategoryName();
                        filteredFoodList.add(food);
                    }
                }
            }

            cacheFoods.add(new CacheFood(category, filteredFoodList));
            mainAdapter.setCacheFood(cacheFoods);
        }
    }

    private void readJSONFromAssets() {
        String jsonFileString = JSONReader.getJsonFromAssets(getApplicationContext(), "food.json");
        HashMap<Boolean, List<Food>> foodHashMap = new HashMap<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonFileString);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (foodHashMap.containsKey(jsonObject.getBoolean("is_recent"))) {
                    List<Food> foodList = foodHashMap.get(jsonObject.getBoolean("is_recent"));
                    foodList.add(new Food(jsonObject.getString("id"), jsonObject.getString("title"), jsonObject.getString("image_url"), jsonObject.getBoolean("is_recent"), jsonObject.getBoolean("is_tfy")));
                    foodHashMap.put(jsonObject.getBoolean("is_recent"), foodList);
                } else {
                    List<Food> foodList = new ArrayList<>();
                    foodList.add(new Food(jsonObject.getString("id"), jsonObject.getString("title"), jsonObject.getString("image_url"), jsonObject.getBoolean("is_recent"), jsonObject.getBoolean("is_tfy")));
                    foodHashMap.put(jsonObject.getBoolean("is_recent"), foodList);
                }
            }
            for (Boolean category : foodHashMap.keySet()) {
                if (category) {
                    cacheFoodList.add(new CacheFood("Recent", foodHashMap.get(true)));
                } else {
                    cacheFoodList.add(new CacheFood("Non Recent", foodHashMap.get(false)));
                }
            }
            mainAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.submit_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submit:
                Toast.makeText(this, "Selection Submitted", Toast.LENGTH_SHORT).show();
                mainAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}