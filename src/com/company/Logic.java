package com.company;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    public boolean bracketStructureValidationWithStack (String str){
        Stack<String> stack = new Stack<>();
        return log(str,stack);
    }

    public boolean bracketStructureValidationWithMyStack(String str){
        MyStack<String> mystack = new MyStack<>();
        return log(str, mystack);
    }

    private boolean log (String str, Stack<String> stack){
        Pattern opensumb = Pattern.compile("[{(\\[]");
        Pattern closesumb = Pattern.compile("[}\\])]");
        Pattern allsumb = Pattern.compile("[^{\\[(}\\])]");
        Matcher firstmatcher = closesumb.matcher(String.valueOf(str.charAt(0)));
        Matcher secondmatcher = opensumb.matcher(String.valueOf(str.charAt(str.length()-1)));
        Matcher allmatcher = allsumb.matcher(str);

        if (firstmatcher.find() || secondmatcher.find() || allmatcher.find()) {
            return false;
        } else {
            stack.push(String.valueOf(str.charAt(0)));
            String topElement;
            for (int i = 1;i < str.length();i++) {
                if (!stack.empty()){
                    topElement = stack.peek();
                    if (topElement.equals("(") && str.charAt(i) == ')'
                            || topElement.equals("{") && str.charAt(i) == '}'
                            || topElement.equals("[") && str.charAt(i) == ']') {
                        stack.pop();
                    } else {
                        stack.push(String.valueOf(str.charAt(i)));
                    }
                } else {
                    stack.push(String.valueOf(str.charAt(i)));
                }
            }
        }
        return stack.empty();
    }

    private boolean log (String str, MyStack<String> mystack) {
        if(str.length() != 0) {
            Pattern opensumb = Pattern.compile("[{(\\[]");
            Pattern closesumb = Pattern.compile("[}\\])]");
            Pattern allsumb = Pattern.compile("[^{\\[(}\\])]");
            Matcher firstmatcher = closesumb.matcher(String.valueOf(str.charAt(0)));
            Matcher secondmatcher = opensumb.matcher(String.valueOf(str.charAt(str.length() - 1)));
            Matcher allmatcher = allsumb.matcher(str);

            if (firstmatcher.find() || secondmatcher.find() || allmatcher.find()) {
                return false;
            } else {
                mystack.push(String.valueOf(str.charAt(0)));
                String topElement;
                for (int i = 1; i < str.length(); i++) {
                    if (!mystack.empty()) {
                        topElement = mystack.peek();
                        if (topElement.equals("(") && str.charAt(i) == ')'
                                || topElement.equals("{") && str.charAt(i) == '}'
                                || topElement.equals("[") && str.charAt(i) == ']') {
                            mystack.pop();
                        } else {
                            mystack.push(String.valueOf(str.charAt(i)));
                        }
                    } else {
                        mystack.push(String.valueOf(str.charAt(i)));
                    }
                }
            }
            return mystack.empty();
        }
        return false;
    }

    public boolean checkForCorrectness(String str) {

        if (str.length() == 0) {
            return true;
        }
        if (str.contains("()")) {
            return checkForCorrectness(str.replaceFirst("\\(\\)", ""));
        }

        if (str.contains("[]")) {
            return checkForCorrectness(str.replaceFirst("\\[]", ""));
        }

        if (str.contains("{}")) {
            return checkForCorrectness(str.replaceFirst("\\{}", ""));
        } else {
            return false;
        }
    }


}
