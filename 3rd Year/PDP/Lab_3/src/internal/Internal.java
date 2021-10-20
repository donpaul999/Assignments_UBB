package internal;

import adt.Matrix;
import task.ColumnTask;
import task.KTask;
import task.MatrixTask;
import task.RowTask;

public class Internal {

    public static int buildElement(Matrix matrix_1, Matrix matrix_2, int i, int j) throws Exception {
        if (i < matrix_1.n && j < matrix_2.m){
            int element = 0;
            for (int k = 0; k < matrix_1.m; k++){
                element += matrix_1.getElem(i, k) * matrix_2.getElem(k, j);
            }
            return element;
        }
        else
            throw new Exception("Element out of bounds!");
    }


    public static MatrixTask initThread(int i, Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfThreads, String taskType) {
        switch (taskType){
            case "ROW":
                return initRowThread(i, matrix_1, matrix_2, result, nbOfThreads);
            case "COLUMN":
                return initColumnThread(i, matrix_1, matrix_2, result, nbOfThreads);
            case "KTH":
                return iniKthThread(i, matrix_1, matrix_2, result, nbOfThreads);
            default:
                return new RowTask();
        }
    }

    private static MatrixTask iniKthThread(int i, Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfThreads) {
        int resultSize = result.n * result.m;
        int count = resultSize / nbOfThreads;

        if (i < resultSize % nbOfThreads)
            count++;

        int iStart = i / result.m;
        int jStart = i % result.m;
        return new KTask(iStart, jStart, count, matrix_1, matrix_2, result, nbOfThreads);
    }

    private static MatrixTask initColumnThread(int i, Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfThreads) {
        int resultSize = result.n * result.m;
        int count = resultSize / nbOfThreads;

        int iStart = count * i % result.n;
        int jStart = count * i / result.n;

        if (i == nbOfThreads - 1)
            count += resultSize % nbOfThreads;

        return new ColumnTask(iStart, jStart, count, matrix_1, matrix_2, result);
    }

    private static MatrixTask initRowThread(int i, Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfThreads) {
        int resultSize = result.n * result.m;
        int count = resultSize / nbOfThreads;

        int iStart = count * i / result.n;
        int jStart = count * i % result.n;

        if (i == nbOfThreads - 1)
            count += resultSize % nbOfThreads;

        return new RowTask(iStart, jStart, count, matrix_1, matrix_2, result);
    }
}
