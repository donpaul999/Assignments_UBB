package Repository;
import Model.*;

public interface IRepo {
    public void addElement(IEnt elementToAdd) throws MyExc;
    public IEnt[] getElements();
    public IEnt getElementFromPosition(int position) throws MyExc;
    public void updateElement(int position, IEnt newElement) throws MyExc;
}
