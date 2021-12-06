package com.wongnai.interview.movie.sync;

import com.wongnai.interview.movie.InvertedIndex;
import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ListIterator;

@Component
public class MovieDataSynchronizer {
    @Autowired
    private MovieDataService movieDataService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private InvertedIndex invertedIndex;

    @Transactional
    public void forceSync() {
        //TODO: implement this to sync movie into repository

        MoviesResponse movieData = movieDataService.fetchAll();
        movieRepository.deleteAll();
        ListIterator<MovieData> iterator = movieData.listIterator();
        while (iterator.hasNext()) {
            MovieData data = iterator.next();
            Movie movie = new Movie(data.getTitle());
            movie.getActors().addAll(data.getCast());
            invertedIndex.addInvertedIndex(movieRepository.save(movie));
        }
    }
}


