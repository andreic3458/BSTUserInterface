/*
 * @author Andrei Ciceu
 * #251355626
 * CS2211
 * 11/11/2024
 * This class is a Key class that holds a label and type for a Record in a BSTNode
 */

public class Key {
    private String label;
    private int type;
    
    /*
     * @param String theLabel
     * @param int theType
     * Public Key constructor that initializes type and label
     */
    public Key(String theLabel, int theType){
        this.label = theLabel.toLowerCase();
        this.type = theType;
    }

    /*
     * @return String
     * Returns label
     */
    public String getLabel(){
        return this.label;
    }

    /*
     * @return int
     * Returns type
     */
    public int getType(){
        return this.type;
    }

    /*
     * @param Key k
     * @return int
     * Compares this key to another to determine which is larger
     */
    public int compareTo(Key k){
        if (this.label.equals(k.label) && this.type == k.type){
            return 0;
        }

        if (this.label.compareTo(k.label) < 0){
            return -1;
        }

        if (this.label == k.label && this.type<k.type){
            return -1;
        }

        return 1;
    }

}
