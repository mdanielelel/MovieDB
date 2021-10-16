package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Genre;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_text, lbl_sinopsis;
    private String movie_id = "";
    private String sinopsis;
    private String movie_img;
    private ImageView lbl_img;
    private MovieViewModel view_model;
    private ArrayList<Integer> genre_id;
    private TextView lbl_genre;
    private String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        sinopsis = intent.getStringExtra("sinopsis");
        movie_img = intent.getStringExtra("movie_img");
        genre_id = intent.getIntegerArrayListExtra("genre_id");

        lbl_text = findViewById(R.id.lbl_title);
        lbl_text.setText(movie_id);

        lbl_sinopsis = findViewById(R.id.lbl_sinopsis_card);
        lbl_sinopsis.setText(sinopsis);

        lbl_img = findViewById(R.id.img_poster_card_nowplaying);
        Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL+movie_img).into(lbl_img);

        lbl_genre = findViewById(R.id.lbl_genre_card);

        view_model = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        view_model.getGenre();
        view_model.getResultGenre().observe(MovieDetailsActivity.this, showNowPLaying);

    }

    @Override
    public void onBackPressed() {

        finish();
    }

    private Observer<Genre> showNowPLaying = new Observer<Genre>() {
        @Override
        public void onChanged(Genre genre) {
            for (int i = 0; i<genre.getGenres().size(); i++){
                for (int j = 0; j<genre_id.size(); j++){
                    if (genre_id.get(j) == genre.getGenres().get(i).getId()){
                        result += genre.getGenres().get(i).getName();
                    }
                }
            }
            lbl_genre.setText(result);
            result = "";
        }
    };
}