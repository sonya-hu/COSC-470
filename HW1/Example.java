package HW1;


/**class declaration for an example
 * each example is (x_i, y_i)
 * x_i = a randomly generated d-bit string, which is comprised of d 0/1 bits
 * y_i = a random bit */

public class Example {

    /**randomly generated d-bit string, x_i */

    private String x;


    /**attribute being predicted, y_i */

    private char y;


    /**default constructor for the Example class
     * initializes x, y to their empty/default values */

    public Example() {
        x = "";
        y = (char) 0;
    } //end Example()


    /**argument constructor for the Example class
     * user specifies the x, y
     * @param xNew the new value of x
     * @param yNew the new value of y */

    public Example(String xNew, char yNew) {
        x = xNew;
        y = yNew;
    } //end Example(xNew, yNew)


    /**retrieves and returns x, the d-bit string
     * @return x */

    public String getX() {
        return x;
    } //end getX()


    /**retrieves and returns y, the random bit
     * @return y */

    public char getY() {
        return y;
    } //end getY()


    /**prints out the x and y components of an example */

    public void print() {
        System.out.println("x: " + x + " | y: " + y);
    } //end print()

} //end Example
