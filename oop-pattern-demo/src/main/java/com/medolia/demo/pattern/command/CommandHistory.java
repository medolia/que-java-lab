package com.medolia.demo.pattern.command;

import java.util.Stack;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() { return history.isEmpty(); }
}
