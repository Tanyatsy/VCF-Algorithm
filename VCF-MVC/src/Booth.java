public class Booth extends BaseBooth {
    int length;
    boolean isVertical;


    Booth(int numBooth, int l, int x, int y) {
        length = l;
        this.x = x;
        this.y = y;
        this.numBooth = numBooth;
    }

    Booth() {
    }

    public void setIsVertical(boolean isVertical) {
        this.isVertical = isVertical;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }



}
