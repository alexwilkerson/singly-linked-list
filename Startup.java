import java.util.Scanner;
import java.util.*;

public class Startup {
  public static Scanner scanner = new Scanner(System.in);
  public static MyStack<String> myStack = new MyStack<String>();
  public static String input;
  public static String result;
  public static String operands = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  public static String operators = "+-*/^";

  public static void main(String[] args) {

    printTitle();
    while(true) {
      getInput();
      printResult();
    }
  }

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

  public static void getInput() {
    System.out.println("Enter an infix expression (type \"quit\" to quit):");
    input = scanner.nextLine();
    if (input.trim().toLowerCase().equals("quit")) System.exit(0);
  }

  public static void printResult() {
    if (!checkParens(input)) {
      System.out.println();
      System.out.println("Illegal expression. Parentheses do not match.");
      System.out.println();
      return;
    }
    result = infixToPostfix(input);
    System.out.println();
    System.out.println("Postfix converted expression: " + result);
    System.out.println();
  }

  public static boolean checkParens(String testString) {
    MyStack<String> testStack = new MyStack<String>();
    String[] testArray = testString.trim().split("");

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

  public static String infixToPostfix(String infix) {
    String postfix = "";
    String[] infixArray = infix.trim().split("");
    for (String c : infixArray) {
      if (operands.indexOf(c) > -1) {
        postfix += c;
      } else if (c.equals("(")) {
        myStack.push(c);
      } else if (c.equals(")")) {
        while (!myStack.getNthFromLast(0).equals("(")) {
          postfix += myStack.pop();
        }
        myStack.pop();
      } else if (operators.indexOf(c) > -1) {
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

  public static int precedence(String c) {
    if (c.equals("^")) return 2;
    if (c.equals("/") || c.equals("*")) return 1;
    return 0;
  }
}
