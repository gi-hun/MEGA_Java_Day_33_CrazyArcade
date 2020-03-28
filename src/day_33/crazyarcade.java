//���ڹ� ���
//1) ���� 4�� ��� 1�� �� 1�� �������� �ڸ��� �����ȴ�
//2) ĳ���� �����Ӱ� �����δ�
//3) ĳ���Ϳ� ���� ������ ĳ���Ͱ� �����̴� �������� ���� ���� �����δ�
//4) ĳ���Ϳ� ���� ��븦 ������ ������ ����ȴ�
//5) ���� �ܰ��ʿ� �������� ��ġ�Ѵ� ���� ���Ҵ� -> ���� �ܰ��� ��ġ�ϸ� ���� ��, �Ʒ� �Ǵ� ��, �� �θ� ������ �� �ֱ� �����̴�
//6) ���� ���� �ѷ��ο� �ִ� ���� ������ �ٽ� �����ؾ� �Ѵ�

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
		
		while(wall_count < 20)		//��  -> 3, ��20�� ����
		{
			int wall1 = ran.nextInt(7);
			int wall2 = ran.nextInt(7);
			
			if(game[wall1][wall2] == 0)
			{
				game[wall1][wall2] = 3;
				wall_count++;
			}
		}
		
		while(item_count < 1)		//������  ->  4, ������ 3�� ����
		{
			int itemcount1 = ran.nextInt(7);
			int itemcount2 = ran.nextInt(7);
			
			if(game[itemcount1][itemcount2] == 0)
			{
				game[itemcount1][itemcount2] = 4;
				item_count++;
			}
		}
		
		while(bomb_count < 3)		//��ź  ->  9, �̹� ��ġ �Ǿ��ִ� ��ź 3�� ����
		{
			int bomdcount1 = ran.nextInt(7);
			int bomdcount2 = ran.nextInt(7);
			
			if(game[bomdcount1][bomdcount2] == 0)
			{
				game[bomdcount1][bomdcount2] = 9;
				bomb_count++;
			}
		}
		
		while(player_count < 1)		//�÷��̾� ->  2
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
			if(check == 1) {System.out.println("!����ڰ� ��ź�� �¾ҽ��ϴ�!"); break;}
			
			System.out.println("��������������������������������������������������������������");
			System.out.println("         Crazy Arcade");
			System.out.println("��������������������������������������������������������������");
			System.out.println("����ġ ������ ��ź�� ����  "+bomb+"EA");
			System.out.println("�������ִ� ��ź ����         "+bomb_count+"EA");
			System.out.println("�������ִ� ���� ����         "+wall_count+"EA");
			System.out.println("��ȹ���� ������ ����         "+item_count+"EA");
			System.out.println("��������������������������������������������������������������");
			
			for (int i = 0; i < 7; i++) 
			{
				for (int j = 0; j < 7; j++) 
				{	
					if(game[i][j] == 3){System.out.print(     "[ ��    ]");}
					else if(game[i][j] == 4){System.out.print("[������]");}
					else if(game[i][j] == 9){System.out.print("[ ��    ]");}
					else if(game[i][j] == 2){System.out.print("[ ��    ]");}
					else if(game[i][j] == 6){System.out.print("[ ���� ]");}
					else if(game[i][j] == 0){System.out.print("[ ��    ]");}
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("[1]�� [2]�� [3]�� [4]�� [5]��ź��ġ [6]����");
			System.out.print("�Է�:");
			int move = sc.nextInt();
			
			if(move == 1 || move == 2 || move == 3 || move == 4) 
			{
				int temp_x = px;		int temp_y = py;
				
				if(move == 1) { temp_y = temp_y - 1; }
				else if(move == 2) { temp_y = temp_y + 1; }
				else if(move == 3) { temp_x = temp_x - 1; }
				else if(move == 4) { temp_x = temp_x + 1; }
				
				if(7 <= temp_y || temp_y < 0){System.out.println("�Է��Ͻ� �������� �̵����� ���մϴ�.");continue;}
				if(7 <= temp_x || temp_x < 0){System.out.println("�Է��Ͻ� �������� �̵����� ���մϴ�.");continue;}
				if(game[temp_y][temp_x] == 3){System.out.println("�Է��Ͻ� �������� �̵����� ���մϴ�.");continue;}
				if(game[temp_y][temp_x] == 9){System.out.println("�Է��Ͻ� �������� �̵����� ���մϴ�.");continue;}
				
				if(game[py][px] == 2){game[py][px] = 0;}
				if(game[temp_y][temp_x] == 4){bomb_count++;}		//������ �Ծ����� ��ź ���� �þ��
				py = temp_y;		
				px = temp_x;
				game[py][px] = 2;
				
			}
			
			//��ź ��ġ
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
						System.out.println("����� �� �ִ� ��ź�� �����ϴ�.");
					}
				}
			}
			
			
			
		}//���� �ݺ� ���� ��ȣ
		
	}
}