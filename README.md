# Cloak
A Character Manager for the HERO System Table Top RPG

## General

### Summary
Cloaks purpose is to ease the time investment of playing the HERO system away from logistics and calculation and towards actually playing the game. This program implements my own sets of rules and stats for my scenario, as well as all the basic HERO system rules. 

### Scope
The Scope of the program includes: quickly managing all relevant statistics for a Character within the HERO System; Using all the relevent stats to play in a game session with full functionality, entirely removing the need to look up information while playing(**Version 1.0**); the ability to load in multiple Characters at once and simulate in game battles with them; the ability to connect to other players and take part in game battles(**Version 2.0**).

### Installation
**For the Application**\
Download the 'Cloak_v_X.X.jar' file.\ 
Open it to run the program.\
     -Make sure you have the latest JRE, as Cloak runs on Java 9 or above. You can download it here\
      "http://www.oracle.com/technetwork/java/javase/downloads/index.html" \
**For the Project**\
Download the Whole Repository, it is an Eclipse Project.\
     -Run it from the 'MainController.java' file\
**For the Source Code**\
Download the src folder if all you are interested in is the project code

### Versions
V0.5\
-Info tab and Stats tab both function as workable tabs\
-Able to save and load character files succesfully to the file system

## Developing Notes
-The MainController.java is where the program is run from and manages all the interactions with the file system\
-The CharacterController is where all the interactions between tabs occurs.\
-Anything labeled a TabController is for the main tabs where the data is recorded.\
-Anything labeled a popup is a self contained object that creates a window and returns the object designated after the window closes.\
-CharacterDatasets is an interface to store all the default values either for data that is not variable, or for data that has specific defaults for new files.
