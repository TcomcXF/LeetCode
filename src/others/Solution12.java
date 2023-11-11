public class Solution12 {
    public static void main(String[] args) {
        Solution12 so = new Solution12();
//        for (int i = 1; i < 10; i++) {
//            System.out.println(so.intToRoman(i));
//        }
        System.out.println(so.intToRoman(58));
        System.out.println(so.intToRoman(1994));
        System.out.println(so.intToRoman(594));

    }

    public String intToRoman(int num) {
        StringBuilder romanStr = new StringBuilder();
        int i = 1;
        int currentNumber = 0;
        StringBuilder currentStr = new StringBuilder();
        while (num > 0) {
            currentNumber = num % 10;
            currentStr.delete(0, currentStr.length());
            // 个，十，百位处理
            switch (i++) {
                case 1:
                    if (currentNumber == 4) {
                        romanStr.insert(0, "IV");
                    } else if (currentNumber == 9) {
                        romanStr.insert(0, "IX");
                    } else {
                        if (currentNumber / 5 > 0) {
                            currentStr.append("V");
                            currentNumber -= 5;
                        }
                        for (int j = 0; j < currentNumber; j++) {
                            currentStr.append("I");
                        }
                        romanStr.insert(0, currentStr);
                    }
                    break;
                case 2:
                    if (currentNumber == 4) {
                        romanStr.insert(0, "XL");
                    } else if (currentNumber == 9) {
                        romanStr.insert(0, "XC");
                    } else {
                        if (currentNumber / 5 > 0) {
                            currentStr.append("L");
                            currentNumber -= 5;
                        }
                        for (int j = 0; j < currentNumber; j++) {
                            currentStr.append("X");
                        }
                        romanStr.insert(0, currentStr);
                    }
                    break;
                case 3:
                    if (currentNumber == 4) {
                        romanStr.insert(0, "CD");
                    } else if (currentNumber == 9) {
                        romanStr.insert(0, "CM");
                    } else {
                        if (currentNumber / 5 > 0) {
                            currentStr.append("D");
                            currentNumber -= 5;
                        }
                        for (int j = 0; j < currentNumber; j++) {
                            currentStr.append("C");
                        }
                        romanStr.insert(0, currentStr);
                    }
                    break;
                default:
                    for (int j = 0; j < currentNumber; j++) {
                        currentStr.append("M");
                    }
                    romanStr.insert(0, currentStr);
            }
            num /= 10;
        }
        return romanStr.toString();
    }
}
