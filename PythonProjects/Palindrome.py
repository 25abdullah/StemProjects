word = str(input("what is your word?"))
word2 = ""
char_list = []
for i in word:
  char_list.append(i)
char_list.reverse()
newWord = word2.join(char_list)
print("Reversed word is"+ " " + newWord)
if newWord == word:
  print("palindrome")
else:
  print("not a palindrome")

