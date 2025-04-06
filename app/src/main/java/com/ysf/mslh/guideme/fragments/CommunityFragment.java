package com.ysf.mslh.guideme.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ysf.mslh.guideme.utils.DataGenerator;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.models.UserStory;
import com.ysf.mslh.guideme.adapters.UserStoryAdapter;
import com.ysf.mslh.guideme.adapters.PostAdapter;
import com.ysf.mslh.guideme.models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommunityFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserStoryAdapter userAdapter;
    private List<UserStory> userList;

    private RecyclerView recyclerView1;
    private PostAdapter postAdapter;
    private List<Post> postList;

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        // Initialize RecyclerView for User Stories
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Generate random data for users
        userList = DataGenerator.generateRandomUsers(20);  // Generate 20 random users
        userAdapter = new UserStoryAdapter(getContext(), userList);
        recyclerView.setAdapter(userAdapter);

        // Initialize RecyclerView for Posts
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Generate random posts data
        postList = generateRandomPosts();
        postAdapter = new PostAdapter(getContext(), postList);
        recyclerView1.setAdapter(postAdapter);
        Log.d("CommunityFragment", "User List Size: " + userList.size());
        Log.d("CommunityFragment", "Post List Size: " + postList.size());

        return view;
    }

    private List<Post> generateRandomPosts() {
        List<Post> posts = new ArrayList<>();
        String[] usernames = {"Alice", "Bob", "Charlie", "Dave", "Eve"};
        String[] locations = {"New York", "London", "Tokyo", "Paris", "Berlin"};
        String[] postTexts = {
                "Just having a great day!",
                "Feeling blessed to be here.",
                "Enjoying the weather!",
                "Had a wonderful time with friends.",
                "Excited for the future!"
        };
        String[] imageUrls = {
                "https://www.example.com/image1.jpg",
                "https://www.example.com/image2.jpg",
                "https://www.example.com/image3.jpg",
                "https://www.example.com/image4.jpg",
                "https://www.example.com/image5.jpg"
        };
        String[] times = {"1 hour ago", "2 hours ago", "3 hours ago", "4 hours ago", "5 hours ago"};

        Random random = new Random();
        for (int i = 0; i < 10; i++) { // Generate 10 random posts
            String username = usernames[random.nextInt(usernames.length)];
            String location = locations[random.nextInt(locations.length)];
            String postText = postTexts[random.nextInt(postTexts.length)];
            String imageUrl = imageUrls[random.nextInt(imageUrls.length)];
            String timePost = times[random.nextInt(times.length)];

            posts.add(new Post(username, location, imageUrl, postText, timePost));
        }
        return posts;
    }
}
