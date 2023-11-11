public class Solution13 {
    public static void main(String[] args) {
        Solution13 so = new Solution13();
        System.out.println(so.romanToInt("III"));
        System.out.println(so.romanToInt("IV"));
        System.out.println(so.romanToInt("IX"));
        System.out.println(so.romanToInt("MCMXCIV"));
        System.out.println(so.romanToInt("LVIII"));
        System.out.println(so.romanToInt("DXCIV"));
    }

    public int romanToInt(String s) {
        StringBuilder str = new StringBuilder(s);
        int result = 0;
        // 处理数字4
        result =  dealWithSpecialNumber(str, "IV", 4, result);
        // 处理数字9
        result =  dealWithSpecialNumber(str, "IX", 9, result);
        // 处理数字40
        result =  dealWithSpecialNumber(str, "XL", 40, result);
        // 处理数字90
        result =  dealWithSpecialNumber(str, "XC", 90, result);
        // 处理数字400
        result =  dealWithSpecialNumber(str, "CD", 400, result);
        // 处理数字900
        result =  dealWithSpecialNumber(str, "CM", 900, result);
        // 处理普通数字
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 'I':
                    result += 1;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    result += 10;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    result += 100;
                    break;
                case 'D':
                    result += 500;
                    break;
                    default:
                        result += 1000;
            }
        }
        return result;
    }

    public int dealWithSpecialNumber(StringBuilder target, String key, int value, int res) {
        int indexNumber = target.indexOf(key);
        if (indexNumber > -1) {
            res += value;
            target.replace(indexNumber, indexNumber + 2, "");
        }
        return res;
    }
}
