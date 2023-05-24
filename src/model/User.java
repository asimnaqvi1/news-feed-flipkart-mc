package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String name;
    private Set<String> following;
    private boolean hasLogin;
    private List<String> feeds;

    public User(String name){
        this.name = name;
        this.following = new HashSet<>();
        this.hasLogin = false;
        this.feeds = new ArrayList<>();
    }

    public void loginUser(){
        this.hasLogin = true;
    }

    public void logoutUser(){
        this.hasLogin = false;
    }

    public void followUser(String user){
        this.following.add(user);
    }

    public String getName(){
        return this.name;
    }

    public boolean isHasLogin(){
        return this.hasLogin;
    }

    public void postFeed(String feed){
        this.feeds.add(feed);
    }

    public Set<String> getFollowing(){
        return this.following;
    }
}
