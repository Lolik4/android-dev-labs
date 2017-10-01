package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLoaderActivity extends Activity {
    
	static public final int GET_TEXT_REQUEST_CODE = 1;
	static private final String URL = "http://www.google.com";
	static private final String TAG = "Lab-Intents";
    
	// Для использования при выборе приложения
	static private final String CHOOSER_TEXT = "Load " + URL + " with:";
    
	// TextView  который отображает текст, введенный в ExplicitlyLoadedActivity
	private TextView mUserTextView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);
		
		// Получаем ссылку на textView
		mUserTextView = (TextView) findViewById(R.id.textView1);
        
		// Объявляем и устанавливаем кнопку Explicit Activation
		Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
		explicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Вызываем startExplicitActivation() при нажатии
			@Override
			public void onClick(View v) {
				
				startExplicitActivation();
                
			}
		});
        
		// Объявляем и устанавливаем кнопку Implicit Activation 
		Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
		implicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Вызываем startImplicitActivation() при нажатии
			@Override
			public void onClick(View v) {
                
				startImplicitActivation();
                
			}
		});
        
	}
    
	
	// Start the ExplicitlyLoadedActivity
	
	private void startExplicitActivation() {
        
		Log.i(TAG,"Вошли в startExplicitActivation()");
		
		// TODO - Создайте новый интент для запуска класса ExplicitlyLoadedActivity

		
		// TODO - Стартуйте Activity используя этот интент и код запроса, описанный выше

		
        
        
	}
    
	// Start a Browser Activity to view a web page or its URL
	
	private void startImplicitActivation() {
        
		Log.i(TAG, "Вошли в startImplicitActivation()");
        
		// TODO - Создайте базовый интент для просмотра URL
		// (ПОДСКАЗКА:  второй параметр с использованиемё Uri.parse())
		
                		
		// TODO - Создайте интент для выбора Activity
		// будет выполнять baseIntent
		// (ПОДСКАЗКА: Используйте метод createChooser() класса Intent)

        
        
        
		// TODO - Стартуйте выбор Activity, используя chooserIntent


        
	}
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        
		Log.i(TAG, "Entered onActivityResult()");
		
		// TODO - Обработать результат только в том случае, если этот метод получит и 
		// RESULT_OK в качестве кода результата и наш код запроса.
		// Если это так, то обновим TextView, показав текст, введенный пользователем.
		

	
    
    
    
    
    }
}
