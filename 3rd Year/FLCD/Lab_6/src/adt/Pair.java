package adt;

public class Pair<T1, T2>
{
    public T1 first;
    public T2 second;


    public Pair(T1 first, T2 second)
    {
        this.first = first;
        this.second = second;
    }


    @Override
    public String toString()
    {
        return first.toString() + " " + second.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!first.equals(pair.first)) {
            return false;
        }
        return second.equals(pair.second);
    }

    @Override
    public int hashCode()
    {
        return 31 * first.hashCode() + second.hashCode();
    }
}