// Import the required libraries for hashing and linked list.
use std::collections::LinkedList;
use sha2::{Sha256, Digest};

// Define a Block struct to represent a block in the blockchain.
#[derive(Debug)]
pub struct Block {
    pub index: usize, // Index of the block in the blockchain.
    pub data: String, // Data contained in the block.
    pub previous_hash: String, // Hash of the previous block.
    pub hash: String, // Hash of the current block.
}

impl Block {
    // Constructor for the Block struct.
    fn new(index: usize, data: &str, previous_hash: &str) -> Block {
        let mut block = Block {
            index,
            data: data.to_string(),
            previous_hash: previous_hash.to_string(),
            hash: String::new(),
        };
        block.hash = block.calculate_hash(); // Calculate the hash of the current block.
        block
    }

    // Method to calculate the hash of the block using SHA-256.
    fn calculate_hash(&self) -> String {
        let input = format!("{}{}{}", self.index, self.data, self.previous_hash);

        // Create a Sha256 instance for hashing.
        let mut hasher = Sha256::new();
        // Perform hashing on the input data.
        hasher.update(input.as_bytes());
        // Convert the hashed bytes to a hexadecimal string.
        format!("{:x}", hasher.finalize())
    }
}

// Define a Blockchain struct to represent the blockchain structure.
#[derive(Debug)]
pub struct Blockchain {
    pub chain: LinkedList<Block>, // LinkedList to store the chain of blocks.
}

impl Blockchain {
    // Constructor for the Blockchain struct.
    pub fn new() -> Blockchain {
        let mut blockchain = Blockchain {
            chain: LinkedList::new(),
        };
        // Add the genesis block (first block) to the blockchain.
        blockchain.chain.push_back(Block::new(0, "Genesis Block", "0"));
        blockchain
    }

    // Method to add a new block to the blockchain.
    pub fn add_block(&mut self, data: &str) {
        // Get the last block in the chain.
        let last_block = self.chain.back().unwrap();
        // Create a new block with the given data and previous block's hash.
        self.chain.push_back(Block::new(last_block.index + 1, data, &last_block.hash));
    }
}