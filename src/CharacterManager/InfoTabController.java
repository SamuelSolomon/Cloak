package CharacterManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * InfoTabController is both a model and controller for a characters infoTab.
 * @author SamuelSolomon
 *
 */
public class InfoTabController extends ScrollPane implements Initializable{
	
	/**
	 * creates an instance of an infoTabController
	 */
	public InfoTabController() {
        this.loadView();
	}
//Program Methods
	/**
	 * calls the fxml loader to populate the values of anything defined in the fxml file reference contained within.
	 */
	private final void loadView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/InfoTab.fxml"));
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
	
	/**
	 * Write all the variables of a InfoTabController Instance using a objectOutputStream
	 * @param s an objectoutputstream meant to save all the variable data in the InfoTabController
	 * @throws IOException
	 */
	public void writeObject(ObjectOutputStream s) throws IOException {
        s.writeUTF(heightText.getText());
        s.writeUTF(genderAppearenceText.getText());
        s.writeUTF(dislikesText.getText());
        s.writeUTF(likesText.getText());
        s.writeUTF(magicText.getText());
        s.writeUTF(buildText.getText());
        s.writeUTF(familiesText.getText());
        s.writeUTF(weightText.getText());
        s.writeUTF(genderText.getText());
        s.writeUTF(nationalityText.getText());
        s.writeUTF(nameText.getText());
        s.writeUTF(scarsText.getText());
        s.writeUTF(demeanorText.getText());
        s.writeUTF(classText.getText());
        s.writeUTF(skillsText.getText());
        s.writeUTF(raceText.getText());
        s.writeUTF(hobbiesText.getText());
        s.writeUTF(ageText.getText());
        s.writeUTF(occupationsText.getText());
        s.writeUTF(hairColorText.getText());
        //s.writeUTF(eyeColorText.getText());
        //s.writeUTF(skinColorText.getText());
    }
	
	/**
	 * Read all the variables of a InfoTabController instance using a objectInputStream
	 * @param s an objectInputStreamMeant to retrieve all the variable data in a InfoTabController Instance
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        heightText.setText(s.readUTF());
        genderAppearenceText.setText(s.readUTF());
        dislikesText.setText(s.readUTF());
        likesText.setText(s.readUTF());
        magicText.setText(s.readUTF());
        buildText.setText(s.readUTF());
        familiesText.setText(s.readUTF());
        weightText.setText(s.readUTF());
        genderText.setText(s.readUTF());
        nationalityText.setText(s.readUTF());
        nameText.setText(s.readUTF());
        scarsText.setText(s.readUTF());
        demeanorText.setText(s.readUTF());
        classText.setText(s.readUTF());
        skillsText.setText(s.readUTF());
        raceText.setText(s.readUTF());
        hobbiesText.setText(s.readUTF());
        ageText.setText(s.readUTF());
        occupationsText.setText(s.readUTF());
        hairColorText.setText(s.readUTF());
        //eyeColorText.setText(s.readUTF());
        //skinColorText.setText(s.readUTF());
    }

	/**
	 * reset all InfoTabController instance variables to their default values.
	 */
	public void clear() {
		heightText.setText("");
        genderAppearenceText.setText("");
        dislikesText.setText("");
        likesText.setText("");
        magicText.setText("");
        buildText.setText("");
        familiesText.setText("");
        weightText.setText("");
        genderText.setText("");
        nationalityText.setText("");
        nameText.setText("");
        scarsText.setText("");
        demeanorText.setText("");
        classText.setText("");
        skillsText.setText("");
        raceText.setText("");
        hobbiesText.setText("");
        ageText.setText("");
        occupationsText.setText("");
        hairColorText.setText("");
        //eyeColorText.setText("");
        //skinColorText.setText("");
	}
	
//fxml resources
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea heightText;

    @FXML
    private TextArea genderAppearenceText;

    @FXML
    private TextArea dislikesText;

    @FXML
    private TextArea likesText;

    @FXML
    private TextArea magicText;

    @FXML
    private TextArea buildText;

    @FXML
    private TextArea familiesText;

    @FXML
    private TextArea weightText;

    @FXML
    private TextArea genderText;

    @FXML
    private TextArea nationalityText;

    @FXML
    private TextArea nameText;

    @FXML
    private TextArea scarsText;

    @FXML
    private TextArea demeanorText;

    @FXML
    private TextArea classText;

    @FXML
    private TextArea skinColorText;

    @FXML
    private TextArea eyeColorText;

    @FXML
    private TextArea skillsText;

    @FXML
    private TextArea raceText;

    @FXML
    private TextArea hobbiesText;

    @FXML
    private TextArea ageText;

    @FXML
    private TextArea occupationsText;

    @FXML
    private TextArea hairColorText;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert heightText != null : "fx:id=\"heightText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert genderAppearenceText != null : "fx:id=\"genderAppearenceText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert dislikesText != null : "fx:id=\"dislikesText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert likesText != null : "fx:id=\"likesText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert magicText != null : "fx:id=\"magicText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert buildText != null : "fx:id=\"buildText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert familiesText != null : "fx:id=\"familiesText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert weightText != null : "fx:id=\"weightText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert genderText != null : "fx:id=\"genderText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert nationalityText != null : "fx:id=\"nationalityText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert scarsText != null : "fx:id=\"scarsText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert demeanorText != null : "fx:id=\"demeanorText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert classText != null : "fx:id=\"classText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert skinColorText != null : "fx:id=\"skinColorText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert eyeColorText != null : "fx:id=\"eyeColorText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert skillsText != null : "fx:id=\"skillsText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert raceText != null : "fx:id=\"raceText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert hobbiesText != null : "fx:id=\"hobbiesText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert ageText != null : "fx:id=\"ageText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert occupationsText != null : "fx:id=\"occupationsText\" was not injected: check your FXML file 'info_tab.fxml'.";
        assert hairColorText != null : "fx:id=\"hairColorText\" was not injected: check your FXML file 'info_tab.fxml'.";
		
	}

}
