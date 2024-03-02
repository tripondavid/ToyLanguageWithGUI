package org.example.mapredojavafx;

import controller.Controller;
import exception.MyException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.ProgramState;
import model.adts.barrier.MyBarrierTable;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.MyHeap;
import model.adts.latch.MyLatchTable;
import model.adts.list.MyList;
import model.adts.lock.MyLockTable;
import model.adts.procedure.MyProcedureTable;
import model.adts.semaphore.MySemaphoreTable;
import model.adts.stack.MyStack;
import model.adts.stack.MyStackInterface;
import model.statements.StatementInterface;
import model.values.StringValue;
import model.values.ValueInterface;
import repository.Repository;
import repository.RepositoryInterface;

import java.util.Map;

public class MainProgramController {
    private Controller controller;
    @FXML
    private TextField numberOfProgramStates;
    @FXML
    private ListView<ProgramState> statesListView;
    @FXML
    private TableView<Map.Entry<Integer, ValueInterface>> heapTableView;
    @FXML
    private ListView<ValueInterface> outListView;
    @FXML
    private ListView<StringValue> fileTableListView;
    @FXML
    private TableView<Map.Entry<String, ValueInterface>> symbolTableView;
    @FXML
    private ListView<StatementInterface> exeStackListView;
    @FXML
    private Button oneStepButton;
    @FXML
    private TableColumn<Map.Entry<String, ValueInterface>, String> nameSymbolTableColumn;
    @FXML
    private TableColumn<Map.Entry<String, ValueInterface>, String> valueSymbolTableColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, ValueInterface>, String> addressHeapTableColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, ValueInterface>, String> valueHeapTableColumn;
    @FXML
    private TableView<Map.Entry<Integer, Integer>> latchTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> latchLocationColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> latchValueColumn;

    @FXML
    public void initialize() {
        oneStepButton.setOnAction(actionEvent -> runOneStepButtonHandler());
        numberOfProgramStates.setDisable(true);

        statesListView.setCellFactory(new Callback<ListView<ProgramState>, ListCell<ProgramState>>() {
            @Override
            public ListCell<ProgramState> call(ListView<ProgramState> programStateListView) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(ProgramState item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null)
                            setText(Integer.toString(item.getId()));
                        else
                            setText("");
                    }
                };
            }
        });

        nameSymbolTableColumn.setCellValueFactory(cellDataMapEntry -> new SimpleStringProperty(cellDataMapEntry.getValue().getKey()));
        valueSymbolTableColumn.setCellValueFactory(cellDataMapEntry -> new SimpleStringProperty(cellDataMapEntry.getValue().getValue().toString()));

        addressHeapTableColumn.setCellValueFactory(cellDataMapEntry -> new SimpleStringProperty(cellDataMapEntry.getValue().getKey().toString()));
        valueHeapTableColumn.setCellValueFactory(cellDataMapEntry -> new SimpleStringProperty(cellDataMapEntry.getValue().getValue().toString()));

        latchLocationColumn.setCellValueFactory(cellDataMapEntry -> new SimpleStringProperty(cellDataMapEntry.getValue().getKey().toString()));
        latchValueColumn.setCellValueFactory(cellDataMapEntry -> new SimpleStringProperty(cellDataMapEntry.getValue().getValue().toString()));

        statesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        statesListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, programState, newState) -> changeThreadState(newState)));
    }

    public void setStatement(StatementInterface statement, String filePath) {
        ProgramState programState = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), statement,
                new MyLockTable(), new MyBarrierTable(), new MyLatchTable(), new MySemaphoreTable(), new MyProcedureTable());

        try {
            RepositoryInterface repository = new Repository(filePath);
            repository.addProgram(programState);
            controller = new Controller(repository);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

        updateFields();
    }

    private void changeThreadState(ProgramState newState) {
        populateExeStack(newState);
        populateSymbolTable(newState);
    }

    private void updateFields() {
        setNumberOfProgramStates();

        changeThreadState(statesListView.getSelectionModel().getSelectedItem());

        populateHeapTable();

        populateFileTable();

        populateLatchTable();

        populateOut();

        populateStateListView();
    }

    private void setNumberOfProgramStates() {
        numberOfProgramStates.setText(String.valueOf(controller.getAllPrograms().size()));
    }

    private void populateHeapTable() {
        if (!controller.getAllPrograms().isEmpty()) {
            heapTableView.getItems().setAll(FXCollections.observableArrayList(controller.getHeapTableGUI().getHeap().entrySet()));
        }
    }

    private void populateFileTable() {
        if (!controller.getAllPrograms().isEmpty()) {
            fileTableListView.getItems().setAll(FXCollections.observableArrayList(controller.getFileTableGUI().getContent().keySet()));
        }
    }

    private void populateOut() {
        if (!controller.getAllPrograms().isEmpty()) {
            outListView.getItems().setAll(FXCollections.observableArrayList(controller.getOutGUI().getList()));
        }
    }

    private void populateSymbolTable(ProgramState programState) {
        if (programState != null)
            symbolTableView.getItems().setAll(FXCollections.observableArrayList(programState.getSymTable().getContent().entrySet()));
        else
            symbolTableView.getItems().setAll(FXCollections.observableArrayList());
    }

    private void populateStateListView() {
        ObservableList<ProgramState> ids = FXCollections.observableList(controller.getAllPrograms());
        statesListView.setItems(ids);
    }

    private void populateLatchTable() {
        if (!controller.getAllPrograms().isEmpty()) {
            latchTableView.getItems().setAll(FXCollections.observableArrayList(controller.getLatchTableGUI().getTable().entrySet()));
        }
    }

    private void populateExeStack(ProgramState programState) {
        if (programState != null) {
            ObservableList<StatementInterface> statements = FXCollections.observableArrayList();
            MyStackInterface<StatementInterface> exeStack = programState.getExeStack();

            while (!exeStack.isEmpty()) {
                try {
                    statements.add(exeStack.pop());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < statements.size(); i++) {
                exeStack.push(statements.get(statements.size() - 1 - i));
            }
            exeStackListView.getItems().setAll(statements);
        } else
            exeStackListView.getItems().setAll(FXCollections.observableArrayList());
    }

    @FXML
    private void runOneStepButtonHandler() {
        try {
            controller.oneStepForAllProgramsGUI();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            oneStepButton.setDisable(true);
        } finally {
            updateFields();
        }
    }
}
