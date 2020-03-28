//소코반 기능
//1) 벽은 4개 골대 1개 공 1개 랜덤으로 자리가 설정된다
//2) 캐릭터 자유롭게 움직인다
//3) 캐릭터와 공이 만나면 캐릭터가 움직이는 방향으로 공이 같이 움직인다
//4) 캐릭터와 공이 골대를 만나면 게임이 종료된다
//5) 공이 외곽쪽에 랜덤으로 위치한는 것을 막았다 -> 공이 외곽에 위치하면 공은 위, 아래 또는 좌, 우 로만 움직일 수 있기 때문이다
//6) 공이 벽에 둘러싸여 있는 경우는 게임을 다시 시작해야 한다

package day_33;

import java.util.Scanner;
import java.util.Random;

public class crazyarcade {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		int game[][] = new int[7][7];
		
		int wall_count = 0;		int item_count = 0;		int bomb_count = 0;
		int player_count = 0;	int px = 0;				int py = 0;
		int item_x = 0;			int item_y = 0;
		int bomb = 3;			int check = -1;
		
		while(wall_count < 20)		//벽  -> 3, 벽20개 설정
		{
			int wall1 = ran.nextInt(7);
			int wall2 = ran.nextInt(7);
			
			if(game[wall1][wall2] == 0)
			{
				game[wall1][wall2] = 3;
				wall_count++;
			}
		}
		
		while(item_count < 1)		//아이템  ->  4, 아이템 3개 설정
		{
			int itemcount1 = ran.nextInt(7);
			int itemcount2 = ran.nextInt(7);
			
			if(game[itemcount1][itemcount2] == 0)
			{
				game[itemcount1][itemcount2] = 4;
				item_count++;
			}
		}
		
		while(bomb_count < 3)		//폭탄  ->  9, 이미 설치 되어있는 폭탄 3개 설정
		{
			int bomdcount1 = ran.nextInt(7);
			int bomdcount2 = ran.nextInt(7);
			
			if(game[bomdcount1][bomdcount2] == 0)
			{
				game[bomdcount1][bomdcount2] = 9;
				bomb_count++;
			}
		}
		
		while(player_count < 1)		//플레이어 ->  2
		{
			int playercount1 = ran.nextInt(7);
			int playercount2 = ran.nextInt(7);
			
			playercount1 = py;
			playercount2 = px;
			
			if(game[playercount1][playercount2] == 0)
			{
				game[playercount1][playercount2] = 2;
				player_count++;
			}
		}
		
		while(true)
		{	
			if(check == 1) {System.out.println("!사용자가 폭탄에 맞았습니다!"); break;}
			
			System.out.println("───────────────────────────────");
			System.out.println("         Crazy Arcade");
			System.out.println("───────────────────────────────");
			System.out.println("▷설치 가능한 폭탄의 갯수  "+bomb+"EA");
			System.out.println("▷남아있는 폭탄 갯수         "+bomb_count+"EA");
			System.out.println("▷남아있는 벽돌 갯수         "+wall_count+"EA");
			System.out.println("▷획들할 아이템 갯수         "+item_count+"EA");
			System.out.println("───────────────────────────────");
			
			for (int i = 0; i < 7; i++) 
			{
				for (int j = 0; j < 7; j++) 
				{	
					if(game[i][j] == 3){System.out.print(     "[ 벽    ]");}
					else if(game[i][j] == 4){System.out.print("[아이템]");}
					else if(game[i][j] == 9){System.out.print("[ 폭    ]");}
					else if(game[i][j] == 2){System.out.print("[ 나    ]");}
					else if(game[i][j] == 6){System.out.print("[ 폭설 ]");}
					else if(game[i][j] == 0){System.out.print("[ ㅇ    ]");}
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("[1]상 [2]하 [3]좌 [4]우 [5]폭탄설치 [6]폭파");
			System.out.print("입력:");
			int move = sc.nextInt();
			
			if(move == 1 || move == 2 || move == 3 || move == 4) 
			{
				int temp_x = px;		int temp_y = py;
				
				if(move == 1) { temp_y = temp_y - 1; }
				else if(move == 2) { temp_y = temp_y + 1; }
				else if(move == 3) { temp_x = temp_x - 1; }
				else if(move == 4) { temp_x = temp_x + 1; }
				
				if(7 <= temp_y || temp_y < 0){System.out.println("입력하신 방향으로 이동하지 못합니다.");continue;}
				if(7 <= temp_x || temp_x < 0){System.out.println("입력하신 방향으로 이동하지 못합니다.");continue;}
				if(game[temp_y][temp_x] == 3){System.out.println("입력하신 방향으로 이동하지 못합니다.");continue;}
				if(game[temp_y][temp_x] == 9){System.out.println("입력하신 방향으로 이동하지 못합니다.");continue;}
				
				if(game[py][px] == 2){game[py][px] = 0;}
				if(game[temp_y][temp_x] == 4){bomb_count++;}		//아이템 먹었을때 폭탄 갯수 늘어난다
				py = temp_y;		
				px = temp_x;
				game[py][px] = 2;
				
			}
			
			//폭탄 설치
			if(move == 5)
			{
				int j=1;
				int[] bomb_x = new int[j];
				int[] bomb_y = new int[j];
				
				if(game[py][px] == 2) 
				{
					if(bomb>0)
					{
						game[py][px] = 6;
						bomb--;
					}
					else if(bomb==0)
					{
						System.out.println("사용할 수 있는 폭탄이 없습니다.");
					}
				}
			}
			
			
			
		}//무한 반복 종료 괄호
		
	}
}