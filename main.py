def capital_indexes(word):
    capital_list = []
    for i in range(0, len(word)):
        if word[i].isupper():
            capital_list.append(i)
    print(capital_list)

capital_indexes("HeLlO")