package model.adt;

public class MyPair<T1, T2>
{
    private T1 first;
    private T2 second;


    public MyPair(T1 first, T2 second)
    {
        this.first = first;
        this.second = second;
    }


    @Override
    public String toString()
    {
        return first.toString() + " " + second.toString();
    }
}
