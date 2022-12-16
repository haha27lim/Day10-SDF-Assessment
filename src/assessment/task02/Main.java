package assessment.task02;

import java.io.Console;

public class Main {


  // variable to store the last result
  private static double $last = 0;

  public static void main(String[] args) {
    Console cons = System.console();

    System.out.println("Welcome.");

    while (true) {
      String input = cons.readLine("> ");

      if (input.equals("exit")) {
        System.out.println("Bye Bye");
        break;
    }

      // split the input into operands and operator
      String[] parts = input.split(" ");
      double operand1 = 0;
      double operand2 = 0;
      String operator = "";

      // check if the first operand is the special keyword
      if (parts[0].equals("$last")) {
        // use the last result as the first operand
        operand1 = $last;
      } else {
        // read the first operand as normal
        operand1 = Double.parseDouble(parts[0]);
      }

      // check if the second operand is the special keyword
      if (parts[2].equals("$last")) {
        // use the last result as the second operand
        operand2 = $last;
      } else {
        // read the second operand as normal
        operand2 = Double.parseDouble(parts[2]);
      }

      // read the operator
      operator = parts[1];

      double result = 0;
      switch (operator) {
        case "+":
          result = operand1 + operand2;
          break;
        case "-":
          result = operand1 - operand2;
          break;
        case "*":
          result = operand1 * operand2;
          break;
        case "/":
          result = operand1 / operand2;
          break;
        default:
          System.err.println("Invalid operator. Please use +, -, *, or /. Please try again.");
          continue;
      }

      // store the result as the last result
      $last = result;
      System.out.println(result);
    }
  }
}
