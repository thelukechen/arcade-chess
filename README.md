# JavaFX Chess Game

## Version Test 1.01

Application of a fully functional chess game. Most JavaFX features are disabled or commented out. 
Uses Maven to compile and run indicated in the pom file. 

## Updates

* Fixed bugs caused with promotion of the pawn by setting the King and Opponent values of the piece when promoted.
  
* Created package pieces and put some of the pieces into that package. Ran into a problem with other pieces 
  because Board.squarearr is a package-private field. 

* Created directories in resources to organize the pictures but pictures returned invalid url or resource. 
Was recommended to use ClassLoader.

## Problems
These are the current problems being considered for improvement or addition to the program. 
The following solutions are in no particular order.

* Organization of class and picture files
* Create test file
* Resolution change between computers
* Static method to access square array
* Duplicating piece once pawn promotes
* Implement draw and resign
* Implement hold and drag to move pieces
* Highlight piece selected and corresponding possible moves
* Add a clock timer
* Use enum for which team and what type of piece
* Make Coordinate class to abstract the values
* Allow possible moves array to be accessible in different classes
* Look into properties in javaFX to reduce passing the App object
* Add a history block that records the game movements

## Issues and Discussions
Please feel free to send bugs and issues in the Issues tab. If you have GUI suggestions or any other suggestions, 
please add them in the Discussions tab.


## Acknowledgement
[My LinkedIn](https://www.linkedin.com/in/thelukechen/)

Email me at 
```
chen.luke11@gmail.com 
```
if you would like to use the code.
