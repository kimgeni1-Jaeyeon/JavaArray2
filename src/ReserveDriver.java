import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Reserve {
    List<String>[] seat2 = new LinkedList[10];

    public Reserve() {
        for (int i = 0; i < 10; i++) {
            seat2[i] = new LinkedList<>();
        }
    }

    public void seatReserve() {
        Scanner sc = new Scanner(System.in);
        System.out.println("현재의 예약 상태는 다음과 같습니다.");
        printArray(seat2);

        while (true) {
            System.out.print("원하는 좌석번호 입력: ");
            int a = sc.nextInt();
            System.out.print("이름 입력: ");
            String name = sc.next();

            if (seat2[a - 1].isEmpty()) {  //왜 == null은 안되지?
                seat2[a - 1].add(name);
                System.out.println("예약 완료");
            } else {
                seat2[a - 1].add(name);
                System.out.println("중복 예약됨.");
            }

            printArray(seat2);
            System.out.println();
            System.out.println("한번 더 예약하시겠습니까? (0: 종료, 1: 계속)");
            if (sc.nextInt() == 0) {
                break;
            }
        }
    }

    private void printArray(List<String>[] seats) {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i].isEmpty()) {
                System.out.print((i + 1) + "번: 0 ");
            } else {
                System.out.print((i + 1) + "번: " + seats[i] + " ");
            }
        }
        System.out.println();
    }
}

public class ReserveDriver {
    public static void main(String[] args) {
        Reserve r1 = new Reserve();
        r1.seatReserve();
    }
}