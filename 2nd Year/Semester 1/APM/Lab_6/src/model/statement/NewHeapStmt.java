package model.statement;

import model.ADT.IMyDictionary;
import model.ADT.IMyHeap;
import model.ADT.IMyStack;
import model.PrgState;
import model.exceptions.ADTException;
import model.exceptions.ExprException;
import model.exceptions.IException;
import model.exceptions.StmtException;
import model.expression.Exp;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;


public class NewHeapStmt implements IStmt {
    String var_name;
    Exp exp;

    public NewHeapStmt(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }



    @Override
    public PrgState execute(PrgState state) throws StmtException, ExprException {
        IMyStack<IStmt> stack = state.getStack();
        IMyDictionary <String, Value> symTbl = state.getSymTable();
        IMyHeap<Value> heap = state.getHeap();
        if(symTbl.isDefined(var_name)){
            if(symTbl.lookup(var_name).getType() instanceof RefType){
                Value val = exp.eval(symTbl, heap);
                Value tblVal = symTbl.lookup(var_name);
                if(val.getType().equals(((RefType)(tblVal.getType())).getInner())){
                    int addr = heap.allocate(val);
                    symTbl.update(var_name, new RefValue(val.getType(), addr));
                }
                else{
                    throw new StmtException("Value's type is not correct!");
                }
            }
            else{
                throw new StmtException("Value's type is not reference!");
            }
        }
        else{
            throw new StmtException("Value is not declared!");
        }
        state.setSymTable(symTbl);
        state.setHeap(heap);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewHeapStmt(var_name, exp.deepCopy());
    }

    @Override
    public String toString(){
        return "new(" + var_name + ", " + exp + ")";
    }

    @Override
    public IMyDictionary<String, Type> typecheck(IMyDictionary<String, Type> typeEnvironment) throws StmtException, ExprException {
        if (!typeEnvironment.isDefined(var_name)) {
            throw new StmtException("The variable " + var_name + " not defined");
        }
        else {
            Type variableType = typeEnvironment.lookup(var_name);
            Type expType = exp.typecheck(typeEnvironment);
            if (variableType.equals(new RefType(expType))) {
                return typeEnvironment;
            }
            else {
                throw new StmtException("The right side and left side in " + this.toString() + " have different types");
            }
        }
    }


}
