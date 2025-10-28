import java.util.Iterator;
import java.util.NoSuchElementException;

//Recommended Development Order:
//Part 1: addNew() and toString().
//Part 2: getLetterCount(), increaseLetterCount(), decreaseLetterCount().
//Part 3: iterator().

/**
 * This class represents an AVL tree to support a character-counting algorithm, has a GUI.
 * This AVL is a form of map that stores characters as the keys and counts as the values associated with the keys.
 * And performs rotation to maintain the balance of the tree.
 * 
 * @author Mohammed Abdur Rahman.
 */
public class ThreeTenBinarySearchTree implements Iterable<ThreeTenSetNode> 
{
	/**
	 * You must use this root variable for the tree and you may not alter this variable's name, type, etc.
	 */
	private ThreeTenSetNode root;
	
	/**
	 * You may not alter this variable's name, type, etc.
	 */
	private int size = 0;
	
	//------------------------------------------------
	// Add any PRIVATE instance variables you want here!
	//------------------------------------------------

	//------------------------------------------------
	// The node class is NOT an inner class this time.
	// It's in ThreeTenSetNode.java.
	//------------------------------------------------

	//------------------------------------------------
	// Additional required methods for this project
	//------------------------------------------------
	
	/**
	 * Default constructor initializing the root to null and size to 0.
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenBinarySearchTree() 
	{
		// Requirement: O(1).
		this.root = null;
		this.size = 0;
	}
	
	/**
	 * This method checks the balance factor of a given node.
	 * @param node whose balance needs to be checked.
	 * @return -1, 0, 1 when balanced or some other numbers when imbalanced.
	 */
	private int checkBalanceFactor(ThreeTenSetNode node) 
	{
		// If the node is null, return -1.
		if (node == null) {
			return -1;
		}

		// Get left subtree of the node.
		ThreeTenSetNode leftSubTree = node.getLeft();
		// Get right subtree of the node.
		ThreeTenSetNode rightSubTree = node.getRight();
		// Calculate the balance factor and return the result: bf = (left - right).
		return nodeHeight(leftSubTree) - nodeHeight(rightSubTree);
	}

	/**
	 * This is the recursive method used in the addNew() method to recursively add nodes into the tree with the given letter in it.
	 * This method also updates the height, checks the balance factor of the currentNode and performs applicable rotation based on the situation.
	 * 
	 * @param currentNode is the node who's children (left and right) are being checked.
	 * @param letter is the character to be inserted.
	 * @return true after successful insertion and rotation.
	 */
	private ThreeTenSetNode addNewRecursively(ThreeTenSetNode currentNode, Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// if the node is null, increase the size and then return node with the given letter and initial count of 1.
		if (currentNode == null) {
			size++;
			return new ThreeTenSetNode(letter, 1);
		}

		// This variable is currentNode's letter.
		Character currNodeLetter = currentNode.getLetter();

		// Return false if duplicates found. NOTE: B: 66, A: 65. So, 66-65=1, 65-66=-1.
		if (letter.compareTo(currNodeLetter) == 0) { 
			return currentNode; 
		}

		// If no duplicate found, execute this block.
		// If the letter to be inserted is bigger than current node's letter, it goes to the right side of the tree.
		if (letter.compareTo(currNodeLetter) > 0) 
		{
			// Base case (when to stop): Reached the end of the tree.
			if (currentNode.getRight() == null) {
				// Create a new node with the letter in it with initial count 1.
				ThreeTenSetNode newNode = new ThreeTenSetNode(letter, 1);
				// Link the new node as the right child of the current node.
				currentNode.setRight(newNode);
				// New node added to the tree, so increase the size by 1.
				size++;
			} else {
				// Keep walking down three until a null is found.
				currentNode.setRight( addNewRecursively(currentNode.getRight(), letter) );
			}
		}

		// If the letter to be inserted is smaller than current node's letter, it goes to the left side of the tree.
		else if (letter.compareTo(currNodeLetter) < 0) 
		{
			// Base case (when to stop): Reached the end of the tree.
			if (currentNode.getLeft() == null) {
				// Create a new node with the letter in it with initial count 1.
				ThreeTenSetNode newNode = new ThreeTenSetNode(letter, 1);
				// Link the new node as the left child of the current node.
				currentNode.setLeft(newNode);
				// New node added to the tree, so increase the size by 1.
				size++;
			} else {
				// Keep walking down the tree until a null is found.
				currentNode.setLeft( addNewRecursively(currentNode.getLeft(), letter) );
			}
		}

		// Update height.
		updateHeight(currentNode);

		// check and store the bf -> balance factor.
		int bf = checkBalanceFactor(currentNode);

		// Situation: RL or RR.
		if (bf < -1) 
		{

			Character rightChildLetter = currentNode.getRight().getLetter();
			// Situation: right-left.
			if (letter.compareTo(rightChildLetter) < 0) {
				ThreeTenSetNode rotatedRightChild = rotateRight(currentNode.getRight());
				currentNode.setRight(rotatedRightChild);
			}
			// Situation: right-right. 
			return rotateLeft(currentNode);
		}

		// Situation: LR or LL
		if (bf > 1) 
		{
			Character nodeLeftChildLetter = currentNode.getLeft().getLetter();
			// Situation: left-right.
			if (letter.compareTo(nodeLeftChildLetter) > 0) {
				ThreeTenSetNode rotatedLeftChild = rotateLeft(currentNode.getLeft());
				currentNode.setLeft(rotatedLeftChild);
			}
			// Situation: left-left.
			return rotateRight(currentNode);
		}
		return currentNode;
	}

	/**
	 * Adds a new character if the set does not already contain that character.
	 * Requirement: You are required to solve this problem recursively unless you are setting the root node. 
	 * This means either this method needs to be recursive or a helper method is required to be.
	 * @param letter the letter to be inserted into the tree.
	 * @return true after successful insertion.
	 * @throws NullPointerException if the character to be added is null.
	 */
	public boolean addNew(Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// Letter to be inserted cannot be null.
		if (letter == null) {
			throw new NullPointerException("You cannot add a null as a character. Please try again with a valid character.");
		}

		// Store size before adding to check if a new node got added or not.
		int oldSize = size;

		// Insert new character and (rebalance -> done in helper function).
		root = addNewRecursively(root, letter);

		// Return true if a new node was added or false if a new node was not added.
		return (size > oldSize) ? true : false;
    }

	/**
	 * This method represents in-order traversal (walk) of the tree.
	 * 
	 * @param sb is the StringBuilder.
	 * @param n is the current node.
	 */
	private void toString(StringBuilder sb, ThreeTenSetNode n) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE
		
		// Base case (when to stop): If the node is empty, stop, return nothing.
		if (n == null) {
			return;
		}

		// The first recursive case should go left.
		toString(sb, n.getLeft());

		// Process the node in-order: adding the node's string followed by a newline to the StringBuilder.
		String nodeString = n.toString();
		sb.append(nodeString).append('\n');

		// The second recursive case should go right.
		toString(sb, n.getRight());
	}

	/**
	 * The toString for this data structure is an in-order walk of the tree.
	 * This method is done for you, but you need to write the recursive helper method.
	 * NOTE: In-order traversal is also sorted, so the letters will be in sorted order.
	 * @return the string in the right format: 'letter': count. 
	 */
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		toString(sb, root);
		return sb.toString().trim();
	}

	/**
	 * This method recursively searches the binary tree to find the given letter and return the letter's total count throughout the tree.
	 * 
	 * @param currentNode is the node that is being checked.
	 * @param letter is the character that is being searched in the current node.
	 * @return a number representing the letter count.
	 * @throws NoSuchElementException if current node is null, meaning you reached the end of the tree.
	 */
	private int getLetterCountRecursively(ThreeTenSetNode currentNode, Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// Base case (when to stop): when current node is null.
		if (currentNode == null) {
			throw new NoSuchElementException("Reached the end of the tree. The letter you are searching for wasn't found.");
		}

		// Base case (when to stop): when a match is found.
		// Get the current node's letter.
		Character currentNodeLetter = currentNode.getLetter();

		// Keep going.
		// if the given letter is the same as current node's letter, return the current node's count.
		if (letter.compareTo(currentNodeLetter) == 0) {
			return currentNode.getCount();
		}

		// If the given letter is bigger than the current node's letter, go to it's right node, compare given letter and that right node's letter, look for a match, and keep going until currentNode is null.
		if (letter.compareTo(currentNodeLetter) > 0) {
			return getLetterCountRecursively(currentNode.getRight(), letter);
		}

		// if (letter.compareTo(currentNodeLetter) < 0).
		// If the given letter is smaller than the current node's letter, go to it's left node, compare given letter and that left node's letter, look for a match, and keep going until currentNode is null.
		else {
			return getLetterCountRecursively(currentNode.getLeft(), letter);
		}
	}

	/**
	 * Given a letter, get it's count. If the letter doesn't exist in the tree, throw a NoSuchElementException.
	 * You are required to solve this problem recursively unless you are setting the root node.
	 * This means either this method needs to be recursive or a helper method is required to be.
	 * 
	 * @param letter to be searched.
	 * @return number representing letter count.
	 * @throws NullPointerException if the letter being searched is null.
	 */
	public int getLetterCount(Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// Letter to be searched cannot be null.
		if (letter == null) {
			throw new NullPointerException("'Null' as characters doesn't count.");
		}

		// Requirement: O(n) worst case, O(lg n) average case, O(lg n) average case.
		return getLetterCountRecursively(root, letter);
    }
	
	/**
	 * This private helper method recursively searches for the letter in each node, updates the count if match is found, and keep searching until reaches the end of the tree + the letter not found.
	 * @param currentNode is the node that is being checked.
	 * @param letter is the character that is being searched.
	 * @return number representing total count.
	 * @throws NoSuchElementException is if the end of the tree is reached and if the element was being wasn't found.
	 */
	private int increaseLetterCountRecursively(ThreeTenSetNode currentNode, Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// Base case (when to stop): when current node is null.
		if (currentNode == null) {
			// If the letter doesn't exist in the tree, throw a NoSuchElementException.
			throw new NoSuchElementException("Reached the end of the tree. The letter you are searching for wasn't found.");
		}

		// Another base case (when to stop): when a match is found, increase the count by 1, and return the latest count.
		Character currentNodeLetter = currentNode.getLetter();

		if (letter.compareTo(currentNodeLetter) == 0) 
		{
			// It is the current node's letter count.
			int currentCount = currentNode.getCount();

			// Increase the current count by 1 because a match is found.
			currentNode.setCount(currentCount+1);

			// return the latest count.
			return currentCount;
		}

		// Keep going.
		// If the given letter is bigger than the current node's letter, then keep going right, see if there is a match, stop if not found and hit null.
		if (letter.compareTo(currentNodeLetter) > 0) {
			return increaseLetterCountRecursively(currentNode.getRight(), letter);
		}

		// if (letter.compareTo(currentNodeLetter) < 0).
		// If the given letter is smaller than the current node's letter, then keep going left, see if there is a match, stop if not found and hit null.
		else {
			return increaseLetterCountRecursively(currentNode.getLeft(), letter);
		}
	}

	/**
	 * You are required to solve this problem recursively unless you are setting the root node.
	 * This means either this method needs to be recursive or a helper method is required to be.
	 * 
	 * @param letter is the character to be searched.
	 * @return number representing the total count for the letter that is being searched.
	 * @throws NullPointerException if the give letter is null.
	 */
	public int increaseLetterCount(Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// Handle null character: cannot search a null character.
		if (letter == null) {
			throw new NullPointerException("Null as a character cannot be searched. Please enter a valid character.");
		}

		// Use the private recursive method.
		return increaseLetterCountRecursively(root, letter);
    }
	
	/**
	 * This private helper method recursively searches for the letter in each node, updates (decrements) the count if match is found, and keep searching until reaches the end of the tree + the letter not found.
	 * @param currentNode is the node that is being checked.
	 * @param letter is the character that is being searched.
	 * @return number representing total count after decrementing.
	 * @throws NoSuchElementException when reached the end of the tree and the letter was being searched wasn't found.
	 */
	private int decreaseLetterCountRecursively(ThreeTenSetNode currentNode, Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		// Base case (when to stop): when current node is null.
		if (currentNode == null) {
			// If the letter doesn't exist in the tree, throw a NoSuchElementException.
			throw new NoSuchElementException("Reached the end of the tree. The letter you are searching for wasn't found.");
		}

		// Another base case (when to stop): when a match is found, decrease the count by 1, and return the latest count.
		Character currentNodeLetter = currentNode.getLetter();

		if (letter.compareTo(currentNodeLetter) == 0) 
		{
			// It is the current node's letter count.
			int currentCount = currentNode.getCount();

			// Decrease the current count by 1 because a match is found.
			currentNode.setCount(currentCount-1);

			// return the latest count after decrementing.
			return currentCount;
		}

		// Keep going.
		// If the given letter is bigger than the current node's letter, then keep going right, see if there is a match, stop if not found and hit null.
		if (letter.compareTo(currentNodeLetter) > 0) {
			return decreaseLetterCountRecursively(currentNode.getRight(), letter);
		}

		// if (letter.compareTo(currentNodeLetter) < 0).
		// If the given letter is smaller than the current node's letter, then keep going left, see if there is a match, stop if not found and hit null.
		else {
			return decreaseLetterCountRecursively(currentNode.getLeft(), letter);
		}
	}

	/**
	 * You are required to solve this problem recursively unless you are setting the root node.
	 * This means either this method needs to be recursive or a helper method is required to be.
	 * 
	 * @param letter is the character to be searched.
	 * @return number representing the total count for the letter that is being searched.
	 * @throws NullPointerException if the give letter is null.
	 */
	public int decreaseLetterCount(Character letter) 
	{
		// This method is required to be recursive.
		// The next line triggers some things in the automatic tester.
		RecursionCheck.logRecursion(); //DO NOT REMOVE THIS LINE

		if (letter == null) {
			throw new NullPointerException("Null as a character cannot be searched. Please enter a valid character.");
		}
		return decreaseLetterCountRecursively(root, letter);
    }

	/**
	 * This is a private helper method that gets the height of a given node. The node can be null or non-null.
	 * 
	 * @param node is the node whose height is being requested.
	 * @return the height of the given node, node can be null or non-null.
	 */
	private int nodeHeight(ThreeTenSetNode node) 
	{
		// if node is null, return -1, else return the node's height.
		return (node == null) ? -1 : node.getHeight();
	}

	/**
	 * This is a private helper method that updates the height based on the calculated tallest subtree.
	 * 
	 * @param node is node whose height is being updated.
	 */
	private void updateHeight(ThreeTenSetNode node) 
	{
		// Get the right node's height.
		int rightNodeHeight = nodeHeight(node.getRight());
		// Get the left node's height.
		int leftNodeHeight = nodeHeight(node.getLeft());
		// update the height by adding 1 to the the tallest subtree.
		node.setHeight( Math.max(rightNodeHeight, leftNodeHeight) + 1);
	}

	/**
	 * This method performs right rotation in the left-left situation, making the tree self-balancing/AVL. 
	 * (e.g. if you add 3, 2, 1 -> we would need to do a rotation at the node with 3, and return the node with 2).
	 * @param node is the node that is unbalanced and needs right rotation.
	 * @return new root resulted from the right rotation.
	 */
	public ThreeTenSetNode rotateRight(ThreeTenSetNode node) 
	{
		// NOTE: left-left situation, do right rotation.
		
		// If the node is null, nothing to rotate. If the left child is null, then LL situation doesn't happen.
		if (node == null || node.getLeft() == null) {
			return node;
		}

		// Tree is unbalanced at the node.
		ThreeTenSetNode unbalancedNode = node;

		// Node's left child.
		ThreeTenSetNode leftChild = unbalancedNode.getLeft();

		// Step 1: Left child's right kid.
		ThreeTenSetNode leftChildRightKid = leftChild.getRight();

		// Step 1.5: Set the left child's right kid as the left kid of the node.
		unbalancedNode.setLeft(leftChildRightKid);

		// Step 2: Set the node as the right child of the left child.
		leftChild.setRight(unbalancedNode);

		// Update heights of rotated nodes.
		updateHeight(unbalancedNode);
		updateHeight(leftChild);

		// Return the new root after rotation.
		return leftChild;
	}

	/**
	 * This method performs left rotation in the right-right situation, making the tree self-balancing/AVL. 
	 * (e.g. if you add 1, 2, 3 -> we would need to do a rotation at the node with 1, and return the node with 2).
	 * @param node is the node that is unbalanced and needs left rotation.
	 * @return new root resulted from the left rotation.
	 */
	public ThreeTenSetNode rotateLeft(ThreeTenSetNode node) 
	{
		// NOTE: right-right situation, do left rotation.

		// If the node is null, nothing to rotate. If the right child is null, then RR situation doesn't happen.
		if (node == null || node.getRight() == null) {
			return node;
		}

		// Tree is unbalanced at the node.
		ThreeTenSetNode unbalancedNode = node;

		// Node's right child.
		ThreeTenSetNode rightChild = unbalancedNode.getRight();

		// Step 1: Right child's left kid.
		ThreeTenSetNode rightChildLeftKid = rightChild.getLeft();

		// Step 1.5: Set the right child's left kid as the right kid of the node.
		unbalancedNode.setRight(rightChildLeftKid);

		// Step 2: Set the node as the left child of the right child.
		rightChild.setLeft(unbalancedNode);

		// Update heights of rotated nodes.
		updateHeight(unbalancedNode);
		updateHeight(rightChild);

		// Return the new root after the rotation.
		return rightChild;
	}

	@Override
	public Iterator<ThreeTenSetNode> iterator() 
	{
		//This is a level-order iterator (BFS).
		// NOTE: You do not need to worry about concurrent modification for this project.
		return new Iterator<>() 
		{
			// This class provides a singly linked list structure for you to use as a queue.
			class QueueNode 
			{
				ThreeTenSetNode value;
				QueueNode next;
				
				QueueNode(ThreeTenSetNode value) {
					this.value = value;
				}
			}
			
			// Initialize the queue with the root node.
			// NOTE: Ternary Operator -> variable = (condition) ? expressionTrue :  expressionFalse;.
			QueueNode queueHead = (root == null) ? null : new QueueNode(root);
			QueueNode queueTail = queueHead;
			
			/**
			 * If there is anything left in the queue, then there is still more to look at.
			 * 
			 * @return true is the queue is not empty.
			 */
			public boolean hasNext() 
			{
				// Requirement: O(1).
				// The queue isn't empty until the head of the queue is null.
				return queueHead != null;
			}

			/**
			 * This method takes something out of the queue, add its children to the queue, and returns the node that was taken out.
			 * 
			 * @return currentNode is the node that was taken out.
			 * @throws NoSuchElementException if the queue is empty.
			 */
			@Override
			public ThreeTenSetNode next() 
			{
				// Requirement: O(1).
				// Throw NoSuchElementException if the queue is empty.
				if (queueHead == null) {
					throw new NoSuchElementException("The queue is empty!");
				}

				// Hints for this method.

				//(1) Take something out of the queue.
				// Stored the removed node.
				ThreeTenSetNode currentNode = queueHead.value;

				//(2) Add its children to the queue.
				// Adding right child.
				if (currentNode.getRight() != null) {
					// Add right child to the queue.
					QueueNode rightChild = new QueueNode(currentNode.getRight());
					queueTail.next = rightChild;

					// Update the pointer.
					queueTail = queueTail.next;
				}
				// Adding left children.
				if (currentNode.getLeft() != null) {
					// Add left child to the queue.
					QueueNode leftChild = new QueueNode(currentNode.getLeft());
					queueTail.next = leftChild;
					
					// Update the pointer.
					queueTail = queueTail.next;
				}

				// Remember: Update head pointer.
				queueHead = queueHead.next;

				//(3) Return the node you took out.
				return currentNode;
			}
		};
	}
	
	/**
	 * Size is the total number of nodes in the tree (or total number of filled slots in an array).
	 * @return size - total number of nodes in the tree.
	 */
	public int size() 
	{
		// Done for you.
		return size;
	}
	
	/**
	 * Returns the root node of the tree.
	 * @return node - the root node of the tree.
	 */
	public ThreeTenSetNode getRoot() 
	{
		// Done for you.
		return root;
	}

	// /**
	//  * Testing code goes here... edit this as much as you want!
	//  * @param args args.
	//  */
	// public static void main(String[] args) 
	// {
	// 	ThreeTenBinarySearchTree bst = new ThreeTenBinarySearchTree();
	// 	if(bst.size() == 0 && bst.addNew('a') && bst.size() == 1 && !bst.addNew('a') && bst.size() == 1) {
	// 		System.out.println("yay 1");
	// 	}
		
	// 	if(bst.addNew('b') && bst.size() == 2 && bst.getRoot().getLetter() == 'a' && bst.getRoot().getRight().getLetter() == 'b') {
	// 		System.out.println("yay 2");
	// 	}
		
	// 	if(bst.toString().equals("'a': 1\n'b': 1")) {
	// 		System.out.println("yay 3");
	// 	}
		
	// 	if(bst.increaseLetterCount('a') == 1 && bst.increaseLetterCount('a') == 2 && bst.getLetterCount('a') == 3) {
	// 		System.out.println("yay 4");
	// 	}
		
	// 	if(bst.decreaseLetterCount('b') == 1 && bst.decreaseLetterCount('b') == 0 && bst.getLetterCount('b') == -1) {
	// 		System.out.println("yay 5");
	// 	}
		
	// 	Iterator<ThreeTenSetNode> itr = bst.iterator();
	// 	if(itr.next().getLetter() == 'a' && itr.next().getLetter() == 'b' && !itr.hasNext()) 
	// 	{
	// 		System.out.println("yay 7");
	// 	}

	// 	ThreeTenBinarySearchTree bst2 = new ThreeTenBinarySearchTree();
	// 	if(bst2.addNew('a') && bst2.addNew('b') && bst2.getRoot().getHeight() == 1 && bst2.getRoot().getRight().getHeight() == 0)
	// 	{
	// 		System.out.println("yay 7");
	// 	}

	// 	if(bst2.addNew('c') && bst2.getRoot().getLetter() == 'b' && bst2.getRoot().getHeight() == 1 && bst2.getRoot().getLeft().getHeight() == 0 && bst2.getRoot().getRight().getHeight() == 0)
	// 	{
	// 		System.out.println("yay 8");
	// 	}
	// }
}