package course.labs.graphicslab;

import java.util.Random;

/**
 * @author Vladimir Ulyanov.
 */

class Bubble {

    // местоположение центра пузыря
    private float xPos, yPos;
    // скорость и направление пузыря
    private float dx, dy;
    // радиус пузыря
    private float radius;

    /**
     * Создает объект пузыря в указанной точке касания
     *
     * @param x      точка касания пальца пользователя
     * @param y      точка касания пальца пользователя
     * @param radius радиус пузыря
     */
    Bubble(float x, float y, float radius) {
        // TODO запоминаем радиус пузыря

        // TODO Точка касания пальца пользователя и есть центр пузыря

        // TODO Устанавливаем скорость и направление пузыря случайным образом при создании пузыря с помощью метода setSpeedAndDirection()
    }

    /**
     * Задает стартовую скорость и направление движения случайным образом
     */
    private void setSpeedAndDirection() {
        Random r = new Random();
        // TODO - Устанавливаем направление движения и скорость
        // Ограничиваем скорость движения по x и y в диапазоне [-3..3] пикселя на движение.
        // Подсказка: r.nextFloat() возвращает случайное вещественное число в интервалле [0; 1]

    }

    /**
     * Задает скорость и направление движения пузыря извне
     *
     * @param dx смещение по оси x
     * @param dy смещение по оси y
     */
    public void setSpeedAndDirection(float dx, float dy) {
        //TODO установить новое направление движения
    }

    /**
     * Пересекает ли пузырь точку (x,y)? содержит ли он эту точку, закрывает ли?
     *
     * @param x координата точки
     * @param y координата точки
     * @return true, если точка находится под пузырем (пузырь ее пересекает)
     */
    boolean intersects(float x, float y) {
        // TODO - Вернуть true если Bubble пересекает точку (x,y)
        return false;
    }

    /**
     * Возвращает координату центра пузыря по оси X
     *
     * @return x-координату
     */
    float getXPos() {
        //TODO
        return 0;
    }

    /**
     * Возвращает координату центра пузыря по оси Y
     *
     * @return y-координату
     */
    float getYPos() {
        //TODO
        return 0;
    }

    /**
     * Возвращает радиус пузыря
     *
     * @return радиус пузыря
     */
    float getRadius() {
        //TODO
        return 0;
    }

    /**
     * Делает шаг анимации, сдвигает шар в указанном направлении
     *
     * @param displayWidth  ширина экрана
     * @param displayHeight высота экрана
     */
    synchronized void move(float displayWidth, float displayHeight) {
        //TODO реализовать отскок от границ экрана. Предварительно проверить, не выйдем ли мы следующим шагом за границу экрана. Учесть радиус пузыря!
        //Подсказка: сначала реализуйте движение по прямой. Потом переходите к отскоку.

        //TODO Реализовать движение по прямой. На каждом шаге необходимо прибавить к текущему местопложению приращение аргумента dx и dy чтобы оказаться в точке (x+dx, y+dy)

    }

    /**
     * Проверяет, ушел ли пузырь с экрана
     *
     * @param displayWidth  ширина экрана
     * @param displayHeight высота экрана
     * @return Возвращаем true, если Bubble ушел с экрана после завершения хода
     */
    synchronized boolean isOutOfView(float displayWidth, float displayHeight) {
        // TODO - Возвращаем true, если Bubble находится вне экрана
        return false;
    }
}