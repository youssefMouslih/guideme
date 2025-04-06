package com.ysf.mslh.guideme.models;

public class UserStory {
        private String userName;
        private String imageUrl;

        public UserStory(String userName, String imageUrl) {
            this.userName = userName;
            this.imageUrl = imageUrl;
        }

        public String getUserName() {
            return userName;
        }

        public String getImageUrl() {
            return imageUrl;
        }


}
