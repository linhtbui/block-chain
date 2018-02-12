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

		boolean valid = false;

		while (!valid){
            this.curHash = computeHash(this.num, this.amount, this.nonce, this.prevHash);

            if(this.curHash.isValid()){
                valid = true;
            } else {
                this.nonce++;
            }
        }
	}
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.num = num;
		this.amount = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;

        this.curHash = computeHash(this.num, this.amount, this.nonce, this.prevHash);
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

    /**
     * @param num The index of the block in the chain
     * @param amount The amount transacted
     * @param nonce An arbitrary number that can only be used once
     * @param prevHash The hash of the previous Block in the chain
     * @return The hash of the new Block
     * @throws NoSuchAlgorithmException
     */
    public static Hash computeHash(int num, int amount, long nonce, Hash prevHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");

        ByteBuffer byte1 = ByteBuffer.allocate(Integer.BYTES);
        ByteBuffer byte2 = ByteBuffer.allocate(Integer.BYTES);
        ByteBuffer byte3 = ByteBuffer.allocate(Long.BYTES);       		;

        byte1.putInt(num);
        byte2.putInt(amount);
        byte3.putLong(nonce);

        md.update(byte1.array());
        md.update(byte2.array());
        if (prevHash != null) {
            md.update(prevHash.getData());
        }
        md.update(byte3.array());

        byte[] hash = md.digest();

        return new Hash(hash);
    }

	public String toString() {
		return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)",
				num, amount, nonce, (prevHash == null) ? "null" : prevHash.toString(), curHash.toString());
	}
	
}
