package domonkos.suranyi.vlq;

import java.util.Arrays;


public class VLQNumber {
    private final int[] value;

    public VLQNumber(final int number) {
        value = int2Bytes(number);
    }

    public VLQNumber(final int[] bytes) {
        value = validateVLQ(bytes);
    }

    public VLQNumber(final byte[] bytes) {
        value = checkLength(new int[bytes.length]);
        for (int i = 0; i < bytes.length; i++)
            value[i] = bytes[i] & 0xFF;
    }

    public final int[] intoByteArray() {
        return value;
    }

    public final int intoInt() {
        return bytes2Int(value);
    }

    private static final int[] validateVLQ(final int[] array) {
        checkLength(array);
        for (int i = 0; i < array.length; i++)
            if (array[i] < 0 || array[i] > 255)
                throw new IllegalArgumentException("The value " + array[i] + "is invalid as byte");
        return array;
    }

    private static final int[] checkLength(final int[] array) {
        if (array.length > 8)
            throw new NumberFormatException("The given byte array should be 8 btyes long at maximum");
        return array;
    }

    private static final int bytes2Int(final int[] array) {
        return Arrays.stream(array).reduce(0, (left, right) -> (left << 8) + right);
    }

    private static final int[] int2Bytes(final int number) {
        int[] ret = new int[4];
        ret[0] = (byte) ((number & 0xff000000) >> 24);
        ret[1] = (byte) ((number & 0x00ff0000) >> 16);
        ret[2] = (byte) ((number & 0x0000ff00) >> 8);
        ret[3] = (byte) (number & 0x000000ff);
        return ret;
    }
}
