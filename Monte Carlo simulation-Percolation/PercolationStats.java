import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
public class PercolationStats {
	private double fractions[];
	private final int t;
//perform independent trials on an n-by-n grid
    public PercolationStats(int n,int trials)
    {
    	t=trials;
    	if(n<=0||trials<=0) {
            throw new IllegalArgumentException();         
         }
    	fractions=new double[trials];
    	int sites=n*n;
    	for(int i=0;i<trials;i++)
    	{Percolation p=new Percolation(n);
    		int[] perm=StdRandom.permutation(sites);
    		while(!p.percolates())
    		{	p.open(StdRandom.uniform(1,n+1),StdRandom.uniform(1,n+1));
    			
    		}
    		fractions[i]=p.numberOfOpenSites()*1.0/sites;
    		    	}  	
    }
//sample mean of percolation threshold
    public double mean()
    {
    	return StdStats.mean(fractions);
    }
//sample standard deviation of percolation threshold
    public double stddev()
    {
    	return StdStats.stddev(fractions);
    }
//low endpoint of 95% confidence interval
    public double confidenceLo()
    {
    	return mean()-1.96*stddev()/Math.sqrt(t);
    }
//high endpoint of 95% confidence interval
    public double confidenceHi()
    {
    	return mean()+1.96*stddev()/Math.sqrt(t);
    }
//test client (see below)
	public static void main(String[] args) 
	{
		int sites=StdIn.readInt();
		int tri=StdIn.readInt();
		PercolationStats ps=new PercolationStats(sites,tri);
		System.out.println("mean								="+ps.mean());
		System.out.println("stdev								="+ps.stddev());
		System.out.println("95% Confidence interval				= [ "+ps.confidenceLo()+" , "+ps.confidenceHi()+" ]");
		
	}
}