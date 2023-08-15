
import turtle as trtl

painter = trtl.Turtle()
painter.speed(0)
painter.pensize(5)
intro=input("Hello! This is a program that will create 3 towers, you get to choose the colors for the towers, there are two colors that alternate for each tower!")

col=input("what do you want the first color to be?")
colio=input("what do you want the alternate color to be?")

# starting location of the tower
x = -150
y = -150

# height of tower and a counter for each floor
num_floors = 21
floor = 0

# iterate
while floor < num_floors:
  # set placement and color of turtle




  painter.penup()
  painter.goto(x, y)
  painter.color(col)
  rem=floor % 6
  if (rem > 2):
      painter.color(colio)
  y = y + 5 # location of next floor
  floor = floor + 1
 

 
  #draw the floor

  painter.pendown()
  painter.forward(50)



x = -50
y = -150

# height of tower and a counter for each floor
num_floors = 21
floor = 0
col=input("what do you want the first color to be for the second tower?")
colio=input("what do you want the alternate color to be for the second tower?")

# iterate
while floor < num_floors:
  # set placement and color of turtle


  painter.penup()
  painter.goto(x, y)
  painter.color(col)

  rem=floor % 6
  if (rem > 2):
    painter.color(colio)
  y = y + 5 # location of next floor
  floor = floor + 1

 
  #draw the floor
  painter.pendown()
  painter.forward(50)

x = 50
y = -150

# height of tower and a counter for each floor
num_floors = 21
floor = 0
col=input("what do you want the first color of the third tower to be?")
colio=input("what do you want the alternate color of the third tower to be?")
# iterate
while floor < num_floors:
  # set placement and color of turtle
  painter.penup()
  painter.goto(x, y)
  painter.color(col)

  rem=floor % 6
  if (rem > 2):
    painter.color(colio)
  y = y + 5 # location of next floor
  floor = floor + 1

 
  #draw the floor
  painter.pendown()
  painter.forward(50)


  

wn = trtl.Screen()
wn.mainloop()