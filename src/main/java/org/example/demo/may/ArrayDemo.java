package org.example.demo.may;

import java.util.HashSet;

public class ArrayDemo {


    public static void main(String[] args) {
//        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"flower", "hyguj", "flight"}));
    }



    //最长相同前缀
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }



    public static int romanToInt(String s) {
        char[] charArray = s.toCharArray();
        int result = 0;
        for (int i = 0; i < charArray.length; i++) {
            //判断最大数，左侧减，右侧加
            if (i < charArray.length - 1 && getValue(charArray[i]) < getValue(charArray[i+1])){
                result -= getValue(charArray[i]);
            }else {
                result += getValue(charArray[i]);
            }
        }
        return result;
    }

    private static int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    public static int myAtoi(String s) {
        char[] charArray = s.toCharArray();
        int i = 0, sign = 1, result = 0;

        while (i < charArray.length && charArray[i] == ' ') {
            i++;
        }
        if (i >= charArray.length) return 0;

        if (charArray[i] == '+' || charArray[i] == '-') {
            sign = (charArray[i] == '-') ? -1 : 1;
            i++;
        }

        while (i < charArray.length && Character.isDigit(charArray[i])) {
            int digit = charArray[i] - '0';
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > 7)) { // 7是MAX_VALUE%10的值
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }
        return result * sign;
    }


    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            //数学预测，防止正向溢出
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            //防止负向溢出
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
        }
        return result;
    }


    public static String convert(String s, int numRows) {
        if(numRows == 1) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            if (curRow == 0) {
                goingDown = true;
            } else if (curRow == numRows - 1) {
                goingDown = false;
            }
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);//头结点
        //两结点和当前结点
        ListNode p = l1, q = l2, current = dummyHead;
        int carry = 0; //进位
        while (p != null || q != null) {
            int x  = p != null ? p.val : 0; //一链表当前结点值
            int y  = q != null ? q.val : 0; //二链表当前结点值
            int sum = carry + x + y; //两链表当前结点值和
            carry = sum / 10; //进位
            current.next = new ListNode(sum % 10); //当前结点值
            current = current.next; //当前结点指针后移
            if (p != null) p = p.next; //一链表指针后移
            if (q != null) q = q.next; //二链表指针后移
        }
        if (carry > 0) {
            current.next = new ListNode(carry); //如果进位不为0，则添加一个新的结点
        }
        return dummyHead.next; //返回头结点的下一个结点
    }


      public static class ListNode {
          int val;
          ListNode next;

          ListNode() {
          }

          ListNode(int val) {
              this.val = val;
          }

          ListNode(int val, ListNode next) {
              this.val = val;
              this.next = next;
          }

          @Override
          public String toString() {
              return "ListNode{" +
                      "val=" + val +
                      ", next=" + next +
                      '}';
          }
      }


    public static String longestPalindrome(String s) {
        if(s.length() < 2){
            return s;
        }
        int max = 1;
        int begin = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray.length; j++) {
                if(j-i+1 > max && isPalindrome(s.substring(i, j+1))){
                    max = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max);
    }




    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static int[] removeDuplicates(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int[] result = new int[hashSet.size()];
        int index = 0;
        for (int num : hashSet){
            result[index++] = num;
        }
        return result;
    }


    public static int getFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return getFibonacci(n-1) + getFibonacci(n-2);
    }


    //是否是元音字母
    public static int countVowels(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                count++;
            }
        }
        return count;
    }


    public static boolean checkOddEven(int num){
        if(num % 2 == 0){
            return false;
        }else {
            return true;
        }
    }





    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }


    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int originalNumber = x;
        int reversedNumber = 0;
        while (x > 0) {
            int lastDigit = x % 10;
            reversedNumber = reversedNumber * 10 + lastDigit;
            x /= 10;
        }
        return originalNumber == reversedNumber;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            m = nums1.length;
            n = nums2.length;
        }

        int left = 0, right = m, halfLen = (m + n + 1) / 2;
        while (left <= right) {
            int i = (left + right) / 2;
            int j = halfLen - i;

            if (i < m && nums2[j - 1] > nums1[i]) {
                left = i + 1; // i is too small
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                right = i - 1; // i is too big
            } else {
                // i is perfect
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                if ((m + n) % 2 == 1) {
                    return maxLeft; // odd case
                }

                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                return (maxLeft + minRight) / 2.0; // even case
            }
        }
        return 0.0;
    }


}
