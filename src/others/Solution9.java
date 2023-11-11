public class Solution9 {
    public static void main(String[] args) {
        Solution9 so = new Solution9();
        System.out.println(so.isPalindrome(-2));
        System.out.println(so.isPalindrome(0));
        System.out.println(so.isPalindrome(9));
        System.out.println(so.isPalindrome(121));
        System.out.println(so.isPalindrome(12321));
        System.out.println(so.isPalindrome(10401));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        if (x % 10 == 0) {
            return false;
        }

        return checkSymmetry2(x);
    }

    public boolean checkSymmetry(int x) {
        String str = String.valueOf(x);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public boolean checkSymmetry2(int x) {
        int newX = 0;
        int tempX = x;
        int currentNumber = 0;
        while(tempX > 0) {
            // 获取个位数字
            currentNumber = tempX % 10;
            // 将个位数字加在新整数的末尾
            newX = newX * 10 + currentNumber;
            // 旧整数去掉个位数字
            tempX /= 10;
        }
        return x == newX;
    }
}
