import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Decoder {
    Encoder encoder;
    String fileName;
    String format;
    private int maxValue;
    private int width;
    private int height;

    private List<Block> encodedY;
    private List<Block> encodedU;
    private List<Block> encodedV;

    private double[][] matrixY;
    private double[][] matrixU;
    private double[][] matrixV;


    public Decoder(Encoder encoder, String fileName) throws IOException {
        this.fileName = fileName;

        width = encoder.getWidth();
        height = encoder.getHeight();

        format = encoder.getFormat();
        maxValue = encoder.getMaxValue();

        encodedY = encoder.getEncodedY();
        encodedU = encoder.getEncodedU();
        encodedV = encoder.getEncodedV();

        this.matrixY = new double[height][width];
        this.matrixU = new double[height][width];
        this.matrixV = new double[height][width];

        decode();
    }

    public void decode() throws IOException {
        for (Block block : encodedY) {
            decodeMatrix(block, matrixY);
        }

        for (Block block : encodedU) {
            decodeMatrix(block, matrixU);
        }

        for (Block block : encodedV) {
            decodeMatrix(block, matrixV);
        }

        writeFile(fileName);
    }

    private void writeFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(format);
        fileWriter.write("\n");
        fileWriter.write(width + " " + height);
        fileWriter.write("\n");
        fileWriter.write(Integer.toString(maxValue));
        fileWriter.write("\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r = (int) (matrixY[i][j] + 1.402 * (matrixV[i][j] - 128));
                int g = (int) (matrixY[i][j] - 0.344 * (matrixU[i][j] - 128) - 0.714 * (matrixV[i][j] - 128));
                int b = (int) (matrixY[i][j] + 1.772 * (matrixU[i][j] - 128));

                if (r < 0) {
                    r = 0;
                } else if (r > 255) {
                    r = 255;
                }
                if (g < 0) {
                    g = 0;
                } else if (g > 255) {
                    g = 255;
                }
                if (b < 0) {
                    b = 0;
                } else if (b > 255) {
                    b = 255;
                }

                fileWriter.write(Integer.toString(r) + '\n');
                fileWriter.write(Integer.toString(g) + '\n');
                fileWriter.write(Integer.toString(b) + '\n');

            }
        }
        fileWriter.close();
    }

    private void decodeMatrix(Block block, double[][] matrix) {
        int line = block.getLine(), col;
        String type = block.getType();
        double[][] blockMatrix = block.getMatrix();

        for (int i = 0; i < (type.equals("Y") ? 8 : 4); i++){
            col = block.getColumn();
            for (int j = 0; j < (type.equals("Y") ? 8 : 4); j++){
                matrix[line][col] = blockMatrix[i][j];
                col ++;
            }
            line ++;
        }
    }
}
