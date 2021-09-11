def mid(word):
    if len(word) % 2 == 0:
        print("")
    elif len(word) % 2 != 0:
        word_len = (len(word) // 2)
        print(word[word_len])

mid("Hello")
