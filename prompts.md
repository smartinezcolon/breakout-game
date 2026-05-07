#Interview Section

Prompt 1 I want to build a Breakout game in Java with Swing using MVC. Before we write any code, interview me. Ask me one question at a time about gameplay, controls, win and loss conditions, and what should be on the screen. After about 8 questions, summarize what I told you as a one-page spec organized by Model, View, and Controller.

Response from Agent: 
That sounds like a great approach! I'll ask you a series of questions to get a clear picture of the game before we start building.

Question 1 (of 8): What should be on the screen? Let's start with the visual layout. How many rows and columns of bricks would you like there to be, and should the bricks have different colors based on their row or hit points?

Prompt 2 (Answering Question 1): 
I want the screen to have a paddle at the bottom, a ball in the middle, and bricks at the top. I want 5 rows and 8 columns of bricks. The bricks should have different colors by row, but each brick only needs one hit to break. Also show the score and lives at the top of the screen.

Response from Agent:

Got it. That gives us a clear layout.

Question 2 (of 8): Controls How would you like to control the paddle? Should it be keyboard-only (e.g., using the Left/Right arrow keys or A/D), mouse-controlled (paddle follows the mouse cursor horizontally), or a combination of both?

Prompt 3 (Answering Question 2): 

I want the paddle to be keyboard-only using just the Left and Right arrow keys. The paddle should move smoothly and stay inside the game window.

Response from Agent:

Perfect, Left/Right arrows it is, with boundary collision for the paddle.

Question 3 (of 8): Gameplay (Ball Physics) How should the ball behave when it hits the paddle? For example, should the ball's bounce angle change depending on where it hits the paddle (e.g., bouncing at a sharper angle if it hits near the edges), or should it just be a simple, standard reflection no matter where it lands?

Prompt 4 (Answering Question 3): 

I want the bounce angle to change depending on where the ball hits the paddle. If it hits near the center, the ball should bounce mostly straight upward. If it hits near the edges, it should bounce at sharper angles. Keep the physics simple and beginner friendly.

Response from Agent:

That makes the gameplay much more engaging while keeping it approachable!

Question 4 (of 8): Gameplay (Starting & Losing a Life) How should the game start, and what happens right after the player loses a life? For example, should the ball rest on top of the paddle waiting for the player to press a key (like the Spacebar) to launch it, or does it spawn in the middle of the screen and drop automatically after a short countdown?

Prompt 5 (Answering Question 4): 

I want the ball to start resting on top of the paddle. The player should press the Spacebar to launch the ball at the start of the game and after losing a life.

Response from Agent:

Excellent, that gives the player control over when the action begins.

Question 5 (of 8): Loss Conditions & Flow How many lives should the player start with? Also, what exactly should happen when they lose all of them? (For example, should a "Game Over" message appear on the screen with an instruction to press a specific key to restart the whole game?)

Prompt 6 (Answering Question 5): 

I want the player to start with 3 lives. Each time the ball falls below the paddle, the player loses one life and the ball resets on top of the paddle waiting for the Spacebar to launch again. When all lives are lost, a "Game Over" message should appear with instructions to press the R key to restart the game.

Response from Agent:

Got it. That's a classic and solid way to handle lives and game overs.

Question 6 (of 8): Win Conditions What should happen when the player successfully breaks all the bricks? Should the game show a "You Win" message with an option to restart (e.g., press 'R' to play again), or would you prefer the game to immediately reset the bricks and increase the speed/difficulty for a new level?

Prompt 7 (Answering Question 6):

I want the game to stay on a single level for now. When all the bricks are destroyed, the game should show a "You Win" message with instructions to press the R key to restart the game.

Response from Agent:

Keeping it to a single level is a great way to ensure the core loop feels tight and polished right out of the gate!

Question 7 (of 8): Scoring How should the scoring system work? Does every broken brick give you the exact same number of points, or do bricks in the higher rows (which might be harder to hit) reward you with more points?

Prompt 8 (Answering Question 7):

I want bricks in the higher rows to give more points than bricks in the lower rows. This makes the harder to hit bricks more rewarding while still keeping the scoring system simple.

Response from Agent: 

That's a smart choice to reward precision!

Question 8 (of 8): Colors and Visuals Finally, do you have any specific preferences for the overall color scheme? For example, what colors would you like for the background, the paddle, the ball, and the 5 specific rows of bricks?

Prompt 9 (Answering Question 8): 

I want a simple arcade style color scheme. The background should be black, the paddle and ball should be white, and each row of bricks should have a different bright color. I want the brick rows to be red, orange, yellow, green, and blue from top to bottom.

#Implementation plan

After this it created a implementation plan and asked for my approval. Which after I reviewed it, I approved it and i started building the game using that plan.

#Specifications build

Prompt:

I want you to write a one-page specification for my Java Swing MVC Breakout game in README.md.

Include these sections:Gameplay, Model — GameModel.javaView — GameView.java, Controller — GameController.java, Added Features (For my future add-ons), Done

# Features/Polish build

Prompt 1: 
I would like to add Title screen with the game name and "Press SPACE to start"

It created the title screen with the game displaying "Breakout" and it also added a "Press SPACE to start" message.

It made the game look more professional, and had a proper start screen, rather than just launching into the game. 

Prompt 2:
I would like to add a sound effect when the ball hits a brick. 

This generated a sound file which everytime the ball hits a brick, it makes a "boop" sound. 

From this I wanted to add a gameover sound effect, and a life lost sound effect.

Prompt 3:
I would like to add a gameover sound effect, and life lost sound effect.

This added 2 new sound effects, life_lost.wav and a game_over.wav. 

Prompt 3: 

I would like to add a pause feature in-game by selecting 'P' on the keyboard.

This feature worked as prompted, however I did not like how the ball continues right away. I would like a 3 second countdown to resume gameplay, and while being paused id like a return to main menu feature. 

So I will prompt it to fix it. 

Prompt 4:

Could you add a 3-second countdown to resume gameplay after unpausing the game. I would also like to add a return to mainmenu 'm' while being paused, and a 'r' to restart while being paused

This added the features I was missing while being paused. It looks more polished and functional. 

Prompt 5:
In the You Win screen, could you add a main menu 'm' option, along with the 'r' to restart option?

I added this because the only way to get back to the mainmenu was to restart the game, then click pause, and then return to menu. Which for future purposes, would make sense to have that option. 
