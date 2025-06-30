
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.PriorityQueue;

public class Test{
	static ArrayList<Integer> b; //array after T()
	static ArrayList<Integer> array; //input array
	static ArrayList<ArrayList<Integer>> bestRoute; //result of UCS
	static ArrayList<ArrayList<Integer>> path; //current path we are exploring
	
	public static void main(String[] args){
		array = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		//System.out.println("Enter numbers");
		int size = -1;
		do {
			System.out.print("Enter the size of the array: ");
			if (scan.hasNextInt()) {
			    size = scan.nextInt();
			} else {
			    System.out.println("I need an integer, please try again.");
			    scan.nextLine();
			}
		} while (size <= 0);
		System.out.println("Enter numbers");
		int elements_counter = size;
		while(elements_counter>0){
			if(scan.hasNextInt()){
				array.add(scan.nextInt());
				elements_counter --;
			}else {
				System.out.println("wrong input, give a number");
				scan.nextLine();
			}
		}
		System.out.println("user input was: "+array);
		System.out.println();
		scan.close();
		Node source =new Node(array,0,0);
		UCS(source);

	}
	
	//lets invert the array
	public static ArrayList<Integer> invert(ArrayList<Integer> array,int n){

			ArrayList<Integer> left = new ArrayList<Integer>();
			ArrayList<Integer> right = new ArrayList<Integer>();
			for(int i=0;i<n;i++){
			 
				left.add(array.get(i));
			}
			
			Collections.reverse(left);
			
			for(int i=n;i<array.size();i++){
				right.add(array.get(i));
			
			}
			//final b array
			b = new ArrayList<Integer>();
			b.addAll(left);
			b.addAll(right);
	
		return b;		
	}
	
	public static boolean isSorted(ArrayList<Integer> b){
		boolean r=false;
		for(int i=0; i<b.size()-1;i++){
			if(b.get(i)<b.get(i+1)){
				r=true;
			}else{
				r=false;
				break;
			}
		}
		return r;
	}
	
	
	public static void UCS(Node start){
		
		//queue that will contain current nodes
		PriorityQueue<Node> queue = new PriorityQueue<Node>(1000,new NodeComparator()); 
		queue.add(start); //initializing the source
		boolean found = false; // will use this to stop producing new nodes if a sorted node is found
		int queuenum=queue.size()-1;
		while(!queue.isEmpty() && !found){
			Node l = queue.remove(); //returns and removes item in the front
			Node nex = l;
			
			path=new ArrayList<ArrayList<Integer>>(); //path holds current node
			path.add(l.data); //added periexomeno tou current node sto path
			System.out.println("father node is: "+path);
			//int queuenum=queue.size();

			while(nex.level != 0){
				nex=nex.root;
				path.add(nex.data);
				//queuenum++;
			}
			if(isSorted(l.data)){
				found=true;
				System.out.println("Node is sorted.");
				System.out.println();
				Collections.reverse(path);
				System.out.println("path is: "+path);
				System.out.println("With cost= "+l.cost);
				queuenum++;

			}else{
				for(int n=2; n<=array.size(); n++){
					ArrayList<Integer> explored = new ArrayList<Integer>(); //holds the node that is currently being explored
					explored=invert(l.data,n);
					boolean visited=false;
					
					for(int m=0; m<path.size();m++){
						if(explored.equals(path.get(m))){
							visited=true; //will return true if a node has been already explored
							System.out.println(path.get(m)+" node has been already explored ");
							queuenum--;
						}
					}
					queuenum++;
					boolean sorted =isSorted(explored); //checks if current node is sorted
					//producing children if node has not been visited OR it is a solution
					if(!visited || isSorted(explored)){
						Node successor = new Node(explored,l.cost+1,l.level+1); //if node has not been visited before create a child
						successor.root=l;
						queue.offer(successor);
						//queuenum++;

						System.out.println("Node that is being explored is: "+explored);
						//System.out.println("queue size in last if "+queuenum);
						System.out.println("is this sorted? "+sorted);
						System.out.println();
					}

				}
			}
			//queuenum++;
		}
		//queuenum++;
		System.out.println("number of expansions: "+queuenum);
	}
}