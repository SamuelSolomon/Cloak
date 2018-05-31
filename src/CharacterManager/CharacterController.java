package CharacterManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
* An Instance of a Character, This is both the data model and the controller for a character. Handles all the interactions between Tabs.
*@param heldCP the number of CP the player has to invest in their character abilities.
*/
public class CharacterController extends VBox implements Initializable {
	public Double heldCP = 0.0;
	public Double totalCPUsed = 0.0;
	private InfoTabController infoTabController;
	private StatsTabController statsTabController;
	
	/**
	* Sets Parameters to Default values upon creation.
	*/
	public CharacterController() {
        this.loadView();
        heldCPLabel.setText("CP Held : " + heldCP);
        totalCPLabel.setText("Total CP Used : " + totalCPUsed);
        infoTabController = new InfoTabController();
        infoTab.setContent(infoTabController);
        statsTabController = new StatsTabController();
        	setCharButtonUpdates();
        statsTab.setContent(statsTabController);
	}

//Program Methods
	/**
	 * calls the fxml loader to populate the values of anything defined in the fxml file reference contained within.
	 */
	public final void loadView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/character_scene.fxml"));
        loader.setRoot(this);
        loader.setController(this);
		try {
            loader.load();
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
       }
	}
	
	/**
	 * Write all the variables of a character Instance using a objectOutputStream
	 * @param s an objectoutputstream meant to save all the variable data in the character file
	 * @throws IOException
	 */
	public void writeObject(ObjectOutputStream s) throws IOException {
        s.writeDouble(heldCP);
        s.writeDouble(totalCPUsed);
        s.writeUTF(characterFileNameLabel.getText());
        infoTabController.writeObject(s);
        statsTabController.writeObject(s);
    }
	
	/**
	 * Read all the variables of a character instance using a objectInputStream
	 * @param s an objectInputStreamMeant to retrieve all the variable data in a character Instance
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        heldCP = s.readDouble();
    	heldCPLabel.setText("Held CP : " + heldCP);
        totalCPUsed = s.readDouble();
        totalCPLabel.setText("Total CP Used : " + totalCPUsed);
    	
    	characterFileNameLabel.setText(s.readUTF());
    	
        infoTabController.readObject(s);
        statsTabController.readObject(s);
        	setCharButtonUpdates();
    }

//Setup Methods
	/**
	 * reset all character instance variables to their default values.
	 */
	public void clear() {
		heldCP = 0.0;
    	heldCPLabel.setText("Held CP : " + heldCP);
    	totalCPUsed = 0.0;
        totalCPLabel.setText("Total CP Used : " + totalCPUsed);
    	
    	setFileName("Unsaved Character File");
    	
    	infoTabController.clear();
    	statsTabController.clear();
    		setCharButtonUpdates();
	}
	
	/**
	 * Presentation Method: enables tabs that are left disabled because they do not yet have functionality
	 */
	public void enableTabs() {
		storyTab.setDisable(false);
		equipmentTab.setDisable(false);
		complicationsTab.setDisable(false);
		talentsTab.setDisable(false);
		powersTab.setDisable(false);
		skillsTab.setDisable(false);
	}
    
    private void setCharButtonUpdates() {
    	for (Node p : statsTabController.CharacteristicVBox.getChildren()) {
        	((CharPaneController)p).charPlusButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent e) {
    				Node n = (Node) e.getSource();
    		    	heldCP -= ((CharPaneController)n.getParent()).characteristic.Cost;
    		    	totalCPUsed += ((CharPaneController)n.getParent()).characteristic.Cost;
    		    	resetCPLabels();
    			}
            });
        	((CharPaneController)p).charMinusButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent e) {
    				Node n = (Node) e.getSource();
    		    	heldCP += ((CharPaneController)n.getParent()).characteristic.Cost;
    		    	totalCPUsed -= ((CharPaneController)n.getParent()).characteristic.Cost;
    		    	resetCPLabels();
    			}
            });
        }
    }
//Dynamic Methods
	/**
	 * takes a string to set the main filename label within the program
	 * @param fileName a string meant to display the filename label
	 */
	public void setFileName (String fileName) {
		characterFileNameLabel.setText(fileName);
	}
	
	/**
	 * increments the held and unused CP of a character by 1
	 * @param event
	 */
	@FXML
    void plusCP(ActionEvent event) {
    	heldCP++;
    	heldCPLabel.setText("Held CP : " + heldCP);
    }
	
	/**
	 * decrements the held and unused CP of a character by 1
	 * @param event
	 */
    @FXML
    void minusCP(ActionEvent event) {
    	heldCP--;
    	heldCPLabel.setText("Held CP : " + heldCP);
    }
    
    public void totalCPChange (int cpChange) {
    	totalCPUsed += cpChange;
    	totalCPLabel.setText("Total CP Used : " + totalCPUsed);
    }
    
    public void resetCPLabels() {
    	heldCPLabel.setText("Held CP : " + heldCP);
		totalCPLabel.setText("Total CP Used : " + totalCPUsed);
    }
//fxml Specific Resources
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="storyTab"
    private Tab storyTab; // Value injected by FXMLLoader

    @FXML // fx:id="infoTab"
    private Tab infoTab; // Value injected by FXMLLoader

    @FXML // fx:id="equipmentTab"
    private Tab equipmentTab; // Value injected by FXMLLoader

    @FXML // fx:id="complicationsTab"
    private Tab complicationsTab; // Value injected by FXMLLoader

    @FXML // fx:id="playTab"
    private Tab playTab; // Value injected by FXMLLoader

    @FXML // fx:id="talentsTab"
    private Tab talentsTab; // Value injected by FXMLLoader

    @FXML // fx:id="characterFileLabel11" 
    private Label heldCPLabel; // Value injected by FXMLLoader

    @FXML // fx:id="powersTab"
    private Tab powersTab; // Value injected by FXMLLoader

    @FXML // fx:id="characterFileLabel1"
    private Label totalCPLabel; // Value injected by FXMLLoader

    @FXML // fx:id="characterFileLabel" a label for displaying the filename of the current character
    private Label characterFileNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="statsTab"
    private Tab statsTab; // Value injected by FXMLLoader

    @FXML // fx:id="skillsTab"
    private Tab skillsTab; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 assert storyTab != null : "fx:id=\"storyTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert infoTab != null : "fx:id=\"infoTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert equipmentTab != null : "fx:id=\"equipmentTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert complicationsTab != null : "fx:id=\"complicationsTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert playTab != null : "fx:id=\"playTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert talentsTab != null : "fx:id=\"talentsTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert heldCPLabel != null : "fx:id=\"characterFileLabel11\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert powersTab != null : "fx:id=\"powersTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert totalCPLabel != null : "fx:id=\"characterFileLabel1\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert characterFileNameLabel != null : "fx:id=\"characterFileLabel\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert statsTab != null : "fx:id=\"statsTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	     assert skillsTab != null : "fx:id=\"skillsTab\" was not injected: check your FXML file 'character_scene.fxml'.";
	}
}