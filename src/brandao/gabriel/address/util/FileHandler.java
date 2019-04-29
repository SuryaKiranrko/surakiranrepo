package brandao.gabriel.address.util;

import brandao.gabriel.address.MainApp;
import brandao.gabriel.address.model.PersonListWrapper;
import java.io.File;
import java.util.prefs.Preferences;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileHandler {
    public static void setFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(FileHandler.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
//            MainApp.getInstance().getPrimaryStage().setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
//            MainApp.getInstance().getPrimaryStage().setTitle("AddressApp");
        }
    }
    
    public static File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(FileHandler.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    public static void loadPersonFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            MainApp.getInstance().getPersonData().clear();
            MainApp.getInstance().getPersonData().addAll(wrapper.getPersons());

            setFilePath(file);

        } catch (Exception e) {
            e.printStackTrace();
            MainApp.getInstance().showAlert("Error", "Could not load data", "Could not load data from file:\n" + file.getPath());
        }
    }
    
    public static void savePersonToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(MainApp.getInstance().getPersonData());

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            FileHandler.setFilePath(file);
        } catch (Exception e) { 
            e.printStackTrace();
            MainApp.getInstance().showAlert("Error", "Could not save data", "Could not save data to file:\n" + file.getPath());
        }
    }
}
