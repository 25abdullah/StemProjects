import random as rob 
import turtle as trtl
import time 

rock_drawer = trtl.Turtle()
painter = trtl.Turtle()
cutter_drawer = trtl.Turtle()
list_of_turtles=[rock_drawer, painter, cutter_drawer]
for i in list_of_turtles:
  i.hideturtle()
  i.speed(500)

turns = int(input("how many times do you want to play?"))
possible_actions = ["paper","rock","scissors"]
computer_score = 0
player_score = 0 

def paper():
  list_of_cord = [(0,10) ,  (0,30),  (0,50),  (0,70), (0,90),]
  for i in range(4):
    painter.color("black")
    painter.pensize(4)
    painter.forward(100)
    painter.left(90)
  for i in range(len(list_of_cord)):
    painter.pensize(2)
    painter.penup()
    painter.goto(list_of_cord[0])
    painter.pendown()
    painter.forward(100)
    list_of_cord.pop(0)
    painter.penup()
    painter.goto(0,0)
    painter.pendown()
  list_of_cord = [(0,10) ,  (0,30),  (0,50),  (0,70), (0,90),]
  time.sleep(3)



def scissors():
   cutter_drawer.color("red")
   cutter_drawer.circle(30)
   cutter_drawer.penup()
   cutter_drawer.goto(150,0)
   cutter_drawer.pendown()
   cutter_drawer.circle(30)
   cutter_drawer.color("black")
   cutter_drawer.penup()
   cutter_drawer.goto(150,60)
   cutter_drawer.right(35)
   cutter_drawer.pendown()
   cutter_drawer.backward(250)
   cutter_drawer.right(75)
   cutter_drawer.forward(25)
   cutter_drawer.left(75)
   cutter_drawer.forward(240)
   cutter_drawer.penup()
   cutter_drawer.goto(0,58)
   cutter_drawer.left(75)
   cutter_drawer.pendown()
   cutter_drawer.forward(250)
   cutter_drawer.right(75)
   cutter_drawer.forward(25)
   cutter_drawer.left(75)
   cutter_drawer.backward(250)
   cutter_drawer.penup()
   cutter_drawer.goto(0,0)
   cutter_drawer.pendown()
   time.sleep(3)
   cutter_drawer.setheading(0)
   cutter_drawer.penup()
   cutter_drawer.goto(0,0)
   cutter_drawer.pendown()



def rock():
  rock_cord = [(-75,25), (-75,45), (-75,65), (-75,85), (-75,105), (-75,125)]
  for i in range(10):
    rock_drawer.hideturtle()
    rock_drawer.left(36)
    rock_drawer.forward(50)
  rock_drawer.penup()
  rock_drawer.goto(-75,25)
  rock_drawer.pendown()
  for i in range(len(rock_cord)):
    rock_drawer.pensize(2)
    rock_drawer.penup()
    rock_drawer.goto(rock_cord[0])
    rock_drawer.pendown()
    rock_drawer.forward(100)
    rock_cord.pop(0)
  time.sleep(3)
  rock_drawer.penup()
  rock_drawer.goto(0,0)
  rock_drawer.pendown()
  rock_cord = [(-75,25), (-75,45), (-75,65), (-75,85), (-75,105), (-75,125)]





for i in range(turns):
  
  user_run = input("What is your choice?").lower()
  rob.shuffle(possible_actions)
  computer_response =rob.choice(possible_actions)
  if user_run not in possible_actions:
    user_run = rob.choice(possible_actions)
    print("sorry, but you did not choose a right input. Therefore we randomly chose",user_run,"for you. ")
  if user_run == "rock" and computer_response == "rock":
    print("you chose rock!")
    rock()
    print("computer chose rock!")
    time.sleep(1.5)
    rock_drawer.clear()
    print("it's a tie! Nobody gets a point.")
    
  elif user_run == "scissors" and computer_response == "scissors":
    print("you chose scissors!")
    scissors()
    print("Computer also chose scissors!")
    time.sleep(1.5)
    cutter_drawer.clear()
    print("it's a tie! Nobody gets a point.")

    
  elif user_run == "paper" and computer_response == "paper":
    print("you chose paper!")
    paper()
    print("The computer also chose paper!")
    time.sleep(1.5)
    painter.clear()
    print("it's a tie! Nobody gets a point!")
  else:
    if user_run == "rock" and computer_response =="scissors":
      print("you chose rock!")
      rock()
      rock_drawer.clear()
      print("The computer chose scissors!")
      scissors()
      cutter_drawer.clear()
      player_score=player_score+1
      print("you win this round!", "user points =", player_score, "computer points =", computer_score)
      
    elif user_run == "rock" and computer_response =="paper":
      print("you chose rock!")
      rock()
      rock_drawer.clear()
      print("The computer chose paper!")
      paper()
      painter.clear()
      computer_score=computer_score+1
      print("The computer wins this round!","user points =", player_score, "computer points =", computer_score)
      
    elif user_run == "paper" and computer_response == "rock":
      print("you chose paper!")
      paper()
      painter.clear()
      print("The computer chose rock!")
      rock()
      rock_drawer.clear()
      player_score=player_score+1
      print("you win this round!","user points =", player_score, "computer points =", computer_score)
      
    elif user_run== 'paper' and computer_response == "scissors":
      print("you chose paper!")
      paper()
      painter.clear()
      print("The computer chose scissors!")
      scissors()
      cutter_drawer.clear()
      computer_score=computer_score+1
      print("The computer wins this round!","user points =", player_score, "computer points =", computer_score)
      
    elif user_run == "scissors" and computer_response == "rock":
      print("you chose scissors!")
      scissors()
      cutter_drawer.clear()
      print("T`  he computer chose rock!")
      rock()
      rock_drawer.clear()
      computer_score=computer_score+1
      print("The computer wins this round!","user points =", player_score, "computer points =", computer_score)
      
    else:
      print("you chose scissors!")
      scissors()
      cutter_drawer.clear()
      print("The computer chose paper! ")
      paper()
      painter.clear()
      player_score= player_score+1
      print("you win this round!","user points =", player_score, "computer points =", computer_score)
print("You scored",player_score, "points.")
print( "The computer scored",computer_score, "points.")
if computer_score > player_score:
  print("you bozo, imagine losing to an npc...")
elif computer_score == player_score:
  print("it's a tie! Not very dapper :((((")
else:
  print("Congrats you win!")
  
wn = trtl.Screen()
wn.mainloop()