package org.example.mapredojavafx;

import exception.MyException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import model.adts.dictionary.MyDictionary;
import model.statements.StatementInterface;
import view.Examples;

public class ChooseProgramController {
    @FXML
    private ListView<StatementInterface> programsList;
    private MainProgramController mainProgramController;

    @FXML
    public void initialize() {
        programsList.setItems(this.getStatements());
        programsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ChooseProgramApplication.class.getResource("main-program.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Main Program");
            stage.setScene(scene);
            stage.show();
            mainProgramController = fxmlLoader.getController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        programsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StatementInterface>() {
            @Override
            public void changed(ObservableValue<? extends StatementInterface> observableValue, StatementInterface oldStatement, StatementInterface newStatement) {
                String filePath = "log" + (programsList.getSelectionModel().getSelectedIndex() - 1) + ".txt";
                mainProgramController.setStatement(newStatement, filePath);
            }
        });
    }

    private ObservableList<StatementInterface> getStatements() {
        ObservableList<StatementInterface> exampleList = FXCollections.observableArrayList();

        StatementInterface[] examples = new Examples().exampleList();

        for (StatementInterface example : examples) {
            try {
                example.typeCheck(new MyDictionary<>());
                exampleList.add(example);
            } catch (MyException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText(e.getMessage());
                error.showAndWait();
            }
        }
        return exampleList;
    }
}
