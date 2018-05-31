package CharacterManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import CharacterManager.CharacterDataSets.Quality;

public class SoulPopupController {
	public boolean soulSubmitted = false;
	private boolean soulCompleted = false;
	
//Class Methods
    @FXML
    private void Complete(ActionEvent event) {
    	if (event.getSource() == cancelButton) {
    		soulSubmitted = false;
    		nameText.getScene().getWindow().hide();
    	} else if (event.getSource() == completeButton) {
    		soulCompleted = true;
    		if (nameText.getText() == null) {
    			nameText.setText("???");
    			soulCompleted = false;
    		}
    		try {
    			Integer.parseInt(cpWorthText.getText());
    		} catch (NumberFormatException e) {
    			cpWorthText.setText("???");
    			soulCompleted = false;
    		}
    		finally {
    			if (soulCompleted == true) {
    				soulSubmitted = true;
        			nameText.getScene().getWindow().hide();
    			}
    		}
    	}
    }
    
    private Quality getQualityEnum() {
    	if (qualityToggle.getSelectedToggle() == weakButton) {
    		return Quality.WEAK;
    	} else if (qualityToggle.getSelectedToggle() == strongButton) {
    		return Quality.STRONG;
    	} else if (qualityToggle.getSelectedToggle() == powerfulButton) {
    		return Quality.POWERFUL;
    	} else {
    		return Quality.LEGENDARY;
    	}
    }
    
    public SoulPaneController getSoulPane() {
    	if (soulSubmitted == false) {
    		return null;
    	}
    	return new SoulPaneController(nameText.getText(), getQualityEnum(), Integer.parseInt(cpWorthText.getText()), forgableButton.isSelected());
    }
    
//Dynamic Methods
    @FXML
    private void ForgableSwitch(ActionEvent event) {
    	if (forgableButton.isSelected()) {
    		forgableButton.setText("Yes");
    	} else {
    		forgableButton.setText("No");
    	}
    }
	
//fxml resources
	@FXML // fx:id="strongButton"
    private RadioButton strongButton; // Value injected by FXMLLoader

    @FXML // fx:id="forgableButton"
    private ToggleButton forgableButton; // Value injected by FXMLLoader

    @FXML // fx:id="Quality"
    private ToggleGroup qualityToggle; // Value injected by FXMLLoader

    @FXML // fx:id="powerfulButton"
    private RadioButton powerfulButton; // Value injected by FXMLLoader

    @FXML // fx:id="nameText"
    private TextField nameText; // Value injected by FXMLLoader

    @FXML // fx:id="legendaryButton"
    private RadioButton legendaryButton; // Value injected by FXMLLoader

    @FXML // fx:id="weakButton"
    private RadioButton weakButton; // Value injected by FXMLLoader

    @FXML // fx:id="cpWorthText"
    private TextField cpWorthText; // Value injected by FXMLLoader
    
    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="completeButton"
    private Button completeButton; // Value injected by FXMLLoader
    
    @FXML
    void initialize() {
        assert strongButton != null : "fx:id=\"strongButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert forgableButton != null : "fx:id=\"forgableButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert qualityToggle != null : "fx:id=\"qualityToggle\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert powerfulButton != null : "fx:id=\"powerfulButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert legendaryButton != null : "fx:id=\"legendaryButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert weakButton != null : "fx:id=\"weakButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert completeButton != null : "fx:id=\"completeButton\" was not injected: check your FXML file 'SoulPopup.fxml'.";
        assert cpWorthText != null : "fx:id=\"cpWorthText\" was not injected: check your FXML file 'SoulPopup.fxml'.";

    }
}

