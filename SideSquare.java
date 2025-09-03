
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideSquare extends JPanel {
    private final Color tileColor;
    private final Color textColor;

    public SideSquare(String value, Color tileColor, Color textColor) {
        this.tileColor = tileColor;
        this.textColor = textColor;

        setLayout(new GridBagLayout());
        setBackground(tileColor);
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JLabel label = new JLabel(value, JLabel.CENTER);
        label.setForeground(textColor);
        add(label);
    }

    public void colors(boolean isColored) {
        if (isColored) {
            setBackground(Color.LIGHT_GRAY);
            getComponent(0).setForeground(Color.DARK_GRAY);
        } else {
            setBackground(tileColor);
            getComponent(0).setForeground(textColor);
        }
    }

    public void blankedOut(boolean isBlankedOut) {
        if (isBlankedOut) {
            getComponent(0).setVisible(false);
        } else {
            getComponent(0).setVisible(true);
        }
    }
}
