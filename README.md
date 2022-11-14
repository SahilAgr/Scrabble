Authors: 
Sahil Agrawal
Patrick Ma
Anirudh Bakshi 
Matthew Huitema


Design Decisions:

The choice to have a central game class was made early on. Game handles all the logic. It handles what happens and when. We decided to make sure it did as little as possible, which in most cases other then board was done. Several things are going to change when we move to GUI, mainly including the way board works. We also will delete parser as user inputs will be from the GUI.

Also, we decided that a player must play from H EIGHT as that was the way we interprited the rules.


Files Included:

1. Dictionary Class : A Class to read the text file and check the word.  
2. Tile Class: Creates all available tiles and any tile manipulation
3. Player Class: Assigns a player letters and scores
4. LetterBag Class: Holds all the letters available for the game
5. Game Class: Drive the whole game including creating the board and the players
6. Coordinates Class: The Coordinates for the board each axis is an enum so there is no user error when picking where to place a piece
7. Command Class: Processes any available command
8. Board Class: Creates the board in the terminal
9. dictonary.txt: a list of all legal scrabble words
10. ReadMe: The ReadMe file with instructions and any information related to the program 
11. BoardFrame: The Frame that the game plays out on 
12. GameEvent: An event object for the game
13. Placement: incapsulated information that is used for board and game
14. ScrabbleController: The controller for all communcation to the model 
15. ScrabbleView: The interface that updates the game event



Set-up Instructions:

Download the zip file. Open IntelliJ IDE and setup a workspace. To open the zip file inside IntelliJ,
from the main menu, click file then select import. Go to General > Existing Projects into Workspace > Next. 
Click the Select Archive File and browse for the zip file that was downloaded in the previous steps. 
The new project should now be visible in the Package Explorer. All the files mentioned above should be in that project. 
Run the main method inside the BoardFrame class to play the game.


Available Commands:
1. help -> will give you a tool tip for the game instructions
2. shuffle -> will shuffle the letters in hand 
3. pass -> passes your turn 
4. place -> places the desired word at a specific coordinate
5. test -> allows the player to test a word to see if it is a legal word and placement



Known Issues: 
1. Some classes do not have junit tests yet


