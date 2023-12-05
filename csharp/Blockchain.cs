using System;

public class Block {
    public string Data;
    public Block? PreviousBlock;

    public Block(string data, Block? previousBlock) {
        Data = data;
        PreviousBlock = previousBlock;
    }
}

public class Blockchain {
    private Block? _lastBlock;

    public void AddBlock(string data) {
        _lastBlock = new Block(data, _lastBlock);
    }

    public void PrintChain() {
        var currentBlock = _lastBlock;
        while (currentBlock != null) {
            Console.WriteLine(currentBlock.Data);
            currentBlock = currentBlock.PreviousBlock;
        }
    }
}
