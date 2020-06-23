public class BiLinkedList<E>{

    private class Node{
        public E e;
        public Node next;
        public Node prev;
        public Node(E e, Node next, Node prev){
            this.e = e;
            this.prev = prev;
            this.next = next;
        }
        public Node(E e){this(e,null,null);}
        public Node(){this(null,null,null);}
        @Override
        public String toString(){
            return (e==null) ? "NULL": e.toString();
        }

    }

//    private Node dummy head;
    private Node dummyHead;
    private Node dummyTail;
    private int size;

    public BiLinkedList(){
        dummyHead = new Node (null,null,null);
        dummyTail = new Node (null,null,null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }
//    constructor using array
    public BiLinkedList(E[] arr){
        this();
        for (E e:arr) {
            this.addLast(e);
        }
    }

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return (size == 0);
    }

    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed, index out of range");
        Node cur = dummyHead.next;
        if(index == size-1){
            cur = dummyTail.prev;
        }
        else {
            for (int i = 0; i < index; i++)
                cur = cur.next;
        }
        return cur.e;
    }
    public E getFirst() {
        return dummyHead.next.e;
//        return get(0);
    }
    public E getLast() {
        return dummyTail.prev.e;
//        return get(size-1);
    }
    public void set(int index,E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed, index out of range");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur !=null ){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }
    public void add (int index, E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, index out of range");
        Node prev = dummyHead;
        for (int i =0;i <index;i++)
            prev = prev.next;
        Node cur = new Node(e,prev.next,prev);
        cur.next.prev = cur;
        prev.next = cur;
        size++;
    }
    public void addLast(E e){
        Node cur = new Node (e, dummyTail, dummyTail.prev);
        cur.prev.next = cur;
        dummyTail.prev = cur;
        size++;
//        add(size, e);
    }
    public void addFirst (E e){
        Node cur = new Node(e,dummyHead.next,dummyHead);
        cur.next.prev = cur;
        dummyHead.next = cur;
        size++;
//        add(0,e);
    }
    public E remove(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed, index out of range");
        Node prev = dummyHead;
//        find prev node of index
        for (int i=0; i<index; i++)
            prev = prev.next;
//        store cur node
        Node cur = prev.next;
//        set prev.next to cur.next to skip cur. maintain bi-directional pointers
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;

        size --;
        return cur.e;
    }
    public E removeFirst(){
        Node cur = dummyHead.next;
        dummyHead.next = cur.next;
        cur.next.prev = dummyHead;
        return cur.e;
    }
    public E removeLast(){
        Node cur = dummyTail.prev;
        dummyTail.prev = cur.prev;
        cur.prev.next = dummyTail;
        return cur.e;
    }
    public void removeValAll (E val) {
       iRemoveAll(val, dummyHead.next,1);
    }
    private void iRemoveAll (E val, Node head, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.print("Call: rmv @"+head+" in ");
        System.out.println(iToString(head));
//        if last node then return -- section 1: basic solution (just return)
        if(head==dummyTail) {
            System.out.print(depthString);
            System.out.print("Return: rmv @"+head+" in ");
            System.out.println(this);
            return;
        }

// remove val matched node  --- section 2: logic implementation from complicated to less complicated
        if(head.e == val) {
//            maintain bi-directional pointers and size to remove 1 node
            head.prev.next = head.next;
            head.next.prev = head.prev;
            size--;
            System.out.print(depthString);
            System.out.print("removed : "  + val + " as " + head + " after remove :");
            System.out.println(this);
        }
//        removeAll remaining nodes  --- section 3: call less complicated solution
        iRemoveAll(val,head.next,depth+1);

        System.out.print(depthString);
        System.out.println("Return: rmv @"+iToString(head));
//        System.out.println(this);
        return;
    }
    private String generateDepthString (int depth) {
        StringBuilder depthString = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            depthString.append("|--");
        }
        return depthString.toString();
    }
    public void removeElement(E e) {
        Node cur;
        for (Node node = dummyHead; (node != null && node.next != null); node = node.next) {
            cur = node.next;
            if (cur.e.equals(e)) {
//        set prev.next to cur.next to skip cur. clear cur
                node.next = cur.next;
                size--;
                return; // remove once, if need to remove all, just delete this line
            }
        }
    }
    @Override
    public String toString(){
        return iToString(dummyHead.next);
    }

    private String iToString(Node head){
        StringBuilder res = new StringBuilder();
        res.append("Head<-->");
        for (Node cur = head; cur != dummyTail; cur = cur.next)
            res.append(cur + "<-->");
        res.append("Tail");
        return res.toString();
    }
    public static void main(String[] args){
        Integer [] arr ={22,11,22,33,44,55,66,66,66,22,33,99,22};
        BiLinkedList <Integer> list = new BiLinkedList<Integer>(arr);
        System.out.println(list);
        list.removeValAll(22);
        System.out.println(list);

        for (int i=0; i<4; i++){
            list.addFirst(i);
            System.out.println(list);
        }
        for (int i=0; i<4; i++){
            list.addLast(i);
            System.out.println(list);
        }
        list.add(5, 777);
        System.out.println(list);
        list.remove(5 );
        System.out.println(list);
        for (int i=0; i<4; i++){
            list.removeFirst();
            System.out.println(list);
        }
        for (int i=0; i<4; i++){
            list.removeLast();
            System.out.println(list);
        }
    }
}
