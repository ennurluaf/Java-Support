package com.support.graphics;

import java.awt.Point;
import java.awt.Rectangle;

import com.support.math.DRect;
import com.support.math.Rect;

public interface Size {

    public int getWidth();

    public int getHeight();

    public static <Rect extends Rectangle> Point get(Rect rect, Pos pos) {
        return pos.posCalc.getPos(rect.x, rect.y, rect.width, rect.height).getLocation();
    }

    public static enum Pos {
        SIZE((x, y, w, h) -> rect(0, 0, w, h)),
        TOPLEFT((x, y, w, h) -> rect(x, y, w, h)),
        TOPRIGHT((x, y, w, h) -> rect(x + w, y, w, h)),
        BOTTOMLEFT((x, y, w, h) -> rect(x, y + h, w, h)),
        BOTTOMRIGHT((x, y, w, h) -> rect(x + w, y + h, w, h)),
        TOPCENTER((x, y, w, h) -> rect(x + w / 2, y, w, h)),
        BOTTOMCENTER((x, y, w, h) -> rect(x + w / 2, y + h, w, h)),
        LEFTCENTER((x, y, w, h) -> rect(x, y + h / 2, w, h)),
        RIGHTCENTER((x, y, w, h) -> rect(x + w, y + h / 2, w, h)),
        CENTER((x, y, w, h) -> rect(x + w / 2, y + h / 2, w, h));

        public PosFunction posCalc;

        private Pos(PosFunction func) {
            this.posCalc = func;
        }

        public interface PosFunction {

            Rect getPos(int x, int y, int w, int h);
        }

        private static Rect rect(int x, int y, int w, int h) {
            return new Rect(x, y, w, h);
        }

        public Rect calcPos(Size sprite, int x, int y) {
            return posCalc.getPos(x, y, sprite.getWidth(), sprite.getHeight());
        }

        public DRect calcPosD(Size sprite, double x, double y) {
            var res = posCalc.getPos((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
            return new DRect(res);
        }
    }

}
