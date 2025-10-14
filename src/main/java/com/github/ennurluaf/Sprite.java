package com.github.ennurluaf;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage implements Size {

    public Sprite(int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public Sprite(BufferedImage img) {
        super(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        getGraphics().drawImage(img, 0, 0, null);
    }

    public Sprite(BufferedImage img, int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
        getGraphics().drawImage(img, 0, 0, width, height, null);
    }

    public GContext create() {
        return new GContext(createGraphics());
    }

    public Rectangle getRect(int x, int y) {
        return Pos.TOPLEFT.calcPos(this, x, y);
    }

    public DRect getDRect(Pos pos, double x, double y) {
        return pos.calcPosD(this, x, y);
    }

    public Rectangle getRect() {
        return Pos.SIZE.posCalc.getPos(0, 0, getWidth(), getHeight());
    }

    public Sprite rotate(double angle) {
        Sprite result = new Sprite(getWidth(), getHeight());
        result.create().rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2).drawImage(this, 0, 0);
        return result;
    }

    public Sprite sub(int x, int y, int width, int height) {
        return new Sprite(this.getSubimage(x, y, width, height));
    }

    public Sprite resize(int width, int height) {
        Sprite result = new Sprite(width, height);
        result.create().drawImage(this, 0, 0, width, height);
        return result;
    }

    public Sprite fill(Color c) {
        Sprite result = new Sprite(getWidth(), getHeight());
        result.create().fill(c).rect(0, 0, getWidth(), getHeight()).dispose();
        return result;
    }

    public Sprite flip(boolean horizontal, boolean vertical) {
        Sprite result = new Sprite(getWidth(), getHeight());
        result.create().scale(horizontal ? -1 : 1, vertical ? -1 : 1)
                .drawImage(this, horizontal ? -getWidth() : 0, vertical ? -getHeight() : 0).dispose();
        return result;
    }

    public void draw(GContext c, int x, int y) {
        c.drawImage(this, x, y);
    }

}
