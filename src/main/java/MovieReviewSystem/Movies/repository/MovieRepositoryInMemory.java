package MovieReviewSystem.Movies.repository;

import MovieReviewSystem.Movies.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieRepositoryInMemory implements MovieRepository {

    private static Map<String, Movie> moviesStore;

    public MovieRepositoryInMemory() {
        moviesStore = new HashMap<>();
    }

    public void save (Movie movie) {
        moviesStore.put(movie.getName(), movie);
    }

    public Movie getByName (String movieName) {
        return moviesStore.getOrDefault(movieName, null);
    }

    public List<Movie> getByGenre (String genre) {
        return moviesStore.values().stream().filter(e -> e.getGenre().contains(genre)).collect(Collectors.toList());
    }

    public List<Movie> getByYear (int year) {
        return moviesStore.values().stream().filter(e -> e.getYear() == year).collect(Collectors.toList());
    }

}
