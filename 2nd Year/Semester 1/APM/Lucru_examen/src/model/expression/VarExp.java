package model.expression;

import model.ADT.IMyHeap;
import model.exceptions.ExprException;
import model.exceptions.MyException;
import model.ADT.IMyDictionary;
import model.type.Type;
import model.value.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String s) {
        id = s;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> tbl, IMyHeap<Value> heap) throws ExprException {
        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(new String(id));
    }

    @Override
    public Type typecheck(IMyDictionary<String, Type> typeEnv) throws ExprException {
        if(typeEnv.isDefined(id)){
            return typeEnv.lookup(id);
        }
        else{
            throw new ExprException("The variable " + this.toString() + " is not defined");
        }
    }
}
