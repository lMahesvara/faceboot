package swingComponents;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(10,5));
        setBackground(new Color(211,207,207));
        setUnitIncrement(20);
    }
}
