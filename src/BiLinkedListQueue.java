public class BiLinkedListQueue<E> implements Queue <E> {
    private BiLinkedList<E> biLinkedList = new BiLinkedList<>();

    @Override
    public int getSize() {
        return biLinkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return biLinkedList.isEmpty();
    }

    @Override
    public E dequeue() {
        return biLinkedList.removeFirst();
//        return biLinkedList.removeLast();
    }

    @Override
    public void enqueue(E e) {
        biLinkedList.addLast(e);
//        biLinkedList.addFirst(e);
    }
    @Override
    public E getFront() {
        return biLinkedList.getFirst();
    }

    @Override
    public String toString(){
        return biLinkedList.toString();
    }

    public static void main(String[] args) {
        BiLinkedListQueue<Integer> blist_que = new BiLinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            blist_que.enqueue(i);
            System.out.println(blist_que);
        }
        for (int i = 0; i < 10; i++) {
            blist_que.dequeue();
            System.out.println(blist_que);
        }
    }
}
