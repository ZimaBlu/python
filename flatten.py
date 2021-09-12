def flatten(t):
    flat_list = [item for sublist in t for item in sublist]
    return flat_list


print(flatten([[1, 2], [3, 4]]))
