package editor;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                String line;

                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                reader.close();
                textEditor.currentFile = file;

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(textEditor, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();

            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                    writer.write(textArea.getText());
                    writer.close();
                    textEditor.currentFile = file;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        else {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textEditor.currentFile), StandardCharsets.UTF_8));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        int confirm = JOptionPane.showConfirmDialog(textEditor, "Do you want to save current file before creating a new one?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirm == JOptionPane.CANCEL_OPTION) {
            return;
        }

        if (confirm == JOptionPane.YES_OPTION) {
            saveFile(textEditor, textArea);
        }

        textArea.setText("");
        textEditor.currentFile = null;
    }

}
