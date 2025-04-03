package com.ysf.mslh.guideme.fragmentsExperience;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.ysf.mslh.guideme.R;

public class ExperienceVideos extends Fragment {

    private PlayerView videoPlayer;
    private ExoPlayer exoPlayer;

    public ExperienceVideos() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_videos, container, false);
        videoPlayer = view.findViewById(R.id.videoPlayer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialiser ExoPlayer
        exoPlayer = new ExoPlayer.Builder(requireContext()).build();
        videoPlayer.setPlayer(exoPlayer);

        // Charger une vidéo (remplace l'URL avec ton propre fichier ou lien)
        Uri videoUri = Uri.parse("https://www.example.com/sample.mp4");
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
