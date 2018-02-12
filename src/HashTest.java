import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    @Test
    public void checkValidHash(){
        Hash h2 = new Hash(new byte[]{
                (byte) 0, (byte) 0, (byte) 0,
                (byte) 123, (byte) 159
        });
        Assertions.assertTrue(h2.isValid());
    }

    @Test
    public void checkInvalidHash(){
        Hash h1 = new Hash(new byte[]{
                (byte) 0, (byte) 123, (byte) 134, (byte) 102
        });
        Assertions.assertFalse(h1.isValid());
    }

    @Test
    public void checkInvalidHashLength(){
        Hash h1 = new Hash(new byte[]{
                (byte) 134, (byte) 102
        });
        Assertions.assertFalse(h1.isValid());
    }

    @Test
    public void checkEqualHashes(){
        Hash h1 = new Hash(new byte[]{
                (byte) 134, (byte) 102
        });
        Hash h2 = new Hash(new byte[]{
                (byte) 134, (byte) 102
        });

        Assertions.assertTrue(h1.equals(h2));
    }

    @Test
    public void checkUnequalHashes(){
        Hash h1 = new Hash(new byte[]{
                (byte) 132, (byte) 102
        });
        Hash h2 = new Hash(new byte[]{
                (byte) 134, (byte) 102
        });

        Assertions.assertFalse(h1.equals(h2));
    }

    @Test
    public void toStringTest(){
        Hash h1 = new Hash(new byte[]{
                (byte) 0, (byte) 123, (byte) 134, (byte) 102
        });
        Assertions.assertEquals(h1.toString(), "007B8666");
    }
}