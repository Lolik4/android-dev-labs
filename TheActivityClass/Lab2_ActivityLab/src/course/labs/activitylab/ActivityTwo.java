package course.labs.activitylab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	// Используйте эти ключи когда вы сохраняете состояния между реконфигурациями
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// Строка для LogCat документирования
	private final static String TAG = "Lab-ActivityTwo";

	// Счетчики жизненного цикла

	// TODO:
	// Создайте переменные с именами
	// mCreate, mRestart, mStart и mResume
	// чтобы подсчитывать вызовы  onCreate(), onRestart(), onStart() и
	// onResume(). Эти переменные должны быть не статическими.

	// Вам потребуется инкрементировать эти переменные когда
	// соответствующие методы жизненного цикла вызываются

	// TODO: Создайте переменные для каждого из TextViews
	// с именами mTvCreate, mTvRestart, mTvStart, mTvResume.
	// для отображения текущего счета каждой переменной счетчика

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		// TODO: Присвойте соответствующие TextView переменным
		// Подсказка: Доступ к TextView через метод Activity с именем findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);


		
		
		
		
		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO:
				// Эта функция закрывает Activity Two
				// Подсказка: используйте в Context метод с именем finish()

				
			
			}
		});

		// Было ли сохранено предыдущее состояние?
		if (savedInstanceState != null) {

			// TODO:
			// Восстановите значения счетчиков из сохраненного состояния
			// Нужно ровно 4 строки кода по одной для каждой переменной-счетчика


			
			
			
			
		}

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onCreate() method");

		// TODO:
		// Обновите соответствующую переменную счетчика
		// Обновите пользовательский интерфейс с помощью метода displayCounts()


		
		
	}

	// Перекрываем методы жизненного циклаы

	@Override
	public void onStart() {
		super.onStart();

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onStart() method");

		// TODO:
		// Обновите соответствующую переменную счетчика
		// Обновите пользовательский интерфейс


		
		
	}

	@Override
	public void onResume() {
		super.onResume();

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onResume() method");

		// TODO:
		// Обновите соответствующую переменную счетчика
		// Обновите пользовательский интерфейс


	
	}

	@Override
	public void onPause() {
		super.onPause();

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onRestart() method");

		// TODO:
		// Обновите соответствующую переменную счетчика
		// Обновите пользовательский интерфейс

	
	
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// // Производим LogCat сообщение, пишем в журнал
		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// TODO:
		// Сохраните информацию о состоянии в виде коллекции пар ключ-значение
		// 4 строки кода, одна для каждого счетчика








	}

	// Обновляет отображаемые счетчики
	// Этот метод ожидает, что значение счетчиков и переменные TextView используют
	// имена, описанные выше
	public void displayCounts() {

		// TODO - раскомментируйте эти строки
	/*
		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	*/
	
	}
}
