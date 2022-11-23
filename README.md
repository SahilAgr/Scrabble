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
4. LetterBag Class: Holds all the letters available for the game
5. Game Class: the model, which takes data from the controller, delegates to backend code, then updates it's observers.
6. Coordinates Class: The Coordinates for the board each axis is an enum so there is no user error when picking where to place a piece
7. Board Class: Creates the board in the terminal
8. dictonary.txt: a list of all legal scrabble words
9. ReadMe.md: The ReadMe file with instructions and any information related to the program 
10: BoardFrame Class: the frame used for the GUI.
11: ScrabbleController Class: the class used to send requests from the frame to the model
12: ScrabbleView Interface: the class that is an observer of game.
13: Placement Class: encapsulation of information which needs to be passed between the model and the frame, for display purposes
14: AIPlayer Class: where we contain our ai desisionmaking.
    14b: 
15: BlackStar.png: the black star image for the starting space.
16-21: all the different tests: tests.

User Manual:

Set-up Instructions:

Download the zip file. Open IntelliJ IDE and setup a workspace. To open the zip file inside IntelliJ,
from the main menu, click file then select import. Go to General > Existing Projects into Workspace > Next. 
Click the Select Archive File and browse for the zip file that was downloaded in the previous steps. 
The new project should now be visible in the Package Explorer. All the files mentioned above should be in that project. 
Run the main method inside the BoardFrame class to play the game.

In set up, you will be prompted with a choice how how many players, and then how many AI players. The game ends after 1 full turn cycle with the letterbag being empty.

Click on a letter to place or test your word. More on that later.

Click on the menu at the top to either pass, shuffle, or read the help module.

As for place: you either have the chance test your word, or to place it.
Functionally they are very similar - test simply removes your letters afterwards and lets you know how many points you wouldve got, if valid.

As for scoring, each and every tile has it's own value.
Im not going to list them.

However, I will describe special squares: light blue grants 2x letter, dark blue gives 3x letter.
Light red multiplies the whole word by 2, whereas Dark red multiplies by 3.

These only are in effect on the turn the letter is placed down.

The player with the highest score at the end of the game is the winner.




Known Issues: 
1. pretty sure scoring is messed up - but see below for why we couldnt find the bug.
2, junit not being fully finished - This was Anirudh's responsibility. He was asked to have this finished by sunday morning, and that my work rided on his timelyness.
as of writing this, hes provided basic ones ~Matthew

