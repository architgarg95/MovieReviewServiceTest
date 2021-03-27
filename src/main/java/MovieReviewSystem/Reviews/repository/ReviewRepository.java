package MovieReviewSystem.Reviews.repository;

import MovieReviewSystem.Reviews.Review;

import java.util.List;

public interface ReviewRepository {

    public void save (Review review);

    public List<Review> getAll ();

    public List<Review> getByMovie (String movieName);

    public List<Review> getByUser (String userName);
}
