package model.statement;

import model.ADT.IMyDictionary;
import model.ADT.IMyHeap;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.expression.Exp;
import model.value.IntValue;
import model.value.Value;


public class NewStmt implements IStmt {
    private String var_name;
    private Exp exp;

    public NewStmt(String var_name, Exp exp){
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws ExprException, ADTException {
        IMyDictionary<String, Value> symTbl = state.getSymTable();
        IMyHeap<Value> heaptbl = state.getHeap();
        Integer heapAddress = heaptbl.allocate(exp.eval(symTbl, state.getHeap()));
        Value v = new IntValue(heapAddress);
        symTbl.add(var_name,  v);
        state.setSymTable(symTbl);
        state.setHeap(heaptbl);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(var_name, exp.deepCopy());
    }

    @Override
    public String toString(){
        return "new( " + var_name + ", " + exp + " )";
    }


    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}