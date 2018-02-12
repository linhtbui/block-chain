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

    private int amount;
    private int count = 1;

    public Node first;
    public Node last;

    public BlockChain(int initial) throws NoSuchAlgorithmException {
        Block firstBlock = new Block(0, initial, null);
        first = new Node(firstBlock, null);
        last = first;
    }

    public Block mine(int amount) throws NoSuchAlgorithmException {
        return new Block(count + 1, amount, last.value.getHash());
    }

    public int getSize(){
        return last.value.getNum() + 1;
    }

    public void append(Block blk) throws NoSuchAlgorithmException {
        if(blk.getPrevHash() != last.value.getHash()){
            return;
        }

        Node newNode = new Node(blk, null);
        last.nextNode = newNode;
        last = newNode;
    }

    public boolean removeLast(){
        if(first == last){ return false; }

        Node firstNode = first;
        Node secondLastNode = null;
        while (firstNode.nextNode != last){
            secondLastNode = firstNode;
        }

        secondLastNode.nextNode = null;
        last = secondLastNode;
        return true;
    }

    public Hash getHash() throws NoSuchAlgorithmException {
        return last.value.getHash();
    }

    public boolean isValidBlockChain() throws NoSuchAlgorithmException {
        Node currentNode = first;
        boolean result = true;

        while (currentNode.nextNode != null){
            if(currentNode.value.getHash() != currentNode.nextNode.value.getHash()){
                result = false;
                break;
            }
        }

        return result;
    }

    public void printBalances(){
        int total = this.first.value.getAmount();

        int Aryan = 0, Linh = 0;

        Node currentNode = this.first;
        while (currentNode.nextNode != null){
            Aryan +=currentNode.value.getAmount();
            currentNode = currentNode.nextNode;
        }
        Linh = total - Aryan;

        System.out.printf("Aryan: %d, Linh: %d", Aryan, Linh);
    }

    public String toString(){

        int cnt = 0;
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
