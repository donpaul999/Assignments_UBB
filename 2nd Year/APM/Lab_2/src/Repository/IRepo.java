package Repository;
import Exceptions.MyException;
import Model.*;

public interface IRepo {
    public void addElement(IEnt elementToAdd) throws MyException;
    public IEnt[] getElements();
    public IEnt getElementFromPosition(int position) throws MyException;
    public void updateElement(int position, IEnt newElement) throws MyException;
}