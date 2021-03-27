package MovieReviewSystem.Reviews;

import MovieReviewSystem.Users.UserType;

public class Review {

    private String userName;

    private String movieName;

    private int score;

    private UserType userType;

    public Review(String userName, String movieName, int score, UserType userType) {
        this.userName = userName;
        this.movieName = movieName;
        this.score = score;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
