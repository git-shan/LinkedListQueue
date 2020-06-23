public class LinkedListQueue <E> implements Queue <E> {
    private class Node{
        public E e;
        public Node next;
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){this(e,null);}

        public Node() {
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }
    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        size = 0;
        tail = new Node();
        head = new Node();
    }

    @Override
    public void enqueue(E e){
        Node cur = new Node(e,tail.next);
        if(size==0)
            head.next = cur;
        else
            tail.next = cur;
        tail = cur;
        size++;
    }
    @Override
    public E dequeue(){
        if(size==0){
            throw new IllegalArgumentException("dequeue failed! empty queue");
        }
        Node cur = head.next;
        head.next = cur.next;
        cur.next = null;
        size--;
        return cur.e;
    }
    @Override
    public boolean isEmpty(){
        return (size==0);
    }
    @Override
    public E getFront(){
        return (tail.e);
    }
    @Override
    public int getSize(){
        return size;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Head->");
        for (Node cur = head.next; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("Tail");
        return res.toString();
    }
    public static void main(String[] args) {
        LinkedListQueue<Integer> list_que = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            list_que.enqueue(i);
            System.out.println(list_que);
        }
        for (int i = 0; i < 10; i++) {
            list_que.dequeue();
            System.out.println(list_que);
        }
    }
}
