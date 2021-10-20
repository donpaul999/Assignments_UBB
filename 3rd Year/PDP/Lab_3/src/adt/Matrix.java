package adt;

import java.util.Random;

public final class Matrix {
    public final int n;
    public final int m;
    public int[][] matrix;

    public Matrix(int n, int m, boolean fill) {
        this.n = n;
        this.m = m;
        this.matrix = new int[n][m];

        if(fill) {
            fill();
        }
    }

    public Matrix() {
        n = 0;
        m = 0;
    }

    public void fill() {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(10) + 1;
            }

        }
    }

    public int getElem(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void setElem(int row, int column, int value) {
        matrix[row][column] = value;
    }
}

