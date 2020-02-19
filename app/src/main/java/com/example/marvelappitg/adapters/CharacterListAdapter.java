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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.marvelappitg.R;
import com.example.marvelappitg.models.modelcharacterlist.Result;
import com.example.marvelappitg.utlitites.Constant;
import com.example.marvelappitg.views.activity.CharacterDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {


    private List<Result> items;
    private Context context;


    public CharacterListAdapter(List<Result> items, Context context) {

        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.character_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.tvCharactername.setText(items.get(position).getName());
        String path=items.get(position).getThumbnail().getPath()+"."+items.get(position).getThumbnail().getExtension();

        RequestOptions options = new RequestOptions()
                .override(ViewGroup.LayoutParams.MATCH_PARENT,150)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(context).load(path).apply(options).into(holder.imgCharacter);

        holder.tvCharactername.setOnClickListener(v -> {
            Intent intent=new Intent(context, CharacterDetailsActivity.class);
            intent.putExtra(Constant.characterDetails,items.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Result> getAllData() {
        return items;
    }

    public void addAll(List<Result> newitems) {
        int startIndex = items.size();
        items.addAll(newitems);
        notifyItemRangeInserted(startIndex, newitems.size());
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
