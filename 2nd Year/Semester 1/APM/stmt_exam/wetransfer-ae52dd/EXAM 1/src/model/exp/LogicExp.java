package model.exp;

import exception.InvalidDataType;
import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IValue;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1 = and &&, 2 = or ||

    public LogicExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Exp getE1() {
        return e1;
    }

    public Exp getE2() {
        return e2;
    }

    public int getOp() {
        return op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, IHeap<Integer,IValue> heap) throws MyException {
        IValue v1, v2;
        v1 = this.e1.eval(symTable,heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = this.e2.eval(symTable,heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if (op == 1)
                    return new BoolValue(n1 && n2);
                if (op == 2)
                    return new BoolValue(n1 || n2);
                else throw new InvalidDataType("invalid bool operand");
            } else throw new InvalidDataType("invalid second operand");
        } else throw new InvalidDataType("invalid first operand");
    }

    @Override
    public String toString() {
        String s = "";
        s += this.e1.toString() + " ";
        if (op == 1)
            s += "and";
        else if (op == 2)
            s += "or";
        s += " " + this.e2.toString();
        return s;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new BoolType()))
        {
            if (typ2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("second operand is not a boolean");
        }
        else
            throw new MyException("first operand is not a boolean");
    }
}
