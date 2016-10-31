package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Добавляем  ToDoItem в адаптер
	// Уведомляем обсерверов, что данные изменились

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Очищаем список адаптеров от всех элементов.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Возвращает число элементов ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Возвращает элемент ToDoItem в выбранной позиции

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Получает ID для ToDoItem
	// В данном случае это всего лишь позиция

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Создайте View для ToDoItem в определенной позиции
	// Не забудьте проверить, содержит ли выделенное convertView уже созданное  View
	// перед созданием нового View
	// Рассмотрите использование паттерна ViewHolder чтобы сделать скроллинг более эффективным.
	// См: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// TODO - Получите текущий ToDoItem
		final ToDoItem toDoItem = null;


		// TODO - Заполните View для данного ToDoItem из todo_item.xml
		RelativeLayout itemLayout = null;

		// Заполните специфичные данные ToDoItem
		// Помните, что данные, которые появляются в этом View
		// соответствуют пользовательскому интерфейсу, описанному
		// в файле макета

		// TODO - Отобразите Title В TextView
		final TextView titleView = null;


		// TODO - Установите CheckBox статуса
		final CheckBox statusView = null;


		// TODO - Обязательно нужно установить OnCheckedChangeListener,
		// который вызывается когда пользователь переключает чекбокс статуса

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {



                        
                        
                        
					}
				});

		// TODO - Отобразить Приоритет в TextView
		final TextView priorityView = null;



		// TODO - Отобразить Время и Дату.
		// Подсказка - используйте ToDoItem.FORMAT.format(toDoItem.getDate()) для получения даты и строки
		final TextView dateView = null;

		// Возвращает View которое только что создали
		return itemLayout;

	}
}
