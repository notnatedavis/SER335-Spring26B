/* Labs/Lab 4/Task2.java
 * 
 * Task 2 Code Snippet
 * SEI CERT Guideline: ERR00-J
 * Do not suppress or ignore checked exceptions
 */

public class Task2 {
    public static void main(String[] args) {
        try {
            showFile(args);
        } catch (IOException e) {
            // handle the exception at the appropriate level
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void showFile(String[] args) throws IOException {
        FileInputStream inputFileStream = null;
        try {
            inputFileStream = new FileInputStream(args[0]);
            readAndShowFile(inputFileStream);
        } catch (FileNotFoundException exc) {
            // rethrow as a more specific, or the same, exception
            throw new IOException("File not found: " + args[0], exc);
        } finally {
            // ensure the stream is closed
            if (inputFileStream != null) {
                try {
                    inputFileStream.close();
                } catch (IOException e) {
                    // log and handle close exception appropriately
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }
}