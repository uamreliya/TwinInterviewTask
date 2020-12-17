package udit.android.twininterviewtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import udit.android.twininterviewtask.R;
import udit.android.twininterviewtask.model.Food;

public class NestedFoodAdapter extends RecyclerView.Adapter<NestedFoodAdapter.MyViewHolder> {

    private List<Food> foodList;
    private Context mContext;

    public NestedFoodAdapter(List<Food> foodList, Context mContext) {
        this.foodList = foodList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvFoodName.setText(foodList.get(position).getTitle());
        Glide.with(mContext).load(foodList.get(position).getImage_url()).into(holder.ivFood);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFood;
        TextView tvFoodName;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFood = itemView.findViewById(R.id.ivFoodImage);
            tvFoodName = itemView.findViewById(R.id.tvCustomChoice);
            checkBox = itemView.findViewById(R.id.ivSelection);
        }
    }
}
