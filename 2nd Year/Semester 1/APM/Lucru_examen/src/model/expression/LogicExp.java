package model.expression;

import model.ADT.IMyHeap;
import model.exceptions.ExprException;
import model.ADT.IMyDictionary;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op;

    public LogicExp(Exp deepCopy, Exp deepCopy1, int op1) {
        e1 = deepCopy;
        e2 = deepCopy1;
        op = op1;
    }

    public LogicExp(String not, Exp exp) {
        e1 = exp;
        e2 = exp;
        op = 3;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> tbl, IMyHeap<Value> heap) throws ExprException {
        Value val1, val2;
        val1 = e1.eval(tbl, heap);
        if (val1.getType().equals(new BoolType())) {
            if (op == 3) {
                Value falseVal = new BoolValue(false);
                return new BoolValue(val1.equals(falseVal));
            }
            val2 = e2.eval(tbl, heap);
            if (val2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)val1;
                BoolValue i2 = (BoolValue)val2;
                boolean x = i1.getValue();
                boolean y = i2.getValue();
                if (op == 1) {
                    return new BoolValue(x && y);
                }
                else if (op == 2) {
                    return new BoolValue(x || y);
                }
            }
            else {
                throw new ExprException("Second operand is not a boolean");
            }
        }
        else {
            throw new ExprException("First operand is not a boolean");
        }

        return new BoolValue(false);
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    @Override
    public Type typecheck(IMyDictionary<String, Type> typeEnv) throws ExprException {
        Type type1 = e1.typecheck(typeEnv);
        Type type2 = e2.typecheck(typeEnv);

        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            }
            else {
                throw new ExprException("The second operand is not a boolean in " + this.toString());
            }
        }
        else {
            throw new ExprException("The first operand is not a boolean in " + this.toString());
        }
    }

    @Override
    public String toString() {
        if(op == 3){
            return "!(" + e1.toString() +")";
        }
        if(op == 1) {
            return e1.toString() + "&&" + e2.toString();
        }
        return e1.toString() + "||" + e2.toString();
    }
}
