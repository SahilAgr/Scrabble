Authors: 
Sahil Agrawal
Patrick Ma
Anirudh Bakshi 
Matthew Huitema

Design Decisions:

The choice to have a central game class was made early on. Game handles all the logic. It handles what happens and when.
We decided to make sure it did as little as possible, which in most cases other then board was done. Several things are going
to change when we move to GUI, mainly including the way board works. We also will delete parser as user inputs will be from the GUI.

Also, we decided that a player must play from H EIGHT as that was the way we interprited the rules.

Files Included:

1. Dictionary Class : A Class to read the text file and check the word.  
2. Tile Class: Creates all available tiles and any tile manipulation
3. Player Class: Assigns a player letters and scores
4. Parser Class: Handles all the user inputs
5. LetterBag Class: Holds all the letters available for the game
6. Game Class: Drive the whole game including creating the board and the players
7. Coordinates Class: The Coordinates for the board each axis is an enum so there is no user error when picking where to place a piece
8. Command Class: Processes any available command
9. Board Class: Creates the board in the terminal
10. dictonary.txt: a list of all legal scrabble words
11. ReadMe: The ReadMe file with instructions and any information related to the program 

User Manual:

Set-up Instructions:

Download the zip file. Open IntelliJ IDE and setup a workspace. To open the zip file inside IntelliJ,
from the main menu, click file then select import. Go to General > Existing Projects into Workspace > Next. 
Click the Select Archive File and browse for the zip file that was downloaded in the previous steps. 
The new project should now be visible in the Package Explorer. All the files mentioned above should be in that project. 
Run the main method inside the Game class to play the game.


Available Commands:
1. help -> will give you a tool tip for the game instructions
2. shuffle -> will shuffle the letters in hand 
3. pass -> passes your turn 
4. place -> places the desired word at a specific coordinate.
    When you place a word that adds to another word, you need to type out the full word (ie. if you have user on the board, you must type out users.)
    place follows a entry off: <command> <direction> <xCoordinate> <yCoordinate> <word>.
    help has a more detailed description of that are valid for direction, coordinates, and word.



Known Issues: 
1. The scoring system has not been fully implemented
2. The file path for the dictionary is not dynamic and needs to be put in manually by the user 
3. While we were able to tell the user that a word is floating, we were not able reset the board properly nor copy it properly.

