package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.Genre;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.repositories.MovieRepository;

import java.util.ArrayList;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();

    }

    //Begin of VM get moviebyid
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMoviesData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }
    //end of VM get moviebyid

    //Begin of VM get now playing
    private MutableLiveData<NowPlaying> resultGetNowPlaying= new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }
    //end of VM get now playing


    private MutableLiveData<Genre> resultGetGenre= new MutableLiveData<>();
    public void getGenre(){
        resultGetGenre = repository.getGenreData();
    }
    public LiveData<Genre> getResultGenre(){
        return resultGetGenre;
    }

}
