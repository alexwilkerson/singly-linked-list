import java.util.Scanner;
import java.util.*;

public class Startup {
  public static Scanner scanner = new Scanner(System.in);
  public static MyStack<String> myStack = new MyStack<String>();
  public static String input;
  public static String operands = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  public static String operators = "+=*/^";

  public static void main(String[] args) {

    printTitle();
    while(true) {
      printInput();
      System.out.println(infixToPostfix(input));
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

  public static void printInput() {
    System.out.println("Enter an infix expression (type \"quit\" to quit):");
    input = scanner.nextLine();
    if (input.trim().toLowerCase().equals("quit")) System.exit(0);
  }

  public static String infixToPostfix(String infix) {
    String postfix = "";
    String[] infixArray = infix.trim().split("");
    for (String c : infixArray) {
      System.out.println(postfix);
      System.out.println(myStack);
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
        while (!myStack.isEmpty() && !myStack.getNthFromLast(0).equals("(") && precedence(c) <= precedence(myStack.getNthFromLast(0))) {
          postfix += myStack.pop();
        }
        if (c.equals("-")) myStack.push("e");
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
    if (c.equals("-") || c.equals("+")) return 0;
    return -1;
  }
}
