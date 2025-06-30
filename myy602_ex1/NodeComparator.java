
import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{
	
	@Override
	public int compare(Node node1,Node node2){
		if(node1.cost<node2.cost){
			return -1; //means it's less than
		}else if(node1.cost>node2.cost){
			return 1; //means greater than
		}
		return 0;
	}
}