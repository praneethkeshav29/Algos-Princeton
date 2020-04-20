import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
//creates n-by-n grid, with all sites initially blocked
	private final int size;
	private final int t, gsize;
	private boolean visits[];
	private WeightedQuickUnionUF sites;
	private int opencount;
//private int vtop,vbottom;
	public Percolation(int n)
	{
		t=n;
		gsize=n*n;
		size=n*n+2;
		sites=new WeightedQuickUnionUF(size);
		visits=new boolean[size];

		for(int i=1;i<=gsize;i++) {
			visits[i]=false;
		}
	}
//opens the site (row, col) if it is not open already
	public void open(int row,int col) {
		int loc=getIndex(row,col);
		if(isOpen(row,col)) {
			return;
		} else {
			visits[loc]=true;
			opencount++;
			if(row==1) {
				sites.union(0,loc);
			}
			if(row==t) {
				int x=size-1;
				sites.union(x,loc);
			}
			if(row>1&&isOpen(row-1,col)) {
				int x=getIndex(row-1,col);
				sites.union(loc,x);
			}
			if(row<t&&isOpen(row+1,col)) {
				int x=getIndex(row+1,col);
				sites.union(loc,x);
			}
			if(col<t&&isOpen(row,col+1)) {
				int x=getIndex(row,col+1);
				sites.union(loc,x);
			}
			if(col>1&&isOpen(row,col-1)) {
				int x=getIndex(row,col-1);
				sites.union(loc,x);
			}
		}
		return;
	}
//is the site (row, col) open?
	public boolean isOpen(int row,int col) {
		int loc=getIndex(row,col);
		if(visits[loc]) {
			return true;
		} else {
			return false;
		}
	}
//is the site (row, col) full?
	public boolean isFull(int row,int col) {
		int loc=getIndex(row,col);
		if(sites.connected(loc,0)) {
			return true;
		} else
			return false;
	}
//returns the number of open sites
	public int numberOfOpenSites() {
		return opencount;
	}
//does the system percolate?
	public boolean percolates() {
		int x=size-1;
		if(sites.connected(0,x))
			return true;
		else
			return false;
	}
	private void validate(int row,int col) {
		if(row>t||row<1) {
			throw new IndexOutOfBoundsException("Row index is out of bounds");
		}
		if(col>t||col<1) {
			throw new IndexOutOfBoundsException("Column index is out of bounds");
		}
	}
	private int getIndex(int row,int col) {
		validate(row,col);
		return ((row-1)*t)+col;
	}
}