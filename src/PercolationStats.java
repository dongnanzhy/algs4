/**
 * Created by dongnanzhy on 6/22/15.
 */
public class PercolationStats {
    private double[] experiment;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        experiment = new double[T];
        for (int k = 0; k < T; k++) {
            Percolation exp = new Percolation(N);
            int count = 0;
            while (!exp.percolates()) {
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);
                if (exp.isOpen(i, j)) {
                    continue;
                }
                exp.open(i, j);
                count++;
            }
            experiment[k] = (double) count/(double) (N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(experiment);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(experiment);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(experiment.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(experiment.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(200, 100);
        System.out.println("mean:" + test.mean());
        System.out.println("stddev:" + test.stddev());
        System.out.println(test.confidenceLo() + " , " + test.confidenceHi());
    }
}
