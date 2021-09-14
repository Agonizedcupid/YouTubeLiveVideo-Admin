package com.aariyan.youtubevideoadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.youtubevideoadmin.Model.VideoModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<VideoModel> list;
    public Adapter (Context context, List<VideoModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VideoModel model = list.get(position);

        holder.title.setText(model.getVideoName());
        holder.description.setText(model.getVideoDescription());

        // Youtube thumnail link is like
        //https://i.ytimg.com/vi/<VIDEO ID>/0.jpg
        String thumb_link = "https://i.ytimg.com/vi/" + model.getVideoUrl() + "/0.jpg";
        Picasso.get().
                load("" + thumb_link)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.thumbnailImage);

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Video").child(model.getId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        notifyDataSetChanged();
                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView thumbnailImage;
        private RelativeLayout layItem;
        private ImageView deleteIcon;

        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            title = convertView.findViewById(R.id.tvTitle);
            description = convertView.findViewById(R.id.tvDescription);
            thumbnailImage = convertView.findViewById(R.id.imgThumb);
            layItem = convertView.findViewById(R.id.layItem);
            deleteIcon = convertView.findViewById(R.id.deleteIcon);
        }
    }
}
