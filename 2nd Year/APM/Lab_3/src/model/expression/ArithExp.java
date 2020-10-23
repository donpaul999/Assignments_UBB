package model.expression;

import exceptions.MyException;
import model.ADT.IMyDictionary;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide


    public ArithExp(Exp deepCopy, Exp deepCopy1, char op) {
        this.e1 = deepCopy;
        this.e2 = deepCopy1;
        if(op == '+')
            this.op = 1;
        if(op == '-')
            this.op = 2;
        if(op == '*')
            this.op = 3;
        if(op == '/')
            this.op = 4;
    }

    @Override
    public Value eval(IMyDictionary<String, Value> tbl) throws MyException {
        Value val1, val2;
        val1 = e1.eval(tbl);
        if (val1.getType().equals(new IntType())) {
            val2 = e2.eval(tbl);
            if (val2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)val1;
                IntValue i2 = (IntValue)val2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();
                if (op == 1) {
                    return new IntValue(n1 + n2);
                }
                else if (op == 2) {
                    return new IntValue(n1 - n2);
                }
                else if (op == 3) {
                    return new IntValue(n1 * n2);
                }
                else if (op == 4) {
                    if (n2 == 0) {
                        throw new MyException("Division by zero");
                    }
                    else {
                        return new IntValue(n1 / n2);
                    }
                }
            }
            else {
                throw new MyException("Second operand is not an integer");
            }
        }
        else {
            throw new MyException("First operand is not an integer");
        }

        return new IntValue(0);
    }



    @Override
    public String toString() {
        switch (op) {
            case 1:
                return e1.toString() + "+" + e2.toString();
            case 2:
                return e1.toString() + "-" + e2.toString();
            case 3:
                return e1.toString() + "*" + e2.toString();
            case 4:
                return e1.toString() + '/' + e2.toString();
            default:
                return "";
        }
    }
}
