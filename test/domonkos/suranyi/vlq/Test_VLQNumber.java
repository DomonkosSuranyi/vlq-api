package domonkos.suranyi.vlq;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_VLQNumber {

    @Test
    public void test_asInt() {
        assertEquals(0x00,  new VLQNumber(new byte[] {0x00, 0x00, 0x00, 0x00}).intoInt());
        assertEquals(0x7F,  new VLQNumber(new byte[] {0x00, 0x00, 0x00, 0x7F}).intoInt());
    }
    
    @Test
    public void test_asBytes() {
        assertArrayEquals(new int[] {0x00, 0x00, 0x00, 0x00},  new VLQNumber(0).intoByteArray());
        assertArrayEquals(new int[] {0x00, 0x00, 0x00, 0x7F},  new VLQNumber(0x7F).intoByteArray());
    }

}
