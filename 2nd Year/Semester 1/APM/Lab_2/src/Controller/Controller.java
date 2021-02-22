package Controller;
import Exceptions.MyException;
import Model.IEnt;
import Repository.*;

public class Controller {
    IRepo repository;
    public Controller(IRepo repository) {
        this.repository = repository;
    }

    public void addElement(IEnt element) throws MyException {
        this.repository.addElement(element);
    }

    public String[] filterItems(int minimumAge) {
        String[] s = new String[this.repository.getElements().length];
        int size = 0;
        for(IEnt item: this.repository.getElements()) {
            if(item == null)
                break;
            if(item.getAge() >= minimumAge) {
                s[size++] = item.toString();
            }
        }
        String[] newArray = new String[size];
        System.arraycopy(s, 0, newArray, 0, size);

        return newArray;
    }


}