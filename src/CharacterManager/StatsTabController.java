package CharacterManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StatsTabController extends ScrollPane{
	private int sanity;
	private boolean isHollow;
	public int soulCount;
	
	public StatsTabController() {
		this.loadView();
		soulCount = 0;
		sanity = 76;
		this.boldSanityTexts();
		sanityTotalLabel.setText("Sanity : " + sanity);
		isHollow = false;
		stateLabel.setText("Whole");
		this.addCharacteristicPanes();
		textArea75.setText("");
		textArea50.setText("");
		textArea25.setText("");
	}
	
//Program Methods
	public final void loadView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/StatsTab.fxml"));
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
        for (Node p : CharacteristicVBox.getChildren()) {
        	((CharPaneController)p).writeObject(s);
        }
        s.writeInt(soulCount);
        for (Node p : SoulVBox.getChildren()) {
        	((SoulPaneController)p).writeObject(s);
        }
        s.writeBoolean(isHollow);
        s.writeInt(sanity);
        s.writeUTF(textArea75.getText());
        s.writeUTF(textArea50.getText());
        s.writeUTF(textArea25.getText());
    }
	
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		CharacteristicVBox.getChildren().clear();
		CharData[] data = CharacterDataSets.getCharacteristicArray();
		for (int i = 0; i < data.length; i++) {
        	CharacteristicVBox.getChildren().add(new CharPaneController(data[i], s.readInt()));
        }
		SoulVBox.getChildren().clear();
        soulCount = s.readInt();
        for (int i = 0; i < soulCount; i++) {
            SoulVBox.getChildren().add(new SoulPaneController(s));
        }
        isHollow = s.readBoolean();
        sanity = s.readInt();
        textArea75.setText(s.readUTF());
        textArea50.setText(s.readUTF());
        textArea25.setText(s.readUTF());
        this.boldSanityTexts();
    }
	
//Class Methods
	private void addCharacteristicPanes() {
		CharacteristicVBox.getChildren().clear();
		CharData[] data = CharacterDataSets.getCharacteristicArray();
		for (int i = 0; i < data.length; i++) {
        	CharacteristicVBox.getChildren().add(new CharPaneController(data[i]));
        }
	}
	
	@FXML
    void addSoul(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/SoulPopup.fxml"));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            // TODO: handle error
            return;
        }

        Stage inputStage = new Stage();
        //inputStage.initOwner(primaryStage);
        inputStage.setScene(newScene);
        inputStage.showAndWait();
        
        if(loader.<SoulPopupController>getController().soulSubmitted) {
        	SoulPaneController soul = loader.<SoulPopupController>getController().getSoulPane();
            SoulVBox.getChildren().add(soul);
            soulCount++;
            //setSoulButtons(soul);
        }
    }

	public void clear() {
		this.addCharacteristicPanes();
		SoulVBox.getChildren().clear();
        soulCount = 0;
        sanity = 76;
		this.boldSanityTexts();
		sanityTotalLabel.setText("Sanity : " + sanity);
		isHollow = false;
		stateLabel.setText("Whole");
		textArea75.setText("");
		textArea50.setText("");
		textArea25.setText("");
	}	
	
	public void setSoulButtons(SoulPaneController s) {
		s.removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				soulCount--;
			}
		});
	}

//Dynamic Methods
	@FXML
    void stateSwitch(ActionEvent event) {
    	if (isHollow) {
        	stateLabel.setText("Whole");
        	stateButton.setText("Switch to Hollow");
        	isHollow = false;
    	} else {
    		stateLabel.setText("Hollow");
        	stateButton.setText("Switch to Whole");
        	isHollow = true;
    	}
    }

    @FXML
    void plusSanity(ActionEvent event) {
    	if (sanity == 0) {
    		sanityMinusButton.setDisable(false);
    	}
    	sanity++;
    	if (sanity == 100) {
    		sanityPlusButton.setDisable(true);
    	}
    	sanityTotalLabel.setText("Sanity : " + sanity);
    	boldSanityTexts();
    }

    @FXML
    void minusSanity(ActionEvent event) {
    	if (sanity == 100) {
    		sanityPlusButton.setDisable(false);
    	}
    	sanity--;
    	if (sanity == 0) {
    		sanityMinusButton.setDisable(true);
    	}
    	sanityTotalLabel.setText("Sanity : " + sanity);
    	boldSanityTexts();
    }
    
    private void boldSanityTexts() {
    	if (sanity < 75) {
    		textArea75.setFont(Font.font("System", FontWeight.BOLD, 12));
    	} else {
    		textArea75.setFont(Font.font("System", FontWeight.NORMAL, 12));
    		textArea50.setFont(Font.font("System", FontWeight.NORMAL, 12));
    		textArea25.setFont(Font.font("System", FontWeight.NORMAL, 12));
    		return;
    	}
    	if (sanity < 50) {
    		textArea50.setFont(Font.font("System", FontWeight.BOLD, 12));
    	} else {
    		textArea50.setFont(Font.font("System", FontWeight.NORMAL, 12));
    		textArea25.setFont(Font.font("System", FontWeight.NORMAL, 12));
    		return;
    	}
    	if (sanity < 25) {
    		textArea25.setFont(Font.font("System", FontWeight.BOLD, 12));
    	} else {
    		textArea25.setFont(Font.font("System", FontWeight.NORMAL, 12));
    	}
    }
    
//fxml Resources
    @FXML // fx:id="CharacteristicVBox"
    public VBox CharacteristicVBox; // Value injected by FXMLLoader

    @FXML // fx:id="sanityPlusButton"
    private Button sanityPlusButton; // Value injected by FXMLLoader

    @FXML // fx:id="textArea50"
    private TextArea textArea50; // Value injected by FXMLLoader

    @FXML // fx:id="sanityTotalLabel"
    private Label sanityTotalLabel; // Value injected by FXMLLoader

    @FXML // fx:id="sanityMinusButton"
    private Button sanityMinusButton; // Value injected by FXMLLoader

    @FXML // fx:id="textArea25"
    private TextArea textArea25; // Value injected by FXMLLoader

    @FXML // fx:id="stateButton"
    private Button stateButton; // Value injected by FXMLLoader

    @FXML // fx:id="stateLabel"
    private Label stateLabel; // Value injected by FXMLLoader

    @FXML // fx:id="SoulVBox"
    public VBox SoulVBox; // Value injected by FXMLLoader

    @FXML // fx:id="textArea75"
    private TextArea textArea75; // Value injected by FXMLLoader
}