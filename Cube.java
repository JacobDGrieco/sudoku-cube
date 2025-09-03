import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Cube extends JPanel {
    private boolean isMonochrome = false;
    private boolean isBlankedOut = false;

    public Cube() {
        setLayout(new GridBagLayout());

        CubeSide leftSide = new CubeSide("Left", Color.ORANGE);
        CubeSide backSide = new CubeSide("Back", Color.BLUE, Color.WHITE);
        CubeSide baseSide = new CubeSide("Base", Color.WHITE);
        CubeSide frontSide = new CubeSide("Front", Color.GREEN);
        CubeSide rightSide = new CubeSide("Right", Color.RED, Color.WHITE);
        CubeSide topSide = new CubeSide("Top", Color.YELLOW);

        add(leftSide, setGBC(0, 1));
        add(backSide, setGBC(1, 0));
        add(baseSide, setGBC(1, 1));
        add(frontSide, setGBC(1, 2));
        add(rightSide, setGBC(2, 1));
        add(topSide, setGBC(3, 1));
    }

    private GridBagConstraints setGBC(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets.set(5, 5, 5, 5); // Padding between sides
        return gbc;
    }

    public void rotateSidesClockwise(CubeSide side) {
        System.out.println("Rotating " + side.getName() + " clockwise");
        Object[][] data = side.getData();
        Object[][] newData = clone3DArray(data);

        // Rotate the current side clockwise
        newData[0][0] = data[1][0];
        newData[0][1] = data[0][0];
        newData[0][2] = data[0][1];
        newData[1][2] = data[0][2];
        newData[2][2] = data[1][2];
        newData[2][1] = data[2][2];
        newData[2][0] = data[2][1];
        newData[1][0] = data[2][0];
        side.rotateData(newData);

        // Rotate the adjacent sides' edges
        CubeSide leftAdj = getSidesClocks(side)[0];
        CubeSide topAdj = getSidesClocks(side)[1];
        CubeSide rightAdj = getSidesClocks(side)[2];
        CubeSide baseAdj = getSidesClocks(side)[3];
        Object[][] leftAdjData = leftAdj.getData();
        Object[][] topAdjData = topAdj.getData();
        Object[][] rightAdjData = rightAdj.getData();
        Object[][] baseAdjData = baseAdj.getData();

        // Base -> Left
        newData = clone3DArray(leftAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = baseAdjData[i][2];
        }
        leftAdj.rotateData(newData);

        // Left -> Top
        newData = clone3DArray(topAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = leftAdjData[i][2];
        }
        topAdj.rotateData(newData);

        // Top -> Right
        newData = clone3DArray(rightAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = topAdjData[i][2];
        }
        rightAdj.rotateData(newData);

        // Right -> Base
        newData = clone3DArray(baseAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = rightAdjData[i][2];
        }
        baseAdj.rotateData(newData);
    }

    public void rotateSidesCounterClockwise(CubeSide side) {
        System.out.println("Rotating " + side.getName() + " clockwise");
        Object[][] data = side.getData();
        Object[][] newData = clone3DArray(data);

        // Rotate the current side clockwise
        newData[0][0] = data[0][1];
        newData[0][1] = data[0][2];
        newData[0][2] = data[1][2];
        newData[1][2] = data[2][2];
        newData[2][2] = data[2][1];
        newData[2][1] = data[2][0];
        newData[2][0] = data[1][0];
        newData[1][0] = data[0][0];
        side.rotateData(newData);

        // Rotate the adjacent sides' edges
        CubeSide leftAdj = getSidesClocks(side)[0];
        CubeSide topAdj = getSidesClocks(side)[1];
        CubeSide rightAdj = getSidesClocks(side)[2];
        CubeSide baseAdj = getSidesClocks(side)[3];
        Object[][] leftAdjData = leftAdj.getData();
        Object[][] topAdjData = topAdj.getData();
        Object[][] rightAdjData = rightAdj.getData();
        Object[][] baseAdjData = baseAdj.getData();

        // Base -> Right
        newData = clone3DArray(rightAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = baseAdjData[i][2];
        }
        rightAdj.rotateData(newData);

        // Right -> Top
        newData = clone3DArray(topAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = rightAdjData[i][2];
        }
        topAdj.rotateData(newData);

        // Top -> Left
        newData = clone3DArray(leftAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = topAdjData[i][2];
        }
        leftAdj.rotateData(newData);

        // Left -> Base
        newData = clone3DArray(baseAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][2] = leftAdjData[i][2];
        }
        baseAdj.rotateData(newData);
    }

    public void rotateMiddleUp(CubeSide side) {
        System.out.println("Rotating " + side.getName() + "'s middle up");
        Object[][] newData;

        // Rotate the middle columns
        CubeSide topAdj = getSidesMiddles(side)[0];
        CubeSide backAdj = getSidesMiddles(side)[1];
        CubeSide baseAdj = getSidesMiddles(side)[2];
        Object[][] frontData = side.getData();
        Object[][] topAdjData = topAdj.getData();
        Object[][] backAdjData = backAdj.getData();
        Object[][] baseAdjData = baseAdj.getData();

        // Side -> Top
        newData = clone3DArray(topAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = frontData[i][1];
        }
        topAdj.rotateData(newData);

        // Top -> Back
        newData = clone3DArray(backAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = topAdjData[i][1];
        }
        backAdj.rotateData(newData);

        // Back -> Base
        newData = clone3DArray(baseAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = backAdjData[i][1];
        }
        baseAdj.rotateData(newData);

        // Base -> Front
        newData = clone3DArray(frontData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = baseAdjData[i][1];
        }
        side.rotateData(newData);
    }

    public void rotateMiddleDown(CubeSide side) {
        System.out.println("Rotating " + side.getName() + "'s middle down");
        Object[][] newData;

        // Rotate the adjacent sides' edges
        // This is a simplified version and assumes a specific cube orientation
        CubeSide topAdj = getSidesMiddles(side)[0];
        CubeSide backAdj = getSidesMiddles(side)[1];
        CubeSide baseAdj = getSidesMiddles(side)[2];
        Object[][] frontData = side.getData();
        Object[][] topAdjData = topAdj.getData();
        Object[][] backAdjData = backAdj.getData();
        Object[][] baseAdjData = baseAdj.getData();

        // Side -> Base
        newData = clone3DArray(baseAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = frontData[i][1];
        }
        baseAdj.rotateData(newData);

        // Base -> Back
        newData = clone3DArray(backAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = baseAdjData[i][1];
        }
        backAdj.rotateData(newData);

        // Back -> Top
        newData = clone3DArray(topAdjData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = backAdjData[i][1];
        }
        topAdj.rotateData(newData);

        // Top -> Front
        newData = clone3DArray(frontData);
        for (int i = 0; i < 3; i++) {
            newData[i][1] = topAdjData[i][1];
        }
        side.rotateData(newData);
    }

    public void shuffle(int rotations) {
        System.out.println("\nHitting the SpongeBob " + rotations + " times");

        for (int i = 0; i < rotations; i++) {
            int randomSide = (int) (Math.random() * 6);
            int randomRotation = (int) (Math.random() * 4);
            CubeSide side = (CubeSide) getComponent(randomSide);
            switch (randomRotation) {
                case 0:
                    rotateSidesClockwise(side);
                    break;
                case 1:
                    rotateSidesCounterClockwise(side);
                    break;
                case 2:
                    rotateMiddleUp(side);
                    break;
                case 3:
                    rotateMiddleDown(side);
                    break;
            }
        }
    }

    private Object[][] clone3DArray(Object[][] array) {
        Object[][] newArray = new Object[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(array[i], 0, newArray[i], 0, 3);
        }
        return newArray;
    }

    private CubeSide[] getSidesClocks(CubeSide side) {
        CubeSide[] sides = new CubeSide[4];

        CubeSide left = (CubeSide) getComponent(0); // Left
        CubeSide back = (CubeSide) getComponent(1); // Back
        CubeSide base = (CubeSide) getComponent(2); // Base
        CubeSide front = (CubeSide) getComponent(3); // Front
        CubeSide right = (CubeSide) getComponent(4); // Right
        CubeSide top = (CubeSide) getComponent(5); // Top

        switch (side.getName()) {
            case "Left":
                sides[0] = back;
                sides[1] = top;
                sides[2] = front;
                sides[3] = base;
                break;
            case "Back":
                sides[0] = right;
                sides[1] = top;
                sides[2] = left;
                sides[3] = base;
                break;
            case "Base":
                sides[0] = left;
                sides[1] = front;
                sides[2] = right;
                sides[3] = back;
                break;
            case "Front":
                sides[0] = left;
                sides[1] = top;
                sides[2] = right;
                sides[3] = base;
                break;
            case "Right":
                sides[0] = front;
                sides[1] = top;
                sides[2] = back;
                sides[3] = base;
                break;
            case "Top":
                sides[0] = left;
                sides[1] = back;
                sides[2] = right;
                sides[3] = front;
                break;
        }
        return sides;
    }

    private CubeSide[] getSidesMiddles(CubeSide side) {
        CubeSide[] sides = new CubeSide[3];

        CubeSide left = (CubeSide) getComponent(0); // Left
        CubeSide back = (CubeSide) getComponent(1); // Back
        CubeSide base = (CubeSide) getComponent(2); // Base
        CubeSide front = (CubeSide) getComponent(3); // Front
        CubeSide right = (CubeSide) getComponent(4); // Right
        CubeSide top = (CubeSide) getComponent(5); // Top

        switch (side.getName()) {
            case "Left":
                sides[0] = top;
                sides[1] = right;
                sides[2] = base;
                break;
            case "Back":
                sides[0] = top;
                sides[1] = front;
                sides[2] = base;
                break;
            case "Base":
                sides[0] = front;
                sides[1] = top;
                sides[2] = back;
                break;
            case "Front":
                sides[0] = top;
                sides[1] = back;
                sides[2] = base;
                break;
            case "Right":
                sides[0] = top;
                sides[1] = left;
                sides[2] = base;
                break;
            case "Top":
                sides[0] = back;
                sides[1] = base;
                sides[2] = front;
                break;
        }
        return sides;
    }

    public void monochrome() {
        isMonochrome = !isMonochrome;
        System.out.println(isMonochrome ? "Sucking the joy out of life" : "Applying a splash of color to the cube");
        for (int i = 0; i < 6; i++) {
            CubeSide side = (CubeSide) getComponent(i);
            side.monochrome(isMonochrome);
        }
    }

    public void blankedOut() {
        isBlankedOut = !isBlankedOut;
        System.out.println("Blanks for days");
        for (int i = 0; i < 6; i++) {
            CubeSide side = (CubeSide) getComponent(i);
            side.blankedOut(isBlankedOut);
        }
    }
}
