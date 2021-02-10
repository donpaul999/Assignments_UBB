package test.model.statement; 

import controller.Controller;
import model.ADT.*;
import model.PrgState;
import model.expression.ValueExp;
import model.expression.VarExp;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import repository.IRepo;
import repository.Repository;

/** 
* IfStmt Tester. 
* 
* @author <Authors name> 
* @since <pre>Oct 30, 2020</pre> 
* @version 1.0 
*/ 
public class IfStmtTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: toString()
    *
    */
    @Test
    public void testToString() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: execute(PrgState state)
    *
    */
    @Test
    public void testExecute() throws Exception {
        IMyStack<IStmt> stack = new MyStack<>();
        IRepo repository = new Repository("log-test.txt");
        Controller controller = new Controller(repository);

        IStmt example_1 = new CompStmt(
                new VarDeclStmt("s" , new BoolType()),
                new CompStmt(new VarDeclStmt("x", new IntType()),
                        new CompStmt(
                                new AssignStmt("s", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("s"),
                                                new AssignStmt("x", new ValueExp(new IntValue(20))),
                                                new AssignStmt("x", new ValueExp(new IntValue(2)))
                                        ),
                                        new PrintStmt(new VarExp("x"))
                                )
                        )
                )
        );

        stack.push(example_1);
        PrgState state = new PrgState(stack, new MyDictionary<String, Value>(), new MyList<Value>());
        System.out.println(state);
        repository.addState(state);
        controller.allStep();

        if(state.getOutConsole().get(0).equals(new IntValue(20)))
            assert (true);
        else
            assert (false);
    }


} 
