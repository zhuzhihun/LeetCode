package dataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
232.用栈实现队列（简单）

225.用队列实现栈（简单）
 */
public class StackAndQueue {

    /*
    请你仅使用两个栈实现先入先出队列。
    队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
    实现 MyQueue 类：
    void push(int x) 将元素 x 推到队列的末尾
    int pop() 从队列的开头移除并返回元素
    int peek() 返回队列开头的元素
    boolean empty() 如果队列为空，返回 true ；否则，返回 false
     */
    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack2.add(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            peek();
            return stack1.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack1.isEmpty()){
                while (!stack2.isEmpty()){
                    stack1.add(stack2.pop());
                }
            }
            return stack1.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty()&&stack2.isEmpty();
        }
    }
    /*
    使用队列实现栈的下列操作：

    push(x) -- 元素 x 入栈
    pop() -- 移除栈顶元素
    top() -- 获取栈顶元素
    empty() -- 返回栈是否为空

     */
    class MyStack {

        Queue<Integer> queue;
        int temp_elem;
        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
            temp_elem = 0;
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.offer(x);
            temp_elem = x;
            //刚添加进来的元素即为栈顶元素
        }


        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int size = queue.size();
            while (size>1){
                temp_elem=queue.peek();
                queue.add(queue.remove());
                size--;
            }
            return queue.remove();
        }

        /** Get the top element. */
        public int top() {
            return temp_elem;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
