package course.labs.graphicslab;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.media.SoundPool.OnLoadCompleteListener;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class BubbleActivity extends Activity {

    // Эти переменные нужны для тестирования, не изменять
    private final static int RANDOM = 0;
    private final static int SINGLE = 1;
    private final static int STILL = 2;
    private static int speedMode = RANDOM;

    private static final String TAG = "Lab-Graphics";

    // Главный view
    private RelativeLayout mFrame;

    // Bitmap изображения пузыря
    private Bitmap mBitmap;

    // размеры экрана
    private int mDisplayWidth, mDisplayHeight;

    // Звуковые переменные

    // AudioManager
    private AudioManager mAudioManager;
    // SoundPool
    private SoundPool mSoundPool;
    // ID звука лопания пузыря
    private int mSoundID;
    // Громкость аудио
    private float mStreamVolume;

    // Детектор жестов
    private GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // Установка пользовательского интерфейса
        mFrame = (RelativeLayout) findViewById(R.id.frame);

        // Загружаем базовое изображение для пузыря
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b64);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Управляем звуком лопания пузыря
        // Используем AudioManager.STREAM_MUSIC в качестве типа потока

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        mStreamVolume = (float) mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC)
                / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        // создаем новый SoundPool, предоставляющий до 10 потоков
        mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);


        //устанавливаем для SoundPool листенер OnLoadCompletedListener который вызывает setupGestureDetector()

        mSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                setupGestureDetector();
            }
        });

        //загружаем звук из res/raw/bubble_pop.wav
        mSoundID = mSoundPool.load(this, R.raw.bubble_pop, 1);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            // Получаем размер экрана для того чтобы View знал, где границы отображения
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();

        }
    }

    BubbleView touchedBubble(float x, float y) {
        for (int i = 0; i < mFrame.getChildCount(); i++) {
            BubbleView current = (BubbleView) mFrame.getChildAt(i);
            if (current.intersects(x, y)) {
                return current;
            }
        }
        return null;
    }

    // Устанавливаем GestureDetector
    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this,

                new GestureDetector.SimpleOnGestureListener() {

                    // Если на BubleView происходит жест швыряния, тогда изменяем его направление и скорость (velocity)

                    @Override
                    public boolean onFling(MotionEvent event1, MotionEvent event2,
                                           float velocityX, float velocityY) {

                        // Реализуем onFling.
                        // Вы можете получить все Views в mFrame используя
                        // метод ViewGroup.getChildCount()

                        BubbleView touched = touchedBubble(event1.getRawX(), event1.getRawY());
                        if (touched != null) {
                            touched.deflect(velocityX, velocityY);
                        }
                        return true;
                    }


                    // Если простое нажатие попадает по BubbleView, тогда лопаем BubbleView
                    // Иначе, создаем новый BubbleView в центре нажатия и добавляем его в
                    // mFrame. Вы можете получить все компоненты в mFrame с помощью метода ViewGroup.getChildAt()

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent event) {

                        // Реализуем onSingleTapConfirmed.
                        // Вы можете получить все объекты View в mFrame используя
                        // метод ViewGroup.getChildCount()
                        float x = event.getRawX();
                        float y = event.getRawY();

                        BubbleView touched = touchedBubble(x, y);
                        if (touched != null) {
                            touched.stop(true);
                        } else {
                            BubbleView bubble = new BubbleView(mFrame.getContext(), x, y);
                            mFrame.addView(bubble);
                            bubble.start();
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Делегируем нажатие детектору жестов gestureDetector
        return mGestureDetector.onTouchEvent(event);

    }

    @Override
    protected void onPause() {

        //Освобождаем все ресурсы пула SoundPool
        mSoundPool.release();
        super.onPause();
    }

    // BubbleView это View который отображает пузырь.
    // Этот класс управляет анимацией, отрисовкой и лопанием.
    // Новый BubbleView создается отдельно для каждого пузыря на экране

    public class BubbleView extends View {

        private static final int BITMAP_SIZE = 64;
        private static final int REFRESH_RATE = 40;
        private final Paint mPainter = new Paint();
        private final Bubble bubble;
        private ScheduledFuture<?> mMoverFuture;
        private int mScaledBitmapWidth;
        private Bitmap mScaledBitmap;

        // местоположение, скорость и направление пузыря
        //private float mXPos, mYPos, mDx, mDy, mRadius, mRadiusSquared;
        private long mRotate, mDRotate;

        BubbleView(Context context, float x, float y) {
            super(context);

            // Создае новое генератор случайных чисел для рандомизации
            // размера, вращения, скорости и направления
            Random r = new Random();

            // Создает битмэп изображения пузыря для этого BubbleView
            createScaledBitmap(r);

            // Радиус Bitmap
            float mRadius = mScaledBitmapWidth / 2;

            //Объект шара
            this.bubble = new Bubble(x, y, mRadius);

            // Устанавливаем вращение BubbleView
            setRotation(r);

            mPainter.setAntiAlias(true);
        }

        private void setRotation(Random r) {

            if (speedMode == RANDOM) {

                // установить вращение в диапазоне [1..3]
                mDRotate = r.nextInt(2) + 1;


            } else {
                mDRotate = 0;

            }
        }

        private void createScaledBitmap(Random r) {

            if (speedMode != RANDOM) {
                mScaledBitmapWidth = BITMAP_SIZE * 3;

            } else {
                //устанавливаем масштабирование размера изображения в диапазоне [1..3] * BITMAP_SIZE
                mScaledBitmapWidth = BITMAP_SIZE * (r.nextInt(2) + 1);

            }

            // Создаем масштабированное изображение, используя размеры, установленные выше.
            mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, mScaledBitmapWidth, mScaledBitmapWidth, true);
        }

        // Начинаем перемещать BubbleView & обновлять экран
        private void start() {

            // Создаем WorkerThread
            ScheduledExecutorService executor = Executors
                    .newScheduledThreadPool(1);

            // Запускаем run() в Worker Thread каждые REFRESH_RATE милисекунд
            // Сохраняем ссылку на данный процесс в mMoverFuture
            mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {

                    // реализуйте логику движения.
                    // Каждый раз, когда данный метод запускается, BubbleView должен
                    // сдвинуться на один шаг. Если BubbleView покидает экран,
                    // останавливаем Рабочий Поток для BubbleView.
                    // В противном случае запрашиваем перерисовку BubbleView.

                    if (moveWhileOnScreen()) {
                        stop(false);
                        Log.d(TAG,"Stopped!");
                    } else {
                        BubbleView.this.postInvalidate();
                    }
                }
            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
        }


        // Возвращает истину, если BubbleView пересекает точку (x,y)
        private synchronized boolean intersects(float x, float y) {
            // Вернуть true если BubbleView пересекает точку (x,y)
            return bubble.intersects(x, y);
        }

        // Отменяем движение Пузыря
        // Удаляем Пузырь с mFrame
        // Играем звук лопания, если BubbleView лопнули

        private void stop(final boolean wasPopped) {

            if (null != mMoverFuture && !mMoverFuture.isDone()) {
                mMoverFuture.cancel(true);
            }

            // Данный код будет выполнен в UI потоке
            mFrame.post(new Runnable() {
                @Override
                public void run() {

                    // Удаляем BubbleView из mFrame
                    mFrame.removeView(BubbleView.this);

                    // Если пузырь лопнут пользователем,
                    // играем звук лопания
                    if (wasPopped) {
                        mSoundPool.play(mSoundID, mStreamVolume, mStreamVolume, 1, 0, 1f);


                    }
                }
            });
        }

        // Изменяем скорость и направление Пузыря.
        private synchronized void deflect(float velocityX, float velocityY) {

            //установить mDx и mDy в качестве новых значений velocity, разделив их на REFRESH_RATE
            float mDx = velocityX / REFRESH_RATE;
            float mDy = velocityY / REFRESH_RATE;
            bubble.setSpeedAndDirection(mDx, mDy);
        }

        // Рисуем Пузырь в его текущем положении
        @Override
        protected synchronized void onDraw(Canvas canvas) {

            //сохраняем canvas
            canvas.save();

            //Увеличиваем вращение исходного изображения на mDRotate
            mRotate += mDRotate;

            //Вращаем canvas на текущий сдвиг
            // Подсказка - Вращаем относительно ценра пузыря, а не его положения.
            canvas.rotate(mRotate, bubble.getXPos(), bubble.getYPos());

            //рисуем изображение в новом положении
            canvas.drawBitmap(mScaledBitmap, bubble.getXPos()-bubble.getRadius(), bubble.getYPos()-bubble.getRadius(), mPainter);

            //восстанавливаем canvas
            canvas.restore();
        }

        // Возвращает true если BubbleView все еще на экране после хода
        // operation
        private synchronized boolean moveWhileOnScreen() {
            bubble.move(mDisplayWidth,mDisplayHeight);
            return bubble.isOutOfView(mDisplayWidth,mDisplayHeight);
        }
    }

    // Не изменяйте следующий код

    @Override
    public void onBackPressed() {
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_still_mode:
                speedMode = STILL;
                return true;
            case R.id.menu_single_speed:
                speedMode = SINGLE;
                return true;
            case R.id.menu_random_mode:
                speedMode = RANDOM;
                return true;
            case R.id.quit:
                exitRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void exitRequested() {
        super.onBackPressed();
    }
}