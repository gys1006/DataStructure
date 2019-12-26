package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {

//        创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):推出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):获取数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("去除的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':  //查看队列头的数据
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("取出的数据是%d\n", res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':  //查看队列头的数据
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");


    }

}

//    使用数组模拟队列
class ArrayQueue {
    private int maxSize;
    private int front;  //头信息前面的一个
    private int rear;
    private int[] arr;

    //        创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//执行队列头的前一个位置
        rear = -1;//指向队列尾,指向队列尾的最后一个数据
    }

    //        判断是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //出队列
    public int getQueue() {
//            判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
            //包含的有return,下面的数据不会执行
        }
        front++;
        return arr[front];
    }

    //        显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //        显示头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return arr[front+1];
    }
}

