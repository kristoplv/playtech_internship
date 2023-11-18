# playtech_internship


## HOW TO LAUNCH:
Run program in Main.java

## FILES:
Main.java
* Handles Player and Casino class creation and launches file reading and writing classes.

Player.java
* Class for Player object. Holds player actions, account balance, wins/losses, ID.

Illegals.java
* Class for illegal players. Holds UID and illegal action.

Casino.java
* Class for Casino object. Handles transactions betweeb bets and players. Holds casino balance, matches and illegal players.

PlayerReader.java
* Takes in List of Players and file name in constructor. Reads from player_data.txt file and populates List players with corresponding data.

Matches.java
* Takes in file name in constructor and saves information about matches to a list. Has one function getMatches() to return all matches.

ResultWriter.java
* Creates and writes into "Results.txt" file:
 + legal players UID, account balance and win/loss ratio;
 + Illegal players UID and first illegal action;
 + Casino balance after all legal matches have been made.

## ADDITIONAL FILES:
* match_data.txt - example match data file for task.
* player_data.txt - example player data file for task.
* result.txt - example results file for task.
* Results.txt - result file generated by program.
