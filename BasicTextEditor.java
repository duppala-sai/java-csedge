import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BasicTextEditor extends JFrame implements ActionListener {
    // Components
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, exitItem;
    private JFileChooser fileChooser;

    public BasicTextEditor() {
        // Setup frame
        setTitle("Basic Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize text area
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Initialize menu bar
        menuBar = new JMenuBar();

        // Initialize file menu
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        // Add action listeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add menu items to file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add file menu to menu bar
        menuBar.add(fileMenu);

        // Set menu bar to the frame
        setJMenuBar(menuBar);

        // Initialize file chooser
        fileChooser = new JFileChooser();

        // Show the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasicTextEditor::new);
    }
}
