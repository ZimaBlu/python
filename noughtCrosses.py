def get_col_row(ref):
    board = ([
        ["X", "O", "X"],
        [" ", " ", " "],
        ["O", " ", " "],
    ])
    column = ref[0]
    row = int(ref[1])-1

    if column == 'A':
        column = 0
    elif column == 'B':
        column = 1
    elif column == 'C':
        column = 2

    return board[row][column]


print(get_col_row("C1"))
