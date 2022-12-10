Authors: 
Sahil Agrawal
Patrick Ma
Matthew Huitema

Anirudh Bakshi 

Design Decisions:

We followed MVC as best we could.
We couldn't decide whether 


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
22: DataStorage: the class which stores save files.
23: CustomBoardFrame: The class which handles the creation of custom boards.
24-26: x y z board.json: The 3 preset board json files.

User Manual:

Set-up Instructions:

Download the zip file. Open IntelliJ IDE and setup a workspace. To open the zip file inside IntelliJ,
from the main menu, click file then select import. Go to General > Existing Projects into Workspace > Next. 
Click the Select Archive File and browse for the zip file that was downloaded in the previous steps. 
The new project should now be visible in the Package Explorer. All the files mentioned above should be in that project. 
Run the main method inside the BoardFrame class to play the game.

In set up, you will be prompted with a choice to choose what kind of board you want to play on. They are: a standard scrabble board, a piece I like to call
modern art, and a board where you will get SO MANY points. You then have a choice how how many players, and then how many AI players. 

The game ends after 1 full turn cycle with the letterbag being empty. (We never actually tested this. I hope it works.)

Click on a letter to place or test your word. More on that later.

Click on the menu at the top to either pass, shuffle, or read the help module. (There are some other options - more on those latter).

As for place: you either have the chance test your word, or to place it.
Functionally they are very similar - test simply removes your letters afterwards and lets you know how many points you wouldve got, if valid.

As for scoring, each and every tile has it's own value.
Im not going to list them.

However, I will describe special squares: light blue grants 2x letter, dark blue gives 3x letter.
Light red multiplies the whole word by 2, whereas Dark red multiplies by 3.

These only are in effect on the turn the letter is placed down.

The player with the highest score at the end of the game is the winner.


You also have the opportunity to save/load a game from the menu. See known issues.

You can create a new custom board from the menu. When you click this, a new screen will show up with a blank scrabble board. Click on a tile once to get a
2 times letter multiplier, twice for a 3 time letter, thrice for a 2 times word multiplier, four times for a 3 times word multiplier, and a 5th click gets
you back to no multiplier. Whenever you are done, you click the "S" in the bottom of the screen and are then brought into a new game (with new player 
prompts)


Known Issues: 
1. Save/Load does not function. Maybe if it had a constructor, it wouldve.
2. We have no undo/redo because we were planning on using serialization to acomplish this. See Known Issue 1.
(couldve just used an arraylist + a simple function because tiles are built to be undone for test but by the time anirudh "finished" his code it was friday 
afternoon)
3. If you skip player one and player 2 happens to be the AI the game does not like that 

