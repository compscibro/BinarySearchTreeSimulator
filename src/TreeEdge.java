//This is complete and you do not need to edit it for this project.

import org.apache.commons.collections15.Factory;
import java.awt.Color;
import java.util.Random;

/**
 *  A host in the network.
 *  
 *  @author K. Raven Russell
 */
public class TreeEdge {
	/**
	 *  The last id given to a edge.
	 */
	public static int LAST_ID = -1;
	
	/**
	 *  The unique id of this edge.
	 */
	private final int id;
	
	/**
	 *  The color of this edge in the visualization.
	 */
	private Color color = Color.BLACK;
	
	/**
	 *  The text label to display next to the edge.
	 */
	private String text = "";
	
	/**
	 *  Creates a tree edge with an ID.
	 */
	public TreeEdge() {
		id = ++LAST_ID;
	}
	
	/**
	 *  Creates a tree edge with an ID.
	 *  
	 *  @param text the text for the edge
	 */
	public TreeEdge(String text) {
		id = ++LAST_ID;
		this.text = text;
	}
	
	/**
	 *  Returns the id of the edge.
	 *  @return the edge's unique identifier
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  Returns the color of the edge in the simulation.
	 *  
	 *  @return the edge's current color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 *  Sets the color of the edge in the simulation.
	 *  @param c the new color to use
	 */
	public void setColor(Color c) {
		this.color = c;
	}
	
	/**
	 *  Sets the text label to display for this edge.
	 *  @param text the new next to display
	 */
	public void setTextLabel(String text) {
		this.text = text;
	}
	
	/**
	 *  Gets the text label displaying for ths edge.
	 *  @return the text on the label
	 */
	public String getTextLabel() {
		return this.text;
	}
	
	/**
	 *  The string representation of a edge
	 *  is just it's cost.
	 *  
	 *  @return the string representation of the edge
	 */
	@Override
	public String toString() {
		return ""+text;
	}
	
	/**
	 *  Sets the hash code of the edge (cost * id).
	 *  
	 *  @return the hash code of the edge
	 */
	@Override
	public int hashCode() {
		return id;
	}
	
	/**
	 *  Two edges are equal if they have the same id.
	 *  
	 *  @return whether two edges are equal
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof TreeEdge) {
			return this.id == ((TreeEdge)o).id;
		}
		return false;
	}
}