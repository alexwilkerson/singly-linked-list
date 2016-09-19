import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

/***********************************
  * Startup Class
  * Created by: Alex Wilkerson
  * Homework 1
  * Date: 09.18.2016
  ***********************************/
// this class is used as the starting point
// for the program. this program gives a user
// interface to type in an infix expression and
// receive its postfix counterpart. the program
// also validates the user's input to verify
// that the user has not entered an invalid
// infix expression.
public class Startup {
  private static Scanner scanner = new Scanner(System.in);
  private static MyStack<String> myStack = new MyStack<String>();
  private static String input;
  private static String result;
  private static final String OPERANDS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final String OPERATORS = "+-*/^";
  private static Pattern pattern;
  private static Matcher matcher;
  // regex pattern to validate legal characters
  private static final String INFIX_PATTERN = "^[a-zA-Z+\\-*/()]+$";

  public static void main(String[] args) {
    pattern = Pattern.compile(INFIX_PATTERN);

    // print title, then run loop forever until
    // user quits by typing 'quit'.
    printTitle();
    while(true) {
      getInput();
      printResult();
    }
  }

  // prints the title for the program.
  public static void printTitle() {
    System.out.println();
    System.out.println("******************************");
    System.out.println("* Infix to Postfix Converter *");
    System.out.println("******************************");
    System.out.println();
    System.out.println("This program will take the input of an infix expression\nand output the equivalent postfix expression.");
    System.out.println();
    System.out.println("To quit at any time, type: \"quit\".");
    System.out.println();
  }

  // gets input from the user and quits the program
  // if user inputs 'quit'.
  public static void getInput() {
    System.out.println("Enter an infix expression (type \"quit\" to quit):");
    input = scanner.nextLine();
    if (input.toLowerCase().equals("quit")) {
      System.out.println("Thank you!");
      System.exit(0);
    }
  }

  // first validates if the input sent by the user is
  // acceptable, then prints an error if it is not.
  // then prints the result of the postfix conversion.
  public static void printResult() {
    if (!validate(input)) {
      System.out.println();
      System.out.println("Illegal expression. Invalid characters.");
      System.out.println();
      return;
    }
    if (!checkParens(input)) {
      System.out.println();
      System.out.println("Illegal expression. Parentheses do not match.");
      System.out.println();
      return;
    }
    result = infixToPostfix(input);
    System.out.println();
    System.out.println("Converted postfix expression: " + result);
    System.out.println();
  }

  // verifies a string to see if it has matching
  // sets of parentheses. does so by implementing 
  // MyStack. a ( will add to the stack and a ) will
  // remove from the stack so long as the stack is not
  // empty.
  public static boolean checkParens(String testString) {
    MyStack<String> testStack = new MyStack<String>();
    String[] testArray = testString.split("");

    for (String c : testArray) {
      if (c.equals(")") && !testStack.isEmpty()) {
        testStack.pop();
      } else if (c.equals(")") && testStack.isEmpty()) {
        return false;
      } else if (c.equals("(")) {
        testStack.push(c);
      }
    }

    if (testStack.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  // uses regex pattern to verify that valid
  // characters are used by the user.
  public static boolean validate(String testString) {
    matcher = pattern.matcher(testString);
    return matcher.matches();
  }

  // this is where the magic happens. this function
  // iterates over the user's input after it has been
  // deemed valid by checkParens and validate. the program
  // then looks at each character and determines whether
  // the character should be added to the postfix string,
  // sent to the stack, or popped off the stack. the
  // function then returns a string of the converted
  // expression.
  public static String infixToPostfix(String infix) {
    String postfix = "";
    String[] infixArray = infix.split("");
    for (String c : infixArray) {
      if (OPERANDS.indexOf(c) > -1) {
        postfix += c;
      } else if (c.equals("(")) {
        myStack.push(c);
      } else if (c.equals(")")) {
        while (!myStack.getNthFromLast(0).equals("(")) {
          postfix += myStack.pop();
        }
        myStack.pop();
      } else if (OPERATORS.indexOf(c) > -1) {
        while (!myStack.isEmpty() &&
               !myStack.getNthFromLast(0).equals("(") &&
               precedence(c) <= precedence(myStack.getNthFromLast(0))) {
          postfix += myStack.pop();
        }
        myStack.push(c);
      }
    }
    while(!myStack.isEmpty()) {
      postfix += myStack.pop();
    }
    return postfix;
  } 

  // determines the precendence of the operators
  // and returns a value.
  public static int precedence(String c) {
    if (c.equals("^")) return 2;
    if (c.equals("/") || c.equals("*")) return 1;
    return 0;
  }
}
