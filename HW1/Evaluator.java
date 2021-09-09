package HW1;

public class Evaluator {

    /**predictor object to be evaluated */

    protected Predictor p;

   
    /**default constructor for the evaluator, where predictor is just null */

    public Evaluator() {
        p = null;
    } //end Evaluator()


    /**argument constructor for the evaluator
     * @param pNew the new predictor to be evaluated */

    public Evaluator(Predictor pNew) {
        p = pNew;
    } //end Evaluator(pNew)


    /**calculate q_h for a given example
     * q_h = 1 if the predictor makes a mistake (if h(x) =/= y), 0 otherwise
     * @param i the index of the example for which q_h is being calculated
     * @return 1 if the predictor made a mistake, 0 otherwise */

    public int calculateQh(int i) {
        char h = (char)(p.calculateH(i) + '0');
        //System.out.println("\th: " + h + ", y: " + p.getDataSet().getExample(i).getY());

        if(h != p.getDataSet().getExample(i).getY()) {
            //System.out.println("\treturn 1");
            return 1;
        } //end if

        else {
            //System.out.println("\treturn 0");
            return 0; 
        } //end else

    } //end calculateQh(i)


    /**calcualte error for the dataset, where
     * error(s) = (1/n) * (sum of q_h for all n examples)
     * error = proportion of examples for which the prediction was wrong
     * @return error 
     * @throws Exception if the dataset has a size of 0 */

    public double calculateError() throws Exception {

        if (p.getDataSet().getN() == 0) {
            throw new Exception("Evaluator::calculateError: size of dataset cannot be 0");
        } //end if

        double scalar = 1.0 / (double) p.getDataSet().getN();
        double sum = 0;

        for (int i = 0; i < p.getDataSet().getN(); i++) {
            sum += calculateQh(i);
        } //end for

        //System.out.println("scalar: " + scalar);
        //System.out.println("sum: " + sum);

        return (scalar * sum);

    } //end calculateError()

} //end Evaluator
