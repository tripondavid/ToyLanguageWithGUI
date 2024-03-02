package view;

import controller.Controller;
import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.MyHeap;
import model.adts.list.MyList;
import model.adts.stack.MyStack;
import model.statements.StatementInterface;
import repository.Repository;
import repository.RepositoryInterface;
import view.commands.ExitCommand;
import view.commands.RunExample;

public class Interpreter {
    /* public static void main(String[] args) throws MyException {
        StatementInterface[] examples = new Examples().exampleList();
        StatementInterface example1, example2, example3, example4, example5, example6, example7, example8;
        ProgramState prg1, prg2, prg3, prg4, prg5, prg6, prg7, prg8;
        RepositoryInterface repo1, repo2, repo3, repo4, repo5, repo6, repo7, repo8;
        Controller controller1, controller2, controller3, controller4, controller5, controller6, controller7, controller8;

        example1 = examples[0];
        prg1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example1);
        repo1 = new Repository("log1.txt");
        repo1.addProgram(prg1);
        controller1 = new Controller(repo1);

        example2 = examples[1];
        prg2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example2);
        repo2 = new Repository("log2.txt");
        repo2.addProgram(prg2);
        controller2 = new Controller(repo2);

        example3 = examples[2];
        prg3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example3);
        repo3 = new Repository("log3.txt");
        repo3.addProgram(prg3);
        controller3 = new Controller(repo3);

        example4 = examples[3];
        prg4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example4);
        repo4 = new Repository("log4.txt");
        repo4.addProgram(prg4);
        controller4 = new Controller(repo4);

        example5 = examples[4];
        prg5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example5);
        repo5 = new Repository("log5.txt");
        repo5.addProgram(prg5);
        controller5 = new Controller(repo5);

        example6 = examples[5];
        prg6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example6);
        repo6 = new Repository("log6.txt");
        repo6.addProgram(prg6);
        controller6 = new Controller(repo6);

        example7 = examples[6];
        prg7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example7);
        repo7 = new Repository("log7.txt");
        repo7.addProgram(prg7);
        controller7 = new Controller(repo7);

        example8 = examples[7];
        prg8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example8);
        repo8 = new Repository("log8.txt");
        repo8.addProgram(prg8);
        controller8 = new Controller(repo8);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand(new RunExample("2", example2.toString(), controller2));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.addCommand(new RunExample("5", example5.toString(), controller5));
        menu.addCommand(new RunExample("6", example6.toString(), controller6));
        menu.addCommand(new RunExample("7", example7.toString(), controller7));
        menu.addCommand(new RunExample("8", example8.toString(), controller8));
        menu.show();
    } */
}
