public class BaseBooth {

    int numBooth;
    int x;
    int y;

    BaseBooth(int numBooth, int x, int y) {
        this.numBooth = numBooth;
        this.x = x;
        this.y = y;
    }

    BaseBooth() {
    }

    public int getNumBooth() {
        return numBooth;
    }

    public void setNumBooth(int numBooth) {
        this.numBooth = numBooth;
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
}
