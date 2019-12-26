package com.atguigu.linkedList;

//该类中实现了两种情况 他们只是在添加链表的时候不同
//第一种方法没有实现在添加时根据数据进行排序
//第二种方法在添加数据时对数据进行排序并进行根据链表的编号进行判断
//第三种情况 对数据的修改

import java.util.Stack;

public class SingleLinked {

    public static void main(String[] args) {
//      进行测试
//      先创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "无用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林兄", "豹子头");

//        加入
//        该方法没有考虑编号
        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        第一种方法的测试
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);


//        第二种方法的测试
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);

        //      显示
        singleLinkedList.list();


//        第三种修改方法测试
        HeroNode newHeroNode = new HeroNode(2, "gys", "第一");
        singleLinkedList.update(newHeroNode);

//       删除节点的测试
        singleLinkedList.del(1);
        singleLinkedList.del(4);


//      显示
        singleLinkedList.list();

//  测试节点的有效个数 不包含头节点

        System.out.println("单链表的有效个数为" + getLength(singleLinkedList.getHead()));


//    测试是否得到了倒数第一个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
        System.out.println("res" + res);

//        测试是否反转
        System.out.println("反转前");
        singleLinkedList.list();
        System.out.println("反转后");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

//        测试逆序打印
        System.out.println("逆序打印");
//       逆序以前
        singleLinkedList.list();
//        逆序打印
        reversePrint(singleLinkedList.getHead());
//        打印之后 原来的数据没有发生变化
        singleLinkedList.list();

    }

    //    方式二 利用栈结构进行实现逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
//        创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
//    将栈中的数据进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


    //    将单链表进行反转
    public static void reverseList(HeroNode head) {
//        如果当前链表为空或者只有一个节点,无需反转,字节返回
        if (head.next == null || head.next.next == null) {
            return;
        }
//        定义一个辅助的变量 帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
//        遍历原来的链表,没遍历一个节点,就将其取出,并放在新的链表的最前面
//        这段话实在是太妙了 需要仔细欣赏
        while (cur != null) {
            next = cur.next; //先保存当前节点的下一个节点
            cur.next = reverseHead.next;   //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;
            cur = next;
        }
//        将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }


    //    查找链表中倒数第K个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
//        得到链表的长度
        int size = getLength(head);
//        第二次遍历size-index位置
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }


//    计算单链表的个数,没有统计头节点

    /**
     * @param head 头节点
     * @return 节点的有效个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0; //空链表
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

//创建SingleLinkedList
class SingleLinkedList {
    //    定义一个头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //    进行添加数据
    public void add(HeroNode heroNode) {
//        因为head节点不能动,这是单项链表 每次需要从头部进行遍历
//        temp相当于指针
        HeroNode temp = head;
//        遍历链表 找到最后
        while (true) {
//            如果找到了链表的最后
            if (temp.next == null) {
                break;
            }
//            如果没有找到链表的最后就往后移动一个节点
            temp = temp.next;
        }
//       当退出while循环时,temp指向最后一个节点
//        将最后的节点的next指向新的节点
        temp.next = heroNode;

    }

    //    第二种方法  根据插入的编号进行排序
    public void addByOrder(HeroNode heroNode) {
//        因为头节点不能动,需要辅助变量找到添加的位置
//        因为是单链表 因为我们找的时temp是位于加入位置的前一个位置
        HeroNode temp = head;
        boolean flag = false;//表示添加的编号是否存在 默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {  //在temp后面进行添加
                break;
            } else if (temp.next.no == heroNode.no) {//说明编号存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("该英雄的编号已经存在,不能进行添加\n", heroNode.no);
        } else {
//            插入到链表中(将原来的链表断开)
            heroNode.next = temp.next;
            temp.next = heroNode;

        }
    }


    //    根据编号进行修改
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        找到需要修改的节点,根据no编号
//        定义一个辅助变量
        HeroNode temp = head.next;
//        修改的数据是否存在
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//到达了链表的最后没有找到
            }
            if (temp.no == heroNode.no) {
//                找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {//没有找到编号
            System.out.println("没有找到需要修改的代码");
        }
    }


    //    删除节点
//    head节点不能动 因此我们需要找到需要删除的前一个节点
//    在比较时时让tenp的no和需要删除的no进行比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//是否找到
        while (true) {
            if (temp.next == null) { //没有该节点
                break;
            }
            if (temp.next.no == no) {
//                找到了待删除节点的前一个no
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
//            进行删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("该节点不存在\n", no);
        }
    }


    //      显示遍历 通过辅助变量来遍历链表
    public void list() {
//        判断链表的数据是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        while (true) {
//            判断是否为最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
//            将temp进行后移
            temp = temp.next;
        }
    }


}


//定义HeroNode
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  //指向下一个节点

    public HeroNode(int no, String name, String hNickname) {
        this.no = no;
        this.nickName = hNickname;
        this.name = name;
    }

    //    重写tostring

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
