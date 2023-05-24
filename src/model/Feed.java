package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Feed {
    String id;
    User user;
    String description;
    List<String> comments;
    List<Vote> votes;
    long createdAt;

    public Feed(String feedMessage, User user){
        this.id = UUID.randomUUID().toString();
        this.description = feedMessage;
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.user = user;
        this.createdAt = System.currentTimeMillis();
    }

    public void addVote(Vote vote){
        this.votes.add(vote);
    }

    public void addComment(String comment){
        this.comments.add(comment);
    }


    public List<Vote> getVotes(){
        return this.votes;
    }

    public int getVoteCount(){
        int votes = 0;
        for(Vote vote: this.votes){
            if(vote.getVoteType() == VoteType.UP_VOTE)
                votes += 1;
        }
        for(Vote vote: this.votes){
            if(vote.getVoteType() == VoteType.DOWN_VOTE)
                votes -= 1;
        }
        return votes;
    }

    public int getCommentCount(){
        return comments.size();
    }

    public long getCreatedAt(){
        return this.createdAt;
    }

    public User getUser(){
        return this.user;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return "POSTED_BY: "+ this.user.getName() + " MESSAGE:" + this.description;
    }

    public List<String> getComments(){
        return this.comments;
    }
}
