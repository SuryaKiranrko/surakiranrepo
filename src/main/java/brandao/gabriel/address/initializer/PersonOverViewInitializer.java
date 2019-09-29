package brandao.gabriel.address.initializer;

import brandao.gabriel.address.MainApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PersonOverViewInitializer implements IInitializer {
    
    private volatile static PersonOverViewInitializer instance;
    private AnchorPane loadedFXML;

    public PersonOverViewInitializer() {
        
    }  
    
    public static PersonOverViewInitializer getInstance() {
        if(instance == null) {
            synchronized(PersonOverViewInitializer.class) {
                if(instance == null) instance = new PersonOverViewInitializer();
            }
        }
        return instance;
    }
    
    @Override
    public Pane getLoadedFXML() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("/fxml/PersonOverView.fxml"));
            loadedFXML = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

   
        return loadedFXML;
    }
    
}
