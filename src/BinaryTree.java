
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
		print(" ",root);
	}
	
	private void print(String space, Node n){
		if(n==null) return;
		System.out.println(space+n.data);
		print(space+space,n.left);
		print(space+space,n.right);
		
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
	}
	
	
	public static void main(String[] args) {
		BinaryTree t = new BinaryTree();
		t.insert(100);
		t.insert(80);
		t.insert(120);
		t.insert(60);
		t.insert(90);
		t.print();
	}
}
