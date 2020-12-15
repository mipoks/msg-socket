package ru.itis.typergame.client;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;


class SecClock {
    private int second;

    public SecClock(int second) {
        this.setSecond(second);
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second % 60;
    }
}


public class ClockFX {
    private static final int CENTER_CLOCK = 150;
    private static final int SEC_ARROW = 50;
    private static final int CLOCK_RADIUS = 70;
    private MyAnimTimer myAnimTimer;

    private Pair<Integer, Integer> getEndCoords(double angle, int radius) {
        Pair<Integer, Integer> O = new Pair<>(CENTER_CLOCK / 2, CENTER_CLOCK / 2);
        int x = (int) (O.getKey() + radius * Math.cos(angle));
        int y = (int) (O.getKey() - radius * Math.sin(angle));
        return new Pair<>(x, y);
    }

    private void drawClock(SecClock сlock, GraphicsContext gc) {
        double angle;
        for (int i = 1; i < 13; i++) {
            angle = Math.PI / 2 - i * Math.PI / 6;
            Pair<Integer, Integer> point = getEndCoords(angle, CLOCK_RADIUS);
            drawCircle(gc, point, 8);
        }
    }

    private void drawCircle(GraphicsContext gc, Pair<Integer, Integer> center, int radius) {
        gc.fillOval(center.getKey() - radius / 2, center.getValue() - radius / 2, radius, radius);
    }

    public void drawOn(Canvas canvas) {

//        Pane root = new Pane();

//        Canvas canvas = new Canvas(CLOCK_RADIUS * 2.5, CLOCK_RADIUS * 2.5);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        doDrawing(gc);

//        root.getChildren().add(canvas);

//        Scene scene = new Scene(root, CLOCK_RADIUS * 2 + 10, CLOCK_RADIUS * 2 + 10, Color.WHITESMOKE);
//
//        stage.setScene(scene);
//        stage.show();
    }

    public void startTimer() {
        myAnimTimer.start();
    }

    public void stopTimer() {
        myAnimTimer.stop();
    }

    private void doDrawing(GraphicsContext gc) {
        SecClock secClock = new SecClock(0);
        drawClock(secClock, gc);
        gc.strokeLine(CENTER_CLOCK / 2, CENTER_CLOCK / 2, CENTER_CLOCK / 2, CENTER_CLOCK / 2 - SEC_ARROW);
        myAnimTimer = new MyAnimTimer(secClock, SEC_ARROW, gc);
    }

    class MyAnimTimer extends AnimationTimer {
        private final SecClock secClock;
        private final int radius;
        private final GraphicsContext gc;
        private long lastUpdate = 0;
        private final ClockRunnable clockRunnable;

        public MyAnimTimer(SecClock secClock, int radius, GraphicsContext gc) {
            this.secClock = secClock;
            this.radius = radius;
            this.gc = gc;
            clockRunnable = new ClockRunnable(secClock, 50, gc);
        }

        @Override
        public void start() {
            lastUpdate = System.nanoTime();
            super.start();
        }

        @Override
        public void handle(long now) {
            if (now - lastUpdate >= 1_000_000_000) {
                secClock.setSecond(secClock.getSecond() + (int) (now / lastUpdate));
                lastUpdate = now;
                Platform.runLater(clockRunnable);
            }
        }
    }

    class ClockRunnable implements Runnable {

        private final SecClock secClock;
        private final int radiusSec;
        private final GraphicsContext gc;

        public ClockRunnable(SecClock secClock, int radiusSec, GraphicsContext gc) {
            this.secClock = secClock;
            this.radiusSec = radiusSec;
            this.gc = gc;
        }

        @Override
        public void run() {
            gc.clearRect(0, 0, CENTER_CLOCK, CENTER_CLOCK);
            drawClock(secClock, gc);
            double angle = Math.PI / 2 - secClock.getSecond() * Math.PI / 30;
            Pair<Integer, Integer> point = getEndCoords(angle, radiusSec);
            gc.strokeLine(CENTER_CLOCK / 2, CENTER_CLOCK / 2, point.getKey(), point.getValue());
        }
    }

}
