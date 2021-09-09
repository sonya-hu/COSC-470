package HW1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


/**class declaration for the Experiment class, contains the main function
 * attempts to evaluate patterns in error, etc from changes in n and d */

public class Experiment {

    /**max n value for this experiment, default value is 1000 */

    public int nMax = 1000;

    
    /**max d value for this experiment, default value is 1000 */

    public int dMax = 1000;


    /**value by which we increment by when increasing the n, d; default value is 100 */

    public int increment = 100;


    /**argument constructor for the Experiment
     * @param nM the new nMax
     * @param dM the new dMax
     * @param inc the new increment value */

    public Experiment(int nM, int dM, int inc) {
        nMax = nM;
        dM = dMax;
        increment = inc;
    } //end Experiment(nM, dM, inc)


    public static void main(String[] args) {
        
        try {
            
            //set up a print writer so that all of the output is written into a text file
            //i later convert this into a csv file and graph it

            FileWriter fw = new FileWriter("COSC-470/HW1/HW1/out.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            //set nMax, dMax, increment to default values unless otherwise specified

            int nM = 1000;
            int dM = 1000;
            int inc = 10;

            if (args.length > 0) {
                nM = Integer.parseInt(args[0]);
                dM = Integer.parseInt(args[1]);
                inc = Integer.parseInt(args[2]);
            } //end if

            Experiment ex = new Experiment(nM, dM, inc);
            ex.loop(pw);
            pw.close();

        } //end try

        catch (Exception e) {
            System.out.println(e.getMessage());
        } //end catch

    } //end main


    /**calculates the error of a dataset with a given n and given d
     * @param i the n for this dataset
     * @param j the d for this dataset 
     * @throws Exception for calculateError()*/

    public void singleEvaluation(int i, int j, PrintWriter pw) throws Exception {
        DataSet ds = new DataSet(i, j);
        ds.generateDataSet();
        Predictor p = new Predictor(ds);
        p.classify();
        Evaluator e = new Evaluator(p);
        double err = e.calculateError();
        pw.println("n = " + i + " | d = " + j + " | goodJ.size() = " + p.goodJ.size() + " | err = " + err);
    } //end singleEvaluation(i, j)


    /**loops through all the possible n's for a given j
     * @param j the d for this experiment 
     * @throws Exception */

    public void loopGivenD(int j, PrintWriter pw) throws Exception {

        for (int i = 1; i < nMax; i += increment) {
            singleEvaluation(i, j, pw);
        } //end for

    } //end loopGivenD(j)


    /**loops through all the given d's for a given i
     * @param i for the n in this experiment 
     * @throws Exception */

    public void loopGivenN(int i, PrintWriter pw) throws Exception {

        for (int j = 1; j < dMax; j += increment) {
            singleEvaluation(i, j, pw);
        } //end for

    } //end loopGivenN(i)


    /**loops through all the given n's and d's simultaneously
     * helps to evaluate any potential interaction effects 
     * @throws Exception */

    public void loop(PrintWriter pw) throws Exception {
        
        for (int i = 1; i < nMax; i += increment) {
            loopGivenN(i, pw);
        } //end for

    } //end loop()

    
    /**function used to test each function, each calculation
     * used for testing
     * @throws Exception from calculateError */

    public void functionByFunctionTest() throws Exception {
        DataSet ds = new DataSet(5, 3);
        ds.generateDataSet();
        ds.print();
        Predictor p = new Predictor(ds);
        System.out.println("\nCALCULATE Q");

        for (int i = 0; i < ds.getN(); i++) {
            for (int j = 0; j < ds.getD(); j++) {
                System.out.println("i = " + i + ", j = " + j + ", q = " + p.calculateQ(i, j));
            } //end for
        } //end for

        System.out.println("\nCALCULATE C");

        for (int j = 0; j < ds.getD(); j++) { 
            System.out.println("j = " + j + ", c = " + p.calculateC(j));
        } //end for

        System.out.println("\nCLASSIFY");
        p.classify();
        System.out.println("goodJ.size(): " + p.goodJ.size());
        System.out.println("\nCALCULATEH");

        for (int i = 0; i < ds.getN(); i++) {
            System.out.println("i = " + i);
            System.out.println("\th: " + p.calculateH(i));
        } //end for

        Evaluator eval = new Evaluator(p);
        System.out.println("\nCALCULATEQ_H");

        for (int i = 0; i < ds.getN(); i++) {
            System.out.println("i = " + i);
            eval.calculateQh(i);
        } //end for

        double err = eval.calculateError();
        System.out.println("error: " + err);

    } //end functionByFunctionTest()


} //end Experiment
