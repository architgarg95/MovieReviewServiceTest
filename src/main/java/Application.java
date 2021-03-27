import MovieReviewSystem.MovieReviewSystemImpl;
import MovieReviewSystem.MoviewReviewSystem;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        MoviewReviewSystem movieReviewSystem = new MovieReviewSystemImpl();

        movieReviewSystem.addMovie("Don", 2006, Arrays.asList("Action","Comedy"));
        movieReviewSystem.addMovie("Tiger", 2008, Arrays.asList("Drama"));
        movieReviewSystem.addMovie("Padmavat", 2006, Arrays.asList("Comedy"));
        movieReviewSystem.addMovie("Lunchbox", 2022, Arrays.asList("Drama"));
        movieReviewSystem.addMovie("Guru", 2006, Arrays.asList("Drama"));
        movieReviewSystem.addMovie("Metro", 2006, Arrays.asList("Romance"));

        movieReviewSystem.addUser("SRK");
        movieReviewSystem.addUser("Salman");
        movieReviewSystem.addUser("Deepika");

        movieReviewSystem.addReview("SRK", "Don", 2);
        movieReviewSystem.addReview("SRK", "Padmavat", 8);
        movieReviewSystem.addReview("Salman", "Don", 5);
        movieReviewSystem.addReview("Deepika", "Don", 9);
        movieReviewSystem.addReview("Deepika", "Guru", 6);
        movieReviewSystem.addReview("SRK", "Don", 10);
        movieReviewSystem.addReview("Deepika", "Lunchbox", 5);
        movieReviewSystem.addReview("SRK", "Tiger", 5);
        movieReviewSystem.addReview("SRK", "Metro", 7);

        movieReviewSystem.topMoviesByViewerScoreAndYear(2006, 1);
        movieReviewSystem.topMoviesByCriticScoreAndYear(2006, 1);
        movieReviewSystem.topMoviesByViewerScoreAndGenre("Drama", 1);
        movieReviewSystem.averageScoreByYear(2006);
    }

}
