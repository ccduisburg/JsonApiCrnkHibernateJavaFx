package com.codenotfound.crnk.client.GUI;

import com.codenotfound.crnk.client.BlogClient;
import com.codenotfound.crnk.domain.model.Address;
import com.codenotfound.crnk.domain.model.Book;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class LibraryGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Scene scene, scene2;
    private VBox root, root2;
    private Button  btnListAdress;
    private TextField fieldUniId;
    private TextArea areaContent;
    private BlogClient client;
    private TableView table;
    private List<Address> alladress;
    private List<Book> allbook;
    private GridPane gridPane;


    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new BlogClient();
        client.init();
        addComponents();
        registerEvents();
        primaryStage.setTitle("List Data");
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    private void addComponents() {
      //  root = new VBox();
        root2 = new VBox();

        fieldUniId = new TextField();
        areaContent = new TextArea();
        table = new TableView<>();
        gridPane = new GridPane();
        alladress = new ArrayList<>();
        btnListAdress = new Button("List Adress");
        root2.getChildren().addAll(gridPane, btnListAdress,areaContent,table);
        //scene = new Scene(root, 500, 500);
        scene2 = new Scene(root2, 500, 500);
        allbook=new ArrayList<>();
    }

    private void registerEvents() {
        ShowAllAdress();
        showAllBook();
        showDataAufGrid();

    }

    private void ShowAllAdress() {
        table.setEditable(true);
//            TableColumn firstNameCol = new TableColumn("id");
//            firstNameCol.setCellValueFactory( new PropertyValueFactory<Adress,Long>("id"));
        TableColumn strasse = new TableColumn("strasse");
        strasse.setCellValueFactory(new PropertyValueFactory<Address, String>("location"));
        TableColumn hnummer = new TableColumn("Hausnummer");
        hnummer.setCellValueFactory(new PropertyValueFactory<Address, Integer>("hnummer"));
        TableColumn PLZ = new TableColumn("PLZ");
        PLZ.setCellValueFactory(new PropertyValueFactory<Address, String>("PLZ"));
        TableColumn city = new TableColumn("city");
        city.setCellValueFactory(new PropertyValueFactory<Address, String>("stadt"));

        alladress.addAll(client.findAllAddresses());
        table.getItems().clear();
        table.getItems().addAll(alladress);
        System.out.println(alladress);
        table.getColumns().addAll(strasse, hnummer, PLZ, city);

    }

    private void showAllBook(){
        table.setEditable(true);
        TableColumn name = new TableColumn("title");
        name.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory<Address, Long>("location"));

        allbook.addAll(client.findAllBooks());

        table.getItems().clear();
        System.out.println(allbook);
        System.out.println(alladress);
        table.getColumns().addAll(name,description);

    }

    private void showDataAufGrid() {
        gridPane.getColumnConstraints().addAll(table.getItems());

    }
}

