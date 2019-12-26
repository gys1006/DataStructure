package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        System.out.println("测试数组模拟环形队列");

//        创建一个队列
        CircleQueue Queue = new CircleQueue(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s/*/(show):显示队列");
            System.out.println("e(exit):推出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):获取数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    Queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    Queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = Queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':  //查看队列头的数据
                    try {
                        int res = Queue.headQueue();
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
class CircleQueue {
    private int maxSize;
    private int front;  //头信息前面的一个
    private int rear;
    private int[] arr;

    //        创建队列的构造器
    public CircleQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//执行队列头位置
        rear = 0;//指向队列尾
    }

    //        判断是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //出队列
    public int getQueue() {
//            判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
            //包含的有return,下面的数据不会执行
        }

//        如果不使用中间变量,没有办法对frout进行操作
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //        显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
//      思路 从frout进行遍历 遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //    求出当前队列的有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    //        显示头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return arr[front];
    }
}

