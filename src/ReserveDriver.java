import java.util.Scanner;

class Reserve {
    int[] seat;

    public Reserve() {
        seat = new int[10];
    }

    public void seatReserve() {
        System.out.printf("좌석을 예약하시겠습니까?(1 또는 0)");
        Scanner sc = new Scanner(System.in);
        System.out.println("현재의 예약 상태는 다음과 같습니다.");
        while (true) {
            System.out.print("원하는 좌석번호 입력: ");
            int a = sc.nextInt();
            if (seat[a - 1] == 1) {
                System.out.println("이미 예약되어있습니다.");
                continue;
            }
            seat[a - 1] = 1;
            printArray(seat);
            System.out.println();
            System.out.println("예약 완료. 한번더?");
            if (sc.nextInt() == 0) {
                break;
            }
        }
    }
    private void printArray(int[] seat) {
        for (int i : seat) {
            System.out.print(i + " ");
        }
    }
}

public class ReserveDriver {
    public static void main(String[] args) {
        Reserve r1 = new Reserve();
        r1.seatReserve();
    }
}