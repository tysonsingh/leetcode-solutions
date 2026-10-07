class MyCircularQueue {

    List<Integer> cirq;
    int head;
    int rear;
    int size;
    int capacity;
    public MyCircularQueue(int k) {
        cirq = new ArrayList<>(k);

        // Initialize k empty positions
        for (int i = 0; i < k; i++) {
            cirq.add(0);
        }

        head = 0;
        rear = 0;
        size = 0;
        capacity = k;
    }
    
    public boolean enQueue(int value) {
        if (size == capacity) {
            return false;
        }

        cirq.set(rear, value);
        rear = (rear + 1) % capacity;
        size++;

        return true;
    }
    
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }

        head = (head + 1) % capacity;
        size--;

        return true;
    }
    
    public int Front() {
        if (size == 0) {
        return -1;
        }

        return cirq.get(head);
    }
    
    public int Rear() {
        if (size == 0) {
            return -1;
        }

        int last = (rear - 1 + capacity) % capacity;
        return cirq.get(last);
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */