package HW1;

import java.util.Vector;

public class Predictor {

    /**dataset that this predictor will classify */

    protected DataSet dataset;

    
    /**vector of good j's, also referred to as P in the algorithm */

    public Vector<Integer> goodJ;


    /**argument constructor for the predictor, no reason for a default constructor
     * @param ds the dataset to that this predictor will classify */

    public Predictor(DataSet ds) {
        dataset = ds;
        goodJ = new Vector<Integer>();
    } //end Predictor(ds)


    /**calculate q for one bit, one example
     * q = 1 if x_j = y (if this attribute is correlated with y), 0 otherwise
     * @param i the index of the example being evaluated 
     * @param j the index of the string position in example i being evaluated 
     * @return 1 if x_j = y, 0 otherwise */

    public int calculateQ(int i, int j) {
        String x = dataset.getExample(i).getX(); 
        char x_j = x.charAt(j);
        
        if (x_j == dataset.getExample(i).getY()) {
            return 1;
        } //end if

        else {
            return 0;
        } //end else

    } //end calculateQ(i, j)


    /**calculate c for a given j
     * c_i = sum of all the times position j correlated with y across all examples / n
     * @param j the position being evaluated
     * @return 1/n * (sum of q_j(x_i, y_i)) */

    public double calculateC(int j) {
        double scalar = 1.0 / (double) dataset.getN();
        double sum = 0;

        for (int i = 0; i < dataset.getN(); i++) {
            sum += calculateQ(i, j);
        } //end for

        //System.out.println("\tsum: " + sum);
        //System.out.println("\tscalar: " + scalar);

        return sum * scalar;

    } //end calculateC(j)


    /**classify j based on c_j
     * if c_j > 1/2 + (1/sqrt(n)), then mark j as "good" by adding it to P, goodJ
     * j is good if looking at it predicts y better than just a random guess (1/2, 50%) + a slight adjustment
     * that scales based on how large the number of examples is
     * @param j the index of a string being evaluated */

    public void classifyJ(int j) {
        double comparison = 0.5 + (1 / Math.sqrt(dataset.getN()));
        
        //System.out.println("comparison: " + comparison);
        //System.out.println("calculateC: " + calculateC(j));

        if (calculateC(j) > comparison) {
            goodJ.add(j);
            //System.out.println("j = " + j + " is good");
        } //end if

    } //end classify(J)


    /**classify all of the j's in the dataset 
     * at the end of this function, will have a set of good j's (P) */

    public void classify() {

        for (int j = 0; j < dataset.getD(); j++) {
            classifyJ(j);
        } //end for

    } //end classify


    /**calculate h (make a prediction) for a given example
     * h = 1 if the majority of good j's predict 1, 0 otherwise
     * if there are no good j's, then still predict 1 because empty sum = 0 = 0
     * @param i the example for which y is being predicted
     * @return prediction, h for example i */

    public int calculateH(int i) {
        double sum = 0;

        for (int j = 0; j < goodJ.size(); j++) {
            sum += calculateQ(i, j);
        } //end for

        //System.out.println("\tsum: " + sum);

        if (sum >= (goodJ.size() / 2)) {
            //System.out.println("\treturn 1");
            return 1;
        } //end if

        //System.out.println("\treturn 0");
        return 0;

    } //end calculateH(i)



    /**retrieves the dataset used by this predictor
     * @return dataset */

    public DataSet getDataSet() {
        return dataset;
    } //end getDataSet()


} //end Predictor
