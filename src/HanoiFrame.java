import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class HanoiFrame extends JFrame {
    HanoiPanel hanoiPanel;

    HanoiFrame() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        int frameHeight = screenHeight - screenHeight / 4;
        int frameWidth = screenWidth;

        setSize(frameWidth, frameHeight);
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
        setTitle("HANOI TOWER");

        hanoiPanel = new HanoiPanel();
        this.add(hanoiPanel);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
    }
}