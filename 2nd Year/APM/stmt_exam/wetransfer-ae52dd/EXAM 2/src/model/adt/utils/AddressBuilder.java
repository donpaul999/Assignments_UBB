package model.adt.utils;

import model.adt.MyIStack;
import model.adt.MyStack;

public class AddressBuilder {
    private Integer address = 1;
    private static MyIStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress() {
        return freeAddress.isEmpty() ? this.address ++ : freeAddress.pop();
    }
}
