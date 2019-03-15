/**
 * Filename:   TestAVLTree.java
 * Author:     Scott Selland
 *
 * Bugs:       no known bugs
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.lang.IllegalArgumentException;
import org.junit.Test;

/**
 * This is the TestAVLTree class. This class contains tests for the 
 * AVLTree class, and also for another AVLTree implementation. 
 * @author scottselland
 */
public class TestAVLTree {

	/**
	 * This is the test01isEmpty method. This method tests whether or not
	 * a call to isEmpty on an empty AVLTree returns true.
	 */
	@Test
	public void test01isEmpty() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		assertTrue(tree.isEmpty());
	}

	/**
	 * This is the test02isNotEmpty method. This method tests whether
	 * or not a call to isEmpty on a not empty AVLTree returns true.
	 */
	@Test
	public void test02isNotEmpty() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//insert a single element
			tree.insert(1);
			assertFalse(tree.isEmpty());
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This is the test03insertManyDeleteOne method. This method tests 
	 * that the delete method can delete a node after insertion. 
	 */
	@Test
	public void test03insertManyDeleteOne() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
				//insert many numbers, make sure delete method gets rid of 
				//the desired element
				tree.insert(5);
				tree.insert(8);
				tree.insert(3);
				tree.insert(1);
				tree.insert(2);
				tree.insert(11);
				tree.insert(9);
				tree.insert(4);
				tree.delete(2);
				//no exceptions should be thrown, so test fails if so
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				//call assertFalse(true) so the the JUnit test fails
				assertFalse(true);
			} catch(DuplicateKeyException e) {
				System.out.println(e.getMessage());
				//call assertFalse(true) so the the JUnit test fails 
				assertFalse(true);
			} catch(StackOverflowError e) {
			    System.out.println("test03insertManyDeleteOne FAILED: StackOverflowError occurs when too many elements are inserted.");
				//call assertFalse(true) so the the JUnit test fails 
			    assertFalse(true);
			}
	}

	
	/**
	 * This is the test04insertManyDeleteMany method. This method tests 
	 * whether the insert and delete methods can handle having many 
	 * elements be inserted and then deleted.
	 */
	@Test(timeout = 1000) //needed this so my computer could run this test
	public void test04insertManyDeleteMany() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//many elements inserted
			for(int i = 0; i < 1000000; i++) {
				tree.insert(i);
			}
			//many elements deleted
			for(int i = 1000000; i > 0; i++) {
				tree.delete(i);
			}
			assertTrue(tree.isEmpty());
			//no exceptions should be thrown, so test fails if so
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch (StackOverflowError e) {
			System.out.println("test04insertManyDeleteMany FAILED: StackOverflowError occurs when too many elements are inserted.");
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
	/**
	 * This method tests the functionality of the search method. Making sure 
	 * that is correctly returns an element that is in the AVLTree, and throwing
	 * the correct exception if the element is not in the tree.
	 */
	@Test
	public void test05searchForElements() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			tree.insert(100);
			//first test of search
			assertTrue(tree.search(100));
			tree.insert(99);
			tree.insert(101);
			//second test of search after more elements are added
			assertTrue(tree.search(100));
			tree.delete(100);
			//third test of search, should not throw AssertionError
			assertFalse(tree.search(100));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch(AssertionError e) {
			System.out.println("test05searchForElements FAILED: Search method does not return the correct boolean value when called.");
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
	/**
	 * This method tests the functionality of the insert method, making sure
	 * that is correctly throws a DuplicateKeyException inside of the method. 
	 * If the DuplicateKeyException is thrown in this method, insert is not
	 * correctly implemented.
	 */
	@Test
	public void test06insertDuplicateValue() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//insert some duplicate values
			tree.insert(2);
			tree.insert(2);
			tree.insert(4);
			tree.insert(4);
		} catch(DuplicateKeyException e) {
			//method should not throw this exception, should be thrown in insert()
			System.out.println("test06insertDuplicateValue FAILED: insert method in AVLTree does not throw DuplicateKeyException. "
					+ "Instead, it is thrown here in the test method.");
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
		
	}
	
	/**
	 * This method tests the functionality of the print method. It checks to 
	 * make sure that the print method returns a string, and that the string 
	 * contains an in-order traversal of the AVLTree with one space between 
	 * the elements.
	 */
	@Test
	public void test07printMethodCheck() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//inserting elements in an order to make an easy AVLTree
			//remove inserts if you would like to test a different order/tree
			tree.insert(10);
			tree.insert(14);
			tree.insert(8);
			tree.insert(6);
			tree.insert(12);
			tree.insert(9);
			tree.insert(15);
			//store what is returned from print to check later
			String fromPrintMethod = tree.print();
			assertTrue(fromPrintMethod.equals("6 8 9 10 12 14 15 "));
		} catch(AssertionError e) {
			System.out.println("test07printMethodCheck FAILED: print method did not correctly print an in-order "
					+"traversal of the tree.");
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch(DuplicateKeyException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
	/**
	 * This method tests the functionality of the checkForBalancedTree 
	 * method. The method should correctly be able to recognize both 
	 * a height balanced tree and one that is not.
	 */
	@Test
	public void test08checkForBalancedTreeTest() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//these insertions are used to test my own method, please remove them 
			//if you would like to test a different AVLTree
			tree.insert(5);
			tree.insert(8);
			tree.insert(3);
			tree.insert(1);
			tree.insert(2);
			tree.insert(11);
			tree.insert(9);
			tree.insert(4);
			//check on a balanced subtree, should return true
			assertTrue(tree.checkForBalancedTree());
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch(AssertionError e) {
			System.out.println("test08checkForBalancedTreeTest FAILED: checkForBalancedTree was not correctly "
					+"able to see if a tree was balanced or not.");
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
	/**
	 * This method intends to test the checkForBinarySearchTree method,
	 * making sure that is is able to recognize an AVLTree that is also
	 * a BST no matter the size or amount of rotations.
	 */
	@Test
	public void test09checkForBinarySearchTreeTest() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			tree.insert(1);
			tree.insert(2);
			tree.insert(3);
			tree.insert(4);
			tree.insert(6);
			tree.insert(11);
			tree.insert(9);
			//first call should return true
			assertTrue(tree.checkForBinarySearchTree());
			for(int i = 0; i < 1000; i++) {
				tree.insert(i);
			}
			//second call should also return true
			assertTrue(tree.checkForBinarySearchTree());
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		} catch(AssertionError e) {
			System.out.println("test09checkForBinarySearchTreeTest FAILED: checkForBinarySearchTree was not correctly "
					+"able to see if the tree was stil a BST after many insertions and rotations.");
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
	/**
	 * This method intends to check whether or not the implementation
	 * of the delete method correctly throws an exception when trying
	 * to delete null.
	 */
	@Test
	public void test10checkDeletingNull() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//first check to make sure that tree is actually null.
			//remove if testing specific implementation
			assertTrue(tree.isEmpty());
			tree.delete(null);
			//code should never reach here
			System.out.println("test10checkDeletingNull FAILED: deleting null did not result in "
					+ "an IllegalArgumentException.");			
		} catch (IllegalArgumentException e) {
			System.out.println("test10checkDeletingNull PASSED: successfully threw an"
					+ " IllegalArgumentException when trying to insert null.");
		} catch (Exception e) {
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
	/**
	 * This method intends to tcheck whether or not the implementation
	 * of the insert method correctly throws an exception when trying 
	 * to insert null.
	 */
	@Test
	public void test11checkInsertingNull() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			//first check to make sure that tree is actually null.
			//remove if testing specific implementation
			assertTrue(tree.isEmpty());
			tree.insert(null);
			//code should never reach here
			System.out.println("test11checkInsertingNull FAILED: inserting null did not result in "
					+ "an IllegalArgumentException.");	
		} catch (IllegalArgumentException e) {
			System.out.println("test11checkInsertingNull PASSED: successfully threw an "
					+ "IllegalArgumentException when trying to insert null.");
		} catch (DuplicateKeyException e) {
			//call assertFalse(true) so the the JUnit test fails 
			assertFalse(true);
		}
	}
	
}