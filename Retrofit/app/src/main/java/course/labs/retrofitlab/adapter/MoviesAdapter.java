package course.labs.retrofitlab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import course.labs.retrofitlab.R;
import course.labs.retrofitlab.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    //Внутрянняя модель - список фильмов для отображения компонентой
    private List<Movie> movies;
    //код, идентификатор ресурса для визуализации отдельного элемента, его макета
    private int rowLayout;
    //Ссылка на контекст, который мы сохраняем при создании объекта адаптера
    private Context context;

    //Класс ViewHolder необходим для наполнения данными элементы пользовательского интерфейса отдельного элемента списка
    //Класс MovieViewHolder - частный случай, использует макет list_item_movie.xml для отображения модели Movie
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        //Главный метод любого ViewHolder-а. Ему отдается View, узел дерева интерфейса, корневой для макета отдельного элемента
        //Основная задача данного метода - осуществить привязки к компонентам Android, чтобы в дальнейшем их можно было изменять/наполнять
        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //

    /**
     * Данный метод используется компонентой RecyclerView, когда ей необходимо отрисовать элемент указанного типа
     * @param parent компонента для наполнения макетом отдельного элемента
     * @param viewType тип элемента (может быть различным, используется в случае разных типов элементов списка)
     * @return ViewHolder для отрисовки отдельного элемента
     */
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        //берем макет из ресурсов по идентификатору rowLayout и заполняем им область для отдельного элемента списка
        //возвращается View, компонент, корневой для данного поддерева элементов пользовательского интерфейса
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        //возвращаем MovieViewHolder в нашем случае для привязки к компонентам
        return new MovieViewHolder(view);
    }


    /**
     * Данный метод используется компонентой RecyclerView для непосредственного наполнения пользовательского интерфейса отдельного элемента
     * По номеру элемента (position) списка находится его ViewHolder и вызывается команда привязки (bind) для подгрузки информации из внутренней модели
     * @param holder холдер, для управления интерфейсом
     * @param position номер элемента списка, обычно используется для выбора данных из модели для наполнения
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        //Получаем i-ый элемента списка моделей. В данном случае элемент списка фильмов с индексом position
        Movie movie = movies.get(position);
        //Наполняем элементы интерфейса данными из модели
        holder.movieTitle.setText(movie.getTitle());
        holder.data.setText(movie.getReleaseDate());
        holder.movieDescription.setText(movie.getOverview());
        holder.rating.setText(movie.getVoteAverage().toString());
    }

    /**
     * Количество элементов списка
     * @return возвращает количество элементов для отрисовки, берется из внтутренней модели данных
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }
}