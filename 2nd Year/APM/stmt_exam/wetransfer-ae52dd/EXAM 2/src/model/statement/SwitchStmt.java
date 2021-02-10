package model.statement;

import exception.MyException;
import model.PrgState;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.exp.Exp;
import model.exp.LogicExp;
import model.type.Type;
import model.value.IValue;

import java.io.IOException;

public class SwitchStmt implements IStmt {

    private final Exp switchExp, firstCaseExp, secondCaseExp;
    private final IStmt firstCaseStmt, secondCaseStmt, defaultStmt;

    public SwitchStmt(Exp switchExp, Exp firstCaseExp, Exp secondCaseExp, IStmt firstCaseStmt, IStmt secondCaseStmt, IStmt defaultStmt) {
        this.switchExp = switchExp;
        this.firstCaseExp = firstCaseExp;
        this.secondCaseExp = secondCaseExp;
        this.firstCaseStmt = firstCaseStmt;
        this.secondCaseStmt = secondCaseStmt;
        this.defaultStmt = defaultStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> executionStack = state.getExeStack();
        MyIDictionary<String, IValue> symbolTable = state.getSymTable();
        IHeap<Integer, IValue> heapTable = state.getHeap();

        IValue exp = switchExp.eval(symbolTable, heapTable);
        IValue exp1 = firstCaseExp.eval(symbolTable, heapTable);
        IValue exp2 = secondCaseExp.eval(symbolTable, heapTable);
        IStmt newStatement;
        if(exp.equals(exp1))
            newStatement = firstCaseStmt;
        else if(exp.equals(exp2))
            newStatement = secondCaseStmt;
        else
            newStatement = defaultStmt;
        executionStack.push(newStatement);
        state.setExeStack(executionStack);
        return null;
        /*
        IStmt newSwitch = new IfStmt(
                new LogicExp(switchExp,firstCaseExp,1), firstCaseStmt, new IfStmt(
                        new LogicExp(switchExp,secondCaseExp,1),secondCaseStmt,defaultStmt));
        state.getStk().push(newSwitch);
        return null;

         */
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = switchExp.typeCheck(typeEnv);
        Type type1 = firstCaseExp.typeCheck(typeEnv);
        Type type2 = secondCaseExp.typeCheck(typeEnv);

        if (type.equals(type1) && type.equals(type2)) {
            firstCaseStmt.typeCheck(typeEnv.cloneDictionary());
            secondCaseStmt.typeCheck(typeEnv.cloneDictionary());
            defaultStmt.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        }
        else throw new RuntimeException("SwitchStmt: The expression types do not match");
    }

    @Override
    public String toString() {
        return "SwitchStmt (" + switchExp +
                ")  case " + firstCaseExp +
                " : " + firstCaseStmt +
                ")  case "  + secondCaseExp +
                " : " + secondCaseStmt +
                ") default" + defaultStmt;
    }
}
