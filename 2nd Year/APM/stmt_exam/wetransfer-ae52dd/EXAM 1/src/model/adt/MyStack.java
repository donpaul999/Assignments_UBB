package model.adt;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

    Stack<T> stack;

    public MyStack(){
        stack=new Stack<T>();
    }

    @Override
    public String toString() {
        String s="";
        for(T t: this.stack)
            s+= t.toString()+"\n";
        return s;
    }

    @Override
    public Stack<T> getStack() {
        return stack;
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
