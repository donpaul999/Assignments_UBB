package model.exp;

import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.RefType;
import model.type.Type;
import model.value.IValue;
import model.value.RefValue;

public class HeapReadingExp implements Exp {

    private Exp exp;

    public HeapReadingExp(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return this.exp.toString();
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = exp.typeCheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the heap reading argument is not a Ref Type");
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, IHeap<Integer,IValue> heap) throws MyException {
        IValue val = this.exp.eval(symTable, heap);
        if (val.getType() instanceof RefType) {
            RefValue newVal = (RefValue) val;
            int address = newVal.getAddr();
            IValue content = heap.getContentFromAddress(address);
            if (content != null)
                return content;
            else throw new MyException("The address doesn't exists in the heap");

        } else throw new MyException("The value is not a Reference Value!");
    }
}
