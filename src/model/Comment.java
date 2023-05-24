package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Comment {
    String id;
    User user;
    String message;
    List<String> comments;
    List<Vote> votes;

    public Comment(User user, String message){
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.message = message;
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public void addComment(String commentId){
        this.comments.add(commentId);
    }

    public String getId(){
        return this.id;
    }


    public String toString(){
        return this.message + " replies : " + comments.toString();
    }
}
