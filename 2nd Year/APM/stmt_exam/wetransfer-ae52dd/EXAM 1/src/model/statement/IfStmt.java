package model.statement;

import exception.InvalidDataType;
import exception.MyException;
import model.PrgState;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.exp.Exp;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IValue;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS; IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp=e;
        thenS=t;
        elseS=el;
    }

    @Override
    public String toString() {
        { return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";}
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyIStack<IStmt> exeStack = state.getExeStack();
        IValue cond = this.exp.eval(symTable,state.getHeap());
        if (!cond.getType().toString().equals("bool"))
            throw new InvalidDataType("cond exp is not a boolean");
        else {
            BoolValue bv = (BoolValue) cond;
            if (bv.getVal())
                exeStack.push(thenS);
            else
                exeStack.push(elseS);
        }
        //return state;
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typeCheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typeCheck(clone(typeEnv));
            elseS.typeCheck(clone(typeEnv));
            return typeEnv;                        
        }                         
        else throw new MyException("The condition of IF has not the type bool");
    }

    private MyIDictionary<String, Type> clone(MyIDictionary<String, Type> typeEnv) {
        return typeEnv.cloneDictionary();
    }
}
