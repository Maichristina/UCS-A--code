
import java.util.ArrayList;


 public class Node{
	public ArrayList<Integer> data;
	public int cost; //root mexri current
	public int level;
	public Node root=null;
	
	public Node(ArrayList<Integer> data,int cost,int level){
		this.data=data;
		this.cost=cost;
		this.level=level;
		
		//System.out.println("data is "+data);
		//System.out.println("cost is "+cost);
		//System.out.println("finished? "+finish);
		
	}
	
/*	public static void createChild(Node source){

		//for(int i=2;i<=source.data.size();i++){
		//	... data = (f).T(...) //arraylist
		//...	sorted=f.issorted	//boolean
		//Node successor= new Node(..) //then add this to explored...and frontier
		for(int n=2; n<=data.size(); n++){
			ArrayList<Integer> l = new ArrayList<Integer>();
			l=invert(source.data,n);
			System.out.println(l);
		}

	}*/
}