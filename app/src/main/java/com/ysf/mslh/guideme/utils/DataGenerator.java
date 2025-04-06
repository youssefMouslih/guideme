package com.ysf.mslh.guideme.utils;

import com.ysf.mslh.guideme.models.UserStory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<UserStory> generateRandomUsers(int count) {
        List<UserStory> userList = new ArrayList<>();
        Random random = new Random();

        String[] sampleNames = {"Ali", "Omar", "Ahmed", "Yassine", "Sara", "Mouad", "Nour", "Fatima"};
        String[] imageUrls = {
                "https://placekitten.com/70/70",
                "https://placeimg.com/70/70/animals",
                "https://placeimg.com/70/70/tech",
                "https://placeimg.com/70/70/people"
        };

        for (int i = 0; i < count; i++) {
            String randomName = sampleNames[random.nextInt(sampleNames.length)];
            String randomImageUrl = imageUrls[random.nextInt(imageUrls.length)];
            userList.add(new UserStory(randomName, randomImageUrl));
        }
        return userList;
    }
}
