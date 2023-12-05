using System;

class Program {
    static void Main(string[] args) {
        // int[] arrayToSort = { 5, 3, 7, 6, 2, 9 };

        // // Print the unsorted array
        // Console.WriteLine("Unsorted array: ");
        // foreach (var item in arrayToSort) {
        //     Console.Write(item + " ");
        // }
        // Console.WriteLine();

        // // Sort the array
        // QuickSort.Sort(arrayToSort);

        // // Print the sorted array
        // Console.WriteLine("Sorted array: ");
        // foreach (var item in arrayToSort) {
        //     Console.Write(item + " ");
        // }

        Blockchain myBlockchain = new Blockchain();

        myBlockchain.AddBlock("Block 1 Data");
        myBlockchain.AddBlock("Block 2 Data");
        myBlockchain.AddBlock("Block 3 Data");

        Console.WriteLine("Blockchain:");
        myBlockchain.PrintChain();
    }
}
