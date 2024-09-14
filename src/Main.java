import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        System.out.printf("좌석을 예약하시겠습니까?(1 또는 0)");
        Scanner sc = new Scanner(System.in);

        System.out.println("현재의 예약 상태는 다음과 같습니다.");
        int Seat[] = {0, 0, 0, 0, 0, 0, 0, 0 , 0, 0};
        while (true){
            System.out.print("원하는 좌석번호 입력: ");
            int a = sc.nextInt();
            if(Seat[a-1] == 1){
                System.out.println("이미 예약되어있습니다.");
                continue;
            }
            Seat[a-1] = 1;
            for(int i:Seat) {
                System.out.print(i+" ");}
            System.out.println();
            System.out.println("예약 완료. 한번더?");
            if(sc.nextInt()== 0){
                break;
            }
        }
    }
}