package com.example.assignmentandroid.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignmentandroid.model.Fact;
import com.example.assignmentandroid.R;

import java.util.List;

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.HeroViewHolder> {
    Context mCtx;
    List<Fact> factList;

    public FactAdapter(Context mCtx, List<Fact> factList) {
        this.mCtx = mCtx;
        this.factList = factList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.fact_item, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Fact fact = factList.get(position);

        String img = fact.getImageHref();
        String tittle = fact.getTitle();
        String description = fact.getDescription();

        if (img == null && tittle == null && description == null) {
            holder.imageView.setVisibility(View.GONE);
            holder.titleView.setVisibility(View.GONE);
            holder.descriptionView.setVisibility(View.GONE);
            holder.cardView.setVisibility(View.GONE);
        }


        Glide.with(mCtx)
                .load(fact.getImageHref())
                .into(holder.imageView);

        holder.titleView.setText(fact.getTitle());
        holder.descriptionView.setText(fact.getDescription());
    }

    @Override
    public int getItemCount() {
        return factList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleView;
        TextView descriptionView;
        CardView cardView;

        public HeroViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleView = itemView.findViewById(R.id.titleView);
            descriptionView = itemView.findViewById(R.id.descriptionView);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
