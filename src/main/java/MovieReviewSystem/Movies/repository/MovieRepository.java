package MovieReviewSystem.Movies.repository;

import MovieReviewSystem.Movies.Movie;

import java.util.List;

public interface MovieRepository {

    public void save (Movie movie);

    public Movie getByName (String movieName);

    public List<Movie> getByGenre (String genre);

    public List<Movie> getByYear (int year);

}
