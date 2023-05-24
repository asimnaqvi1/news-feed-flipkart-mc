package util;

import model.Feed;
import model.Vote;
import model.VoteType;

import java.util.Comparator;
import java.util.List;

public class FeedComparator implements Comparator<Feed> {
    @Override
    public int compare(Feed f1, Feed f2) {
        int f1Votes = 0;
        if(f1.getVoteCount() > f2.getVoteCount())
            return -1;
        if(f2.getVoteCount() > f1.getVoteCount())
            return 1;

        if(f1.getCommentCount() > f2.getCommentCount())
            return -1;
        if(f2.getCommentCount() > f1.getCommentCount())
            return 1;

        if(f2.getCreatedAt() < f1.getCreatedAt())
            return 1;
        if(f1.getCreatedAt() < f2.getCreatedAt())
            return -1;

        return 0;
    }
}
