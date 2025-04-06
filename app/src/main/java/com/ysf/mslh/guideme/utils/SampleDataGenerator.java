package com.ysf.mslh.guideme.utils;

import com.ysf.mslh.guideme.models.MediaItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class to generate sample data for testing the UI
 */
public class SampleDataGenerator {

    private static final String[] PHOTO_URLS = {
            "https://images.unsplash.com/photo-1604537529428-15bcbeecfe4d",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470",
            "https://images.unsplash.com/photo-1600359746315-119f1b92c7e7",
            "https://images.unsplash.com/photo-1526772662000-3f88f10405ff",
            "https://images.unsplash.com/photo-1506905925346-21bda4d32df4",
            "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9",
            "https://images.unsplash.com/photo-1552733407-5d5c46c3bb3b",
            "https://images.unsplash.com/photo-1519125323398-675f0ddb6308",
            "https://images.unsplash.com/photo-1507525428034-b723cf961d3e"
    };

    private static final String[] VIDEO_URLS = {
            "https://images.unsplash.com/photo-1604537529428-15bcbeecfe4d",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470",
            "https://images.unsplash.com/photo-1600359746315-119f1b92c7e7",
            "https://images.unsplash.com/photo-1526772662000-3f88f10405ff",
            "https://images.unsplash.com/photo-1506905925346-21bda4d32df4",
            "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9"
    };

    private static final String[] FAVORITE_URLS = {
            "https://images.unsplash.com/photo-1506905925346-21bda4d32df4",
            "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9",
            "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee",
            "https://images.unsplash.com/photo-1597265120145-0179a92f06c5"
    };

    /**
     * Generate a list of photo media items
     * @param count Number of items to generate
     * @return List of photo media items
     */
    public static List<MediaItem> generatePhotoItems(int count) {
        List<MediaItem> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String url = PHOTO_URLS[random.nextInt(PHOTO_URLS.length)];
            items.add(new MediaItem("photo_" + i, url, false));
        }

        return items;
    }

    /**
     * Generate a list of video media items
     * @param count Number of items to generate
     * @return List of video media items
     */
    public static List<MediaItem> generateVideoItems(int count) {
        List<MediaItem> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String url = VIDEO_URLS[random.nextInt(VIDEO_URLS.length)];
            items.add(new MediaItem("video_" + i, url, true));
        }

        return items;
    }

    /**
     * Generate a list of favorite media items
     * @param count Number of items to generate
     * @return List of favorite media items
     */
    public static List<MediaItem> generateFavoriteItems(int count) {
        List<MediaItem> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String url = FAVORITE_URLS[random.nextInt(FAVORITE_URLS.length)];
            boolean isVideo = random.nextBoolean();
            items.add(new MediaItem("favorite_" + i, url, isVideo));
        }

        return items;
    }
}