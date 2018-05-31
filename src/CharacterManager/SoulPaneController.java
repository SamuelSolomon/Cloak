package CharacterManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import CharacterManager.CharacterDataSets.Quality;

public class SoulPaneController extends GridPane{
	public boolean isForged = false;
	public boolean isBroken = false;
	public boolean isRemoved = false;
	public int cpWorth;
	public boolean isForgable;
	
	public SoulPaneController(String name, Quality qualityEnum, int cpWorth, boolean isForgable){
		this.loadView();
		nameLabel.setText(name);
		setQualityLabel(qualityEnum);
		worthLabel.setText(""+cpWorth+" CP");
		if (isForgable == false) {
			forgeButton.setDisable(true);
			this.getChildren().remove(forgeInfoTextArea);
		}
		
		this.cpWorth = cpWorth;
		this.isForgable = isForgable;
	}
	
	public SoulPaneController(ObjectInputStream s) throws ClassNotFoundException, IOException{
		this.loadView();
		this.readObject(s);
	}
//Program Methods
	public final void loadView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/SoulPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
		try {
            loader.load();
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
       }
	}
	
	public void writeObject(ObjectOutputStream s) throws IOException {
        s.writeUTF(nameLabel.getText());
        s.writeUTF(qualityLabel.getText());
        s.writeInt(cpWorth);
        s.writeBoolean(isForgable);
        if (isForgable) {
        	s.writeUTF(forgeInfoTextArea.getText());
        }
        
    }
	
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        nameLabel.setText(s.readUTF());
        qualityLabel.setText(s.readUTF());
        cpWorth = s.readInt();
        worthLabel.setText(""+cpWorth+" CP");
        isForgable = s.readBoolean();
        if (isForgable) {
        	forgeInfoTextArea.setText(s.readUTF());
        } else {
        	this.getChildren().remove(forgeInfoTextArea);
        }
    }
//Class Methods
	private void setQualityLabel(Quality qualityEnum) {
		  if (qualityEnum == Quality.WEAK) {
			qualityLabel.setText("Weak");
		} else if (qualityEnum == Quality.STRONG) {
			qualityLabel.setText("Strong");
		} else if (qualityEnum == Quality.POWERFUL) {
			qualityLabel.setText("Powerful");
		} else {
			qualityLabel.setText("Legendary");
		}
	}
	
	@FXML
	void Forge(ActionEvent event) {
	    Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setContentText("Weapon Forging Functionality has not yet been implemented");
		a.showAndWait();
	}

	@FXML
	void Break(ActionEvent event) {
		isBroken = true;
	}

	@FXML
	void Remove(ActionEvent event) {
		((VBox)this.getParent()).getChildren().remove(this);
	}
	
//fxml resources
    @FXML // fx:id="qualityLabel"
    private Label qualityLabel; // Value injected by FXMLLoader

    @FXML // fx:id="forgeInfoTextArea"
    private TextArea forgeInfoTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="worthLabel"
    private Label worthLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader
    
    @FXML // fx:id="forgeButton"
    private Button forgeButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="breakButton"
    public Button breakButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="removeButton"
    public Button removeButton; // Value injected by FXMLLoader
}
