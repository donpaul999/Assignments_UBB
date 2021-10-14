public class Block {
    private String type;
    private double[][] matrix;
    private int line;
    private int column;

    public Block(String type, double[][] matrix, int line, int column){
        this.type = type;
        this.matrix = matrix;
        this.line = line;
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (double[] doubles : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                value.append(doubles[j]);
                value.append(" ");
            }
            value.append("\n");
        }

        return "Block{" + "values=" + value + ", type=" + type + ", position=" + line + " "  + column + '}';
    }
}
