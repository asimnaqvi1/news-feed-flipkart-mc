package service;

import exception.*;
import model.*;
import util.FeedComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NeedFeedService {
    Map<String, User> userData;
    Map<String, Feed> feedData;
    Map<String, Comment> commentData;

    public static NeedFeedService INSTANCE = new NeedFeedService();

    private NeedFeedService(){
        this.userData = new HashMap<>();
        this.feedData = new HashMap<>();
        this.commentData = new HashMap<>();
    }

    public void registerUser(String userName) {
        if (userData.containsKey(userName))
            throw new UserAlreadyRegisteredException();
        userData.put(userName, new User(userName));
    }

    public void loginUser(String userName) {
        if (!userData.containsKey(userName))
            throw new UserNotRegisteredException();
        User user = userData.get(userName);
        user.loginUser();
    }

    public Feed postFeed(String userName, String feedMessage) throws UserNotRegisteredException, UserNotLoginException {
        if (!isUserLogin(userName))
            throw new UserNotLoginException();
        User user = userData.get(userName);
        Feed feed = new Feed(feedMessage, user);
        user.postFeed(feed.getId());
        feedData.put(feed.getId(), feed);
        return feed;
    }

    public void follow(String follower, String followed) throws UserNotRegisteredException, UserNotLoginException {
        if (!isUserLogin(follower))
            throw new UserNotLoginException();

        if (!isUserRegistered(followed))
            throw new UserNotRegisteredException();

        User followerUser = userData.get(follower);
        User followedUser = userData.get(followed);
        followerUser.followUser(followedUser.getName());
    }

    public Comment comment(String feedId, String userName, String commentMessage) throws UserNotRegisteredException, UserNotLoginException {
        if (!feedData.containsKey(feedId))
            throw new FeedNotFoundException();

        if (!isUserLogin(userName))
            throw new UserNotLoginException();

        User user = userData.get(userName);
        Feed feed = feedData.get(feedId);
        Comment comment = new Comment(user, commentMessage);
        commentData.put(comment.getId(), comment);
        feed.addComment(comment.getId());
        return comment;
    }

    public Comment commentOnComment(String commentId, String userName, String commentMessage) throws UserNotRegisteredException, UserNotLoginException {
        if (!commentData.containsKey(commentId))
            throw new CommentNotFoundException();

        if (!isUserLogin(userName))
            throw new UserNotLoginException();

        User user = userData.get(userName);
        Comment comment = commentData.get(commentId);
        Comment nestedComment = new Comment(user, commentMessage);
        comment.addComment(nestedComment.getId());
        commentData.put(nestedComment.getId(), nestedComment);
        return nestedComment;
    }

    public Vote vote(String feedId, String userName, VoteType voteType) throws UserNotRegisteredException, UserNotLoginException {
        if (!feedData.containsKey(feedId))
            throw new FeedNotFoundException();

        if (!isUserLogin(userName))
            throw new UserNotLoginException();

        User user = userData.get(userName);
        Feed feed = feedData.get(feedId);
        Vote vote = new Vote(user, voteType);
        feed.addVote(vote);
        return vote;
    }

    public List<Feed> showNewsFeeds(String userName) throws UserNotRegisteredException, UserNotLoginException {
        if (!isUserLogin(userName))
            throw new UserNotLoginException();

        User user = userData.get(userName);
        List<Feed> feeds = feedData.values()
                .stream()
                .filter(feed -> user.getFollowing().contains(feed.getUser()))
                .sorted(new FeedComparator())
                .collect(Collectors.toList());
        List<Feed> notFollowedFeedList = feedData.values()
                .stream()
                .filter(feed -> !user.getFollowing().contains(feed.getUser()))
                .sorted(new FeedComparator())
                .collect(Collectors.toList());
        feeds.addAll(notFollowedFeedList);
        return feeds;
    }

    private boolean isUserRegistered(String userName) {
        return userData.containsKey(userName);
    }

    private boolean isUserLogin(String userName) throws UserNotRegisteredException {
        if (!isUserRegistered(userName))
            throw new UserNotRegisteredException();
        User user = userData.get(userName);
        return user.isHasLogin();
    }
}
