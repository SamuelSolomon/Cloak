package CharacterManager;

//controller dependencies
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

//.fxml file dependencies
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;


/**
 * Runs the Program, and manages files and display options.
 * 
 * @param opened	shows whether the first file has been opened
 * @param savesDirectory	the default file directory for opening and saving files.
 * @param url	the file location of the currently opened character, defaults to null
 * @param characterController the variable for the currently opened character file
 */
public class MainController extends Application {
	private boolean opened;
	private File savesDirectory;
	private File url;
	private CharacterController characterController;
	private Stage primaryStage;
	
//Program Methods
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Parent mainMenu = FXMLLoader.load(getClass().getResource("fxmlFiles/main_stage.fxml"));
			Scene programMenu = new Scene(mainMenu);
			programMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(programMenu);
			primaryStage.setTitle("Cloak");
			primaryStage.show();
		
			savesDirectory = new File(System.getProperty("user.home"), ".Cloak/saves");
			if (! savesDirectory.exists()) {
				savesDirectory.mkdirs();
			}
			
			opened = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * stops the program
     * Updates Needed
     * The program should ask the user if they want to save before ending the program
     * all save and open methods should disable interaction wit the primary stage.
     */
    @FXML
    void quit(ActionEvent event) {
    	Platform.exit();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
//Class Methods
    /**
     * activates the tabs are left disabled at program start becaseu they do not yet have functionality
     */
    @FXML
    void enableTabs(ActionEvent event) {
    	characterController.enableTabs();
    }
	
//Dynamic Methods
    /**
     * promts a user to select a file and opens it, if the file is in an improper format(such as not being a saved character file) it still loads it, but prompts the user with an error message as well.
     * Updates Needed
     * should only prompt user to save when something has been changed
     * if statements should be optimized to only need one run through of the open file protocol
     * the steps to open a file should be moved into their own method
     */
    @FXML
    void openFile(ActionEvent event) {
    	try {
    	if (opened == true) {
    		Alert a = new Alert(Alert.AlertType.WARNING, "Would you like to save?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    		Optional<ButtonType> option = a.showAndWait();
    		if (option.isPresent() && option.get() != ButtonType.CANCEL) {
    			if (option.get() == ButtonType.YES) {
        			saveToFile(new ActionEvent());
        		} 
    			FileChooser fileChooser = new FileChooser();
    	    	fileChooser.setTitle("Open Character File");
    	    	fileChooser.setInitialDirectory(savesDirectory);
    	    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    	    	url = fileChooser.showOpenDialog(primaryStage);
    	    	if (url != null) {
    	    		FileInputStream fileInput;
        			fileInput = new FileInputStream(url);
        	    	ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        	    	characterController.readObject(objectInput);
        	    	objectInput.close();
    	    	}
    		}
    	} else {
    		FileChooser fileChooser = new FileChooser();
	    	fileChooser.setTitle("Open Character File");
	    	fileChooser.setInitialDirectory(savesDirectory);
	    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
	    	url = fileChooser.showOpenDialog(primaryStage);
	    	if (url != null) {
	    		characterController = new CharacterController();
	    		mainMenu.getChildren().add(characterController);
	    		opened = true;
	    		enableTabs.setDisable(false);
	    		
		    	FileInputStream fileInput = new FileInputStream(url);
		    	ObjectInputStream objectInput = new ObjectInputStream(fileInput);
		    	characterController.readObject(objectInput);
		    	objectInput.close();
	    	}
    	}
    	} catch (EOFException e) {
    		Alert a = new Alert(Alert.AlertType.ERROR);
    		a.setContentText("The chosen file did not load as expected and may have been corrupted");
    		a.show();
    		e.printStackTrace();
		} catch (Exception e) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("there was a problem loading the File");
    		a.show();
    		e.printStackTrace();
		} 
    }

    /**
     * clears the character instance for a new character file, prompts the user to save before doing so.
     * Updates Needed
     * make it so the prompt to save the file only appears when something has been changed since the last save.
     */
    @FXML
    void openNew(ActionEvent event) {
    	if (opened == false) {
    		characterController = new CharacterController();
    		mainMenu.getChildren().add(characterController);
    		opened = true;
    		enableTabs.setDisable(false);
    	} else {
    		Alert a = new Alert(Alert.AlertType.WARNING, "Would you like to save?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    		Optional<ButtonType> option = a.showAndWait();
    		if (option.isPresent() && option.get() != ButtonType.CANCEL) {
    			if (option.get() == ButtonType.YES) {
						saveToFile(new ActionEvent());
        		}
    			characterController.clear();
    		} else {
    			return;
    		}
    	}
    	url = null;
    }

    /**
     * saves the current character instance to the current url, or if url is null then calls the savetonewfile method
     */
    @FXML
    void saveToFile(ActionEvent event) {
    	try {
    	if (url == null) {
    		saveToNewFile(new ActionEvent());
    	} else {
    		FileOutputStream fileOutput;
			fileOutput = new FileOutputStream(url);
    		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
    		characterController.writeObject(objectOutput);
    		objectOutput.close();
    	}
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * saves the current character to a newly selected or created file url and saves the current url to it.
     */
    @FXML
    void saveToNewFile(ActionEvent event) {
    	try {
    	FileChooser fileChooser = new FileChooser();
   	 	fileChooser.setTitle("Save Character File");
   		fileChooser.setInitialDirectory(savesDirectory);
   		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
   		url = fileChooser.showSaveDialog(primaryStage);
   		if (url != null) {
   			characterController.setFileName(url.getName());
   			
   			FileOutputStream fileOutput;
   			fileOutput = new FileOutputStream(url);
   	   		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
   	   		characterController.writeObject(objectOutput);
   	   		objectOutput.close();
   		}
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
//fxml resources
  	@FXML // fx:id="openNew" item in the file menu
      private MenuItem openNew; // Value injected by FXMLLoader

      @FXML // fx:id="mainMenu" the main screen
      private VBox mainMenu; // Value injected by FXMLLoader

      @FXML // fx:id="saveAs" item in the file menu
      private MenuItem saveAs; // Value injected by FXMLLoader

      @FXML // fx:id="save" item in the file menu
      private MenuItem save; // Value injected by FXMLLoader

      @FXML // fx:id="quit" item in the file menu
      private MenuItem quit; // Value injected by FXMLLoader

      @FXML // fx:id="programMenu" the file menu
      private MenuBar programMenu; // Value injected by FXMLLoader

      @FXML // fx:id="fileMenu" item in the file menu
      private Menu fileMenu; // Value injected by FXMLLoader

      @FXML // fx:id="open" item in the file menu
      private MenuItem open; // Value injected by FXMLLoader
      
      @FXML // fx:id="enableTabs" item in the file menu
      private MenuItem enableTabs; // Value injected by FXMLLoader
}
