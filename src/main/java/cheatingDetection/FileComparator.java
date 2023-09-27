package cheatingDetection;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class FileComparator {

    public static void main(String[] args) throws Exception {
        // Create two text files to compare
        PrintWriter pw1 = new PrintWriter("file1.txt");
        pw1.println("Hello world");
        pw1.println("This is a test");
        pw1.println("Goodbye");
        pw1.close();

        PrintWriter pw2 = new PrintWriter("file2.txt");
        pw2.println("Hello world");
        pw2.println("This is not a test");
        pw2.println("Goodbye");
        pw2.close();

        // Create two text panes to display the files
        JTextPane tp1 = new JTextPane();
        tp1.setEditable(false);
        tp1.read(new FileReader("file1.txt"), null);

        JTextPane tp2 = new JTextPane();
        tp2.setEditable(false);
        tp2.read(new FileReader("file2.txt"), null);

        // Create a highlighter and a painter for the text panes
        Highlighter hl1 = tp1.getHighlighter();
        Highlighter hl2 = tp2.getHighlighter();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        // Compare the files line by line and highlight the similar lines
        BufferedReader br1 = new BufferedReader(new FileReader("file1.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("file2.txt"));
        String line1, line2;
        int offset1 = 0, offset2 = 0;
        while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
            if (line1.equals(line2)) { // If the lines are equal, highlight them
                hl1.addHighlight(offset1, offset1 + line1.length(), painter);
                hl2.addHighlight(offset2, offset2 + line2.length(), painter);
            }
            offset1 += line1.length() + 1; // Update the offsets for the next line
            offset2 += line2.length() + 1;
        }
        br1.close();
        br2.close();

        // Create a frame to show the text panes
        JFrame frame = new JFrame("File Comparator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));
        frame.add(new JScrollPane(tp1));
        frame.add(new JScrollPane(tp2));
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
