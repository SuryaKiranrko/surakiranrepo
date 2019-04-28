package brandao.gabriel.address.initializer;

import brandao.gabriel.address.MainApp;
import brandao.gabriel.address.controller.RootLayoutController;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class RootLayoutInitializer {

    private volatile static RootLayoutInitializer instance;
    private BorderPane loadedLayout;

    public RootLayoutInitializer() {
        
    }  
    
    public static RootLayoutInitializer getInstance() {
        if(instance == null) {
            synchronized(RootLayoutInitializer.class) {
                if(instance == null) instance = new RootLayoutInitializer();
            }
        }
        return instance;
    }
    
    public BorderPane getLoadedLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            loadedLayout = (BorderPane) loader.load();
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(MainApp.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }

   
        return loadedLayout;
    };

}