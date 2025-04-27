package game.map;

/**
 * Represents a position on the game map using row and column coordinates.
 * Immutable to ensure safe use as a Map key.
 */
public class Position {
    private final int row;
    private final int col;

    /**
     * Constructs a Position with specified row and column.
     * @param row row index (>= 0)
     * @param col column index (>= 0)
     */
    public Position(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Row and col must be non-negative");
        }
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Computes the Manhattan distance to another position.
     * @param other the other position (must not be null)
     * @return |row - other.row| + |col - other.col|
     */
    public int distanceTo(Position other) {
        if (other == null) {
            throw new IllegalArgumentException("Other position must not be null");
        }
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position that = (Position) o;
        return this.row == that.row && this.col == that.col;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(row);
        result = 31 * result + Integer.hashCode(col);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", col=" + col + '}';
    }
}
