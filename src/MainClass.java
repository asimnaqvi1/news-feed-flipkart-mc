import model.Comment;
import model.Feed;
import model.VoteType;
import service.NeedFeedService;

import java.util.List;

public class MainClass {
    public static void main(String[] args) throws Exception{

        NeedFeedService needFeedService = NeedFeedService.INSTANCE;

        // REGISTER
        needFeedService.registerUser("lucious");
        needFeedService.registerUser("albus");
        needFeedService.registerUser("tom");


        // LOGIN
        needFeedService.loginUser("tom");
        needFeedService.loginUser("albus");


        // POST FEEDS
        Feed feed1 = needFeedService
                .postFeed("tom", "feed1 by tom");
        Feed feed2 = needFeedService
                .postFeed("tom", "feed2 by tom");

        Thread.sleep(100);

        Feed feed3 = needFeedService
                .postFeed("albus", "feed3 by albus");
        Feed feed4 = needFeedService
                .postFeed("albus", "feed4 by albus");

        // NEW LOGIN
        needFeedService.loginUser("lucious");

        // UPVOTE feed2
        needFeedService.vote(feed2.getId(), "lucious", VoteType.UP_VOTE);

        // FOLLOWING
        needFeedService.follow("lucious", "albus");
        // needFeedService.follow("lucious", "tom");

        // SHOW NEWS FEED
        List<Feed> feedList = needFeedService.showNewsFeeds("lucious");
        for(Feed feed: feedList)
            System.out.println(feed);


        // COMMENTS on feed1
        Comment luciousFirstComment = needFeedService
                .comment(feed1.getId(), "lucious", "Shut up TOM!");
        Comment tomsReply = needFeedService
                .commentOnComment(luciousFirstComment.getId(), "tom", "Shut up LUCIOUS!");

        System.out.println(feed1.getComments());
    }
}
