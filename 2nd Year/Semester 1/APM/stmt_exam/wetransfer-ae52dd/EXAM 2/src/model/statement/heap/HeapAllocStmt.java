package model.statement.heap;

import com.sun.jdi.Value;
import exception.MyException;
import exception.VariableNotDefined;
import model.PrgState;
import model.adt.Heap;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.exp.Exp;
import model.statement.IStmt;
import model.type.RefType;
import model.type.Type;
import model.value.IValue;
import model.value.RefValue;

import java.io.IOException;

public class HeapAllocStmt implements IStmt {

    private final String varName;
    private final Exp expression;

    public HeapAllocStmt(String varName, Exp value) {
        this.varName = varName;
        this.expression = value;
    }

    public String getVarName() {
        return varName;
    }

    public Exp getExpression() {
        return expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();
        if (symTable.isDefined(varName)) {
            Type varType = symTable.lookup(varName).getType();

            if (varType instanceof RefType) {
                RefType refType = (RefType) varType;
                IValue content = this.expression.eval(symTable, heap);
                if (content.getType().equals(refType.getInner())) {
                    int newAddress = heap.getValidAddress();
                    heap.put(newAddress, content);
                    symTable.update(varName, new RefValue(newAddress, content.getType()));
                } else throw new MyException("The type aren't equal!");
            } else throw new MyException("The variable hasn't the type ReferenceType!");
        } else throw new VariableNotDefined("Variable not defined!");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVariableName = typeEnv.lookup(varName);
        Type typeExp = expression.typeCheck(typeEnv);
        if(typeVariableName.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("Right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "HeapAllocStmt{" +
                "varName='" + varName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
