package MovieReviewSystem.Reviews.repository;

import MovieReviewSystem.Reviews.Review;

import java.util.*;
import java.util.stream.Collectors;

public class ReviewsRepositoryInMemory implements ReviewRepository {

    private static List<Review> reviews;
    private static Map<String, List<Integer>> movieReviewMap;
    private static Map<String, List<Integer>> userReviewMap;

    public ReviewsRepositoryInMemory() {
        reviews = new ArrayList<>();
        movieReviewMap = new HashMap<>();
        userReviewMap = new HashMap<>();
    }

    public void save (Review review) {
        int index = reviews.size();
        reviews.add(review);

        List<Integer> movieReviewsList = movieReviewMap.computeIfAbsent(review.getMovieName(), k -> new ArrayList<>());
        movieReviewsList.add(index);

        List<Integer> userReviewsList = userReviewMap.computeIfAbsent(review.getUserName(), k -> new ArrayList<>());
        userReviewsList.add(index);
    }

    public List<Review> getAll () {
        return reviews;
    }

    public List<Review> getByMovie (String movieName) {
        List<Integer> movieReviewsList = movieReviewMap.getOrDefault(movieName, Collections.emptyList());
        return movieReviewsList.stream().map(k -> reviews.get(k)).collect(Collectors.toList());
    }

    public List<Review> getByUser (String userName) {
        List<Integer> userReviewsList = userReviewMap.getOrDefault(userName, Collections.emptyList());
        return userReviewsList.stream().map(k -> reviews.get(k)).collect(Collectors.toList());
    }

}
