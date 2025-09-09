import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CubeSide extends JPanel {
    private final Color borderColor;
    private JPanel grid;

    public CubeSide(String sideName, Color tileColor, Color textColor) {
        this.borderColor = tileColor;
        setName(sideName);
        setBackground(Color.DARK_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create border for differentiating the sides
        setBorder(BorderFactory.createLineBorder(tileColor, 2));

        JLabel title = new JLabel(sideName);
        title.setForeground(Color.WHITE);
        add(title);

        grid = new JPanel(new GridLayout(3, 3));
        grid.setOpaque(true);
        grid.setBackground(Color.DARK_GRAY); // optional
        add(grid, BorderLayout.CENTER);

        // Initialize the table with empty values
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add(new SideSquare(count++ + "", tileColor, textColor));
            }
        }
    }

    public CubeSide(String sideName, Color tileColor) {
        this(sideName, tileColor, Color.BLACK);
    }

    public Object[][] getData() {
        Object[][] data = new JPanel[3][3];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                data[i][j] = grid.getComponent(count++);
            }
        }
        return data;
    }

    public void rotateData(Object[][] newData) {
        grid.removeAll();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add((JPanel) newData[i][j]);
            }
        }

        repaint();
        revalidate();
    }

    public void colors(boolean isColored) {
        for (int i = 0; i < 9; i++) {
            SideSquare panel = (SideSquare) grid.getComponent(i);
            panel.colors(isColored);
            if (isColored) {
                grid.setBorder(BorderFactory.createLineBorder(borderColor, 2));
            } else {
                grid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        }
    }

    public void blankedOut(boolean isBlankedOut) {
        for (int i = 0; i < 9; i++) {
            SideSquare panel = (SideSquare) grid.getComponent(i);
            panel.blankedOut(isBlankedOut);
        }
    }
}
