package src;
// Import the required libraries for hashing and linked list.
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

// Define a Block class to represent a block in the blockchain.
class Block {
    int index; // Index of the block in the blockchain.
    String data; // Data contained in the block.
    String previousHash; // Hash of the previous block.
    String hash; // Hash of the current block.

    // Constructor for the Block class.
    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash(); // Calculate the hash of the current block.
    }

    // Method to calculate the hash of the block using SHA-256.
    public String calculateHash() {
        String input = index + data + previousHash;
        try {
            // Create a MessageDigest instance for SHA-256 hashing.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Perform hashing on the input data.
            byte[] hashBytes = digest.digest(input.getBytes());
            // Convert the hashed bytes to a hexadecimal string.
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }
            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

// Define a Blockchain class to represent the blockchain structure.
public class Blockchain {
    LinkedList<Block> chain; // LinkedList to store the chain of blocks.

    // Constructor for the Blockchain class.
    public Blockchain() {
        chain = new LinkedList<>();
        // Add the genesis block (first block) to the blockchain.
        chain.add(new Block(0, "Genesis Block", "0"));
    }

    // Method to add a new block to the blockchain.
    public void addBlock(String data) {
        // Get the last block in the chain.
        Block lastBlock = chain.getLast();
        // Create a new block with the given data and previous block's hash.
        chain.add(new Block(lastBlock.index + 1, data, lastBlock.hash));
    }

    // Main method to demonstrate the usage of the Blockchain class.
    public static void main(String[] args) {
        // Create a new blockchain.
        Blockchain myBlockchain = new Blockchain();
        // Add blocks to the blockchain.
        myBlockchain.addBlock("Block 1");
        myBlockchain.addBlock("Block 2");
        myBlockchain.addBlock("Block 3");

        // Iterate through the blockchain and print each block's properties.
        for (Block block : myBlockchain.chain) {
            System.out.println("Block index: " + block.index);
            System.out.println("Data: " + block.data);
            System.out.println("Previous hash: " + block.previousHash);
            System.out.println("Hash: " + block.hash);
            System.out.println();
        }
    }
}
