// Note: Add JavaDocs, but don't change the class in ANY way!

/**
 * This class represents the node class for ThreeTenBinarySearchTree.java file.
 */
class ThreeTenSetNode extends TreeNode 
{
	/**
	 * Character represents a letter and initially null.
	 */
	private Character letter = null;

	/**
	 * Count is initially 0.
	 */
	private int count = 0;

	/**
	 * NEW: Added this instance variable for self-balancing purposes.
	 * This represents height.
	 */
	private int height = 0;
	
	/**
	 * This node represents left child.
	 */
	private ThreeTenSetNode left;

	/**
	 * This node represents right child.
	 */
	private ThreeTenSetNode right;
	
	/**
	 * Default constructor with the given letter, count is default: 0.
	 * 
	 * @param letter is the character.
	 */
	public ThreeTenSetNode(Character letter) { this.letter = letter; }

	/**
	 * Constructor with the given letter and count.
	 * 
	 * @param letter is the character.
	 * @param count is the counter for the letter/character.
	 */
	public ThreeTenSetNode(Character letter, int count) {
		this.count = count;
		this.letter = letter;
	}
	
	/**
	 * Returns the character/letter in the node.
	 * 
	 * @return character/letter that is stored.
	 */
	public Character getLetter() {
		return this.letter;
	}
	
	/**
	 * Returns the count of the node.
	 * 
	 * @return count of the node.
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * Sets the count for the node.
	 * 
	 * @param count is the new set count.
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Sets the left node of the tree.
	 * 
	 * @param left is the node to be set as left node.
	 */
	public void setLeft(ThreeTenSetNode left) {
		this.left = left;
	}
	
	/**
	 * Returns the left node of the tree.
	 * 
	 * @return left node of the tree.
	 */
	public ThreeTenSetNode getLeft() {
		return left;
	}
	
	/**
	 * Sets the right node of the tree.
	 * 
	 * @param right is the node to be set as right node.
	 */
	public void setRight(ThreeTenSetNode right) {
		this.right = right;
	}
	
	/**
	 * Returns the right node of the tree.
	 * 
	 * @return the right node of the tree.
	 */
	public ThreeTenSetNode getRight() {
		return right;
	}
	
	/**
	 * NEW: Added this get height method for self-balancing purposes.
	 * This method returns the height.
	 * @return hright height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * NEW: Added this get height method for self-balancing purposes.
	 * @param height is the height to be set.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns a string in this format: "'letter': count".
	 * @return String in  this format: "'letter': count".
	 */
	@Override
	public String toString() 
	{
		return "'" + letter + "': " + count;
	}
}