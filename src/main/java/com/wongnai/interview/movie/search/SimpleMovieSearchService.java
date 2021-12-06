package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class
		List<Movie> movieList = new ArrayList<>();
		MoviesResponse movieData = movieDataService.fetchAll();
		ListIterator<MovieData> iterator = movieData.listIterator();
		while (iterator.hasNext()){
			MovieData data = iterator.next();
			String[] dataTitle = data.getTitle().split(" ");
			for (int i = 0; i < dataTitle.length ; i++){
				if(dataTitle[i].equalsIgnoreCase(queryText)){
					Movie movie = new Movie(data.getTitle());
					movie.getActors().addAll(data.getCast());
					movieList.add(movie);
					break;
				}
			}
		}
		return movieList;
	}
}
