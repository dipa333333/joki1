package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {

    private Context context;
    private List<House> houseList;

    public HouseAdapter(Context context, List<House> houseList) {
        this.context = context;
        this.houseList = houseList;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_house, parent, false);
        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        House house = houseList.get(position);

        holder.tvHouseTitle.setText(house.getTitle());
        holder.tvHousePrice.setText(house.getPrice());

        // Cek apakah gambar URI tersedia dari galeri
        if (house.getImageUri() != null && !house.getImageUri().isEmpty()) {
            holder.ivHouseImage.setImageURI(Uri.parse(house.getImageUri()));
        } else {
            // Gambar default jika user tidak memilih foto
            holder.ivHouseImage.setImageResource(R.mipmap.ic_launcher);
        }

        // Logic klik untuk pergi ke DetailActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("EXTRA_HOUSE", house);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

    public static class HouseViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHouseImage;
        TextView tvHouseTitle, tvHousePrice;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHouseImage = itemView.findViewById(R.id.ivHouseImage);
            tvHouseTitle = itemView.findViewById(R.id.tvHouseTitle);
            tvHousePrice = itemView.findViewById(R.id.tvHousePrice);
        }
    }
}