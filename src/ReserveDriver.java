import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter; // 시간 단위 설정
import java.time.format.DateTimeParseException; // 직접 시간 입력받는데에 필요


class ReserveInfo implements Comparable{
    String name;
    LocalDateTime now;

    @Override
    public int compareTo(Object o) {
        if ( this.now.isEqual(((ReserveInfo)o).now)) return 0;
        else if ( this.now.isBefore(((ReserveInfo)o).now)) return -1;
        else return 1;
    }
}

class Reserve {
    List<ReserveInfo>[] seat2 = new LinkedList[10];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // 분단위까지만 표시

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

            ReserveInfo RInfo = new ReserveInfo();

            System.out.print("이름 입력: ");
            RInfo.name = sc.next();

            System.out.print("예약 시간 설정(0은 현재시간, 1은 직접입력): ");
            int b = sc.nextInt();
            sc.nextLine();

            if (b == 0)
            {RInfo.now = LocalDateTime.now();}
            else {
                LocalDateTime dateTime = null;
                while (true) {
                    System.out.print("예약 시간 입력 (형식: yyyy-MM-dd HH:mm): ");
                    String input = sc.nextLine();//공백도 포함
                    try {
                        dateTime = LocalDateTime.parse(input, formatter);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("잘못된 입력 형식입니다. 다시 시도해 주세요.");
                    }
                }
                RInfo.now = dateTime;

            }

            boolean isSameTime = false; //이거 에러 보자
            for (ReserveInfo existingInfo : seat2[a - 1]) {
                if (existingInfo.now.format(formatter).equals(RInfo.now.format(formatter))) {
                    System.out.println("동일한 시간에 이미 예약이 되어 있습니다.");
                    isSameTime = true;
                    break;
                }
            }

            if (!isSameTime) {  //왜 == null은 안되지? seat2[a - 1].isEmpty()
                seat2[a - 1].add(RInfo);  //여기서 add(RInfo.name) 하면 안된다
                System.out.println("예약 완료");
                Collections.sort(seat2[a-1]);
//                Collections.sort(seat2[a - 1], new Comparator<ReserveInfo>() {
//                    @Override
//                    public int compare(ReserveInfo o1, ReserveInfo o2) {
//                        return o1.now.compareTo(o2.now);
//                    }
//                });
            }

            printArray(seat2);
            System.out.println();
            System.out.println("한번 더 예약하시겠습니까? (0: 종료, 1: 계속)");
            if (sc.nextInt() == 0) {
                break;
            }
        }
    }

    private void printArray(List<ReserveInfo>[] seats) {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i].isEmpty()) {
                System.out.println((i + 1) + "번: 0 ");
            } else {
                for(ReserveInfo info : seats[i])
                    System.out.println((i + 1) + "번: 이름: " + info.name + " 예약 시간: " + info.now.format(formatter)); //분단위
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