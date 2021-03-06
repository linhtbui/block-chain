import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockChainDriver {

    static Scanner scanner;
    private static BlockChain chain;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //mine: discovers the nonce for a given transaction
        //append: appends a new block onto the end of the chain
        //remove: removes the last block from the end of the chain
        //check: checks that the block chain is valid
        //report: reports the balances of Alice and Bob
        //help: prints this list of commands
        //quit: quits the program

        scanner = new Scanner(System.in);
        chain = new BlockChain(Integer.parseInt(args[0]));
        boolean run = true;
        
        System.out.println(chain.toString());
        do {
            System.out.print("Command? ");
            String input = scanner.nextLine().trim();

            switch (input) {
                    // Maybe works
                case "mine": mine();
                    break;
                case "append": append();
                    break;
                case "remove": remove();
                    break;
                case "check": check();
                    break;
                case "report": report();
                    break;
                    // Works
                case "help": help();
                    break;
                    // Works
                case "quit":
                	System.out.println("Exiting!");
                	run = false;
                	return;
                default: 
                	System.out.println("Invalid command!");
                    continue;
            }
            System.out.print("\n");
            System.out.println(chain.toString());

        } while (run);
    }

    // Commands
    /**
     * Mine a new block
     * @throws NoSuchAlgorithmException
     */
    private static void mine() throws NoSuchAlgorithmException {
        System.out.print("Amount transferred: ");
        int amount = Integer.parseInt(scanner.nextLine());
        Block minedBlock = chain.mine(amount);

        System.out.println(String.format("\namount = %d, nonce = %d",
                minedBlock.getAmount(), minedBlock.getNonce()));
    }
    /**
     * Append the block to the blockchain
     * @throws NoSuchAlgorithmException
     */
    private static void append() throws NoSuchAlgorithmException {
        System.out.print("Amount transferred: ");
        int amount = Integer.parseInt(scanner.nextLine());

        System.out.print("Nonce: ");
        long nonce = Long.parseLong(scanner.nextLine());

        Block newBlock = new Block(chain.last.value.getNum()+1, amount, chain.last.value.getHash(), nonce);
        chain.append(newBlock);
    }
    private static void remove(){
        chain.removeLast();
    }
    private static void check() throws NoSuchAlgorithmException {
        System.out.println("Chain is " + (chain.isValidBlockChain() ? "valid" : "invalid" + "."));
    }
    private static void report(){
        chain.printBalances();
    }
    
    private static void help(){
        System.out.print(
                "Valid commands: -"
                + "\nmine: discovers the nonce for a given transaction"
                + "\nappend: appends a new block onto the end of the chain"
                + "\nremove: removes the last block from the end of the chain"
                + "\ncheck: checks that the block chain is valid"
                + "\nreport: reports the balances of Alice and Bob"
                + "\nhelp: prints this list of commands"
                + "\nquit: quits the program\n");
    }

}
