import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

	private int num, amount;
	private Hash prevHash, curHash;
	private long nonce;
	
	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;

		do{
            this.curHash = computeHash(this.num, this.amount, this.nonce, this.prevHash);			this.nonce++;
        } while (!this.curHash.isValid());
	}
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;

        this.curHash= computeHash(this.num, this.amount, this.nonce, this.prevHash);
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
	public Hash getHash() throws NoSuchAlgorithmException {
        return this.curHash;
	}

    public static Hash computeHash(int num, int amount, long nonce, Hash preHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");

        ByteBuffer byte1 = ByteBuffer.allocate(Integer.BYTES);
        ByteBuffer byte2 = ByteBuffer.allocate(Integer.BYTES);
        ByteBuffer byte3 = ByteBuffer.allocate(Long.BYTES);       		;

        byte1.putInt(num);
        byte2.putInt(amount);
        byte3.putLong(nonce);

        md.update(byte1.array());
        md.update(byte2.array());
        if (preHash != null) {
            md.update(preHash.getData());
        }
        md.update(byte3.array());

        byte[] hash = md.digest();

        return new Hash(hash);
    }

	public String toString() {	
		return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)",
				num, amount, nonce, prevHash.toString(), curHash.toString());
	}
	
}
