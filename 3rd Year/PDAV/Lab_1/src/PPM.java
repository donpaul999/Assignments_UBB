import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PPM {
    private String imgPath;
    private String format;
    private int height;
    private int width;
    private int maxValue;

    private int[][] red;
    private int[][] green;
    private int[][] blue;

    private double[][] y;
    private double[][] u;
    private double[][] v;


    public PPM(String imgPath) throws IOException {
        this.imgPath = imgPath;
        this.readImg();
        this.convertToYUV();
    }

    private void readImg() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(imgPath));

        format = reader.readLine();
        reader.readLine();

        String line = reader.readLine();
        width = Integer.parseInt(line.split(" ")[0]);
        height = Integer.parseInt(line.split(" ")[1]);

        maxValue = Integer.parseInt(reader.readLine());

        red = new int[height][width];
        green = new int[height][width];
        blue = new int[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                red[i][j] = Integer.parseInt(reader.readLine());
                green[i][j] = Integer.parseInt(reader.readLine());
                blue[i][j] = Integer.parseInt(reader.readLine());
            }
        }
    }

    private void convertToYUV() {
        y = new double[height][width];
        u = new double[height][width];
        v = new double[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                y[i][j] = 0.299 * red[i][j] + 0.587 * green[i][j] + 0.114 * blue[i][j];
                u[i][j] = -0.169 * red[i][j] - 0.331 * green[i][j] + 0.499 * blue[i][j] + 128;
                v[i][j] = 0.499 * red[i][j] - 0.418 * green[i][j] - 0.0813 * blue[i][j] + 128;
            }
    }

    public double[][] getY() {
        return y;
    }

    public double[][] getU() {
        return u;
    }

    public double[][] getV() {
        return v;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getFormat() {
        return format;
    }
}
