import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;

class BlockChainTest {

    @Test
    public void CheckHashParity() throws NoSuchAlgorithmException {
        BlockChain bc = new BlockChain(300);

        Block mine1 = bc.mine(-150);
        bc.append(mine1);

        Block mine2 = bc.mine(-100);
        bc.append(mine2);

        Assertions.assertTrue(bc.isValidBlockChain());

        Block mine3 = bc.mine(-100);
        bc.append(mine3);

        Assertions.assertFalse(bc.isValidBlockChain());
    }

    @Test
    public void CheckRemoveLast() throws NoSuchAlgorithmException {
        BlockChain bc = new BlockChain(300);

        Block mine1 = bc.mine(-150);
        bc.append(mine1);

        Block mine2 = bc.mine(-100);
        bc.append(mine2);

        Assertions.assertTrue(bc.removeLast());
    }

}