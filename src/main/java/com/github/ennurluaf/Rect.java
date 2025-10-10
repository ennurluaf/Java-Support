package com.github.ennurluaf;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("unused")
public class Rect extends Rectangle {

    private int left, right, top, bottom;
    private Point topleft, topright, bottomleft, bottomright;

    public Rect(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.left = x;
        this.right = x + width;
        this.top = y;
        this.bottom = y + height;
        this.topleft = new Point(left, top);
        this.topright = new Point(right, top);
        this.bottomleft = new Point(left, bottom);
        this.bottomright = new Point(right, bottom);
    }

    public Rect(Vector p, Dimension d) {
        this((int) p.x, (int) p.y, d.width, d.height);
    }

    public Rect copy() {
        return new Rect(x, y, width, height);
    }

    public int left() {
        return left = x;
    }

    public int right() {
        return right = x + width;
    }

    public int top() {
        return top = y;
    }

    public int bottom() {
        return bottom = y + height;
    }

    public Point topleft() {
        return topleft = new Point(left(), top());
    }

    public Point topright() {
        return topright = new Point(right(), top());
    }

    public Point bottomleft() {
        return bottomleft = new Point(left(), bottom());
    }

    public Point bottomright() {
        return bottomright = new Point(right(), bottom());
    }
}
