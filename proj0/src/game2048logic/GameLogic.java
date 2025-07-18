package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048logic.MatrixUtils;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        if (r == 0) {
            return 0;
        }
        for (int i = r; i > 0; i--) {
            /* move up because top is empty square*/
            if (board[i - 1][c] == 0 && i > minR) {
                board[i - 1][c] = board[i][c];
                board[i][c] = 0;
            }
            /* merge because top has the same tile*/
            else if (board[i -1 ][c] == board[i][c] && i > minR) {
                board[i - 1][c] *= 2;
                board[i][c] = 0;
                return i;
            }
            else {
                break;
            }
        }
        return 0;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int mergedRow = -1;
        for (int i = 1; i < board.length; i++) {
            if (board[i][c] != 0) {
                int newMergedRow = moveTileUpAsFarAsPossible(board, i, c, mergedRow);
                if (newMergedRow > mergedRow) {
                    mergedRow = newMergedRow;
                }
            }
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            tiltColumn(board, i);
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        int[] firstRow = board[0];
        int[] secondRow = board[1];
        int[] thirdRow = board[2];
        int[] fourthRow = board[3];
        if (side == Side.EAST) {
            MatrixUtils.rotateLeft(board);
            tiltUp(board);
            MatrixUtils.rotateRight(board);
            return;
        } else if (side == Side.WEST) {
            MatrixUtils.rotateRight(board);
            tiltUp(board);
            MatrixUtils.rotateLeft(board);
            return;
        } else if (side == Side.SOUTH) {
            MatrixUtils.rotateLeft(board);
            MatrixUtils.rotateLeft(board);
            tiltUp(board);
            MatrixUtils.rotateRight(board);
            MatrixUtils.rotateRight(board);
            return;
        } else {
            tiltUp(board);
            return;
        }
    }
}
