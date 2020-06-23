public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size =0;

    }
    public LoopQueue(){
        this (10);
    }
    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public void enqueue(E e){
        data [tail] = e;
        size++;
        tail = (tail +1) % data.length;
        if (tail == front) // queue is full
            resize(getCapacity() * 2);
    }
    @Override
    public E dequeue(){
        if(isEmpty())
            throw new IllegalArgumentException("dequeue failed, queue is empty");
        E ret = data [front];
        data[front] = null;
        front = (front+1) % data.length;
        size --;
        if (size == getCapacity() / 4 && getCapacity()/2!=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E []) new Object[newCapacity +1];
        for (int i =0;i<size;i++)
            newData[i] = data[(i+front)%data.length];
        data =  newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("lQueue:size=%d, capacity =%d, f=%d, t=%d\n", size, getCapacity(),front, tail));
        res.append("front [");
        for(int i =front; i!= tail; i= (i+1)% data.length ){
            res.append(data[i]);
            if((i+1)% data.length != tail )
                res.append(",");
        }
        res.append("] tail");
        return res.toString();
    }
    public static void main(String[] args) {
        LoopQueue<Integer> aq= new LoopQueue<>();
        for (int i =0;i<5; i++){
            aq.enqueue(i);
            System.out.println(aq);
        }
        for (int i =0;i<5; i++){
            aq.dequeue();
            System.out.println(aq);
        }
        System.out.println(aq.isEmpty());
    }
}
