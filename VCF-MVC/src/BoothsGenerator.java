import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class BoothsGenerator {
    final int MAXITER = 100;
    public JSONArray jsonArray = new JSONArray();
    private int numberBigBooths = 5;
    private int numberSmallBooths = 11;
    private int lengthBigBooths = 2;
    private int lengthSmallBooths = 1;
    private int SIZEMAP = (int) Math.sqrt(numberBigBooths * lengthBigBooths + numberSmallBooths * lengthSmallBooths) + 1;
    private Booth[] arrayOfBooths = new Booth[numberBigBooths + numberSmallBooths];
    private CellMap[][] cellMap = new CellMap[SIZEMAP + 1][SIZEMAP + 1];
    private boolean isBig;

    public BoothsGenerator() {

    }

    public void clearMap() {
        for (int x = 0; x < SIZEMAP; x++) {
            for (int y = 0; y < SIZEMAP; y++) {
                cellMap[x][y].setCel(0);
            }
        }
    }

   public void deleteBooths(int boothsNumber) {
        int len = 0;
        for (int m = 0; m < arrayOfBooths.length; m++) {
            if(arrayOfBooths[m] != null)
                len++;
        }
        for (int i = 0; i < len - boothsNumber; i++) {
                cellMap[arrayOfBooths[i].getX()][arrayOfBooths[i].getY()].setCel(0);
            arrayOfBooths[i] = null;
        }
    }

    public void createMap() {
        for (int x = 0; x < SIZEMAP; x++) {
            for (int y = 0; y < SIZEMAP; y++) {
                cellMap[x][y] = new CellMap(x, y, 0);
            }
        }
    }

    public void createVCF() throws JSONException {
        createMap();
        setBoothsToMap(numberBigBooths, lengthBigBooths, true);
        setBoothsToMap(numberSmallBooths, lengthSmallBooths, false);
        System.out.println("\n---------Final Map:");
        showMap();
    }

    void setBoothsToMap(int numberOfBooths, int lengthOfBooth, boolean isBigBooth) throws JSONException {

        isBig = isBigBooth;

        Random random = new Random();
        boolean isVertical = random.nextBoolean();
        for (int i = 0; i < numberOfBooths; i++) {
            if ( createBooths(i, lengthOfBooth, isVertical)) {
                System.out.println((isBig ? "big" : "small") + " booth is built: " + (i + 1));
            } else if (setMiddleBooth(i, lengthOfBooth, isVertical)) {
                System.out.println((isBig ? "big" : "small") + " booth is built: " + (i + 1));
            }
            makeJson(i, lengthOfBooth);
            showMap();
        }
    }

    public boolean createBooths(int numBooth, int lengthBooths, boolean isVertical) {
        if (numBooth != 0 && arrayOfBooths[numBooth - 1].isVertical) {
            isVertical = true;
        } else if (numBooth != 0) {
            isVertical = false;
        }

        arrayOfBooths[numBooth] = new Booth();
        arrayOfBooths[numBooth].setVertical(isVertical);
        arrayOfBooths[numBooth].setLength(lengthBooths);

        if (isVertical) {
            return setVerticalBooth(lengthBooths, numBooth);
        } else {
            return setHorizontalBooth(lengthBooths, numBooth);
        }
    }


    private boolean setMiddleBooth(int numBooth, int lengthBooths, boolean isVertical) {
        if (numBooth != 0 && arrayOfBooths[numBooth - 1].isVertical) {
            isVertical = true;
        } else if (numBooth != 0) {
            isVertical = false;
        }
        arrayOfBooths[numBooth] = new Booth();
        arrayOfBooths[numBooth].setVertical(isVertical);
        arrayOfBooths[numBooth].setLength(lengthBooths);

        for (int x = 1; x < SIZEMAP; x++) {
            for (int y = 1; y < SIZEMAP; y++) {
                if (checkIfFitX(x, y, lengthBooths) || checkIfFitY(x, y, lengthBooths)) {
                    setBoothOnMap(x, y, lengthBooths, 1, numBooth, isBig);
                    return true;
                }
            }
        }
        return false;
    }

    public int checkIfMiddleIsEmpty() {
        int countEmptySpaces = 0;
        for (int x = 1; x < SIZEMAP - 1; x++) {
            for (int y = 1; y < SIZEMAP - 1; y++) {
                if (cellMap[x][y].getCell() == 0)
                    countEmptySpaces++;
            }
        }
        return countEmptySpaces;
    }


    public boolean checkIfFitY(int x, int y, int lengthBooth) {
        int fit = 0;
        for (int i = 0; i < lengthBooth; i++) {
            if (y + i < SIZEMAP && cellMap[x][y + i].getCell() == 0) {
                if (!isBig) {
                    if (x + i + 1 == SIZEMAP) {
                        fit += 0;
                    } else if (y + i + 1 < SIZEMAP && cellMap[x][y + i + 1].getCell() == 0) {
                        fit += 0;
                    } else fit += 1;
                }
                fit += 0;
            } else fit += 1;
        }
        return fit == 0;
    }

    public boolean checkIfFitX(int x, int y, int lengthBooth) {
        int fit = 0;
        for (int i = 0; i < lengthBooth; i++) {
            if (x + i < SIZEMAP && cellMap[x + i][y].getCell() == 0) {
                if (!isBig) {
                    if (x + i + 1 == SIZEMAP) {
                        fit += 0;
                    } else if (x + i + 1 < SIZEMAP && cellMap[x + i + 1][y].getCell() == 0) {
                        fit += 0;
                    } else fit += 1;
                }
                fit += 0;
            } else fit += 1;
        }
        return fit == 0;
    }

    public boolean setVerticalBooth(int lengthBooth, int numBooth) {
        for (int i = 0; i < MAXITER; i++) {
            if (numBooth == 0 && isBig) {
                setBoothOnMap(1, 1, lengthBooth, 1, numBooth, isBig);
                return true;
            } else {
                if (numBooth % 2 == 0) {
                    for (int x = 0; x < SIZEMAP - 1; x++) {
                        for (int y = 2; y < SIZEMAP; y++) {
                            if (checkIfFitX(x, y, lengthBooth)) {
                                setBoothOnMap(x, y, lengthBooth, 1, numBooth, isBig);
                                return true;
                            }
                        }

                    }
                } else {
                    for (int y = 0; y < SIZEMAP - 1; y++) {
                        for (int x = 2; x < SIZEMAP; x++) {
                            if (checkIfFitX(x, y, lengthBooth) && checkIfFitY(x, y, lengthBooth)) {
                                setBoothOnMap(x, y, lengthBooth, 1, numBooth, isBig);
                                return true;
                            }
                        }

                    }
                }
            }
        }
        return false;
    }

    public boolean setHorizontalBooth(int lengthBooth, int numBooth) {
        for (int i = 0; i < MAXITER; i++) {
            if (numBooth == 0 && isBig) {
                setBoothOnMap(1, 1, 1, lengthBooth, numBooth, isBig);
                return true;
            } else {
                if (numBooth % 2 == 0) {
                    for (int x = 0; x < SIZEMAP - 1; x++) {
                        for (int y = 2; y < SIZEMAP; y++) {
                            if (checkIfFitY(x, y, lengthBooth) && checkIfFitX(x, y, lengthBooth)) {
                                setBoothOnMap(x, y, 1, lengthBooth, numBooth, isBig);
                                return true;
                            }
                        }

                    }
                } else {
                    for (int y = 0; y < SIZEMAP - 1; y++) {
                        for (int x = 2; x < SIZEMAP; x++) {
                            if (checkIfFitY(x, y, lengthBooth)) {
                                setBoothOnMap(x, y, 1, lengthBooth, numBooth, isBig);
                                return true;
                            }
                        }

                    }
                }

            }
        }
        return false;
    }

    void setBoothOnMap(int xBooth, int yBooth, int sizeX, int sizeY, int numBooth, boolean isBigBooth) {
        arrayOfBooths[numBooth].setNumBooth(numBooth);
        arrayOfBooths[numBooth].setX(xBooth);
        arrayOfBooths[numBooth].setY(yBooth);
        for (int x = xBooth; x < (xBooth + sizeX); x++) {
            for (int y = yBooth; y < (yBooth + sizeY); y++) {
                cellMap[x][y].setCel(numBooth + 1);
                cellMap[x][y].setSmall(!isBigBooth);
            }
        }
    }

    private void makeJson(int i, int lengthOfBooth) throws JSONException {
        String Align;
        if (arrayOfBooths[i].isVertical) {
            Align = "XAxis";
        } else
            Align = "YAxis";
        VCFModel vcfModel = new VCFModel("Xor", "Large", lengthOfBooth, 1, Align, arrayOfBooths[i].getX(), arrayOfBooths[i].getY());
        JSONObject newObject = new JSONObject();
        newObject.put("Name", vcfModel.Name);
        newObject.put("Size", vcfModel.Size);
        newObject.put("Width", vcfModel.Width);
        newObject.put("Height", vcfModel.Height);
        newObject.put("Align", vcfModel.Align);
        newObject.put("XPosition", vcfModel.XPosition);
        newObject.put("YPosition", vcfModel.YPosition);
        jsonArray.put(newObject);
    }

    public void showMap() {
        System.out.print("    ");
        for (int i = 0; i < SIZEMAP; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("  +");
        for (int i = 0; i < SIZEMAP; i++) {
            System.out.print("--");
        }
        System.out.println("-+");
        for (int x = 0; x < SIZEMAP; x++) {
            System.out.print(x + " | ");
            for (int y = 0; y < SIZEMAP; y++) {
                if (cellMap[x][y].getCell() >= 1) {
                    if (cellMap[x][y].isSmall())
                        System.out.print("x ");
                    else System.out.print("X ");
                } else System.out.print("~ ");
            }
            System.out.println("|");
        }
        System.out.print("  +");
        for (int i = 0; i < SIZEMAP; i++) {
            System.out.print("--");
        }
        System.out.println("-+");
    }
}
