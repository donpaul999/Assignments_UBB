package task;

import adt.Matrix;
import adt.Pair;

public final class RowTask extends MatrixTask {
    public RowTask(int iStart, int jStart, int count, Matrix matrix_1, Matrix matrix_2, Matrix result) {
        super(iStart, jStart, count, matrix_1, matrix_2, result);
    }

    public RowTask() {
        super();
    }

    @Override
    public void computeResult() {
        int i = iStart, j = jStart;
        int size = sizeOfTask;
        while (size > 0 && i < result.n && j < result.m) {
            pairs.add(new Pair<>(i, j));
            j++;
            size--;
            if (j == result.n) {
                j = 0;
                i++;
            }
        }
    }
}
