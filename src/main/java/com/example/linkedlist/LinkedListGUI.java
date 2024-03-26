package com.example.linkedlist;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;

public class LinkedListGUI extends Application {
    private LinkedList<String> linkedList = new LinkedList<>();
    private TextArea outputTextArea;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1000, 1000);

        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setStyle("-fx-font-size: 30px;");
        root.setCenter(outputTextArea);

        TextField inputField = new TextField();
        inputField.setPromptText("Enter element");
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String element = inputField.getText();
            linkedList.add(element); // Передаем только данные для добавления
            updateOutput();
            inputField.clear();
        });
        Button addAtIndexButton = new Button("Add at Index");
        addAtIndexButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(inputField.getText().split("\\s+")[0]);
                String element = inputField.getText().split("\\s+")[1];
                if (index >= 0 && index <= linkedList.getSize()) {
                    linkedList.addAtIndex(index, element); // Добавление элемента по индексу
                    updateOutput();
                    inputField.clear();
                } else {
                    System.out.println("Invalid index. Please enter a valid index."); // Предупреждение о недопустимом индексе
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer index followed by a value separated by space."); // Предупреждение, если введенное значение не является целым числом
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Please enter an index followed by a value separated by space."); // Предупреждение, если введенное значение не является целым числом
            }
        });

        Button removeButton = new Button("Remove by Index");
        removeButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(inputField.getText());
                if (index >= 0 && index < linkedList.getSize()) {
                    linkedList.removeByIndex(index); // Удаляем элемент по индексу
                    updateOutput();
                    inputField.clear();
                } else {
                    System.out.println("Invalid index.Enter a valid index."); // Предупреждение о недопустимом индексе
                }
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid integer index."); // Предупреждение, если введенное значение не является целым числом
            }
        });
        Button removeByValueButton = new Button("Remove by Value");
        removeByValueButton.setOnAction(e -> {
            String valueToRemove = inputField.getText();
            if (!valueToRemove.isEmpty()) {
                linkedList.removeByValue(valueToRemove); // Удаление элемента по его значению
                updateOutput();
                inputField.clear();
            } else {
                System.out.println("Enter a valid value.");
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            linkedList.LinkedListDest(); // Очищаем список
            updateOutput(); // Обновляем вывод
        });

        Button sizeButton = new Button("Show Size");
        sizeButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("List Size");
            alert.setHeaderText(null);
            alert.setContentText("Current size of the list: " + linkedList.getSize());
            alert.showAndWait();
        });

        Button isEmptyButton = new Button("Check if Empty");
        isEmptyButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Check");
            alert.setHeaderText(null);
            if (linkedList.isEmpty()) {
                alert.setContentText("The list is empty.");
            } else {
                alert.setContentText("The list is not empty.");
            }
            alert.showAndWait();
        });

        Button containsButton = new Button("Check Value");
        containsButton.setOnAction(e -> {
            String valueToCheck = inputField.getText();
            if (!valueToCheck.isEmpty()) {
                if (linkedList.contains(valueToCheck)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Value Check");
                    alert.setHeaderText(null);
                    alert.setContentText("The list contains the value: " + valueToCheck);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Value Check");
                    alert.setHeaderText(null);
                    alert.setContentText("The list does not contain the value: " + valueToCheck);
                    alert.showAndWait();
                }
            } else {
                System.out.println("Please enter a valid value to check.");
            }
        });

        Button getValueButton = new Button("Get Value");
        getValueButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(inputField.getText());
                if (index >= 0 && index < linkedList.getSize()) {
                    String value = linkedList.get(index);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Get Value");
                    alert.setHeaderText(null);
                    alert.setContentText("Value at index " + index + ": " + value);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Get Value");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid index. Please enter a valid index.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Get Value");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid integer index.");
                alert.showAndWait();
            }
        });
        Button changeValueButton = new Button("Change Value");
        changeValueButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(inputField.getText());
                if (index >= 0 && index < linkedList.getSize()) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Change Value");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Enter new value:");
                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(newValue -> {
                        linkedList.set(index, newValue);
                        updateOutput();
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Change Value");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid index. Please enter a valid index.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Change Value");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid integer index.");
                alert.showAndWait();
            }
        });

        Button getPositionButton = new Button("Get Position");
        getPositionButton.setOnAction(e -> {
            String valueToFind = inputField.getText();
            if (!valueToFind.isEmpty()) {
                int index = linkedList.indexOf(valueToFind);
                if (index != -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Get Position");
                    alert.setHeaderText(null);
                    alert.setContentText("The position of \"" + valueToFind + "\" in the list is: " + index);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Get Position");
                    alert.setHeaderText(null);
                    alert.setContentText("\"" + valueToFind + "\" is not found in the list.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Get Position");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a value to find.");
                alert.showAndWait();
            }
        });

        HBox inputBox = new HBox(10, inputField, addButton,addAtIndexButton, removeButton, removeByValueButton, clearButton, sizeButton, isEmptyButton, containsButton, getValueButton, changeValueButton, getPositionButton);
        inputBox.setPadding(new Insets(10));
        root.setBottom(inputBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("lab1");
        primaryStage.show();
    }

    private void updateOutput() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String item : linkedList) {
            sb.append(index).append(": ").append(item).append("\n");
            index++;
        }
        outputTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

