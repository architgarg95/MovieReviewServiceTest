package MovieReviewSystem;

import MovieReviewSystem.Movies.Movie;
import MovieReviewSystem.Reviews.Review;
import MovieReviewSystem.Users.User;
import MovieReviewSystem.Users.UserType;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MoviewReviewSystem {

    public void addMovie (String movieName, int year, List<String> genre);

    public void addUser (String userName);

    public void addReview (String userName, String movieName, int score);

    public void topMoviesByViewerScoreAndYear (int year, int n);

    public void topMoviesByViewerScoreAndGenre (String genre, int n);

    public void topMoviesByCriticScoreAndYear (int year, int n);

    public void topMoviesByCriticScoreAndGenre (String genre, int n);

    public void averageScoreByYear (int year);

    public void averageScoreByGenre (String genre);

    public void averageScoreByMovie (String movieName);

}
