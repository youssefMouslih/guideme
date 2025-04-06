package com.ysf.mslh.guideme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.models.UserStory;

import java.util.List;

public class UserStoryAdapter extends RecyclerView.Adapter<UserStoryAdapter.UserViewHolder> {

    private Context context;
    private List<UserStory> userList;

    public UserStoryAdapter(Context context, List<UserStory> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_story_card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserStory user = userList.get(position);
        holder.userName.setText(user.getUserName());

        // Use an image loading library like Glide to load the image from URL
        Glide.with(context)
                .load(user.getImageUrl())
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        ImageView userImage;

        public UserViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tvUser);
            userImage = itemView.findViewById(R.id.imgUser);
        }
    }
}