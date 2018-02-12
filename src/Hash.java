import java.util.Arrays;

public class Hash {
	
	private byte[] hash;
	/**
	 * Hash Constructor
	 * @param data: an array of byte
	 */

	public Hash(byte[] data) {
		this.hash = data;
	}
	
	/**
	 * 
	 * @return: hash, the byte array of current Hash
	 */

	public byte[] getData() {
		return hash;
	}
	
	/**
	 * 
	 * @return boolean: true if 3 first digit of hash is 0, else false
	 */

	public boolean isValid() {
		if(hash.length < 3){ return false; }
		return 	(hash[0] == 0) && 
				(hash[1] == 0) && 
				(hash[2] == 0);
	}
	
	/**
	 * @return: String format of byte b in hexadecimal 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    for (byte b : hash) {
	        sb.append(String.format("%02X", Byte.toUnsignedInt(b)));
	    }
	    return sb.toString();	    
	}
	/**
	 * @return: boolean : true if two hash objects are equal, else false
	 */
	public boolean equals(Object other) {
		if(!(other instanceof Hash)){
			return false;
		}

		return Arrays.equals(this.hash, ((Hash) other).hash);
	}

}
