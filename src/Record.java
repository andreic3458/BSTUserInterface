/*
 * @author Andrei Ciceu
 * #251355626
 * CS2211
 * 11/11/2024
 * Record class that contains Key and data, used in a BSTNode
 */

public class Record {
    private Key k;
    private String data;

    /*
     * @param Key k
     * @param String theData
     * Public Record constructor
     */
    public Record(Key k, String theData){
        this.k = k;
        this.data = theData;
    }

    /*
     * @return Key
     * Returns Key k
     */
    public Key getKey(){
        return this.k;
    }

    /*
     * @return String
     * Returns String data
     */
    public String getDataItem(){
        return this.data;
    }
}
