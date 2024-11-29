/*
 * @author Andrei Ciceu
 * #251355626
 * CS2211
 * 11/11/2024
 * BSTNode class that is used in BinarySearchTree, that stores parent, left child, right child, and record
 */

public class BSTNode {
    private BSTNode parent;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private Record item;

    /*
     * @param Record item
     * Public BSTNode constructor
     */
    public BSTNode(Record item) {
        this.item = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;

    }

    /*
     * @return Record
     * Returns Record item
     */
    public Record getRecord(){
        return this.item;
    }

    /*
     * @param Record d
     * Public setRecord method
     */
    public void setRecord(Record d){
        this.item = d;
    }

    /*
     * @return BSTNode
     * Returns left child node
     */
    public BSTNode getLeftChild(){
        return this.leftChild;
    }

    /*
     * @return BSTNode
     * Returns right child node
     */
    public BSTNode getRightChild(){
        return this.rightChild;
    }

    /*
     * @return BSTNode
     * Returns parent node
     */
    public BSTNode getParent(){
        return this.parent;
    }

    /*
     * @param BSTNode u
     * Public setter method for Left child node
     */
    public void setLeftChild(BSTNode u){
        this.leftChild = u;
    }

    /*
     * @param BSTNode u
     * Public setter method for Right child node
     */
    public void setRightChild(BSTNode u){
        this.rightChild = u;
    }

    /*
     * @param BSTNode u
     * Public setter method for parent node
     */
    public void setParent(BSTNode u){
        this.parent = u;
    }

    /*
     * @return boolean
     * Public method that determines if this node is a leaf node, returning true or false
     */
    public boolean isLeaf(){
        if (this.leftChild == null && this.rightChild == null){
            return true;
        }
        return false;
    }

}
