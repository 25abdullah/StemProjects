import random
import turtle as trtl
painter = trtl.Turtle()
secret =random.randint(1,50)
print("I have a number from 1-50, try to guess it! If you win the turtle will draw you a little something :)")
guess = int(input('Guess: '))
if guess<secret:
  print("My number is greater")
elif guess>secret:
  print("My number is smaller")
else:
  print("Great job! You got it correct on the first try, very dapper!")
  painter.right(60)
  painter.backward(100)
  painter.right(60)
  painter.forward(100)
  painter.right(120)
  painter.forward(120)
  painter.penup()
  painter.goto(0,0)
  painter.left(120)
  painter.pendown()
  painter.backward(120)
  painter.hideturtle()
if guess!=secret:
  guess2=int(input('Guess: '))
  if guess2!=secret:
    if guess2<secret:
      print("my number is greater")
    elif guess2>secret:
      print("my number is smaller")
    guess3=int(input('Guess: '))
    if guess3!=secret:
      print('sorry the correct answer was',secret,)
    
    else:
      painter.right(60)
      painter.backward(100)
      painter.right(60)
      painter.forward(100)
      painter.right(120)
      painter.forward(120)
      painter.penup()
      painter.goto(0,0)
      painter.left(120)
      painter.pendown()
      painter.backward(120)
      painter.hideturtle()
      print("Nice job! You got it on the last try :)")
  else:
      painter.right(60)
      painter.backward(100)
      painter.right(60)
      painter.forward(100)
      painter.right(120)
      painter.forward(120)
      painter.penup()
      painter.goto(0,0)
      painter.left(120)
      painter.pendown()
      painter.backward(120)
      painter.hideturtle()
      print("Great job! You got right the second time, pretty good.")    