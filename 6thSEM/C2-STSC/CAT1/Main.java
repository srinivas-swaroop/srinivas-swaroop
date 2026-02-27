class List {

    node head = null;

    class node {
        int data;
        node next;
        node prev;

        node(int n) {
            data = n;
            next = null;
            prev = null;
        }
    }

    void insert(int n) {
        node newnode = new node(n);
        if (head == null) {
            head = newnode;
        } else {
            node cur = head;
            while (cur.next != null)
                cur = cur.next;

            cur.next = newnode;
            newnode.prev = cur;
        }
    }

    void display() {
        node cur = head;
        while (cur != null) {
            System.out.print(cur.data + " <-> ");
            cur = cur.next;
        }
        System.out.println("null");
    }

    // SAME LOGIC, NOW CONSISTENT
    void bit() {

        node first = head;
        node last = head;

        node res = null;
        node resend = null;

        // move last to end
        while (last.next != null)
            last = last.next;

        while (first != last) {

            // take from front
            if (first.data <= last.data) {

                if (res == null) {
                    res = resend = first;
                    first = first.next;
                } else {
                    node cur = first.next;
                    node pre = first.prev;

                    // detach first
                    pre.next = cur;
                    cur.prev = pre;

                    // attach to result
                    resend.next = first;
                    first.prev = resend;
                    first.next = null;

                    resend = first;
                    first = cur;
                }

            }
            // take from end
            else {

                if (res == null) {
                    res = resend = last;
                    last = last.prev;
                } else {
                    node cur = last.prev;

                    // detach last
                    cur.next = null;

                    // attach to result
                    resend.next = last;
                    last.prev = resend;
                    last.next = null;

                    resend = last;
                    last = cur;
                }
            }
        }

        // add middle element
        resend.next = first;
        first.prev = resend;
        first.next = null;

        head = res;
    }

    public static void main(String[] args) {

        List l = new List();

        // HARD-CODED BITONIC LIST
        l.insert(1);
        l.insert(4);
        l.insert(8);
        l.insert(6);
        l.insert(2);

        System.out.println("Before sorting:");
        l.display();

        System.out.println("\nAfter bitonic sort:");
        l.bit();
        l.display();
    }
}
