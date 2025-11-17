// Readers–Writers Problem using Mutex Logic (Non-threaded Simulation)
class ReadersWritersMutex {
    public static void main(String[] args) {
        int data = 10;           // Shared resource
        int readCount = 0;       // Number of readers currently reading
        boolean mutex = true;    // Mutex for readCount
        boolean rw_mutex = true; // Mutex for reader-writer access

        System.out.println("Initial Data = " + data);

        // Reader 1 tries to read
        if (mutex && rw_mutex) {
            mutex = false; // Lock mutex
            readCount++;
            if (readCount == 1) rw_mutex = false; // First reader locks rw_mutex
            mutex = true; // Unlock mutex
            System.out.println("Reader 1 is reading data: " + data);
        }

        // Reader 2 tries to read
        if (mutex && rw_mutex == false) {
            mutex = false;
            readCount++;
            System.out.println("Reader 2 is reading data: " + data);
            mutex = true;
        }

        // Writer tries to write
        if (rw_mutex) {
            System.out.println("Writer waiting... readers active.");
        } else {
            // All readers finished
            rw_mutex = true; // Writers get chance
        }

        // Readers finished reading
        mutex = false;
        readCount -= 2; // Both readers done
        if (readCount == 0) rw_mutex = true; // Release writer lock
        mutex = true;

        // Writer writes now
        if (rw_mutex) {
            rw_mutex = false; // Writer locks
            System.out.println("Writer is writing new data...");
            data = 20;
            rw_mutex = true; // Unlock
        }

        // Readers again
        if (rw_mutex) {
            System.out.println("Reader 1 reads new data: " + data);
            System.out.println("Reader 2 reads new data: " + data);
        }
    }
}
