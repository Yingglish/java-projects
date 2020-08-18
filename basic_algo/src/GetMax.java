/**
 * 给定两个数a和b，如何不用比较运算符，返回较大的数
 */
public class GetMax {
    public static int flip(int n) {
        return n ^ 1; // 异或，相同0，不同1
    }

    /**
     * n >= 0 返回 1 n < 0 返回1
     */
    public static int sign(int n) {
        // n的符号位与1相与 二进制中符号位0代表正数，1代表负数
        // n>0 : 0&1-->0 ,n<0 : 1&1-->1
        return flip((n >> 31) & 1);
    }

    public static int getMax1(int a, int b) {
        int c = a - b; // a-b的值可能出现溢出，导致返回结果不正确
        int scA = sign(c); // 表示c的符号
        int scB = flip(scA); // 表示与c相反的符号
        return a * scA + b * scB;
    }


    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    public static void main(String[] args) {
        int a = -16;
        int b = 1;
        System.out.println(getMax1(a, b));
//        System.out.println(getMax2(a, b));
//        a = 2147483647;
//        b = -2147480000;
//        System.out.println(getMax1(a, b)); // wrong answer because of overflow
//        System.out.println(getMax2(a, b));

    }
}
