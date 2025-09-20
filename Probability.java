public class Probability {
    public static void main(String[] args) {
        System.out.print(or(new double[]{0.5,0.2, 0.01, 0.127}));
    }
    static double or(double[] terms){

        //x Or y is equal to 1-((1-x)(1-y))
        double andTerms = 1;
        for (double term:terms){
            double notTerm = 1-term;
            andTerms*=notTerm;
        }
        return 1-andTerms;
    }
    static double orADDITION(double[] terms){
        double sum = 0;
        for (double term:terms){sum+=term;}
        return sum;
    }
}
