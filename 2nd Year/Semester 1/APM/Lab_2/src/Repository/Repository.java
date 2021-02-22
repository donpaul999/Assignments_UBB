package Repository;

import Exceptions.MyException;
import Model.IEnt;

public class Repository implements IRepo {
    private IEnt[] things;
    private int size, length;

    public Repository(int maxCapacity) {
        things = new IEnt[maxCapacity];
        size = 0;
        length = maxCapacity;
    }

    @Override
    public void addElement(IEnt elementToAdd) throws MyException {
        if(size == length) {
            throw new MyException("Limit reached!");
        }
        things[size++] = elementToAdd;
    }

    @Override
    public IEnt[] getElements() {
        return things;
    }

    @Override
    public IEnt getElementFromPosition(int position) throws MyException {
        if(position >= size || position < 0)
            throw new MyException("Invalid position");
        return things[position];
    }

    @Override
    public void updateElement(int position, IEnt newElement) throws MyException {
        if(position >= size || position < 0)
            throw new MyException("Invalid position");
        things[position] = newElement;
    }
}