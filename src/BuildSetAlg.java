//This is done for you, but you can read this if you'd like!

import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.OrderedKAryTree;
import edu.uci.ics.jung.graph.Tree;

import edu.uci.ics.jung.graph.util.EdgeType;

import java.util.*;

/**
 *  This algorithm performs the steps to create a BST set from a collection
 *  of characters.
 *  
 *  @author Katherine (Raven) Russell
 */
class BuildSetAlg implements ForestAlg<ThreeTenSetNode,TreeEdge> {
	/**
	 *  The tree storage.
	 */
	ThreeTenBinarySearchTree tree;
	
	/**
	 *  The JUNG tree to display.
	 */
	OrderedKAryTree<ThreeTenSetNode, TreeEdge> displayTree;
	
	/**
	 *  Whether or not the algorithm has been started.
	 */
	private boolean started = false;
	
	/**
	 *  The possible modes the algorithm can be in.
	 */
	private enum Mode { COUNTING, DONE };
	
	/**
	 *  The algorithm's current mode.
	 */
	private Mode mode = null;
	
	/**
	 *  Current character (if in counting mode).
	 */
	private Character currentChar;
	
	@Override
	public EdgeType treeEdgeType() {
		return EdgeType.UNDIRECTED;
	}
	
	/**
	 *  Sets the current character being examined.
	 *  
	 *  @param currentChar the new character
	 */
	public void setCurrentChar(Character currentChar) {
		this.currentChar = currentChar;
	}
	
	@Override
	public boolean isStarted() {
		return started;
	}
	
	@Override
	public void start() {
		this.started = true;
	}
	
	@Override
	public void finish() {
		// Unused. Required by the interface.
	}
	
	@Override
	public void cleanUpLastStep() {
		// Unused. Required by the interface.
	}
	
	@Override
	public boolean setupNextStep() {
		//we're counting, but out of characters
		if(this.mode == Mode.COUNTING && currentChar == null) {
			//stop!
			this.mode = Mode.DONE;
		}
		
		return (this.mode != Mode.DONE);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void reset(Forest<ThreeTenSetNode,TreeEdge> displayTree) {
		this.displayTree = (OrderedKAryTree<ThreeTenSetNode,TreeEdge>) displayTree;
		this.tree = new ThreeTenBinarySearchTree();
		this.started = false;
		this.mode = Mode.COUNTING;
		this.currentChar = null;
	}
	
	@Override
	public void doNextStep() {
		//we are in counting mode
		if (this.mode == Mode.COUNTING) {
			//try to put the character in the map
			if(!this.tree.addNew(currentChar)) {
				//if it won't go, then increase the count
				this.tree.increaseLetterCount(currentChar);
			}
			resetDisplayTree();
		}
	}
	
	/**
	 *  Make the display tree update because there is a new node.
	 */
	private void resetDisplayTree() {
		//remove everyone
		this.displayTree = new OrderedKAryTree<>(2);
		
		//init
		ThreeTenSetNode root = this.tree.getRoot();
		LinkedList<ThreeTenSetNode> nodes = new LinkedList<>();
		
		//add root into things
		nodes.add(root);
		this.displayTree.addVertex(root);
		
		//add everyone else
		while(!nodes.isEmpty()) {
			ThreeTenSetNode current = nodes.remove(0);
			ThreeTenSetNode leftChild = current.getLeft();
			ThreeTenSetNode rightChild = current.getRight();
			if(leftChild != null) {
				//this.displayTree.addVertex(leftChild);
				this.displayTree.addEdge(new TreeEdge("left"), current, leftChild, 0);
				nodes.add(leftChild);
			}
			if(rightChild != null) {
				//this.displayTree.addVertex(rightChild);
				this.displayTree.addEdge(new TreeEdge("right"), current, rightChild, 1);
				nodes.add(rightChild);
			}
		}
	}
	
	/**
	 *  The tree may need to be returned if it is replaced
	 *  by the collection.
	 *  
	 *  @return the internal k-ary tree
	 */
	public OrderedKAryTree<ThreeTenSetNode,TreeEdge> getInternalTree() {
		return this.displayTree;
	}
	
	@Override
	public String toString() {
		return this.tree.toString();
	}
}