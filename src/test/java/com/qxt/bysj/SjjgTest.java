package com.qxt.bysj;


import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SjjgTest {

    static Long print(int n){
        for(int i=0;i<n;i++){
            System.out.println(i);
        }
        return System.currentTimeMillis();
    }

    static Long printSelf(int n){
        if(n>0){
            int i = n-1;
            System.out.println(i);
            printSelf(i);
        }
        return System.currentTimeMillis();
    }

    static double f1(int n,double[] a,double x){
        double p = a[0];
        for(int i=1;i<n;i++){
            p += a[i]*(Math.pow(x,i));
        }
        return p;
    }

    static double f2(int n,double[] a,double x){
        double p = a[n-1];
        for(int i=n-1;i>0;i--){
            p = p*x+a[i-1];
        }
        return p;
    }

    static double f3(double x){
        double p = 0;
        for(int i=0;i<=9;i++){
            p+=i*Math.pow(x,i);
        }
        return p;
    }

    static double MaxSubseqSum2( double[]  A,int left,int right ) {
        double leftSum = 0;
        double rightSum = 0;
        double leftMaxSum =0;
        double rightMaxSum = 0;
        double MaxLeftBorderSum = 0;
        double MaxRightBorderSum = 0;

        int mid = (left+right)/2;
        if(left==right){
            if(A[left]>0){
                return A[left];
            }else {
                return 0;
            }
        }
        leftMaxSum = MaxSubseqSum2(A,left,mid);
        rightMaxSum = MaxSubseqSum2(A,mid+1,right);

        /* 下面求跨分界线的最大子列和 */
        for(int i=mid; i>=left; i-- ) { /* 从中线向左扫描 */
            leftSum += A[i];
            if( leftSum > MaxLeftBorderSum )
                MaxLeftBorderSum = leftSum;
        } /* 左边扫描结束 */

        for(int i=mid+1; i<=right; i++ ) { /* 从中线向右扫描 */
            rightSum += A[i];
            if( rightSum > MaxRightBorderSum )
                MaxRightBorderSum = rightSum;
        } /* 右边扫描结束 */


        return Max3(leftMaxSum,rightMaxSum,MaxLeftBorderSum + MaxRightBorderSum);
    }


    static double Max3( double A, double B, double C )
    { /* 返回3个整数中的最大值 */
        return A > B ? A > C ? A : C : B > C ? B : C;
    }

    static double MaxSubseqSum2( double[]  A ) {
        return MaxSubseqSum2(A,0,A.length-1);
    }

    static double findMax(double[] a){
        double maxSum = 0;
        double sum = 0;
        for(int i=0;i<a.length;i++){
            sum += a[i];
            if(sum>maxSum){
                maxSum = sum;
            }else if(sum<a[0]){
                sum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入n：");
        int n = sc.nextInt();
        double[] a = new double[n];
        for(int i=0;i<n;i++){
            double d=Math.random()*10;
            DecimalFormat df = new DecimalFormat( "0" );
            String str=df.format( d );
            a[i] = Double.parseDouble(str);
            if(i%2==0){
                a[i] = -Double.parseDouble(str);
            }
        }
        Long startTs1 = System.currentTimeMillis();
        double res1 = findMax(a);
        Long endTs1 = System.currentTimeMillis();
        Long startTs2 = System.currentTimeMillis();
        double res2 = MaxSubseqSum2(a);
        Long endTs2 = System.currentTimeMillis();
        System.out.println("res1:"+res1+"use1:"+(endTs1-startTs1));
        System.out.println("res2:"+res2+"use2:"+(endTs2-startTs2));
    }

    @Test
    public void generateParenthesis() {
        int n = 5;
        List<String> list = new ArrayList<>();
        String res = "";
        generateParenthesis(list,0,0,res,n);
        System.out.print(list);
    }

    public void generateParenthesis(List<String> list,int left,int right,String res,int n) {
        if(left>n || right>n) return;
        if(left == n && right == n)  list.add(res);
        if(left>=right){
            String res1 = new String(res);
            generateParenthesis(list,left+1,right,res+"(",n);
            generateParenthesis(list,left,right+1,res1+")",n);
        }
    }

    @Test
    public void shortestPalindrome() {
        String s = "aacecaaa";
        String x = "";
        String res = "dcdbabdcb";
        List<String> list = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            x += s.substring(i,i+1);
            if(!list.contains(s.substring(i,i+1))){
                list.add(s.substring(i,i+1));
            }
        }
        shortestPalindrome(list, s,x);
        System.out.print(list);
    }

    public void shortestPalindrome(List<String> list,String s,String x) {

    }

    @Test
    public  void KthBigest(){
        Integer[] a = {6,3,23,4,93,42,34,566,3,6,7,8,9,0};
        List<Integer> s = Arrays.asList(a);
        System.out.print(findKthBigest(s,7));
    }

    private int findKthBigest(List<Integer> s,int k){
        int num = 0;
        int index = (int) (Math.random() * s.size());
        Integer flag= s.get(index);
        List<Integer> s1 = s.stream().filter(i -> i>=flag).collect(Collectors.toList());
        List<Integer> s2 = s.stream().filter(i -> i<flag).collect(Collectors.toList());
        if(s2.size() == 0){
            num = findKthBigest(s1,k);
        }
        if(s1.size() == k){
            num = flag;
        }
        if(s1.size() > k){
            num = findKthBigest(s1,k);
        }
        if(s1.size() < k){
            num = findKthBigest(s2,k-s1.size());
        }
        return num;
    }

    @Test
    public void getLeastN1umbers() {
        int k = 8;
        int[] res = new int[k];
        int[] arr = new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4};
        quickSor1t(arr,0,arr.length-1);
        for(int i=0;i<k;i++){
            res[i] = arr[i];
        }
    }

    private void quickSor1t(int []arr,int begin,int end){
        if(begin<end){
            int index = getIndex(arr,begin,end);
            quickSor1t(arr,begin,index);
            quickSor1t(arr,index+1,end);
        }
    }

    private int getIndex(int []arr,int begin,int end){
        int a = arr[begin];
        while (begin<end){
            while (begin<end && arr[end] >=a){
                end--;
            }
            arr[begin] = arr[end];
            while (begin<end && arr[begin] <= a){
                begin++;
            }
            arr[end] = arr[begin];
        }
        arr[begin] = a;
        return begin;
    }

    static int count = 0;
    static int count2 = 0;

    @Test
    public void numSquares() {
        int n = 43;
        findSq1(n);
        System.out.print(count2);
    }
    private void findSq1(int n){
        List<Integer> list = new ArrayList<>();
        int s = 1;
        int j = 1;
        list.add(1);
        while (s<=n){
            s = s+(j*2+1);
            if(s>n) break;
            list.add(s);
            j++;
        }
        int i = list.size()-1;
        for(;i>=0;i--){
            findSq(n,list,i);
        }
    }

    private void findSq(int n,List<Integer> list,int i){
        if(n>0){
            int sum = 0;
            int j = i;
            while (n>=0){
                if(sum+list.get(j)>n){
                    j--;
                    continue;
                }
                if(sum+list.get(j)<=n){
                    sum+=list.get(j);
                    break;
                }
            }
            count++;
            findSq(n-sum,list,i);
        }else {
            if(i==list.size()-1){
                count2 = count;
            }
            if(count2>count){
                count2 = count;
            }
            count = 0;
            return;
        }
    }


    @Test
    public void shortestPali1ndrome() {
        String s = "a";
        System.out.print(shortestPalindrome(s));
    }

    public String shortestPalindrome(String s) {
        StringBuilder s1 = new StringBuilder();
        if(s.equals("")) return s;
        String q = s.substring(0,1);
        String p = s.replaceAll(q,"");
        if(p.equals("")) return s;
        if(isPal( s)){
            return  s;
        }
        String res = "";
        for(int i=s.length()-1;i>0;i--){
            s1.append(s.substring(i, i + 1));
            res = s1+s;
            if(isPal(res)){
                return res;
            }
        }
        return res;
    }

    private boolean isPal(String s){
        int begin = 0;
        int end = s.length()-1;
        int middle = (begin+end)/2;
        while (begin<=end){
            String b = s.substring(begin,begin+1);
            String e = s.substring(end,end+1);
            if(b.equals(e)){
                begin++;
                end--;
            }else {
                return false;
            }
        }
        return true;
    }
}