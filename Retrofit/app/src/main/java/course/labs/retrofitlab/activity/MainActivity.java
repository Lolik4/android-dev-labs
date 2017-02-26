package course.labs.retrofitlab.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import course.labs.retrofitlab.R;
import course.labs.retrofitlab.adapter.MoviesAdapter;
import course.labs.retrofitlab.model.Movie;
import course.labs.retrofitlab.model.MoviesResponse;
import course.labs.retrofitlab.rest.ApiClient;
import course.labs.retrofitlab.rest.TMDBInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO - полученный с сайта themoviedb.org ключ API KEY
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Необходимо получить API KEY от themoviedb.org!", Toast.LENGTH_LONG).show();
            return;
        }

        //получаем компоненту RecyclerView и задаем ей способ отображения, линейный менеджер макета
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Генерируем адаптер для взаимодействия по сети с помощью библиотеки Retrofit
        TMDBInterface apiService =
                ApiClient.getClient().create(TMDBInterface.class);

        //Осуществляем асинхронный запрос к серверу в соответствии с описанием в классе TMDBInterface
        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        //Обрабатываем ответ от сервера на запрос
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                //код ответа сервера (200 - ОК), в данном случае далее не используется
                int statusCode = response.code();
                //получаем список фильмов, произведя парсинг JSON ответа с помощью библиотеки Retrofit
                List<Movie> movies = response.body().getResults();
                //создаем адаптер для компонента RecyclerView.
                //компонента станет отображать список загруженных фильмов
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                //onFailure вызывается, когда проблема при отправке запроса. Например, сервер не отвечает или нет сети.
                //Заносим сведения об ошибке в журнал методом Log.e(TAG, MESSAGE)
                //Данный метод используется для журнализации ошибок (e = error)
                Log.e(TAG, t.toString());
            }
        });
    }
}
