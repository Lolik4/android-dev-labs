package course.labs.graphicslab;



import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;


public class BubbleTests {


    private Bubble bubble;
    private Bubble bigBubble;

    @Before
    public void prepareBubble() {
        bubble = new Bubble(50, 50, 10);
        bigBubble = new Bubble(100, 100, 100);
    }

    @Test
    public void creationPosition() {
        assertEquals(50f, bubble.getXPos());
        assertEquals(50f, bubble.getYPos());
        assertEquals(100f, bigBubble.getXPos());
        assertEquals(100f, bigBubble.getYPos());
    }

    @Test
    public void radius() {
        assertEquals(10f, bubble.getRadius());
        assertEquals(100f, bigBubble.getRadius());
    }

    @Test
    public void defaultSpeed() {
        bubble.move(1000, 1000);
        assertFalse(50f == bubble.getXPos() && 50f == bubble.getYPos());
        bigBubble.move(1000, 1000);
        assertFalse(100f == bigBubble.getXPos() && 100f == bigBubble.getYPos());

        //default speed interval
        for (int i = 0; i < 10000; i++) {
            Bubble b = new Bubble(100, 100, 10);
            float x = b.getXPos();
            float y = b.getYPos();
            b.move(1000, 1000);
            float y2 = b.getYPos();
            float x2 = b.getYPos();
            assertTrue("Пузырь движется", x2!=x || y2 != y);
            assertTrue("Стартовая скорость в интервалле [-3;3]", Math.abs(x - x2) <= 3 && Math.abs(y - y2) <= 3);
        }
    }

    @Test
    public void setSpeed() {
        //stop
        bubble.setSpeedAndDirection(0, 0);
        bubble.move(1000, 1000);
        assertEquals(50f, bubble.getXPos());
        assertEquals(50f, bubble.getYPos());
        bigBubble.setSpeedAndDirection(0, 0);
        bigBubble.move(10000, 10000);
        assertEquals(100f, bigBubble.getXPos());
        assertEquals(100f, bigBubble.getYPos());

        //move
        bubble.setSpeedAndDirection(10, 10);
        bubble.move(1000, 1000);
        assertEquals(60f, bubble.getXPos());
        assertEquals(60f, bubble.getYPos());
    }

    @Test
    public void testCollissions() {
        //top and left
        bigBubble.setSpeedAndDirection(-10, -10);
        bigBubble.move(1000, 1000);
        assertEquals("left collision", 110f, bigBubble.getXPos());
        assertEquals("top collision", 110f, bigBubble.getYPos());
        //right and bottom
        bubble.setSpeedAndDirection(5, 5);
        bubble.move(62f, 62f);
        assertEquals("right collision", 45f, bubble.getXPos());
        assertEquals("bottom collision", 45f, bubble.getYPos());
    }

    @Test
    public void intersections() {
        assertTrue(bubble.intersects(51, 51));
        assertTrue(bubble.intersects(56, 48));
        assertFalse(bubble.intersects(0, 0));
        assertFalse(bubble.intersects(41, 41));
    }

    @Test
    public void outsider() {
        assertTrue(bubble.isOutOfView(20, 20));
        assertFalse(bubble.isOutOfView(200,200));
        assertTrue(new Bubble(-100, 100, 10).isOutOfView(200,200));
        assertTrue(new Bubble(100, -100, 10).isOutOfView(200,200));
    }
}
