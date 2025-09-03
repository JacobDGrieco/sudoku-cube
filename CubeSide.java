import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CubeSide extends JPanel {
    private Color borderColor;

    public CubeSide(String sideName, Color tileColor, Color textColor) {
        this.borderColor = tileColor;
        setName(sideName);
        setLayout(new GridLayout(3, 3));

        // Create border for differentiating the sides
        setBorder(BorderFactory.createLineBorder(tileColor, 2));

        // Initialize the table with empty values
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                add(new SideSquare(count++ + "", tileColor, textColor));
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
                data[i][j] = getComponent(count++);
            }
        }
        return data;
    }

    public void rotateData(Object[][] newData) {
        removeAll();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                add((JPanel) newData[i][j]);
            }
        }

        repaint();
        revalidate();
    }

    public void monochrome(boolean isMonochrome) {
        for (int i = 0; i < 9; i++) {
            SideSquare panel = (SideSquare) getComponent(i);
            panel.monochrome(isMonochrome);
            if (isMonochrome) {
                setBorder(BorderFactory.createLineBorder(borderColor, 2));
            } else {
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        }
    }

    public void blankedOut(boolean isBlankedOut) {
        for (int i = 0; i < 9; i++) {
            SideSquare panel = (SideSquare) getComponent(i);
            panel.blankedOut(isBlankedOut);
        }
    }
}
