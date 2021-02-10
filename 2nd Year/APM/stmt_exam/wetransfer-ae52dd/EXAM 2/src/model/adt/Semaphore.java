package model.adt;

import model.adt.utils.AddressBuilder;

import java.util.Map;

public class Semaphore implements ISemaphore {

    MyIDictionary<Integer, MyIPair> semaphore;
    private AddressBuilder semaphoreAddress = new AddressBuilder();

    public Semaphore() {
        this.semaphore = new MyDictionary<>();
    }

    @Override
    public void setSemaphores(MyIDictionary<Integer, MyIPair> semaphores) {
        this.semaphore=semaphores;
    }

    @Override
    public Integer getSemaphoreAddress() {
        return semaphoreAddress.getFreeAddress();
    }

    @Override
    public boolean exists(Integer index) {
        MyIPair pairValue = semaphore.lookup(index);
        return false;
    }

    @Override
    public MyIDictionary<Integer, MyIPair> getSemaphore() {
        return semaphore;
    }

    @Override
    public void put(Integer foundIndex, MyIPair integerListPair) {
        semaphore.add(foundIndex,integerListPair);
    }

    @Override
    public Map<Integer, MyIPair> getContent() {
        return semaphore.getDictionary();
    }
}
