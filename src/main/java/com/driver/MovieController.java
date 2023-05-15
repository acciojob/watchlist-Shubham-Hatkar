package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController
{
    @Autowired
    MovieService movieService;

   @PostMapping("/add-movie")
   public ResponseEntity addMovie(@RequestBody Movie movie)
   {
       movieService.addMovie(movie);
       return new ResponseEntity("Movie added successfully.", HttpStatus.ACCEPTED);
   }

   @PostMapping("/add-director")
   public ResponseEntity addDirector(@RequestBody Director director)
   {
       movieService.addDirector(director);
       return new ResponseEntity("Director added successfully.", HttpStatus.ACCEPTED);
   }

   @PutMapping("/add-movie-director-pair")
   public ResponseEntity addMovieDirectorPair(@RequestParam("moviename") String moviename,
                                               @RequestParam("directorname") String directorname)
   {
       movieService.addMovieDirectorPair(moviename, directorname);
       return new ResponseEntity("Director-Movie pair added successfully.", HttpStatus.ACCEPTED);
   }

   @GetMapping("/get-movie-by-name/{name}")
   public ResponseEntity getMovieByName(@PathVariable("name") String name)
   {
       Movie movie = movieService.getMovieByName(name);
       return new ResponseEntity(movie,HttpStatus.FOUND);
   }

   @GetMapping("/get-director-by-name/{name}")
   public ResponseEntity getDirectorByName(@PathVariable("name") String directorname)
   {
       Director director = movieService.getDirectorByName(directorname);
       return new ResponseEntity(director, HttpStatus.FOUND);
   }

   @GetMapping("/get-movies-by-director-name/{director}")
   public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorname)
   {
       List<String> moviesList = movieService.getMoviesByDirectorName(directorname);
       return new ResponseEntity(moviesList, HttpStatus.FOUND);
   }

   @GetMapping("/get-all-movies")
   public ResponseEntity findAllMovies()
   {
       List<String> moviesList = movieService.getAllMovies();
       return new ResponseEntity(moviesList, HttpStatus.FOUND);
   }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorname") String directorname)
    {
        movieService.deleteDirectorByName(directorname);
        return new ResponseEntity("Director and his movies are deleted.", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors()
    {
        movieService.deleteAllDirectors();
        return new ResponseEntity("All directors and movies of them are deleted successfully.", HttpStatus.ACCEPTED);
    }
}
