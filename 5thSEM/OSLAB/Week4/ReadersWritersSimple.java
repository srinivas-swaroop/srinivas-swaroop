// Very Simple Readers–Writers Example (No threads, just logic)
class ReadersWritersSimple {
    public static void main(String[] args) {
        int data = 10;   // Shared data
        boolean writing = false;

        System.out.println("Initial Data = " + data);
        
        // Readers reading
        if (!writing) {
            System.out.println("Reader 1 is reading data: " + data);
            System.out.println("Reader 2 is reading data: " + data);
        }

        // Writer starts writing
        writing = true;
        System.out.println("Writer is writing new data...");
        data = 20;
        writing = false;

        // Readers reading after writing
        if (!writing) {
            System.out.println("Reader 1 reads new data: " + data);
            System.out.println("Reader 2 reads new data: " + data);
        }
    }
}
