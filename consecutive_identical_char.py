def double_letters(word) -> object:
    repeat = False
    length = len(word)
    for i in range(length-1):
        if word[i] == word[i+1]:
            repeat = True
    print(repeat)
    return repeat


double_letters('helol')
