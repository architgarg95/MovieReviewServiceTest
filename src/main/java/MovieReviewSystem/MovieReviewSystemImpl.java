package MovieReviewSystem;


import MovieReviewSystem.Movies.Movie;
import MovieReviewSystem.Movies.repository.MovieRepository;
import MovieReviewSystem.Movies.repository.MovieRepositoryInMemory;
import MovieReviewSystem.Reviews.Review;
import MovieReviewSystem.Reviews.repository.ReviewRepository;
import MovieReviewSystem.Reviews.repository.ReviewsRepositoryInMemory;
import MovieReviewSystem.Users.User;
import MovieReviewSystem.Users.UserType;
import MovieReviewSystem.Users.repository.UserRepository;
import MovieReviewSystem.Users.repository.UserRepositoryInMemory;

import java.util.*;

public class MovieReviewSystemImpl implements MoviewReviewSystem {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private final Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            return b.getValue() - a.getValue();
        }
    };

    public MovieReviewSystemImpl() {
        this.movieRepository = new MovieRepositoryInMemory();
        this.userRepository = new UserRepositoryInMemory();
        this.reviewRepository = new ReviewsRepositoryInMemory();
    }

    public void addMovie (String movieName, int year, List<String> genre) {
        Movie movie = new Movie(movieName, year, genre);
        movieRepository.save(movie);
        System.out.println("Movie Added Successfully");
    }

    public void addUser (String userName) {
        User user = userRepository.getByName(userName);
        if (user != null) {
            System.out.println("User Already Present");
            return;
        }
        user = new User(userName, UserType.VIEWER);
        userRepository.save(user);
        System.out.println("User Added Successfully");
    }

    public void addReview (String userName, String movieName, int score) {
        // Invalid score check
        if (score < 0 || score > 10) {
            System.out.println("Invalid Score");
            return;
        }

        // Invalid User Check
        User user = userRepository.getByName(userName);
        if (user == null) {
            System.out.println("User Not Found");
            return;
        }

        // Inavlid Movie Check
        Movie movie = movieRepository.getByName(movieName);
        if (movie == null) {
            System.out.println("Movie Not Found");
            return;
        }
        if (movie.getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            System.out.println("Movie yet to be released.");
            return;
        }

        // find if already reviewed this movie
        List<Review> reviews = reviewRepository.getByUser(userName);
        if (reviews.stream().anyMatch(k -> k.getMovieName().equals(movieName))) {
            System.out.println("Cannot provide review for a movie twice");
            return;
        }

        Review review = new Review(userName, movieName, score, user.getUserType());
        reviewRepository.save(review);
        System.out.println("Review Added Successfully");

        upgradeUser(user, reviews.size()+1);
    }

    private void upgradeUser (User user, int reviewsCount) {
        if (reviewsCount > 30) {
            user.setUserType(UserType.ADMIN);
        }
        else if (reviewsCount > 10) {
            user.setUserType(UserType.EXPERT);
        }
        else if (reviewsCount > 2) {
            user.setUserType(UserType.CRITIC);
        }

        userRepository.save(user);
    }

    public void topMoviesByViewerScoreAndYear (int year, int n) {
        List<Movie> movies = movieRepository.getByYear(year);

        List<Map.Entry<String, Integer>> moviesScoreList = new ArrayList<>();
        for (Movie movie : movies) {
            List<Review> reviews = reviewRepository.getByMovie(movie.getName());
            int movieScore = 0;
            for (Review review : reviews) {
                if (review.getUserType().equals(UserType.VIEWER)) {
                    movieScore += review.getScore() * review.getUserType().getScoreMultiplier();
                }
            }

            moviesScoreList.add(new AbstractMap.SimpleEntry(movie.getName(), movieScore));
        }

        moviesScoreList.sort(comparator);

        printN(moviesScoreList, n);
    }

    public void topMoviesByViewerScoreAndGenre (String genre, int n) {
        List<Movie> movies = movieRepository.getByGenre(genre);

        List<Map.Entry<String, Integer>> moviesScoreList = new ArrayList<>();
        for (Movie movie : movies) {
            List<Review> reviews = reviewRepository.getByMovie(movie.getName());
            int movieScore = 0;
            for (Review review : reviews) {
                if (review.getUserType().equals(UserType.VIEWER)) {
                    movieScore += review.getScore() * review.getUserType().getScoreMultiplier();
                }
            }

            moviesScoreList.add(new AbstractMap.SimpleEntry(movie.getName(), movieScore));
        }
        moviesScoreList.sort(comparator);
        printN(moviesScoreList, n);
    }


    public void topMoviesByCriticScoreAndYear (int year, int n) {
        List<Movie> movies = movieRepository.getByYear(year);

        List<Map.Entry<String, Integer>> moviesScoreList = new ArrayList<>();
        for (Movie movie : movies) {
            List<Review> reviews = reviewRepository.getByMovie(movie.getName());
            int movieScore = 0;
            for (Review review : reviews) {
                if (review.getUserType().equals(UserType.CRITIC)) {
                    movieScore += review.getScore() * review.getUserType().getScoreMultiplier();
                }
            }

            moviesScoreList.add(new AbstractMap.SimpleEntry(movie.getName(), movieScore));
        }

        moviesScoreList.sort(comparator);

        printN(moviesScoreList, n);
    }

    public void topMoviesByCriticScoreAndGenre (String genre, int n) {
        List<Movie> movies = movieRepository.getByGenre(genre);

        List<Map.Entry<String, Integer>> moviesScoreList = new ArrayList<>();
        for (Movie movie : movies) {
            List<Review> reviews = reviewRepository.getByMovie(movie.getName());
            int movieScore = 0;
            for (Review review : reviews) {
                if (review.getUserType().equals(UserType.CRITIC)) {
                    movieScore += review.getScore() * review.getUserType().getScoreMultiplier();
                }
            }

            moviesScoreList.add(new AbstractMap.SimpleEntry(movie.getName(), movieScore));
        }
        moviesScoreList.sort(comparator);
        printN(moviesScoreList, n);
    }


    public void averageScoreByYear (int year) {
        List<Movie> movies = movieRepository.getByYear(year);
        int totalScore = 0;
        int reviewsCount = 0;
        for (Movie movie : movies) {
            List<Review> reviews = reviewRepository.getByMovie(movie.getName());
            for (Review review : reviews) {
                totalScore += review.getScore() * review.getUserType().getScoreMultiplier();
            }
            reviewsCount += reviews.size();
        }

        System.out.println("Average Score for year " + year + " : " + ((double)totalScore/reviewsCount));
    }

    public void averageScoreByGenre (String genre) {
        List<Movie> movies = movieRepository.getByGenre(genre);
        int totalScore = 0;
        int reviewsCount = 0;
        for (Movie movie : movies) {
            List<Review> reviews = reviewRepository.getByMovie(movie.getName());
            for (Review review : reviews) {
                totalScore += review.getScore() * review.getUserType().getScoreMultiplier();
            }
            reviewsCount += reviews.size();
        }

        System.out.println("Average Score for genre " + genre + " : " + ((double)totalScore/reviewsCount));
    }

    public void averageScoreByMovie (String movieName) {
        int totalScore = 0;
        int reviewsCount = 0;
        List<Review> reviews = reviewRepository.getByMovie(movieName);
        for (Review review : reviews) {
            totalScore += review.getScore() * review.getUserType().getScoreMultiplier();
        }
        reviewsCount += reviews.size();

        System.out.println("Average Score for movie " + movieName + " : " + ((double)totalScore/reviewsCount));
    }


    private void printN (List<Map.Entry<String, Integer>> moviesWithScore, int n) {
        for (int  i = 0; i < moviesWithScore.size() && i < n; i++) {
            Map.Entry<String, Integer> movie = moviesWithScore.get(i);
            System.out.println(movie.getKey() + "-" + movie.getValue() + " ratings");
        }
    }




}
