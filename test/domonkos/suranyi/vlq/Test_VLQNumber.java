package domonkos.suranyi.vlq;

import static org.junit.Assert.*;

import org.junit.Test;


public class Test_VLQNumber {

    @Test
    public void test_asInt() {
        assertEquals(0x00000000, new VLQNumber(new int[] {0x00, 0x00, 0x00, 0x00}).intoInt());
        assertEquals(0x0000007F, new VLQNumber(new int[] {0x00, 0x00, 0x00, 0x7F}).intoInt());
        assertEquals(0x00000080, new VLQNumber(new int[] {0x00, 0x00, 0x81, 0x00}).intoInt());
        assertEquals(0x00002000, new VLQNumber(new int[] {0x00, 0x00, 0xC0, 0x00}).intoInt());
        assertEquals(0x00003FFF, new VLQNumber(new int[] {0xFF, 0x7F}).intoInt());
        assertEquals(0x00004000, new VLQNumber(new int[] {0x81, 0x80, 0x00}).intoInt());
        assertEquals(0x001FFFFF, new VLQNumber(new int[] {0xFF, 0xFF, 0x7F}).intoInt());
        assertEquals(0x00200000, new VLQNumber(new int[] {0x81, 0x80, 0x80, 0x00}).intoInt());
        assertEquals(0x08000000, new VLQNumber(new int[] {0xC0, 0x80, 0x80, 0x00}).intoInt());
        assertEquals(0x0FFFFFFF, new VLQNumber(new int[] {0xFF, 0xFF, 0xFF, 0x7F}).intoInt());
    }

    @Test
    public void test_asBytes() {
        assertArrayEquals(new int[] {0x00}, new VLQNumber(0x00000000).intoByteArray());
        assertArrayEquals(new int[] {0x7F}, new VLQNumber(0x0000007F).intoByteArray());
        assertArrayEquals(new int[] {0x81, 0x00}, new VLQNumber(0x00000080).intoByteArray());
        assertArrayEquals(new int[] {0xC0, 0x00}, new VLQNumber(0x00002000).intoByteArray());
        assertArrayEquals(new int[] {0xFF, 0x7F}, new VLQNumber(0x00003FFF).intoByteArray());
        assertArrayEquals(new int[] {0x81, 0x80, 0x00}, new VLQNumber(0x00004000).intoByteArray());
        assertArrayEquals(new int[] {0xFF, 0xFF, 0x7F}, new VLQNumber(0x001FFFFF).intoByteArray());
        assertArrayEquals(new int[] {0x81, 0x80, 0x80, 0x00}, new VLQNumber(0x00200000).intoByteArray());
        assertArrayEquals(new int[] {0xC0, 0x80, 0x80, 0x00}, new VLQNumber(0x08000000).intoByteArray());
        assertArrayEquals(new int[] {0xFF, 0xFF, 0xFF, 0x7F}, new VLQNumber(0x0FFFFFFF).intoByteArray());
    }
}
