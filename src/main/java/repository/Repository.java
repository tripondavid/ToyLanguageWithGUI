package repository;

import exception.MyException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {
    private List<ProgramState> programStateList;
    private String logFilePath;

    public Repository() {
        this.programStateList = new ArrayList<>();
    }

    public Repository(String logFilePath) throws MyException {
        try {
            PrintWriter testPath = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            testPath.close();
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }

        this.programStateList = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void addProgram(ProgramState programState) {
        programStateList.add(programState);
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws MyException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        logFile.println(programState.toString());
        logFile.flush();
        if (programState.getExeStack().isEmpty())
            logFile.close();
    }

    @Override
    public List<ProgramState> getProgramList() {
        return programStateList;
    }

    @Override
    public void setProgramList(List<ProgramState> programStateList) {
        this.programStateList = programStateList;
    }
}
