package Exe.EX3;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3.
 */
public class MyMap2D implements Map2D {
    private int[][] _map;

    public MyMap2D(int w, int h) {
        init(w, h);
    }

    public MyMap2D(int size) {
        this(size, size);
    }

    public MyMap2D(int[][] data) {
        this(data.length, data[0].length);
        init(data);
    }

    @Override
    public void init(int w, int h) {
        _map = new int[w][h];

    }

    @Override
    public void init(int[][] arr) {
        init(arr.length, arr[0].length);
        for (int x = 0; x < this.getWidth() && x < arr.length; x++) {
            for (int y = 0; y < this.getHeight() && y < arr[0].length; y++) {
                this.setPixel(x, y, arr[x][y]);
            }
        }
    }

    @Override
    public int getWidth() {
        return _map.length;
    }

    @Override
    public int getHeight() {
        return _map[0].length;
    }

    @Override
    public int getPixel(int x, int y) {
        return _map[x][y];
    }

    @Override
    public int getPixel(Point2D p) {
        return this.getPixel(p.ix(), p.iy());
    }

    public void setPixel(int x, int y, int v) {
        _map[x][y] = v;
    }

    public void setPixel(Point2D p, int v) {
        setPixel(p.ix(), p.iy(), v);
    }

    @Override
    public void drawSegment(Point2D p1, Point2D p2, int v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawRect(Point2D p1, Point2D p2, int col) {
        int left = (int) Math.min(p1.x(), p2.x());
        int right = (int) Math.max(p1.x(), p2.x());
        int top = (int) Math.max(p1.y(), p2.y());
        int bottom = (int) Math.min(p1.y(), p2.y());

        for (int x = left; x <= right; x++) {
            for (int y = bottom; y <= top; y++) {
                setPixel(x, y, col);
            }
        }
    }

    @Override
    public void drawCircle(Point2D p, double rad, int col) {
        // Checking which points are at a smaller or same distance from our center and painting it
        for (int x = 0; x < this.getWidth(); x++) {

            for (int y = 0; y < this.getHeight(); y++) {

                Point2D sPoint = new Point2D(x, y);
                double dis = sPoint.distance(p);

                if (dis <= rad) {
                    this.setPixel(x, y, col);
                }
            }
        }

    }


    @Override
    public int fill(Point2D p, int new_v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int fill(int x, int y, int new_v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Point2D[] shortestPath(Point2D p1, Point2D p2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int shortestPathDist(Point2D p1, Point2D p2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void nextGenGol() {
        int[][] nextGenMap = this._createEmptyMap();
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                int neighboursCount = this._getNeighboursCount(x, y);
                boolean isAlive = this._isAlive(x, y);
                boolean isDead = !isAlive;
                if (isAlive && neighboursCount < 2) {
                    nextGenMap[x][y] = WHITE;
                } else if (isAlive && (neighboursCount == 2 || neighboursCount == 3)) {
                    nextGenMap[x][y] = BLACK;
                } else if (isAlive && neighboursCount > 3) {
                    nextGenMap[x][y] = WHITE;
                } else if (isDead && neighboursCount == 3) {
                    nextGenMap[x][y] = BLACK;
                }
            }
        }
        init(nextGenMap);
    }

    private int[][] _createEmptyMap() {
        int[][] emptyMap = new int[this.getWidth()][this.getHeight()];
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                emptyMap[x][y] = WHITE;
            }
        }
        return emptyMap;
    }

    private boolean _isAlive(int x, int y) {
        if (x < 0 || x > this.getWidth() - 1) {
            return false;
        }
        if (y < 0 || y > this.getHeight() - 1) {
            return false;
        }
        return _map[x][y] != WHITE;
    }

    private int _getNeighboursCount(int x, int y) {
        int sum = 0;
        if (this._isAlive(x - 1, y - 1)) {
            sum += 1;
        }
        if (this._isAlive(x, y - 1)) {
            sum += 1;
        }
        if (this._isAlive(x + 1, y - 1)) {
            sum += 1;
        }
        if (this._isAlive(x - 1, y)) {
            sum += 1;
        }
        if (this._isAlive(x + 1, y)) {
            sum += 1;
        }
        if (this._isAlive(x - 1, y + 1)) {
            sum += 1;
        }
        if (this._isAlive(x, y + 1)) {
            sum += 1;
        }
        if (this._isAlive(x + 1, y + 1)) {
            sum += 1;
        }
        return sum;
    }

    @Override
    public void fill(int c) {
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                this.setPixel(x, y, c);
            }
        }
    }
}
