package model.exp;

import exception.DivisionByZero;
import exception.InvalidDataType;
import exception.MyException;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.type.IntType;
import model.type.Type;
import model.value.IValue;
import model.value.IntValue;

public class ArithExp implements Exp{
    private int op; //1-plus, 2-minus, 3-multiplication, 4-division
    private Exp e1, e2;

    public ArithExp(int op, Exp e1, Exp e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    public Exp getE1() {
        return e1;
    }

    public Exp getE2() {
        return e2;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap<Integer,IValue> heap) throws MyException{
        IValue v1,v2;
        v1= e1.eval(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2; int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op==1)
                    return new IntValue(n1+n2);
                if (op==2)
                    return new IntValue(n1-n2);
                if (op==3)
                    return new IntValue(n1*n2);
                if (op==4)
                    if(n2==0)
                        throw new DivisionByZero("division by zero");
                    else
                        return new IntValue(n1/n2);
                throw new MyException("invalid operand");
            }
            else
                throw new InvalidDataType("second operand is not an integer");
        }
        else
            throw new InvalidDataType("first operand is not an integer");
    }

    public int getOp() {return this.op;}

    public Exp getFirst() {
        return this.e1;
    }

    public Exp getSecond() {
        return this.e2;
    }

    public String toString() {
        String s = "";
        s += this.e1.toString() + " ";
        if (this.op == 1)
            s += "+";
        else if (this.op == 2)
            s += "-";
        else if (this.op == 3)
            s += "*";
        else if (this.op == 4)
            s += "/";
        s += " " + this.e2.toString();
        return s;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }
}
