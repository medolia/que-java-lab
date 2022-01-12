package com.medolia.demo.pattern.visitor;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public interface Shape {

    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);

}
