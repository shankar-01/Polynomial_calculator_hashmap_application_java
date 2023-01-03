import java.util.Scanner;
class Main{
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to Polynomial calculator");
    System.out.println("Enter polynomial expressions as (-4.5)X^1+(-2.5)X^0+1X^3");
    System.out.print("1st expression> ");
    Polynomial p = new Polynomial(scan.nextLine());
    System.out.print("2nd expression> ");
		Polynomial q = new Polynomial(scan.nextLine());

    System.out.println("Polynomial p: " + p);
    System.out.println("Polynomial q: " + q);
    System.out.println("p+q: " + p.add(q));
    System.out.println("p-q: " + p.subtract(q));
    System.out.println("p*q: " + p.multiply(q));
  }
}
