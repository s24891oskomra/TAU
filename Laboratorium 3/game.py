import random

class Board:
    def __init__(self, rows, cols, obstacle_chance=0.2):
        if rows < 5 or cols < 5:
            raise ValueError("Minimum 5x5")
        self.rows = rows
        self.cols = cols
        self.board = [['.' for _ in range(cols)] for _ in range(rows)]
        self._generate(obstacle_chance)

    def _generate(self, chance):
        for r in range(self.rows):
            for c in range(self.cols):
                if random.random() < chance:
                    self.board[r][c] = 'X'

        def get_edge():
            side = random.choice(['T', 'B', 'L', 'R'])
            if side == 'T': return 0, random.randint(0, self.cols - 1)
            if side == 'B': return self.rows - 1, random.randint(0, self.cols - 1)
            if side == 'L': return random.randint(0, self.rows - 1), 0
            return random.randint(0, self.rows - 1), self.cols - 1

        self.start = get_edge()
        while True:
            self.end = get_edge()
            dist = abs(self.start[0] - self.end[0]) + abs(self.start[1] - self.end[1])
            if self.start != self.end and dist > 1:
                break

        self.board[self.start[0]][self.start[1]] = 'A'
        self.board[self.end[0]][self.end[1]] = 'B'

    def display(self):
        for row in self.board:
            print(" ".join(row))

    def can_move(self, curr_pos, direction):
        r, c = curr_pos
        moves = {'UP': (r - 1, c), 'DOWN': (r + 1, c), 'LEFT': (r, c - 1), 'RIGHT': (r, c + 1)}

        if direction not in moves:
            return False, "Błędny kierunek"

        nr, nc = moves[direction]

        if not (0 <= nr < self.rows and 0 <= nc < self.cols):
            return False, f"Poza planszą: ({nr},{nc})"

        if self.board[nr][nc] == 'X':
            return False, f"Przeszkoda: ({nr},{nc})"

        return True, f"Ruch OK: ({nr},{nc})"

    def save(self, filename="board.txt"):
        with open(filename, 'w') as f:
            for row in self.board:
                f.write(" ".join(row) + "\n")


# TESTOWANIE METOD I CORNER CASE'ÓW
b = Board(5, 5)
b.display()
print(f"Start: {b.start}, Stop: {b.end}")

pos = b.start
for d in ['UP', 'DOWN', 'LEFT', 'RIGHT']:
    possible, msg = b.can_move(pos, d)
    print(f"Kierunek {d}: {msg}")

# Test wyjścia poza planszę (Corner Case)
print(f"Test poza planszą (góra z 0,0): {b.can_move((0, 0), 'UP')}")