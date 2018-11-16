package danamic_beat_16;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x,y = 580 - (1000/Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	//여기를 실수해서 드랍노트가 아래서부터 떨어지기시작함.
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	public void close() {//해당 노트를 성공적으로 입력해서 해당노트가 더이상 움직이지 않도록 함
		proceeded =false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("S")) {//S를 누르면
			x= 228;
		}
		else if(noteType.equals("D")) {
			x= 332;
		}
		else if(noteType.equals("F")) {
			x= 436;
		}
		else if(noteType.equals("Space")) {
			x= 540;
		}
		else if(noteType.equals("J")) {
			x= 744;
		}
		else if(noteType.equals("K")) {
			x= 848;
		}
		else if(noteType.equals("L")) {
			x= 952;
		}
		this.noteType = noteType;
	}
	public void screenDraw(Graphics2D g) {//노트타입이 어떤거냐에따라 그리는 그림이 달라짐.
		if(!noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else  {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x+100, y, null);
		}
	}
	public void drop() {
		y +=Main.NOTE_SPEED;
		if(y >620) {
			System.out.println("Miss");
			close();
			//620이상 판정바를 떨어져 누르면 미스를 띠운다.
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();//1초에 700픽셀만큼 y좌표가 아래쪽으로 내려감
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();//프로씨디드가 끝났을떄는 쓰레드정지
					break;
				}
			}		
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}	
	}
	public String judge() {
		if(y >= 613) {
			System.out.println("Late");
			close();//리듬입력 바밑으로 안감.
			return "Late";
		}
		else if(y >= 600) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 587) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y >= 565) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 550) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 535) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
