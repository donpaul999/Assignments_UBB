import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encoder {
    private List<Block> encodedY;
    private List<Block> encodedU;
    private List<Block> encodedV;

    private int maxValue;
    private int width;
    private int height;

    String format;

    public Encoder(PPM image) throws IOException {
        maxValue = image.getMaxValue();
        width = image.getWidth();
        height = image.getHeight();

        format = image.getFormat();

        encodedY = storeBlocks(image, "Y", image.getY());
        encodedU = storeBlocks(image, "U", image.getU());
        encodedV = storeBlocks(image, "V", image.getV());

        printValues();
    }

    private void printValues() throws IOException {
        FileWriter fileWriter = new FileWriter("output_y.txt");

        for (Block block : encodedY) {
            fileWriter.write(String.valueOf(block));
            fileWriter.write("\n");
        }

        fileWriter = new FileWriter("output_u.txt");

        for (Block block : encodedU) {
            fileWriter.write(String.valueOf(block));
            fileWriter.write("\n");
        }

        fileWriter = new FileWriter("output_v.txt");

        for (Block block : encodedV) {
            fileWriter.write(String.valueOf(block));
            fileWriter.write("\n");
        }
    }

    private List<Block> storeBlocks(PPM image, String type, double[][] matrix) {
        List<Block> encoded = new ArrayList<>();

        for (int i = 0; i < image.getHeight(); i += 8)
            for (int j = 0; j < image.getWidth(); j += 8) {
                double[][] matrix8Blocks = generate8x8Matrix(i, j, matrix);
                if (type.equals("Y"))
                    encoded.add(new Block(type, matrix8Blocks, i, j));
                else {
                    double[][] matrix4Blocks = generate4x4Matrix(matrix);
                    encoded.add(new Block(type, matrix4Blocks, i, j));
                }
            }

        return encoded;
    }

    private double[][] generate4x4Matrix(double[][] matrix) {
        double[][] newMatrix = new double[4][4];
        int newL = 0, newC;
        for (int i = 0; i < 8; i += 2) {
            newC = 0;
            for (int j = 0; j < 8; j += 2) {
                double result = average(i, j, matrix);
                newMatrix[newL][newC] = result;
                newC ++;
            }
            newL ++;
        }
        return newMatrix;
    }

    private double average(int line, int col, double[][] matrix) {
        double sum = 0;
        for (int i = line; i < line + 2; i++) {
            for (int j = col; j < col + 2; j++) {
                sum += matrix[line][col];
            }
        }
        return sum / 4;
    }

    private double[][] generate8x8Matrix(int line, int col, double[][] matrix) {
        int newL = 0, newC;
        double[][] newMatrix = new double[8][8];
        for (int i = line; i < line + 8; i++) {
            newC = 0;
            for (int j = col; j < col + 8; j++) {
                newMatrix[newL][newC] = matrix[i][j];
                newC ++;
            }
            newL ++;
        }
        return newMatrix;
    }

    public List<Block> getEncodedY() {
        return encodedY;
    }

    public List<Block> getEncodedU() {
        return encodedU;
    }

    public List<Block> getEncodedV() {
        return encodedV;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFormat() {
        return format;
    }
}
