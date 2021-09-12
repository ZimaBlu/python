def count(word):
    syl = 1
    for i in word:
        if i == '-':
            syl += 1
    return syl


print(count("ho-tel"))
