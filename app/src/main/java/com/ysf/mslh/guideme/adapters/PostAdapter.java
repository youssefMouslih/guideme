package com.ysf.mslh.guideme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_post_card, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = postList.get(position);

        // Set the random username, location, and other data
        holder.usernameTextView.setText(post.getUsername());
        holder.locationTextView.setText(post.getLocation());
        holder.postTextView.setText(post.getPostText());
        holder.timeTextView.setText(post.getTimePost());

        // Load the random image using Glide or Picasso
        Glide.with(context)
                .load(post.getImageUrl())
                .into(holder.profileImageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, locationTextView, postTextView, timeTextView;
        ImageView profileImageView;

        public PostViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.tvUsername);
            locationTextView = itemView.findViewById(R.id.tvlocation);
            postTextView = itemView.findViewById(R.id.tvPostText);
            timeTextView = itemView.findViewById(R.id.tvTimePost);
            profileImageView = itemView.findViewById(R.id.imgUser);
        }
    }
}
