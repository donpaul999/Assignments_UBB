package model.statement.heap;

import com.sun.jdi.Value;
import exception.MyException;
import model.PrgState;
import model.adt.IHeap;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.exp.Exp;
import model.statement.IStmt;
import model.type.RefType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IValue;
import model.value.RefValue;

import java.io.IOException;

public class HeapWriteStmt implements IStmt {

    private String varName;
    private Exp expression;

    public HeapWriteStmt(String varName, Exp value) {
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
            IValue value = symTable.lookup(varName);
            //verify if the type of the variable is a ReferenceType
            if (value.getType() instanceof RefType) {
                int address = ((RefValue) value).getAddr();
                IValue contentOrg = heap.getContentFromAddress(address);
                if (heap.getContentFromAddress(address) != null) {
                    IValue content = this.expression.eval(symTable, heap);
                    //verify if the type of the expression is the same with the one already allocated in the heap for that variable
                    if (content.getType().equals(contentOrg.getType())) {
                        heap.update(address, content);
                    } else
                        throw new MyException("The types of the expressions aren't equal!");
                } else throw new MyException("The address is not a key in the heap!");
            } else
                throw new MyException("The type is not a ReferenceType!");
        } else throw new MyException("The variable is not defined!");
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
        return "HeapWriteStmt{" +
                "varName='" + varName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
