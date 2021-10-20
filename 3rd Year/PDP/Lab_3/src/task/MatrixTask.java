package task;

import adt.Matrix;
import adt.Pair;
import internal.Internal;

import java.util.ArrayList;
import java.util.List;

public abstract class MatrixTask extends Thread {
    public List<Pair<Integer,Integer>> pairs;
    public final int iStart;
    public final int jStart;
    public final int sizeOfTask;

    public final Matrix matrix_1;
    public final Matrix matrix_2;
    public final Matrix result;

    public int nbOfTask;

    public MatrixTask(int iStart, int jStart, int sizeOfTask, Matrix matrix_1, Matrix matrix_2, Matrix result) {
        this.iStart = iStart;
        this.jStart = jStart;
        this.sizeOfTask = sizeOfTask;
        this.matrix_1 = matrix_1;
        this.matrix_2 = matrix_2;
        this.result = result;
        this.pairs = new ArrayList<>();
        computeResult();
    }

    public MatrixTask(int iStart, int jStart, int sizeOfTask, Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfTask) {
        this.iStart = iStart;
        this.jStart = jStart;
        this.sizeOfTask = sizeOfTask;
        this.matrix_1 = matrix_1;
        this.matrix_2 = matrix_2;
        this.result = result;
        this.pairs = new ArrayList<>();
        this.nbOfTask = nbOfTask;
        computeResult();
    }

    public MatrixTask() {
        this.iStart = 0;
        this.jStart = 0;
        this.sizeOfTask = 0;
        this.matrix_1 = new Matrix();
        this.matrix_2 = new Matrix();
        this.result = new Matrix();
        this.pairs = new ArrayList<>();
    }

    public abstract void computeResult();

    @Override
    public void run() {
        for (Pair<Integer,Integer> p: pairs){
            int i = p.first;
            int j = p.second;
            try {
                result.setElem(i,j, Internal.buildElement(matrix_1, matrix_2, i, j));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
