import java.util.Arrays;

public class Hash {
	
	private byte[] hash;

	public Hash(byte[] data) {
		this.hash = data;
	}

	public byte[] getData() {
		return hash;
	}

	public boolean isValid() {
		if(hash.length < 3){ return false; }
		return 	(hash[0] == 0) && 
				(hash[1] == 0) && 
				(hash[2] == 0);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    for (byte b : hash) {
	        sb.append(String.format("%02X", Byte.toUnsignedInt(b)));
	    }
	    return sb.toString();	    
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Hash)){
			return false;
		}

		return Arrays.equals(this.hash, ((Hash) other).hash);
	}

}
