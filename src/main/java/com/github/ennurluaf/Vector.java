package com.github.ennurluaf;

public class Vector {

    public double x, y, z;

    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector copy() {
        return new Vector(this.x, this.y, this.z);
    }

    public Vector add(Vector v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }

    public Vector sub(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vector mult(double m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    public Vector divide(double m) {
        this.x /= m;
        this.y /= m;
        this.z /= m;
        return this;
    }

    public double dot(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector cross(Vector v) {
        return new Vector(
                this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y + this.y + this.z * this.z);
    }

    public Vector normalize() {
        double length = Math.sqrt(x * x + y * y);
        if (length == 0) {
            return new Vector(0, 0);
        }
        return new Vector(x / length, y / length);
    }
}
