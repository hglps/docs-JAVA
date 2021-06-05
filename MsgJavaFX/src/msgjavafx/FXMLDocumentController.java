/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msgjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Hiago
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label textMain;
    private Button pressMe;
    
    @FXML
    private void clicouBotao(ActionEvent event) {
        textMain.setText("Vai dar tudo certo :) ");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
