package Repository;

import Model.IEnt;

public class ArrayRepository implements IRepo {
    private IEnt[] things;
    private int size, length;

    public ArrayRepository(int maxCapacity) {
        things = new IEnt[maxCapacity];
        size = 0;
    }

    @Override
    public void addElement(IEnt elementToAdd) throws MyExc {
        if(size == length) {
            throw MyExc("Limit reached!");
        }
        things[size++] = elementToAdd;
    }

    @Override
    public IEnt[] getElements() {
        return things;
    }

    @Override
    public IEnt getElementFromPosition(int position) throws MyExc {
        if(position >= size || position < 0)
            throw MyExc("Invalid position");
        return things[position];
    }

    @Override
    public void updateElement(int position, IEnt newElement) throws MyExc {
        if(position >= size || position < 0)
            throw MyExc("Invalid position");
        things[position] = newElement;
    }
}
