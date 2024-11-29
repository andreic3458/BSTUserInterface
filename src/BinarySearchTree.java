/*
 * @author Andrei Ciceu
 * #251355626
 * CS2211
 * 11/11/2024
 * BinarySearchTree class that stores the root node and performs various actions common in a Binary Search Tree
 */

public class BinarySearchTree {
    private BSTNode root;

    /*
     * Public BinarySearchTree constructor
     */
    public BinarySearchTree(){
        this.root = null;
    }

    /*
     * @return BSTNode
     * Returns root node
     */
    public BSTNode getRoot(){
        return this.root;
    }

    /*
     * @param BSTNode r
     * @param Key k
     * @return BSTNode
     * Recursive public method that returns BSTNode from tree with Key k
     */
    public BSTNode get(BSTNode r, Key k){
        if (r == null){
            return null;
        }


        int compare = k.compareTo(r.getRecord().getKey());

        if (compare == 0){
            return r;
        } else if (compare<0){
            return get(r.getLeftChild(), k);
        } else {
            return get(r.getRightChild(), k);
        }
    }

    /*
     * @param BSTNode r
     * @param Record d
     * @throws DictionaryException
     * Public method that inserts new BSTNode in Tree with Record d
     * Throws DictionaryException if node with Record d exists in the tree
     */
    public void insert(BSTNode r, Record d)throws DictionaryException{
        if (r == null){
            root = new BSTNode(d);
            return;
        }

        Key k = d.getKey();
        int compare = k.compareTo(r.getRecord().getKey());

        if (compare == 0){
            throw new DictionaryException("Key already exists in the tree");
        } else if (compare < 0){
            if (r.getLeftChild() != null){
                insert(r.getLeftChild(), d);
            } else {
                BSTNode newNode = new BSTNode(d);
                r.setLeftChild(newNode);
                newNode.setParent(r);
            }
        } else {
            if (r.getRightChild() != null){
                insert(r.getRightChild(),d);
            } else {
                BSTNode newNode = new BSTNode(d);
                r.setRightChild(newNode);
                newNode.setParent(r);
            }
        }
    }

    /*
     * @param BSTNode r
     * @param Key k
     * @throws DictionaryException
     * Public remove method that removes node with Key k using a recursive helper method
     * Throws DictionaryException if node with Key k not found in Tree
     */
    public void remove (BSTNode r, Key k) throws DictionaryException{
        if (get(r, k) == null){
            throw new DictionaryException("Key not found in tree");
        }
        BSTNode node = get(r, k);
        if (node.isLeaf()){
            replaceParent(node, null);
        } else if (node.getLeftChild() == null){
            replaceParent(node, node.getRightChild());
        } else if (node.getRightChild() == null){
            replaceParent(node, node.getLeftChild());
        } else {
            BSTNode successor = smallest(node.getRightChild());
            node.setRecord(successor.getRecord());
            remove(node, node.getRecord().getKey());
        }
    }

    /*
     * @param BSTNode r
     * @param Key k
     * @return BSTNode
     * Public successor method that finds successor of node r
     */
    public BSTNode successor (BSTNode r, Key k){
        BSTNode target = get(r,k);
        if (target == null){
            return null;
        }

        if (target.getRightChild() != null){
            return smallest(target.getRightChild());
        }

        BSTNode parent = target.getParent();
        
        while (parent != null && target == parent.getRightChild()){
            target = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    /*
     * @param BSTNode r
     * @param Key k
     * @return BSTNode
     * Public predecessor method that finds predecessor of node r
     */
    public BSTNode predecessor (BSTNode r, Key k){
        BSTNode target = get(r,k);
        if (target == null){
            return null;
        }

        if (target.getLeftChild() != null){
            return largest(target.getLeftChild());
        }

        BSTNode parent = target.getParent();
        
        while (parent != null && target == parent.getLeftChild()){
            target = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    /*
     * @param BSTNode r
     * @return BSTNode
     * Public smallest method that finds node with smallest key in Tree
     */
    public BSTNode smallest (BSTNode r){
        if (r.getLeftChild() != null){
            return smallest(r.getLeftChild());
        }
        return r;
    }

    /*
     * @param BSTNode r
     * @return BSTNode
     * Public largest method that finds node with largest key in Tree
     */
    public BSTNode largest (BSTNode r){
        if (r.getRightChild() != null){
            return largest(r.getRightChild());
        }
        return r;
    }
    
    /*
     * @param BSTNode r
     * @param BSTNode replace
     * Private helper method for replacing parent used in remove
     */
    private void replaceParent(BSTNode r, BSTNode replace) {
        if (r.getParent() == null) {
            root = replace;
        } else if (r == r.getParent().getLeftChild()) {
            r.getParent().setLeftChild(replace);
        } else {
            r.getParent().setRightChild(replace);
        }
        if (replace != null) {
            replace.setParent(r.getParent());
        }
    }
}
