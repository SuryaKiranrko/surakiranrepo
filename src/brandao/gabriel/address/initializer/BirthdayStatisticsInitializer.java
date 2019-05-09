package brandao.gabriel.address.initializer;

import brandao.gabriel.address.MainApp;
import brandao.gabriel.address.controller.BirthdayStatisticsController;
import brandao.gabriel.address.controller.Controller;
import brandao.gabriel.address.model.Person;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BirthdayStatisticsInitializer implements IInitializer {
    private volatile static BirthdayStatisticsInitializer instance;
    private AnchorPane loadedFXML;
    private BirthdayStatisticsController controller;

    public BirthdayStatisticsInitializer() {
        
    }  
    
    public static BirthdayStatisticsInitializer getInstance() {
        if(instance == null) {
            synchronized(BirthdayStatisticsInitializer.class) {
                if(instance == null) instance = new BirthdayStatisticsInitializer();
            }
        }
        return instance;
    }
    
    @Override
    public Pane getLoadedFXML() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/BirthdayStatistics.fxml"));
            loadedFXML = (AnchorPane) loader.load();
            controller = (BirthdayStatisticsController) loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return loadedFXML;
    }
    
    public void showBirthdayStatistics() {
        List<Person> personData = MainApp.getInstance().getPersonData();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(MainApp.getInstance().getPrimaryStage());
        Scene scene = new Scene(getLoadedFXML());
        dialogStage.setScene(scene);
        controller.setPersonData(personData);

        dialogStage.show();
    }
}
