package udit.android.twininterviewtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import udit.android.twininterviewtask.R;
import udit.android.twininterviewtask.model.CacheFood;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    List<CacheFood> foodList;
    Context mContext;

    public MainAdapter(List<CacheFood> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_food_item_view, parent, false);
        mContext = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CacheFood cacheFood = foodList.get(position);
        holder.textView.setText(cacheFood.getCategoryName());

        NestedFoodAdapter nestedFoodAdapter = new NestedFoodAdapter(cacheFood.getFoodList(), mContext);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(nestedFoodAdapter);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setCacheFood(List<CacheFood> cacheFood) {
        foodList = cacheFood;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_heading);
            recyclerView = itemView.findViewById(R.id.rv_internal_food);
        }
    }
}
