package model.statement;

import model.ADT.IMyDictionary;
import model.ADT.IMyHeap;
import model.PrgState;
import model.exceptions.ExprException;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStmt implements IStmt {
    private String variableName;
    private Exp exp;

    public WriteHeapStmt(String variableName, Exp exp) {
        this.variableName = variableName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyDictionary<String, Value> symTable = state.getSymTable();
        IMyHeap<Value> heap = state.getHeap();

        if (symTable.isDefined(variableName)) {
            if (symTable.lookup(variableName).getType() instanceof RefType) {
                RefValue refVal = (RefValue) symTable.lookup(variableName);
                if (heap.contains(refVal.getAddress())) {
                    Value val = exp.eval(symTable, heap);
                    if (symTable.lookup(variableName).getType().equals(new RefType(val.getType()))) {
                        int address = refVal.getAddress();
                        heap.update(address, val);
                    }
                    else {
                        throw new StmtException("The pointing variable has a different type than the evaluated expression.");
                    }
                }
                else {
                    throw new StmtException("The address to which " + variableName + " points is not in the heap");
                }
            }
            else {
                throw new StmtException(variableName + " is not a reference variable");
            }
        }
        else {
            throw new StmtException(variableName + " is not defined");
        }

        return null;
    }


    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(new String(variableName), exp.deepCopy());
    }

    @Override
    public String toString() {
        return "wH(" + variableName + "," + exp + ")";
    }
}