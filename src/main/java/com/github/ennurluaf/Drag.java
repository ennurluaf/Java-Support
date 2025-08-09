package com.github.ennurluaf;

import java.awt.Point;
import java.awt.geom.Point2D.Double;

public class Drag {

    public Double origin, offset;
    public boolean active;

    public int size, width, height, cols, rows;

    public Drag() {
        this.origin = new Double(0, 0);
        this.offset = new Double(0, 0);
        this.active = false;
    }

    public Drag(int size, int width, int height) {
        this();
        this.size = size;
        this.width = width;
        this.height = height;
        this.cols = width / size;
        this.rows = height / size;
    }

    public Double getMouse(Point mouse) {
        return new Double(
            mouse.x - origin.x, mouse.y - origin.y
        );
    }

    public void press(Point p) {
        this.active = true;
        this.offset.x = p.x - this.origin.x;
        this.offset.y = p.y - this.origin.y;
    }

    public void drag(Point p) {
        if (this.active) {
            this.origin.x = p.x - this.offset.x;
            this.origin.y = p.y - this.offset.y;
        }
    }

    public void release() {
        this.active = false;
    }
    
    public Point getLineOffset() {
        int x = (int) origin.x - (int) (origin.x / this.size) * this.size;
        int y = (int) origin.y - (int) (origin.y / this.size) * this.size;
        return new Point(x, y);
    }

    public void draw(GContext c) {
        var offset = getLineOffset();
        c.stroke(0,50);
        for (int i = 0; i < cols; i++) {
            int x = i * size + offset.x;
            c.line(x, 0, x, getWidth());
        }
        for (int i = 0; i < rows; i++) {
            int y = i * size + offset.y;
            c.line(0, y, getWidth(), y);
        }
        c.fill(50).circle((int) origin.x, (int) origin.y, 5);
    }

}
