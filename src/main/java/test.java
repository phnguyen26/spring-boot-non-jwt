import java.util.*;
public class test{
    static int MOD = 1000000007;
    public static long solve(int n, int k){
        if (k == 0) return 1;
        long tmp = solve(n, k/2);
        long res = tmp * tmp % MOD;
        if (k%2==1) res = res * n % MOD;
        return res;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(Math.sqrt(n));
        System.out.println("aaa");
//        try{
//            System.out.println(Long.parseLong(n));
//        }catch(NumberFormatException e){
//            System.out.println(e.getMessage());
//            System.out.println("loi ne");
//        }
    }
}
