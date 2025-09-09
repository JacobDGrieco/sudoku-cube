import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Sudoku {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sudoku");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 750);
            frame.setVisible(true);

            JPanel panel = new JPanel();
            panel.setBackground(Color.DARK_GRAY);
            frame.add(panel);
            frame.setContentPane(panel);

            Cube cube = new Cube();
            panel.add(cube);

            JPanel controls = new JPanel(new GridLayout(2, 3));
            controls.setBackground(Color.DARK_GRAY);
            panel.add(controls);

            JComboBox<String> sideList = new JComboBox<>(new String[] {
                    "Left", "Back", "Base", "Front", "Right", "Top"
            });
            controls.add(sideList);
            JComboBox<String> rotationList = new JComboBox<>(new String[] {
                    "Side Clockwise", "Side CounterClockwise", "Middle Col Up", "Middle Col Down", "Middle Row Left",
                    "Middle Row Right"
            });
            controls.add(rotationList);
            JButton goForRotation = new JButton("Rotate");
            goForRotation.addActionListener(e -> {
                CubeSide side = (CubeSide) cube.getComponent(sideList.getSelectedIndex());
                switch (rotationList.getSelectedItem().toString()) {
                    case "Side Clockwise":
                        cube.rotateSidesClockwise(side);
                        break;
                    case "Side CounterClockwise":
                        cube.rotateSidesCounterClockwise(side);
                        break;
                    case "Middle Col Up":
                        cube.rotateMiddleColUp(side);
                        break;
                    case "Middle Col Downe":
                        cube.rotateMiddleColDown(side);
                        break;
                    case "Middle Row Left":
                        cube.rotateMiddleRowLeft(side);
                        break;
                    case "Middle Row Right":
                        cube.rotateMiddleRowRight(side);
                        break;
                }
            });
            controls.add(goForRotation);

            JTextField shuffleCount = new JTextField();
            shuffleCount.setToolTipText("Shuffle Count (default random 1-50)");
            controls.add(shuffleCount);
            JButton goForShuffle = new JButton("Shuffle");
            goForShuffle.addActionListener(e -> {
                int count = shuffleCount.getText().isEmpty() ? (int) (Math.random() * 50) + 1
                        : Integer.parseInt(shuffleCount.getText());
                cube.shuffle(count);
            });
            controls.add(goForShuffle);

            JPanel options = new JPanel(new GridLayout(2, 1));
            panel.add(options);
            JButton colors = new JButton("Colors");
            colors.addActionListener(e -> cube.colors());
            options.add(colors);
            JButton blank = new JButton("Blanks");
            blank.addActionListener(e -> cube.blankedOut());
            options.add(blank);
        });
    }
}