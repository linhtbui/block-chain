public class Block {

	private int num, amount;
	private Hash prevHash, curHash;
	private long nonce;
	
	public Block(int num, int amount, Hash prevHash) {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
	}
	public Block(int num, int amount, Hash prevHash, long nonce){
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
	}
	
	public int getNum() {
		return this.num;
	}
	public int getAmount() {
		return this.amount;
	}
	public long getNonce() { 
		return this.nonce;
	}
	public Hash getPrevHash() {
		return this.prevHash;
	}
	public Hash getHash() {
		return this.curHash;
	}
	public String toString() {	
		return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)", 
				num, amount, nonce, prevHash.toString(), curHash.toString());
	}
	
}
