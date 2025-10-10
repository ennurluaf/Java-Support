package com.github.ennurluaf;

public class Clock {

    public static final double LARGE = 1_000_000_0.0;
    private long lastTime;
    private double fps, frameTime;

    public Clock() {
        this.lastTime = System.nanoTime();
        this.fps = 0.0;
        this.frameTime = 0.0;
    }

    public double tick(double maxFPS) {
        long now = System.nanoTime();
        long elapsedNanos = now - lastTime;
        lastTime = now;

        frameTime = elapsedNanos / LARGE;
        if (frameTime > 0) {
            fps = 1.0 / frameTime;
        }

        if (maxFPS > 0) {
            double targetFrameTime = 1.0 / maxFPS;
            if (frameTime < targetFrameTime) {
                long sleepMillis = (long) ((targetFrameTime - frameTime) * 1000);
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                }

                now = System.nanoTime();
                elapsedNanos = now - lastTime;
                lastTime = now;
                frameTime = elapsedNanos / LARGE;
                if (frameTime > 0) {
                    fps = 1.0 / frameTime;
                }
            }
        }
        return frameTime;
    }

    public double tick() {
        return tick(0);
    }

    public double getFPS() {
        return fps;
    }

}
