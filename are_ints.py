def only_ints(first, second):
    all_ints = False
    if isinstance(first,int) and isinstance(second,int):
        all_ints = True
    return all_ints

print(only_ints(1,1))