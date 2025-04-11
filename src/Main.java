import editor.TextEditor;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditor editor = new TextEditor();
            editor.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            editor.setVisible(true);
        });
    }
}