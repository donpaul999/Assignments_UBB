import java.util.Scanner;

class Average
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input the number of elements: ");
        int n = in.nextInt();
        double sum = 0;
        double[] givenNumbers = new double[100];

        for(int i = 0; i < n; ++i) {
            givenNumbers[i] = in.nextDouble();
        }

        for(int i = 0; i < n; ++i) {
            sum += givenNumbers[i];
        }

        System.out.println("Average of five numbers is: " + sum / n);
    }

}