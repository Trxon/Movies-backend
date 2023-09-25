package com.example.movies.service;

import com.example.movies.repository.MovieRepository;
import com.example.movies.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getSingleMovie(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
