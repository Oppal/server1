package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "movieName")
    @NotNull(message = "Specify movie name")
    private String movieName;

    @Column(name = "movieType")
    private MovieType movieType;

   // @JsonIgnore
    @ManyToMany
    @JoinTable(name = "movieCategory",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> category;

    @Column(name="releaseYear")
    @NotNull(message = "Release year cannot be blank")
    private int releaseYear;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    public Movie() {
    }

    public Movie(@NotNull(message = "Movie name is required") String movieName, @NotNull(message = "Movie Type is required") MovieType movieType, MovieVerified verified, List<Category> category, @NotNull(message = "Release year cannot be blank") int releaseYear, Client client) {
        this.movieName = movieName;
        this.movieType = movieType;
        this.category = category;
        this.releaseYear = releaseYear;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieType=" + movieType +
                ", category=" + category +
                ", releaseYear=" + releaseYear +
                ", user=" + client +
                '}';
    }
}