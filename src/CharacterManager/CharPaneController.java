package CharacterManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CharPaneController extends HBox{
	public CharData characteristic;
	public Double totalInvestment;
	public int currentValue;
	
	public CharPaneController(CharData characteristic){
		this.loadView();
		this.characteristic = characteristic;
		currentValue = characteristic.initialValue;
		setLabels();
	}
	
	public CharPaneController(CharData characteristic, int currentValue){
		this.loadView();
		this.characteristic = characteristic;
		this.currentValue = currentValue;
		setLabels();
	}

//Program Methods
	private final void loadView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/CharPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException exc) {
            exc.printStackTrace();
            // this is pretty much fatal, so:
            System.exit(1);
       }
	}
	
	public void writeObject(ObjectOutputStream s) throws IOException {
        s.writeInt(currentValue);
    }
	
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        currentValue = s.readInt();
        setLabels();
    }
	
//Class Methods
	private void setLabels() {
		costLabel.setText(""+characteristic.Cost);
		charNameLabel.setText(""+characteristic.Label);
		softCapLabel.setText(""+characteristic.SoftCap);
		currentValueLabel.setText(""+currentValue);
		
		totalInvestment = (currentValue - characteristic.initialValue)*characteristic.Cost;
		totalInvestmentLabel.setText(""+totalInvestment);
	}

//Dynamic Methods
    @FXML
    void plusOne(ActionEvent event) {
    	if (currentValue < characteristic.SoftCap) {
    		currentValue++;
        	totalInvestment += characteristic.Cost;
    	} else if (currentValue >= characteristic.SoftCap) {
    		currentValue++;
        	totalInvestment += characteristic.Cost*2;
    	}
    	currentValueLabel.setText(""+currentValue);
    	totalInvestmentLabel.setText(""+totalInvestment);
    }

    @FXML
    void minusOne(ActionEvent event) {
    	if (currentValue > 0 && currentValue <= characteristic.SoftCap) {
        	currentValue--;
        	totalInvestment -= characteristic.Cost;
    	} else if (currentValue > characteristic.SoftCap) {
        	currentValue--;
        	totalInvestment -= characteristic.Cost*2;
    	}
    	currentValueLabel.setText(""+currentValue);
    	totalInvestmentLabel.setText(""+totalInvestment);
    }

//fxml resources
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label costLabel;

    @FXML
    private Label totalInvestmentLabel;

    @FXML
    private Label charNameLabel;

    @FXML
    private Label currentValueLabel;

    @FXML
    private Label softCapLabel;
    
    @FXML // fx:id="charPlusButton"
    public Button charPlusButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="charMinusButton"
    public Button charMinusButton; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        assert costLabel != null : "fx:id=\"costLabel\" was not injected: check your FXML file 'characteristics_pane.fxml'.";
        assert totalInvestmentLabel != null : "fx:id=\"totalInvestmentLabel\" was not injected: check your FXML file 'characteristics_pane.fxml'.";
        assert charNameLabel != null : "fx:id=\"charNameLabel\" was not injected: check your FXML file 'characteristics_pane.fxml'.";
        assert currentValueLabel != null : "fx:id=\"currentValueLabel\" was not injected: check your FXML file 'characteristics_pane.fxml'.";
        assert softCapLabel != null : "fx:id=\"softCapLabel\" was not injected: check your FXML file 'characteristics_pane.fxml'.";

    }
}

