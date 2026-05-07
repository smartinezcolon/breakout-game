# Breakout Game Specification

## Gameplay
The player controls a paddle at the bottom of the screen and uses it to bounce a ball upward to destroy bricks. The game contains 5 rows and 8 columns of bricks. The player starts with 3 lives. If the ball falls below the paddle, the player loses a life and the ball resets on top of the paddle until the Spacebar is pressed again. Destroying all bricks wins the game.

## Model — GameModel.java
- **Game Entities**: Manages the state of the `Paddle`, `Ball`, and `Brick` objects.
- **State tracking**: Tracks `lives` (starts at 3), `score`, and `gameState` (START, PLAYING, GAME_OVER, VICTORY).
- **Physics and Logic**:
  - Handles ball movement and wall bouncing.
  - Handles paddle movement within boundaries.
  - Detects collisions between the ball and paddle, adjusting the bounce angle based on where the ball hits the paddle.
  - Detects collisions between the ball and bricks, marking bricks as destroyed.
  - Updates the score with higher points awarded for bricks in higher rows.
  - Checks win conditions (all bricks cleared) and loss conditions (ball falls below paddle).

## View — GameView.java
- **Rendering Loop**: Reads the Model state to draw all entities on screen.
- **Visual Elements**:
  - Draws a solid black background.
  - Draws the paddle as a white rectangle.
  - Draws the ball as a white circle.
  - Draws the 5 rows of bricks in different colors: Red, Orange, Yellow, Green, Blue.
  - Draws the Heads-Up Display (HUD) showing current score and remaining lives at the top.
- **Overlays**:
  - Displays "Press SPACE to Launch" when the ball is resting on the paddle.
  - Displays "Game Over - Press 'R' to Restart" when the player runs out of lives.
  - Displays "You Win! - Press 'R' to Restart" when all bricks are cleared.

## Controller — GameController.java
- **Game Loop**: Runs a Swing `Timer` at ~60fps to continuously update the model and repaint the view.
- **Input Handling**:
  - Listens for `Left Arrow` to move the paddle left.
  - Listens for `Right Arrow` to move the paddle right.
  - Listens for `Spacebar` to launch the ball from the resting state.
  - Listens for `R` to restart the game entirely after a win or loss.

## Added Features
- Added Title screen with game name and start instructions
- Added sound effect when the ball hits a brick

## Done
- [x] Create GameState enum
- [x] Create Model classes (`Brick.java`, `Paddle.java`, `Ball.java`, `GameModel.java`)
- [x] Create View class (`GameView.java`)
- [x] Create Controller class (`GameController.java`)
- [x] Create Main class (`Main.java`)
- [x] Compile and run to verify functionality
- [x] Paddle moves smoothly and stays inside window
- [x] Ball launches with Spacebar
- [x] Ball bounces with dynamic angles off the paddle
- [x] Bricks disappear when hit, score updates based on row value
- [x] Lives decrease when ball falls below paddle
- [x] Win screen appears when all bricks are destroyed
- [x] Game over screen appears when lives reach 0
- [x] 'R' key restarts the game
