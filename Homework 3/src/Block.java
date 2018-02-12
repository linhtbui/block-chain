import java.lang.Object;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 
    The block’s number.
    The data contained in the block.
    The hash of the previous node’s block in the list if it exists.
    The discovered nonce value.

 *
 */

public class Block {

	private int num, amount;
	private Hash prevHash, curHash;
	private long nonce;
	
	
	public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("sha-256");
	    md.update(msg.getBytes());
	    byte[] hash = md.digest();
	    return hash;    
	}
	
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

