package repository;

import exception.MyException;
import model.ProgramState;

import java.util.List;

public interface RepositoryInterface {
    void addProgram(ProgramState programState);
    void logProgramStateExecution(ProgramState programState) throws MyException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStateList);
}
