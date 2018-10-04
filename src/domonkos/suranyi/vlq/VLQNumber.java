package domonkos.suranyi.vlq;

import java.util.Arrays;


public class VLQNumber {
    private final int[] value;

    public VLQNumber(final int number) {
        value = int2Bytes(number);
    }

    public VLQNumber(final int[] bytes) {
        value = removeLeadingZeros(validateVLQ(bytes));
    }

    public VLQNumber(final byte[] bytes) {
        value = removeLeadingZeros(checkLength(new int[bytes.length]));
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
        return Arrays.stream(array).reduce(0, (left, right) -> (left << 7) + (right - 0b1000_0000)) + 0b1000_0000;
    }

    private static final int[] int2Bytes(final int number) {
        int[] ret = new int[4];
        ret[0] = ((number & 0b0000_1111_1110_0000_0000_0000_0000_0000) >> 21);
        ret[1] = ((number & 0b0001_1111_1100_0000_0000_0000) >> 14);
        ret[2] = ((number & 0b0011_1111_1000_0000) >> 7);
        ret[3] = (number & 0b0111_1111);

        ret = removeLeadingZeros(ret);
        for (int i = 0; i < ret.length - 1; i++)
            ret[i] += 0b1000_0000;
        return ret;
    }

    private static final int[] removeLeadingZeros(final int[] array) {
        for (int i = 0; i < 4; i++)
            if (array[i] != 0)
                return Arrays.copyOfRange(array, i, array.length);
        return new int[] {0};
    }

    @Override
    public String toString() {
        return Integer.toString(intoInt());
    }
}
