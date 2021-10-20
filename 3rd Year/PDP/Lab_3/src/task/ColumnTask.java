package task;

import adt.Matrix;
import adt.Pair;

public class ColumnTask extends MatrixTask{
    public ColumnTask(int iStart, int jStart, int count, Matrix matrix_1, Matrix matrix_2, Matrix result) {
        super(iStart, jStart, count, matrix_1, matrix_2, result);
    }

    @Override
    public void computeResult() {
        int i = iStart, j = jStart;
        int size = sizeOfTask;
        while (size > 0 &&  i < result.n && j < result.m) {
            pairs.add(new Pair<>(i, j));
            i++;
            size--;
            if (i == result.m) {
                i = 0;
                j++;
            }
        }
    }
}
