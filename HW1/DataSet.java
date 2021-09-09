package HW1;


import java.util.Random;
import java.util.Vector;

/**public class for the entire dataset
 * comprised of n examples */

public class DataSet {
    
    /**number of examples in the dataset */

    private int n;


    /**number of bits in each string for each example */

    private int d;


    /**vector of examples contained in the dataset */

    protected Vector<Example> dataset;


    /**random object used to generate bits */

    Random random;


    /**default constructor for a dataset
     * creates an empty dataset */

    public DataSet() {
        n = 0;
        d = 0;
        dataset = new Vector<Example>(n);
        random = new Random();
    } //end DataSet()


    /**argument constructor for a dataset
     * @param nNew the new number of examples in the dataset 
     * @param dNew the new number of bits in each example's string */

    public DataSet(int nNew, int dNew) {
        n = nNew;
        d = dNew;
        dataset = new Vector<Example>(n);
        random = new Random();
    } //end DataSet(nNew)


    /**generates a full dataset
     * generates n examples */

    public void generateDataSet() {
        
        for (int i = 0; i < n; i++) {
            Example ex = generateExample();
            dataset.addElement(ex);
        } //end for

    } //end generateDataSet()


    /**generates a new example 
     * @return example, containing a randomly generated string and a randomy generated char */

    private Example generateExample() {
        String x = "";

        for (int j = 0; j < d; j++) {
            char randBit = generateRandomBit();
            x = x + randBit;
        } //end for 

        char y = generateRandomBit();
        Example ex = new Example(x, y);
        return ex;
    } //add generateExample()


    /**generates a random bit 
     * @return randomly generated bit as a char, either 0 or 1 */
    
    private char generateRandomBit() {
        int randBit = random.nextInt(2);
        return Character.forDigit(randBit, 10);
    } //end generateRandomBit()


    /**retrieves and returns an example at a given index
     * @param i the index to retrieve from 
     * @return Example stored at index i */

    public Example getExample(int i) {
        return dataset.get(i);
    } //end getExample(i)


    /**returns the n, number of elements
     * @return n */

    public int getN() {
        return n;
    } //end getN(0)


    /**returns the d, number of bits in each string 
     * @return d */

    public int getD() {
        return d;
    } //end getD(0)


    /**prints out each example in a dataset */

    public void print() {
        
        for (int i = 0; i < n; i++) {
            getExample(i).print();
        } //end for

    } //end print()


} //end DataSet

