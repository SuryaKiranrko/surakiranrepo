package brandao.gabriel.address.initializer;

import brandao.gabriel.address.MainApp;
import brandao.gabriel.address.controller.Controller;
import brandao.gabriel.address.controller.PersonEditDialogController;
import brandao.gabriel.address.model.Person;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonEditDialogInitializer implements IInitializer {
    private volatile static PersonEditDialogInitializer instance;
    private AnchorPane loadedFXML;
    private PersonEditDialogController controller;

    public PersonEditDialogInitializer() {
        
    }  
    
    public static PersonEditDialogInitializer getInstance() {
        if(instance == null) {
            synchronized(PersonEditDialogInitializer.class) {
                if(instance == null) instance = new PersonEditDialogInitializer();
            }
        }
        return instance;
    }
    
    @Override
    public Pane getLoadedFXML() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/PersonEditDialog.fxml"));
            loadedFXML = (AnchorPane) loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
   
        return loadedFXML;
    }
    
    public boolean showPersonEditDialog(Person person){
        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getInstance().getPrimaryStage());
            Scene scene = new Scene(getLoadedFXML());
            dialogStage.setScene(scene);

            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
    };
}
