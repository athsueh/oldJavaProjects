
public class Cell {
	private int x,y;
	private int neigh = 0;
	private int alive = 0;
	public void alive(){
		alive = 1;
	}
	public void dead(){
		alive = 0;
	}
	public Cell(int xx, int yy){
		x = xx;
		y = yy;
	}
	public int stat(){
		return alive;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void plusN(){
		neigh++;
	}
	public int getN(){
		return neigh;
	}
	public void clearN(){
		neigh = 0;
	}
}
