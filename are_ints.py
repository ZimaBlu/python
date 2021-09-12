def only_ints(first, second):
    if isinstance(first, bool) or isinstance(second, bool):
        isint = False
    elif isinstance(first, int) and isinstance(second, int):
        isint = True
    else:
        isint = False
    return isint


print(only_ints("a", False))
