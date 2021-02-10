package model.exp;

import exception.InvalidDataType;
import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;

public class RelationalExp implements Exp {
    Exp exp1, exp2;
    int rel; //1 <, //2 <=, 3 ==, 4 !=, 5 >, 6 >=

    public RelationalExp(Exp ex1, Exp ex2, int rel) {
        this.exp1 = ex1;
        this.exp2 = ex2;
        this.rel = rel;
    }


    public int getRel() {
        return rel;
    }



    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = exp1.typeCheck(typeEnv);
        typ2 = exp2.typeCheck(typeEnv);
        if (typ1.equals(new IntType()))
        {
            if (typ2.equals(new IntType()))
                return new BoolType();
            else
                throw new MyException("Rel: second operand is not an integer");
        }
        else
            throw new MyException("Rel: first operand is not an integer");
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, IHeap<Integer,IValue> heap) throws MyException {
        IValue v1,v2;
        v1= exp1.eval(symTable,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(symTable,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2; int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (rel==1)
                    return new BoolValue(n1<n2);
                if (rel==2)
                    return new BoolValue(n1<=n2);
                if (rel==3)
                    return new BoolValue(n1==n2);
                if (rel==4)
                    return new BoolValue(n1!=n2);
                if (rel==5)
                    return new BoolValue(n1>n2);
                if (rel==6)
                    return new BoolValue(n1>=n2);
                throw new MyException("Rel: invalid operand");
            }
            else
                throw new InvalidDataType("Rel: second operand is not an integer");
        }
        else
            throw new InvalidDataType("Rel: first operand is not an integer");
    }

    @Override
    public String toString() {
        String relOp = switch (rel) {
            case 1 -> "<";
            case 2 -> "<=";
            case 3 -> "==";
            case 4 -> "!=";
            case 5 -> ">";
            case 6 -> ">=";
            default -> "";
        };
        return exp1.toString() + " " + relOp + " " + exp2.toString();
    }
}
