package com.ysf.mslh.guideme;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.MediaItem;
import java.util.List;
import java.util.Random;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private List<MediaItem> mediaItems;
    private boolean showPlayButton;
    private int[] backgroundColors = {
            Color.parseColor("#E57373"), // Red
            Color.parseColor("#81C784"), // Green
            Color.parseColor("#64B5F6"), // Blue
            Color.parseColor("#FFD54F"), // Yellow
            Color.parseColor("#BA68C8"), // Purple
            Color.parseColor("#4FC3F7"), // Light Blue
            Color.parseColor("#FF8A65")  // Orange
    };
    private Random random = new Random();

    public MediaAdapter(List<MediaItem> mediaItems, boolean showPlayButton) {
        this.mediaItems = mediaItems;
        this.showPlayButton = showPlayButton;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        MediaItem item = mediaItems.get(position);

        // Set a random background color for the ImageView
        holder.imgMedia.setBackgroundColor(backgroundColors[random.nextInt(backgroundColors.length)]);

        // Show or hide play button based on content type
        holder.imgPlayButton.setVisibility(showPlayButton ? View.VISIBLE : View.GONE);

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),
                    item.isVideo() ? "Playing video: " + item.getId() : "Viewing photo: " + item.getId(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mediaItems != null ? mediaItems.size() : 0;
    }

    static class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMedia;
        ImageView imgPlayButton;

        MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMedia = itemView.findViewById(R.id.imgMedia);
            imgPlayButton = itemView.findViewById(R.id.imgPlayButton);
        }
    }
}