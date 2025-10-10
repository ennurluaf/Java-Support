package com.github.ennurluaf;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("unused")
public class DRect extends Rectangle2D.Double {

    private double left, right, top, bottom;
    private Vector topleft, topright, bottomleft, bottomright;

    public DRect(double x, double y, double width, double height) {
        super(x, y, width, height);

        this.left = x;
        this.right = x + width;
        this.top = y;
        this.bottom = y + height;
        this.topleft = new Vector(left, top);
        this.topright = new Vector(right, top);
        this.bottomleft = new Vector(left, bottom);
        this.bottomright = new Vector(right, bottom);
    }

    public DRect(Rect r) {
        this(r.x, r.y, r.width, r.height);
    }

    public DRect(Vector p, Dimension d) {
        this(p.x, p.y, d.width, d.height);
    }

    public DRect copy() {
        return new DRect(x, y, width, height);
    }

    public JSList<DRect> collideList(JSList<DRect> list) {
        return list.filter(r -> r.intersects(this));
    }

    public double left() {
        return left = x;
    }

    public double right() {
        return right = x + width;
    }

    public double top() {
        return top = y;
    }

    public double bottom() {
        return bottom = y + height;
    }

    public Vector topleft() {
        return topleft = new Vector(left(), top());
    }

    public Vector topright() {
        return topright = new Vector(right(), top());
    }

    public Vector bottomleft() {
        return bottomleft = new Vector(left(), bottom());
    }

    public Vector bottomright() {
        return bottomright = new Vector(right(), bottom());
    }

}
