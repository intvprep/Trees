import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
	
	private Node root;
	
	public void binaryTree(){
		root = null;
	}
	
	public boolean lookup(int data){
		
		return lookup(root,data);
	}
	
	public boolean lookup(Node node, int data) {		
		if(node==null)return false;
		
		if(data == node.data) 
			return true;
		else if(data < node.data){
			return lookup(node.left, data);
		}else{
			return lookup(node.right, data);
		}
	}
	
	public void insert(int data){
		root = insert(root,data);
	}
		
	private Node insert(Node node, int data) {
		if(node == null){
			node = new Node(data);
			return node;
		}
		if(data<=node.data){
			node.left = insert(node.left, data);
		}else{
			node.right = insert(node.right, data);
		}
		return node;
	}	
	
	public void print(){
		print("",root);
	}
	
	//total number of elements present in tree.
	public int size(){
		return size(root);
	}
	
	//
	private int size(Node node) {
		if(node == null){
			return 0;
		}else{
			return size(node.left)+1+size(node.right);
		}		
	}

	public int maxDepth(){
		return depth(root);
	}
	
	public int height(){
		return maxDepth();
	}
	
	
	private int depth(Node node) {
		if(node==null){ 
			return 0;
		}else{
			int lDepth = depth(node.left);
			int rDepth = depth(node.right);
			return Math.max(lDepth, rDepth)+1;
		}
		
	}

	public int minValue(){
		return minValue(root);
	}
	
	private int minValue(Node node) {
		Node ptr = node;
		while(ptr.left!=null){
			ptr = ptr.left;
		}
		return ptr.data;
	}
	
	public int maxValue(){
		return maxvalue(root);
	}

	private int maxvalue(Node node) {
		Node ptr = node;
		while(ptr.right != null){
			ptr = ptr.right;
		}
		return ptr.data;
	}

	private void print(String space, Node n){
		if(n==null) return;  
		System.out.println(space+n.data);
		print(space+"  ",n.left);
		print(space+"  ",n.right);
		
	}

	public void printPostOrder(){
		postOrder(root);
	}

	private void postOrder(Node node){
		if(node==null) return;
		postOrder(node.left);
		postOrder(node.right);

		System.out.println(node.data);
	}

	public void printInOrder(){
		//inOrder(root);
		inOrderWithStack(root);
	}

	public void inOrder(Node node){
		if(node==null) return;
		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}
	
	private void inOrderWithStack(Node node){
		Stack<Node> stack = new Stack<>();		
		while(node!=null){
			stack.push(node);
			node = node.left;			
		}		
		while(!stack.isEmpty()){
			node = stack.pop();
			System.out.print(node.data+" ");
			if(node.right!=null){
				node = node.right;
				while(node!=null){
					stack.push(node);
					node = node.left;
				}
			}
		}
		
		
	}

	public void printPreOrder(){
		preOrder(root); 
	}

	public void preOrder(Node node){
		if(node==null)return;
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
//	100
//	  70
//	    50
//	    80
//	      75
//	      90
//	  120
//	    110
//	    140	
//	100, 70, 120, 50, 80, 110, 140, 75, 90	
	public void printBreadthFirst(){
		System.out.println("Breadth first traversal");
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()){
			Node node = q.poll();
			if(node!=null){
				System.out.print(node.data);System.out.print(" ");
				q.offer(node.left);
				q.offer(node.right);
			}
		}
		System.out.println();
	}
	
	public void printLevelOrder(){
		System.out.println("print level order");
		int h  = height();
		for(int i=1; i<=h;i++)
			printGivenLevel(root, i);
		System.out.println("");
	}
	
	private void printGivenLevel(Node node, int level){
		if(node == null) return;
		if(level ==1 )//root
			System.out.print(node.data+" ");
		else if(level >1){
			printGivenLevel(node.left, level-1);
			printGivenLevel(node.right, level-1);
		}
	}

	public boolean hasPath(int sum){
		return hasPath(root,sum);
	}

	private boolean hasPath(Node node, int sum){
		if(node == null) return(sum==0);

		return (hasPath(node.left,sum-node.data) || hasPath(node.right,sum-node.data));

	}

	public void printPaths() { 
	  int[] path = new int[1000]; 
	  printPaths(root, path, 0); 
	}
	private void printPaths(Node node, int[] path, int pathLen) { 
	  if (node==null) return;

	  // append this node to the path array 
	  path[pathLen] = node.data; 
	  pathLen++;

	  // it's a leaf, so print the path that led to here 
	  if (node.left==null && node.right==null) { 
	    printArray(path, pathLen); 
	  } 
	  else { 
	  // otherwise try both subtrees 
	    printPaths(node.left, path, pathLen); 
	    printPaths(node.right, path, pathLen); 
	  } 
	}
	
	private void printArray(int[] ints, int len) { 
	  int i; 
	  for (i=0; i<len; i++) { 
	   System.out.print(ints[i] + " "); 
	  } 
	  System.out.println(); 
	}
	
	public Node deleteNode(int data){
		return delete(root,data);
	}
	
	
	
	private Node delete(Node node, int data) {
		if(node == null) return null;
		
		if(data<node.data)
			node.left = delete(node.left, data);
		else if(data>node.data)
			node.right = delete(node.right,data);
		else{
			if(node.left == null)return node.right;
			if(node.right==null)return node.left;
			
			node.data = minValue(node.right);
			
			node.right = delete(node.right,node.data);
		}
		return node;
	}

	private static class Node{
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
			left = null;
			right = null;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return Integer.toString(data);
		}
	}
	
	
	public static void main(String[] args) {
		BinaryTree t = new BinaryTree();
		t.insert(100);
		t.insert(70);
		t.insert(120);
		t.insert(50);
		t.insert(80);
		t.insert(110);
		t.insert(140);
		t.insert(75);
		t.insert(90);
		t.print();
		System.out.println("Minimum vaule in tree: "+t.minValue());
		t.printBreadthFirst();//100, 70, 120, 50, 80, 110, 140, 75, 90
		t.printLevelOrder();
		//t.deleteNode(100);
		//System.out.println("After deleting 100");
		t.print();

		System.out.println("Max Depth: "+t.maxDepth());
		System.out.println("Depth of 60 "+t.depth(new Node(60)));
		System.out.println("Size : "+t.size());
		System.out.println("Min value : "+t.minValue());
		System.out.println("Max value : "+t.maxValue());		
		System.out.println("Post Order Traversal");
		t.printPostOrder();
		System.out.println("In Order Traversal");
		t.printInOrder();
		System.out.println("Pre Order Traversal");
		t.printPreOrder();
		System.out.println(t.hasPath(220));
		System.out.println("Print paths");
		t.printPaths();


	}
}
