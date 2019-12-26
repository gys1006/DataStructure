package com.atguigu.linkedList;


//双向链表的实现
public class DoublleLinkedListDemo {
    public static void main(String[] args) {

    }
}

//创建SingleLinkedList
class DoubleLinkedList {
    //    定义一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }


    //      显示遍历 通过辅助变量来遍历链表
    public void list() {
//        判断链表的数据是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
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


    //    进行添加数据
    public void add(HeroNode2 heroNode) {
//        因为head节点不能动,这是单项链表 每次需要从头部进行遍历
//        temp相当于指针
        HeroNode2 temp = head;
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
//        将最后的节点的next指向新的节点 并且将新节点的前一个位置指向最后一个节点的前面
        temp.next = heroNode;
        heroNode.pre = temp;

    }

    //    修改原来的节点 和单项链表的修改基本一样
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
//        找到需要修改的节点,根据no编号
//        定义一个辅助变量
        HeroNode2 temp = head.next;
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


    //  从双向链表中删除节点
//双向链表可以自我删除和单项链表不同(单项链表需要找到需要删除的前一个节点进行删除)
    public void del(int no) {
//        判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空,无法删除");
            return;
        }

        HeroNode2 temp = head.next;  //辅助指针
        boolean flag = false;//是否找到
        while (true) {
            if (temp == null) { //没有该节点
                break;
            }
            if (temp.no == no) {
//                找到了待删除节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
//            进行删除
            temp.pre.next = temp.next;
//            对最后一个节点进行判断
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("该节点不存在\n", no);
        }
    }
}


//定义HeroNode
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;  //指向下一个节点
    public HeroNode2 pre;   //指向上一个节点

    public HeroNode2(int no, String name, String hNickname) {
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
