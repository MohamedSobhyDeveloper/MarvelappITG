package com.example.marvelappitg.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelappitg.R;
import com.example.marvelappitg.models.modelcharacterlist.Itemcomics;
import com.example.marvelappitg.models.modelcharacterlist.Itemevents;
import com.example.marvelappitg.models.modelcharacterlist.Itemseries;
import com.example.marvelappitg.models.modelcharacterlist.Itemstories;
import com.example.marvelappitg.utlitites.DataEnum;
import com.example.marvelappitg.views.activity.ShowImageActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InflateListAdapter extends RecyclerView.Adapter<InflateListAdapter.ViewHolder> {



    private List<Itemcomics> itemsComics;
    private List<Itemseries> itemsSeries;
    private List<Itemstories> itemsStories;
    private List<Itemevents> itemsEvents;

    private Context context;


    public InflateListAdapter(List<Itemcomics> itemcomics, List<Itemseries> itemseries, List<Itemstories> itemsStories, List<Itemevents> itemevents, Context context) {

        this.itemsComics = itemcomics;
        this.itemsSeries = itemseries;
        this.itemsStories = itemsStories;
        this.itemsEvents = itemevents;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (itemsComics != null) {

            holder.tvCharactername.setText(itemsComics.get(position).getName());

        } else if (itemsSeries != null) {
            holder.tvCharactername.setText(itemsSeries.get(position).getName());

        } else if (itemsStories != null) {
            holder.tvCharactername.setText(itemsStories.get(position).getName());

        } else {
            holder.tvCharactername.setText(itemsEvents.get(position).getName());

        }

        holder.imgCharacter.setOnClickListener(v -> {
            if (itemsComics != null) {

                Intent intent=new Intent(context, ShowImageActivity.class);
                intent.putExtra("url",itemsComics.get(position).getResourceURI());
                intent.putExtra("type", DataEnum.CallComicsDetails.name());
                context.startActivity(intent);
            } else if (itemsSeries != null) {
                Intent intent=new Intent(context, ShowImageActivity.class);
                intent.putExtra("url",itemsSeries.get(position).getResourceURI());
                intent.putExtra("type",DataEnum.CallseriesDetails.name());
                context.startActivity(intent);
            } else if (itemsStories != null) {
//                    Intent intent=new Intent(context, ShowImageActivity.class);
//                    intent.putExtra("url",itemsComics.get(position).getResourceURI());
//                    intent.putExtra("type",DataEnum.CallstoriesDetails.name());
//                    context.startActivity(intent);
            } else {
                Intent intent=new Intent(context, ShowImageActivity.class);
                intent.putExtra("url",itemsEvents.get(position).getResourceURI());
                intent.putExtra("type",DataEnum.CalleventDetails.name());
                context.startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        if (itemsComics != null) {
            return itemsComics.size();

        } else if (itemsSeries != null) {
            return itemsSeries.size();

        } else if (itemsStories != null) {
            return itemsStories.size();

        } else {
            return itemsEvents.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgCharacter)
        ImageView imgCharacter;
        @BindView(R.id.tvCharactername)
        TextView tvCharactername;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
