maximum_words = int(
    input("What is the maximum number of words you can't exceed?"))
minimum_words = int(input("What is the minimum number of words you need?"))
if maximum_words == minimum_words:
    print(
        "you can't have the same number of words for your maximum and minimum, please try again my fellow npc."
    )
elif maximum_words < minimum_words:
    print(
        "your minimum amount of words can't be less than the maximum number of words, please try again my fellow npc"
    )
else:
    basic_word = input("Input your sentence or paragraph here")
    words_list = basic_word.split()
    number_of_words = len(words_list)
    print("There are", number_of_words, "words.")

    if number_of_words > (minimum_words) and number_of_words < (maximum_words):
        print("nice job, your word count is in the right range.You have",
              number_of_words,
              "words which is greater than the minimum word count of",
              minimum_words, "words and have less than", maximum_words,
              "words which is the maximum number of words.")
    elif number_of_words < (minimum_words):
        print("your word count is under the minimum number of words, you have",
              number_of_words, "words but you need", minimum_words, "words.")
    elif number_of_words == (minimum_words):
        print("very dapper, you are at the exact minimum number of words of",
              minimum_words, "words.")
    elif number_of_words == (maximum_words):
        print("very dapper, you are at the exact maximum number of words of",
              maximum_words, "words.")

    else:
        print("your word count is above the limit,you have", number_of_words,
              "words but you only need", maximum_words, "words.")
