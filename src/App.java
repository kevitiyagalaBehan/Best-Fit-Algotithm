import java.util.ArrayList;
import java.util.Scanner;

// Class representing a memory block
class MemoryBlock {
    int size;
    int id;
    boolean isAllocated;
    int remainingSize; // Added to store the remaining size after allocation

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
        this.isAllocated = false;
        this.remainingSize = size; // Initially, the remaining size is equal to the full size
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List to hold memory blocks
        ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

        // Step 1: Read memory block sizes
        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();

        System.out.print("Enter the sizes of the memory blocks: ");

        for (int i = 0; i < numBlocks; i++) {
            int blockSize = scanner.nextInt();
            memoryBlocks.add(new MemoryBlock(i + 1, blockSize)); // Correctly create and add MemoryBlock objects
        }

        // Step 2: Start allocation requests
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Allocate memory");
            System.out.println("2. View memory blocks");
            System.out.println("3. Exit");

            System.out.print("Enter the choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    allocateMemory(memoryBlocks, scanner);
                    break;
                case 2:
                    viewMemoryBlocks(memoryBlocks);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Allocate memory using the Best Fit strategy
    private static void allocateMemory(ArrayList<MemoryBlock> memoryBlocks, Scanner scanner) {
        System.out.print("Enter the size of the memory request: ");
        int requestSize = scanner.nextInt();

        int bestFitIndex = -1;
        int smallestFitSize = Integer.MAX_VALUE;

        // Finding the best-fit block
        for (int i = 0; i < memoryBlocks.size(); i++) {
            MemoryBlock block = memoryBlocks.get(i);

            if (!block.isAllocated && block.size >= requestSize && block.size < smallestFitSize) {
                bestFitIndex = i;
                smallestFitSize = block.size;
            }
        }

        // Allocate memory if a suitable block is found
        if (bestFitIndex != -1) {
            MemoryBlock allocatedBlock = memoryBlocks.get(bestFitIndex);
            allocatedBlock.isAllocated = true;
            allocatedBlock.remainingSize = allocatedBlock.size - requestSize; // Update remaining size
            System.out.println("Memory allocated in Block ID: " + allocatedBlock.id + " of size: " + requestSize);
        } else {
            System.out.println("No suitable memory block found. Allocation failed.");
        }
    }

    // View the status of memory blocks
    private static void viewMemoryBlocks(ArrayList<MemoryBlock> memoryBlocks) {
        System.out.println("Memory Blocks Status:");
        System.out.println("ID\tSize\tAllocated\tRemaining Size");
        for (MemoryBlock block : memoryBlocks) {
            System.out.println(block.id + "\t" + block.size + "\t" + (block.isAllocated ? "Yes" : "No") + "\t\t" + block.remainingSize);
        }
    }
}
