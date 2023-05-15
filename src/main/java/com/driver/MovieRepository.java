package com.driver;

import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository
{
     private HashMap<String,Movie> moviesDB;
     private HashMap<String,Director> directorsDB;
     private HashMap<String,List<String>> directorAndMoviesDB;

    public MovieRepository() {
        this.moviesDB = new HashMap<>();
        this.directorsDB = new HashMap<>();
        this.directorAndMoviesDB = new HashMap<>();
    }



    public void addMovie(Movie movie)
    {
        moviesDB.put(movie.getName(), movie);
    }

    public void addDirector(Director director)
    {
        directorsDB.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String moviename, String directorname)
    {
         if(directorsDB.containsKey(directorname) && moviesDB.containsKey(moviename))
            {
                if(directorAndMoviesDB.containsKey(directorname))
                {
                    List<String> oldlist = directorAndMoviesDB.get(directorname);
                    oldlist.add(moviename);
                    directorAndMoviesDB.put(directorname,oldlist);
                }
                else
                {
                    List<String> newlist = new ArrayList<>();
                    newlist.add(moviename);
                    directorAndMoviesDB.put(directorname,newlist);
                }
            }
    }

    public List<Movie> getAllMovies()
    {
        List<Movie> movies = new ArrayList<>();
        for(String name : moviesDB.keySet())
        {
            movies.add(moviesDB.get(name));
        }
        return movies;
    }


    public List<String> getMoviesByDirectorName(String directorname)
    {
        List<String> list = new ArrayList<>();
        if(directorAndMoviesDB.containsKey(directorname)) list = directorAndMoviesDB.get(directorname);
        return list;
    }

    public Director getDirectorByName(String directorname)
    {
        if(directorsDB.containsKey(directorname)) return directorsDB.get(directorname);
        return null;
    }

    public Movie getMovieByName(String name)
    {
        if(moviesDB.containsKey(name)) return moviesDB.get(name);
        return null;
    }

    public void deleteDirectorByName(String directorname)
    {
        if(directorsDB.containsKey(directorname)) directorsDB.remove(directorname);
        if(directorAndMoviesDB.containsKey(directorname))
        {
            List<String> movies = directorAndMoviesDB.get(directorname);
            for(String movie : movies)
            {
                if(moviesDB.containsKey(movie))
                {
                    moviesDB.remove(movie);
                }
            }
            directorAndMoviesDB.remove(directorname);
        }

    }

    public void deleteAllDirectors()
    {
        for(String directorName : directorsDB.keySet())
        {
            deleteDirectorByName(directorName);
        }
    }
}
