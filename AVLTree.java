/**
 * Filename:   AVLTree.java
 * Author:    Scott Selland
 *
 * Bugs:       no known bugs
 */

import java.lang.IllegalArgumentException;

/** This is the AVLTree class. This class creates an AVLTree
 * that sorts elements based on their keys. 
 * @param <K> generic type for elements to be inserted
 */
public class AVLTree<K extends Comparable<K>> implements AVLTreeADT<K> {
	/* fields */
	private BSTNode<K> root; //this is the root element (top level element in AVLTree)
	private int heightOfTree; //this is the height of the tree (used in insert method?)
	private int currentHeightOfSubtree; //this is the reference to the current height in the insert method
	private BSTNode <K> g; //this is the grandparent node pointer for the checkHeightImbalance method
	private BSTNode <K> p; //this is the parent node pointer for the checkHeightImbalance method
	private BSTNode <K> c; //this is the child node pointer for the checkHeightImbalance method
	private BSTNode <K> newlyInserted; //this is a reference to the most recently inserted node
	private BSTNode <K> toBePrinted; //separate pointer at the root for print method
	
	/** This is the BSTNode class. This class represents the elements
	 * that will be inserted into the AVLTree. 
	 * @param <K> generic type for elements to be inserted
	 */
	class BSTNode<K> {
		/* fields */
		private K key;	//this is the type of element that is being used in the tree
		private int height;	//height of the node in the tree
		private BSTNode<K> left, right;	//references to each node's left and right subtree
		
		/**
		 * This is the constructor for a BSTNode. A BSTNode is able to be
		 * put into an AVLTree due to its properties containing data about 
		 * it's left and right child.
		 * @param key nodes are inserted in an order based on their key
		 */
		BSTNode(K key) {
			this.key = key;
			height = 0;
			right = null;
			left = null;
		}
		
		/**
		 * This is the setHeight() mutator method. This method is used
		 * to set the height of a node based on its subtree in the AVLTree.
		 * @param heightOfLeaf height to be given to node
		 */
		private void setHeight(int heightOfLeaf) {
			this.height = heightOfLeaf;
		}
		
		/**
		 * This is the getLeft() accessor method. This method is used 
		 * to get the left child of a node in the AVLTree.
		 * @return the left child of the node that this method is called on
		 */
		private BSTNode<K> getLeft() {
			return this.left;
		}
		
		/**
		 * This is the getRight() accessor method. This method is used 
		 * to get the right child of a node in the AVLTree.
		 * @return the right child of the node that this method is called on
		 */
		private BSTNode<K> getRight() {
			return this.right;
		}
		
		/**
		 * This is the setRight() mutator method. This method is used 
		 * to set the right child of the node the method is called on 
		 * in the AVLTree.
		 * @param node node to be given right child
		 */
		private void setRight(BSTNode<K> node) {
			right = node;
		}
		
		/**
		 * This is the setLeft() method. This method is used 
		 * to set the left child of the node the method is called on 
		 * in the AVLTree.
		 * @param node node to be given left child
		 */
		private void setLeft(BSTNode<K> node) {
			left = node;
		}
		
		/**
		 * This is the getKey() method. This method is used
		 * to get the key of the node the method is called on
		 * in the AVLTree.
		 * @return the key of the node this method is called on
		 */
		private K getKey() {
			return this.key;
		}
		
	}
	
	public static void main (String[] args) throws IllegalArgumentException, DuplicateKeyException {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
	}
	
	private void setRoot(BSTNode<K> node) {
		root = node;
	}
	/**
	 * This is the isEmpty method. This method checks the AVLTree
	 * for a root node and returns a boolean based on whether or not
	 * there is a root.
	 * @return boolean whether root element is there
	 */
	@Override
	public boolean isEmpty() {
		//root node will tell if there is a tree or not
		if(root == null) {
			return true;
		}
		return false;
	}

	/**
	 * This is the public insert method. This method calls the private insert
	 * helper method which inserts the element correctly through recursion.
	 * @throws DuplicateKeyException if duplicate node is inserted
	 * @throws IllegalArgumentException if node is null value
	 * @param key element's key to be inserted in AVLTree
	 */
	@Override
	public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
			currentHeightOfSubtree = 1; //set to 1 at the start of insertion
		try {
			root = insert(root, key);
		} catch(IllegalArgumentException e) {
			System.out.println("Failed to insert element because it is null.");
		}
	}
	/**
	 * This is the private insert method that acts as a helper for the public
	 * insert method. This method correctly inserts an element
	 * based on it's key through recursion. The method then goes back through the inserted 
	 * element's subtree and checks for height imbalance.
	 * @param n
	 * @param key
	 * @return
	 * @throws DuplicateKeyException
	 */
	private BSTNode<K> insert(BSTNode<K> n, K key) throws DuplicateKeyException {
		try {
			//check for first null reference
			if (n == null) {
				BSTNode<K> newNode = new BSTNode<K>(key);
				newNode.setHeight(currentHeightOfSubtree);
				newlyInserted = newNode;
		        return newNode;
		    } 
			//check for duplicate
		    if (n.getKey().equals(key)) {
		        throw new DuplicateKeyException();
		    }
		    if (key.compareTo(n.getKey()) < 0) {
		        // add key to the left subtree
		        n.setLeft(insert(n.getLeft(), key));
		        //recursion back to get height of each node
		        currentHeightOfSubtree++;
		        n.setHeight(currentHeightOfSubtree);
		        root = checkHeightImbalance(n);
		        return n;
		    }
		    else {
		        // add key to the right subtree
		        n.setRight(insert(n.getRight(), key));
		        //recursion back to get height of each node
		        currentHeightOfSubtree++;
	    			n.setHeight(currentHeightOfSubtree); 
	    			root = checkHeightImbalance(n);
		        return n;
		    }
		} catch (DuplicateKeyException e) {
			System.out.println("Failed to insert element "+key+" because it is a duplicate value.");
			return n;
		}
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public void delete(K key) throws IllegalArgumentException {
		// TODO: implement delete()
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean search(K key) throws IllegalArgumentException {
		// TODO: implement search()
		return false;
	}

	/**
	 * This is the print method. This method prints an in-order traversal 
	 * of all the nodes in the AVLTree.
	 * @return a string of the keys in the AVLTree in order
	 */
	@Override
	public String print() {		
		String inOrder = ""; //string to be returned after recursion
		toBePrinted = root;
		if(toBePrinted == null) {
			return "";
		}
		//System.out.println(root.getKey()+" hiiiii");
		print(toBePrinted.getLeft());
		inOrder += toBePrinted.getKey() +" ";
		print(toBePrinted.getRight());
		return inOrder;
	}
	/**
	 * This is the print helper method. This method recursively calls
	 * itself in order to correctly save the in-order traversal of the
	 * AVLTree in a string.
	 * @param node root node to start at
	 * @return a string of the keys in the AVLTree in order
	 */
	private String print(BSTNode<K> node) {
		String inOrder = ""; //string to be returned after recursion
		if(node == null) {
			return "";
		}
		print(node.getLeft());
		inOrder += node.getKey() +" ";
		print(node.getRight());
		return inOrder;
	}
	
	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean checkForBalancedTree() {
		// TODO: implement checkForBalancedTree()
		return false;
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean checkForBinarySearchTree() {
		return checkForBinarySearchTree(root);
	}
	
	private boolean checkForBinarySearchTree(BSTNode <K> n) {
		if(root == null) {
			return true;
		}
		return false;
	}
	/**
	 * This is the checkHeightImbalance method. This method is called when 
	 * the insert method is recursively checking/setting heights of each 
	 * node. It checks a node's left and right subtree for any imbalance, 
	 * and figures out what kind of rotations are necessary to fix it. 
	 * NOTE: This method is incomplete - it does not accurate track the root
	 * @param node to be checked for height imbalance
	 */
	private BSTNode <K> checkHeightImbalance(BSTNode <K> n) {
		//check the subtree's left and right heights
		if((getHeight(n.getLeft()) - getHeight(n.getRight())) > 1) {
			//if newlyInserted key is less than the middle node, it is in the middle node's 
			//left subtree
			if(newlyInserted.getKey().compareTo(n.getLeft().getKey()) < 0) {
				root = rightRotate(n);
			}
			//if newlyInserted key is > than the middle node, it is in the right subtree 
			//and we need two rotations
			else {
				root = leftRotate(n.getLeft());
				root = rightRotate(n);
			}
		}
		else if((getHeight(n.getLeft()) - getHeight(n.getRight())) < -1) {
			//if newlyInserted key is > than the middle node, it is in the right subtree
			if(newlyInserted.getKey().compareTo(n.getRight().getKey()) > 0) {
				root = leftRotate(n);
			}
			//if newlyInserted key is < than the middle node, it is in the left subtree
			//and we need two rotations
			else {
				root = rightRotate(n.getRight());
				root = leftRotate(n);
			}
		}
		return root;
	}
	/**
	 * This is the getHeight() accessor method. This method is used
	 * to get the height of a node based on its subtree in the AVLTree.
	 * @return the height of the node this method is called on
	 */
	private int getHeight(AVLTree<K>.BSTNode<K> node) {
			if(node == null) {
				return 0;
			}
			else {
				return node.height;
			}
	}
	/**
	 * This is the rightRotate method. This method is able to correctly rotate
	 * nodes to the right based on their height imbalance in order to correctly 
	 * configure them in the AVLTree.
	 * @param node that is height imbalanced
	 */
	private BSTNode <K> rightRotate(BSTNode<K> node) {
		//set up hierarchy
		g = node;
		p = node.getLeft();
		//set parent nodes right to grandparent node
		node.getLeft().setRight(node);
		if(node == root) {	
			setRoot(node.getLeft());
		}
		//set grandparents left node to parents right
		node.setLeft(p.getRight());
		return root;
	}
	
	/**
	 * This is the leftRotate method. This method is able to correctly rotate
	 * nodes to the left based on their height imbalance in order to correctly 
	 * configure them in the AVLTree.
	 * @param node that is height imbalanced
	 */
	private BSTNode <K> leftRotate(BSTNode<K> node) {
		//set up hierarchy
		g = node;
		p = node.getRight();

		//set parent nodes left to grandparent node
		node.getRight().setLeft(node);
		
		//change the root reference if necessary

		//set grandparents right node to parents left
		node.setRight(p.getLeft());
		return root;
	}
}