package task;

import adt.Matrix;
import adt.Pair;

public class KTask extends MatrixTask{
    public KTask(int iStart, int jStart, int count, Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfTasks) {
        super(iStart, jStart, count, matrix_1, matrix_2, result, nbOfTasks);
    }

    @Override
    public void computeResult() {
        int i = iStart, j = jStart;
        int size = sizeOfTask;
        while (size > 0 && i < result.n) {
            pairs.add(new Pair<>(i, j));
            size--;
            i += (j + nbOfTask) / result.m;
            j = (j + nbOfTask) % result.m;
        }
    }
}
