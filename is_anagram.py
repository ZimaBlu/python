def is_anagram(w1, w2):
    if sorted(w1) == sorted(w2):
        ana = True
    else:
        ana = False
    return ana


print(is_anagram("typhoon", "opython"))