def add_dots(add):
    res = '.'.join(add[i:i + 1] for i in range(0, len(add), 1))
    return res


def remove_dots(remove):
    remove = remove.replace('.', '')
    return remove


print(add_dots('abcdefg'))
print(remove_dots('h.e.l.l.o'))
