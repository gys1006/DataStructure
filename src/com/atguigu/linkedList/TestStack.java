package com.atguigu.linkedList;

import java.util.Stack;

//栈的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<Object> stack = new Stack<>();
//        入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
//出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }

    }
}
