/**
 * Filename:   AVLTreeADT.java
 * Author:     Scott Selland
 *
 * Bugs:       no known bugs
 */

import java.lang.IllegalArgumentException;

public interface AVLTreeADT<K extends Comparable<K>> {

	/**
	 * Checks for an empty AVL tree.
	 * @return true if AVL tree contains 0 items
	 */
	public boolean isEmpty();

	/**
	 * Adds key to the AVL tree
	 * @param key
	 * @throws DuplicateKeyException if key is already in the AVL tree
	 * @throws IllegalArgumentException if null value inserted
	 */
	public void insert(K key) throws DuplicateKeyException, IllegalArgumentException;
	
	/**
	 * Deletes key from the AVL tree
	 * @param key
	 * @throws IllegalArgumentException if try to delete null
	 */
	public void delete(K key) throws IllegalArgumentException;
	
	/**
	 * Search for a key in AVL tree
	 * @param key
	 * @return true if AVL tree contains that key
	 * @throws IllegalArgumentException if searching for a null value
	 */
	public boolean search(K key) throws IllegalArgumentException;
	
	/**
	 * Performs in-order traversal of AVL Tree
	 * @return a String with all the keys, in order, with exactly one space between keys
	 */
	public String print();
	
	/**
	 * Checks for the Balanced Search Tree.
	 * @return true if AVL tree is balanced tree
	 */
	public boolean checkForBalancedTree();
	
	/**
	 * Checks for Binary Search Tree.
	 * @return true if AVL tree is binary search tree.
	 */
	public boolean checkForBinarySearchTree();
}