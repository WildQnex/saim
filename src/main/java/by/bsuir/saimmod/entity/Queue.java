package by.bsuir.saimmod.entity;

import java.util.ArrayDeque;

public class Queue {

    private int length;
    private ArrayDeque<Message> deque = new ArrayDeque<>();

    public Queue(int length) {
        this.length = length;
    }

    public void addMessage(Message message) {
        if (deque.size() < length) {
            deque.addLast(message);
        }
    }

    public Message getMessage() {
        if (deque.size() != 0) {
            return deque.pollFirst();
        } else {
            return null;
        }
    }

    public ArrayDeque<Message> getDeque() {
        return deque;
    }

    public boolean isTotallyEmpty(){
        return deque.size() == 0;
    }

    public boolean isEmpty(){
        return deque.size() < length;
    }

    public boolean isNotEmpty(){
        return deque.size() > 0;
    }
}
