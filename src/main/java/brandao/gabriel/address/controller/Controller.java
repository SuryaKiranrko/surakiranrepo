package brandao.gabriel.address.controller;

import brandao.gabriel.address.MainApp;

public abstract class Controller {
    protected MainApp mainApp;
    public Controller() {
        mainApp = MainApp.getInstance();
    }
}
