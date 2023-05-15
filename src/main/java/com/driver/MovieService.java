package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie)
    {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director)
    {
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String moviename, String directorname)
    {
        movieRepository.addMovieDirectorPair(moviename, directorname);
    }

    public Movie getMovieByName(String name)
    {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String directorname)
    {
        return movieRepository.getDirectorByName(directorname);
    }

    public List<String> getMoviesByDirectorName(String directorname)
    {
         return movieRepository.getMoviesByDirectorName(directorname);
    }

    public List<String> getAllMovies()
    {
       List<Movie> movies = movieRepository.getAllMovies();
       List<String> movieslist = new ArrayList<>();
       for(Movie movie : movies)
       {
           movieslist.add(movie.getName());
       }
       return movieslist;
    }

    public void deleteDirectorByName(String directorname)
    {
        movieRepository.deleteDirectorByName(directorname);
    }

    public void deleteAllDirectors()
    {
        movieRepository.deleteAllDirectors();
    }
}
