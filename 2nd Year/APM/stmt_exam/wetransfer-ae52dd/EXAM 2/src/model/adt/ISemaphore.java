package model.adt;

import java.util.Map;

public interface ISemaphore {

    void setSemaphores(MyIDictionary<Integer, MyIPair> semaphores);

    Integer getSemaphoreAddress();

    boolean exists(Integer index);

    MyIDictionary<Integer, MyIPair> getSemaphore();

    void put(Integer foundIndex, MyIPair integerListPair);

    public Map<Integer, MyIPair> getContent();

}
