import chess
import sys

def get_available_moves(fen):
    board = chess.Board(fen)
    return [move.uci() for move in board.legal_moves]

def main():
    fen = sys.argv[1]
    moves = get_available_moves(fen)
    print(moves)

if __name__ == "__main__":
    main()