package com.medolia.demo.pattern.command;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class CopyCommand extends Command{
    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }
}
