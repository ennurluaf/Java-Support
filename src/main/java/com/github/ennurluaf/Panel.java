package com.github.ennurluaf;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Panel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener {

    protected Clock clock;
    protected double deltaTime;
    protected Thread thread;

    protected String name;
    protected int width, height;

    public Panel(String name, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.clock = new Clock();
        this.deltaTime = 0.0;
        this.name = name;
        this.width = width;
        this.height = height;
        start();
    }

    private void start() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    protected abstract void paintComponent(Graphics g);

    @Override
    public abstract void run();

    public void main() {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
