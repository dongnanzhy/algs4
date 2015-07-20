/**
 * Created by dongnanzhy on 6/22/15.
 */
public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF wquf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        wquf = new WeightedQuickUnionUF(N*N+2);
    }

    private void connectNeighbor(int i, int j) {
        int N = grid.length;
        if (i == 0 ) {
            if (isOpen(i+2, j+1) && !wquf.connected(N+j+1, j+1)) {
                wquf.union(N+j+1, j+1);
            }
        } else if (i == N-1) {
            if (isOpen(i, j+1) && !wquf.connected((i-1)*N+j+1, i*N+j+1)) {
                wquf.union((i-1)*N+j+1, i*N+j+1);
            }
        }else if (j == 0) {
            if (isOpen(i, j+1) && !wquf.connected((i-1)*N+1, i*N+1)) {
                wquf.union((i-1)*N+1,i*N+1);
            }
            if (isOpen(i+2, j+1) && !wquf.connected((i+1)*N+1, i*N+1)) {
                wquf.union((i+1)*N+1, i*N+1);
            }
            if (isOpen(i+1, j+2) && !wquf.connected(i*N+2, i*N+1)) {
                wquf.union(i*N+2, i*N+1);
            }
        } else if (j == N-1) {
            if (isOpen(i, j+1) && !wquf.connected((i-1)*N+j+1, i*N+j+1)) {
                wquf.union((i-1)*N+j+1,i*N+j+1);
            }
            if (isOpen(i+2, j+1) && !wquf.connected((i+1)*N+j+1, i*N+j+1)) {
                wquf.union((i+1)*N+j+1,i*N+j+1);
            }
            if (isOpen(i+1, j) && !wquf.connected((i+1)*N-1, (i+1)*N)) {
                wquf.union((i+1)*N-1, (i+1)*N);
            }
        } else {
            if (isOpen(i, j+1) && !wquf.connected((i-1)*N+j+1, i*N+j+1)) {
                wquf.union((i-1)*N+j+1, i*N+j+1);
            }
            if (isOpen(i+2, j+1) && !wquf.connected((i+1)*N+j+1, i*N+j+1)) {
                wquf.union((i+1)*N+j+1,i*N+j+1);
            }
            if (isOpen(i+1, j) && !wquf.connected(i*N+j, i*N+j+1)) {
                wquf.union(i*N+j,i*N+j+1);
            }
            if (isOpen(i+1, j+2) && !wquf.connected(i*N+j+2, i*N+j+1)) {
                wquf.union(i*N+j+2, i*N+j+1);
            }
        }
    }
    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        int N = grid.length;
        if (i < 1 || i > N ) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        } else if (j < 1 || j > N) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }
        if (isOpen(i, j))  return;

        i--;j--;
        grid[i][j] = true;

        if (i == 0) {
            wquf.union(0, j+1);
        } else if (i == N-1) {
            wquf.union(N*(N-1)+j+1, N*N+1);
        }
        connectNeighbor(i, j);
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        int N = grid.length;
        if (i < 1 || i > N ) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        } else if (j < 1 || j > N) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }
        i--;j--;
        return grid[i][j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        int N = grid.length;
        if (i < 1 || i > N ) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        } else if (j < 1 || j > N) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }
        i--;j--;
        return wquf.connected(0, i*N+j+1);
    }

    // does the system percolate?
    public boolean percolates() {
        int N = grid.length;
        return wquf.connected(0, N*N+1);
    }

    public static void main(String[] args) {
//        Percolation test = new Percolation(6);
//        test.open(1,6);
//        test.open(2,6);
//        test.open(3,6);
//        test.open(4,6);
//        test.open(5,6);
//        test.open(5,5);
//        test.open(6,5);
//        System.out.println(test.percolates());
    }  // test client (optional)
}
