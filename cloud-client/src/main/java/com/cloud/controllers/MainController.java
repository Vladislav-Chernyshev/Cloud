package com.cloud.controllers;


import com.cloud.network.Net;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Net net;

    public ListView<String> clientView;
    public ListView<String> serverView;
    public TextField input;

    private void readListFiles(ListView <String> view) {
        try {
            view.getItems().clear();
            Long filesCount = net.readLong();
            for (int i = 0; i < filesCount; i++) {
                String fileName = net.readUtf();
                view.getItems().addAll(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try {
            while (true) {
                String command = net.readUtf();
                if (command.equals("#list#")) {
                    readListFiles(clientView);
                }else if (command.equals("#server-list#")){
                    readListFiles(serverView);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            net = new Net("localhost", 8189);
            Thread readThread = new Thread(this::read);
            readThread.setDaemon(true);
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}