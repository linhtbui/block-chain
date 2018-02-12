import java.security.NoSuchAlgorithmException;

public class BlockChain {

    public class Node {
        public Block value;
        public Node nextNode;

        public Node(Block value, Node next){
            this.value = value;
            this.nextNode = next;
        }
    }
    
    private int count = 1;

    public Node first;
    public Node last;

    public BlockChain(int initial) throws NoSuchAlgorithmException {
        Block firstBlock = new Block(0, initial, null);
        first = new Node(firstBlock, null);
        last = first;
    }

    /**
     * @param amount The amount transacted
     * @return A new Block
     * @throws NoSuchAlgorithmException
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        return new Block(count, amount, last.value.getHash());
    }

    public int getSize(){
        return last.value.getNum() + 1;
    }

    /**
     * @param blk A block to be appended to the blockchain
     * @throws NoSuchAlgorithmException
     */
    public void append(Block blk) throws NoSuchAlgorithmException {
        if(blk.getPrevHash() != last.value.getHash()){
            return;
        }

        Node newNode = new Node(blk, null);
        last.nextNode = newNode;
        last = newNode;
        count++;
    }

    /**
     * @return If the last block was removed
     */
    public boolean removeLast(){
        if(first == last){ return false; }

        Node currentNode = first;
        while (currentNode.nextNode != last){
            currentNode = currentNode.nextNode;
        }

        currentNode.nextNode = null;
        last = currentNode;
        return true;
    }

    public Hash getHash() throws NoSuchAlgorithmException {
        return last.value.getHash();
    }

    /**
     * @return If the blockchain contains any invalid blocks
     * @throws NoSuchAlgorithmException
     */
    public boolean isValidBlockChain() throws NoSuchAlgorithmException {

        Node currentNode = first;
        boolean result = true;

        while (currentNode.nextNode != null){
            if(currentNode.value.getHash() != currentNode.nextNode.value.getPrevHash()){
                result = false;
                break;
            }

            currentNode = currentNode.nextNode;
        }

        return result && !goesBelowZero();
    }

    /**
     * Prints the final balances of Aryan and Linh after going through
     * the whole blockchain history.
     */
    public void printBalances(){
        int total = this.first.value.getAmount();
        int Aryan = total, Linh = 0;

        if(this.first.nextNode == null){
            System.out.printf("Aryan: %d, Linh: %d", Aryan, Linh);
            return;
        }

        Node currentNode = this.first.nextNode;
        while (currentNode != null){
            Aryan += currentNode.value.getAmount();
            Linh -= currentNode.value.getAmount();
            currentNode = currentNode.nextNode;
        }

        System.out.printf("Aryan: %d, Linh: %d", Aryan, Linh);
    }

    private boolean goesBelowZero(){
        int Aryan = this.first.value.getAmount();
        int Linh = 0;
        boolean isInvalid = false;

        if(this.first.nextNode == null){
            return false;
        }

        Node currentNode = this.first.nextNode;
        while (currentNode != null && !isInvalid){
            Aryan += currentNode.value.getAmount();
            Linh -= currentNode.value.getAmount();

            if(Aryan < 0 || Linh < 0){
                isInvalid = true;
            }

            currentNode = currentNode.nextNode;
        }

        return isInvalid;
    }

    public String toString(){
        Node currentNode = first;

        if(currentNode == null){
            return "";
        }

        StringBuilder retString = new StringBuilder();
        while (currentNode != null){
            retString.append(currentNode.value.toString()).append("\n");
            currentNode = currentNode.nextNode;
        }

        return retString.toString();
    }
}
