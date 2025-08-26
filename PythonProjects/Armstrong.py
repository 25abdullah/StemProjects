num_str = str(input("what is your number?"))
initial_list = []
for i in num_str:
    initial_list.append(i)

number_list = list(map(int, initial_list))
num_int = int(num_str)
length = int((len(number_list)))  


value = 0
for i in range(len(number_list)):
    print(number_list[i]**length)
    value = (number_list[i]**length) + value

if value == num_int:
    print("Armstrong!")
else:
    print("no armstrong!")
    
    