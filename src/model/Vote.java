package model;

import java.util.UUID;

public class Vote {
    String id;
    User user;
    VoteType voteType;

    public Vote(User user, VoteType voteType){
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.voteType = voteType;
    }

    public void upVote(){
        this.voteType = VoteType.UP_VOTE;
    }

    public void downVote(){
        this.voteType = VoteType.DOWN_VOTE;
    }

    public VoteType getVoteType(){
        return this.voteType;
    }
}
