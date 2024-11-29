/*
 * @author Andrei Ciceu
 * #251355626
 * CS2211
 * 11/11/2024
 * BSTDictionary class that implements BSTDictionaryADT using BinarySearchTree
 */

public class BSTDictionary implements BSTDictionaryADT{

    private BinarySearchTree tree;
    
    /*
     * Public BSTDictionary constructor
     */
    public BSTDictionary(){
        tree = new BinarySearchTree();
    }

    /*
     * @param Key k
     * @return Record
     * Public get method that returns Record with Key k in Dictionary
     */
    public Record get(Key k) {
        BSTNode node = tree.get(tree.getRoot(),k);
        if (node == null){
            return null;
        }
        return node.getRecord();
    }

    /*
     * @param Record d
     * @throws DictionaryException
     * Public put method that puts a BSTNode with Record d into tree
     * Throws Dictionary Exception if node with Key already exists in Dictionary
     */
    public void put(Record d) throws DictionaryException {
        if (tree.get(tree.getRoot(), d.getKey()) != null){
            throw new DictionaryException("Key already used in dictionary");
        }
        tree.insert(tree.getRoot(), d);
    }

    /*
     * @param Key k
     * @throws DictionaryException
     * Public remove method that removes Record with key k from Dictionary
     * Throws DictionaryException if Record with Key k isn't in Dictionary
     */
    public void remove(Key k) throws DictionaryException {
        if (tree.get(tree.getRoot(), k) == null){
            throw new DictionaryException("Record with key not found in Dictionary");
        }
        tree.remove(tree.getRoot(), k);
    }

    /*
     * @param Key k
     * @returns Record
     * Public successor method that returns the successor to Record with Key k
     */
    public Record successor(Key k) {
        BSTNode node = tree.successor(tree.getRoot(), k);
        if (node == null){
            return null;
        }
        return node.getRecord();
    }

    /*
     * @param Key k
     * @returns Record
     * Public predecessor method that returns the predecessor to Record with Key k
     */
    public Record predecessor(Key k) {
        BSTNode node = tree.predecessor(tree.getRoot(), k);
        if (node == null){
            return null;
        }
        return node.getRecord();
    }

    /*
     * @return Record
     * Returns Record with smallest value in Dictionary
     */
    public Record smallest() {
        if (tree.getRoot() == null){
            return null;
        }
        return tree.smallest(tree.getRoot()).getRecord();
    }

    /*
     * @return Record
     * Returns Record with largest value in Dictionary
     */
    public Record largest() {
        if (tree.getRoot() == null){
            return null;
        }
        return tree.largest(tree.getRoot()).getRecord();
    }
    
}
