package brandao.gabriel.address;


import java.io.File;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import brandao.gabriel.address.model.Person;
import brandao.gabriel.address.model.PersonListWrapper;
import brandao.gabriel.address.initializer.PersonOverViewInitializer;
import brandao.gabriel.address.initializer.RootLayoutInitializer;
import brandao.gabriel.address.util.FileHandler;

public class MainApp extends Application {
    private volatile static MainApp instance;
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp() {
        personData.add(new Person("Zé", "Wilson"));
        personData.add(new Person("William", "Sallum"));
        personData.add(new Person("Cristiano", "Maffort"));
        personData.add(new Person("Flávio", "Coutinho"));
        personData.add(new Person("Glivia", "Angelica"));
        personData.add(new Person("Edson", "Marchetti"));
        personData.add(new Person("Marcao", "Amaral"));
        personData.add(new Person("Daniel", "Hasan"));
        personData.add(new Person("Adelson", "Silva"));
    }
    
    public static MainApp getInstance() {
        if(instance == null) {
            synchronized(MainApp.class) {
                if(instance == null) instance = new MainApp();
            }
        }
        return instance;
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        
        this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_icon.png"));
        rootLayout = (BorderPane) RootLayoutInitializer.getInstance().getLoadedFXML();
        
        // Try to load last opened person file.
        File file = FileHandler.getFilePath();
        if (file != null) {
            FileHandler.loadPersonFromFile(file);
        }
        
        initRootLayout();
        showPersonOverview();
    }
    
    private void initRootLayout() {
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showPersonOverview() {
        AnchorPane personOverView = (AnchorPane)PersonOverViewInitializer.getInstance().getLoadedFXML();
        rootLayout.setCenter(personOverView);
    }
    
    public void showAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}