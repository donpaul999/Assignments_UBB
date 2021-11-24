package domain;

import java.io.Serializable;

public class ErrorMessage extends Message implements Serializable {
    public String var;
    public int rank;

    public ErrorMessage(String var, int rank) {
        this.var = var;
        this.rank = rank;
    }
}
