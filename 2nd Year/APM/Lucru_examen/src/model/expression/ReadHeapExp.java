package model.expression;

import model.ADT.IMyDictionary;
import model.ADT.IMyHeap;
import model.exceptions.ExprException;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExp implements Exp {
    private Exp exp;

    public ReadHeapExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> symTable, IMyHeap<Value> heap) throws ExprException {
        Value val = exp.eval(symTable, heap);
        if (val instanceof RefValue) {
            RefValue refVal = (RefValue) val;
            if (heap.contains(refVal.getAddress())) {
                return heap.get(refVal.getAddress());
            } else {
                throw new ExprException("The address doesn't exist in the heap");
            }

        } else {
            throw new ExprException("The expression could not be evaluated to a RefValue");
        }
    }

    @Override
    public Exp deepCopy() {
        return new ReadHeapExp(exp.deepCopy());
    }


    @Override
    public String toString() {
        return "rH(" + exp + ")";
    }

    @Override
    public Type typecheck(IMyDictionary<String, Type> typeEnv) throws ExprException {
        Type type = exp.typecheck(typeEnv);
        if (type instanceof RefType) {
            RefType refType = (RefType)type;
            return refType.getInner();
        }
        else {
            throw new ExprException("The expression is not of a reference type in " + this.toString());
        }
    }
}