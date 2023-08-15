import random as rob

list_of_characters = ['q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m','1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M','!','@','#','$','&','*', "."]

list_for_gmail = ['q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m','1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M', "."]

ask_user_password = int(input("How many characters do you want in your password?"))

rob.shuffle(list_of_characters)
password = []

for i in range(ask_user_password):
  char= rob.choice(list_of_characters)
  password.append(char)
rob.shuffle(password)
print("".join(password))

ask_user_email = input("would you like a username to make a new random gmail, say 'yes' or 'no'?")
gmail = ["@", "gmail", ".","com"]
if ask_user_email == "yes":
  ask_char = int(input("How many characters do you want in the gmail username?"))
  if ask_char < 5:
    print("sorry, you must input a number between 6 and 30 characters to make a new gmail.")
  elif ask_char >30:
    print("sorry you must input a new number between 6 and 30 characters to make a new gmail.")
  else:
    for i in range(ask_char):
      char = rob.choice(list_for_gmail)
      gmail.insert(0,char)
    print("your randomly generated new gmail is ")
    print("".join(gmail))
else:
  print("Thanks for using this program, hope this password serves you well ;)")

    