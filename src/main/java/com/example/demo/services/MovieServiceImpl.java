package com.example.demo.services;

import com.example.demo.UnauthorizedException;
import com.example.demo.models.Category;
import com.example.demo.models.Client;
import com.example.demo.models.Movie;
import com.example.demo.models.MovieType;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.MovieRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    public MovieRepository movieRepository;
    public ClientRepository clientRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ClientRepository clientRepository) {
        this.movieRepository = movieRepository;
        this.clientRepository = clientRepository;
    }


    //Get all movies
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }


    @Override
    public Movie create(Movie Movie) {
        Optional<Movie> exist = findByMovieNameAndReleaseYear(Movie.getMovieName(), Movie.getReleaseYear());
        return exist.orElseGet(()->movieRepository.save(Movie));
    }

    //find movies by id
    @Override
    public Movie findByID(Long id) throws NotFoundException {
        return movieRepository.findById(id).orElseThrow(()->
                new NotFoundException("Movie id" + id + "does not exist"));
    }

    //querymovie
    @Override
    public List<Movie> findByCategoryAndType(Long id, MovieType type) {
        System.out.println(type);
        System.out.println(MovieType.suggested);
        return movieRepository.findByCategoryAndMovieType(new Category(id), type);
    }

    @Override
    public Movie update(Long id, Long clientid, Movie Movie) throws NotFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException("Movie" + id +"does not exist"));
        Optional<Client> user = Optional.ofNullable(clientRepository.findById(clientid).orElseThrow(() -> new NotFoundException("User with ID"  + clientid+  "is not present")));
        if (movie.getClient().getId().equals(clientid)){
            movie.setMovieName(Movie.getMovieName());
            movie.setReleaseYear(Movie.getReleaseYear());
            movie.setCategory(Movie.getCategory());
            return movieRepository.save(movie);
        }
        throw new UnauthorizedException("This user did not add the movie");
    }

    @Override
    public String delete(Long id, Long clientid) throws NotFoundException {
        Optional<Client> user = Optional.ofNullable(clientRepository.findById(clientid).orElseThrow(() -> new NotFoundException("The specified user with ID" + clientid+ "does not exist")));
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException("Movie with movie ID"  + id +  "cannot be found"));
        if (movie.getClient().getId().equals(clientid)){
            movieRepository.delete(movie);
            return id.toString();
        }
        throw new UnauthorizedException("This user did not add the movie");
    }

    @Override
    public Optional<Movie> findByMovieNameAndReleaseYear(String name, int year) {
        return movieRepository.findByMovieNameAndReleaseYear(name, year);
    }
}