import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    public void getHashTest() throws NoSuchAlgorithmException {
        Hash ph1 = new Hash(new byte[]{
                (byte) 0, (byte) 123, (byte) 134, (byte) 102
        });
        Block b1 = new Block(12, 400, ph1, 1238L);

        //Assertions.assertTrue(Pattern.matches("Block (\\d+) \\(Amount: (-?\\d+), Nonce: (\\d+), prevHash: ([A-E0-9]+), hash: ([A-E0-9]+)\\)",
        //        b1.toString()));
    }

}