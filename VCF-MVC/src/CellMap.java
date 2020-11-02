public class CellMap {
    int x;
    int y;
    int cell;
    boolean isSmall = false;

    public CellMap(int x, int y, int cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;
    }

    public CellMap() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCell() {
        return cell;
    }

    public void setCel(int cell) {
        this.cell = cell;
    }

    public boolean isSmall() {
        return isSmall;
    }

    public void setSmall(boolean small) {
        isSmall = small;
    }
}
