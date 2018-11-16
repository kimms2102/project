package danamic_beat_16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Game extends Thread{
	
	private Image noteRouteLineImage =new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage =new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage =new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage(); //아까 저장한 introImageBackground파일을 담을수있는 객체
	
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();//space를 두개로 나눈이유는 스페이스의 길이가 다른 버튼보다 길기때문에
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage ;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	
	
	private String titleName;//현재실행할곡의 이름을 의미
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle,false);
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		for(int i =0;i <noteList.size();i++)//판정라인보다 위쪽에 드랍노트가 그려져야하기 때문에, judgementLineImage아래에 for문을 옮겼다.
		{
			Note note = noteList.get(i);//리스트에 있는 노트들을 불러와서 각각의 노트들을 그려줄수 있도록함.
			if(note.getY()>620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);//임의의 사용하지않는 노트는 알아서 화면에서 지워짐
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//안티엘리어싱이 적용이되서 텍스트가 꺠짐없이 매끄럽게출력
		g.setFont(new Font("Arial",Font.BOLD,30));//arial폰트로 굵은 글씨체 크기 30인 글씨그릴수있음
		g.drawString(titleName, 20, 702);//현재실행중인 곡의 정보를 보여줄수있도록함.
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial",Font.PLAIN,25));
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);	
		g.drawString("F", 478, 609);	
		g.drawString("Space Bar", 580, 609);	
		g.drawString("J", 784, 609);	
		g.drawString("K", 889, 609);	
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant",Font.BOLD,30));
		g.drawString("000000", 565, 702);
		g.drawImage(blueFlareImage, 340, 300, null);
		g.drawImage(judgeImage, 515, 420,null);
		g.drawImage(keyPadSImage, 228, 580,null);
		g.drawImage(keyPadDImage, 332, 580,null);
		g.drawImage(keyPadFImage, 436, 580,null);
		g.drawImage(keyPadSpace1Image, 540, 580,null);
		g.drawImage(keyPadSpace2Image, 640, 580,null);	
		g.drawImage(keyPadJImage, 744, 580,null);
		g.drawImage(keyPadKImage, 848, 580,null);
		g.drawImage(keyPadLImage, 952, 580,null);
	}
	public void PressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();//반복하지 않아야 되기 때문에 false를 넣어야함.
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void PressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumBig1.mp3",false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void PressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void PressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		noteRouteSpace2Image =new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 	
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
		noteRouteSpace2Image =new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void PressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void PressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void PressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();//interrupt() 메소드는 현재 수행하고 있는 명령을 바로 중지시킨다
	}
	public void dropNotes(String titleName) {//드랍노트에서 한개의 배열을만들어줌
		Beat[] beats =null;
		if(titleName.equals("IU Pallete")&& difficulty.equals("Easy")){
			int startTime = 4460 - Main.REACH_TIME * 1000;//패키지 3의 리치타임에 구애받음 이상함
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime + gap * 2,"D"),
					new Beat(startTime + gap * 4,"S"),
					new Beat(startTime + gap * 6,"D"),
					new Beat(startTime + gap * 8,"S"),
					new Beat(startTime + gap * 10,"D"),
					new Beat(startTime + gap * 12,"S"),
					new Beat(startTime + gap * 14,"J"),
					new Beat(startTime + gap * 18,"K"),
					new Beat(startTime + gap * 20,"J"),
					new Beat(startTime + gap * 22,"K"),
					new Beat(startTime + gap * 24,"J"),
					new Beat(startTime + gap * 26,"K"),
					new Beat(startTime + gap * 28,"S"),
					new Beat(startTime + gap * 30,"D"),
					new Beat(startTime + gap * 32,"S"),
					new Beat(startTime + gap * 36,"D"),
					new Beat(startTime + gap * 38,"S"),
					new Beat(startTime + gap * 40,"D"),
					new Beat(startTime + gap * 52,"Space"),
					
			};
		}
		else if(titleName.equals("Cjamm 신기루")&& difficulty.equals("Easy")){
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
					new Beat(startTime + gap * 2,"D"),
					new Beat(startTime + gap * 4,"S"),
					new Beat(startTime + gap * 6,"D"),
					new Beat(startTime + gap * 8,"S"),
					new Beat(startTime + gap * 10,"D"),
					new Beat(startTime + gap * 12,"S"),
					new Beat(startTime + gap * 14,"J"),
					new Beat(startTime + gap * 18,"K"),
					new Beat(startTime + gap * 20,"J"),
					new Beat(startTime + gap * 22,"K"),
					new Beat(startTime + gap * 24,"J"),
					new Beat(startTime + gap * 26,"K"),
					new Beat(startTime + gap * 28,"S"),
					new Beat(startTime + gap * 30,"D"),
					new Beat(startTime + gap * 32,"S"),
					new Beat(startTime + gap * 36,"D"),
					new Beat(startTime + gap * 38,"S"),
					new Beat(startTime + gap * 40,"D"),
					new Beat(startTime + gap * 52,"Space"),
			};
		}
		else if(titleName.equals("Postmalone Phyco")&& difficulty.equals("Easy")){
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
					new Beat(startTime + gap * 2,"D"),
					new Beat(startTime + gap * 4,"S"),
					new Beat(startTime + gap * 6,"D"),
					new Beat(startTime + gap * 8,"S"),
					new Beat(startTime + gap * 10,"D"),
					new Beat(startTime + gap * 12,"S"),
					new Beat(startTime + gap * 14,"J"),
					new Beat(startTime + gap * 18,"K"),
					new Beat(startTime + gap * 20,"J"),
					new Beat(startTime + gap * 22,"K"),
					new Beat(startTime + gap * 24,"J"),
					new Beat(startTime + gap * 26,"K"),
					new Beat(startTime + gap * 28,"S"),
					new Beat(startTime + gap * 30,"D"),
					new Beat(startTime + gap * 32,"S"),
					new Beat(startTime + gap * 36,"D"),
					new Beat(startTime + gap * 38,"S"),
					new Beat(startTime + gap * 40,"D"),
					new Beat(startTime + gap * 52,"Space"),
			};
		}
		else if(titleName.equals("IU Pallete")&& difficulty.equals("Hard")){
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
					new Beat(startTime + gap * 2,"D"),
					new Beat(startTime + gap * 4,"S"),
					new Beat(startTime + gap * 6,"D"),
					new Beat(startTime + gap * 8,"S"),
					new Beat(startTime + gap * 10,"D"),
					new Beat(startTime + gap * 12,"S"),
					new Beat(startTime + gap * 14,"J"),
					new Beat(startTime + gap * 18,"K"),
					new Beat(startTime + gap * 20,"J"),
					new Beat(startTime + gap * 22,"K"),
					new Beat(startTime + gap * 24,"J"),
					new Beat(startTime + gap * 26,"K"),
					new Beat(startTime + gap * 28,"S"),
					new Beat(startTime + gap * 30,"D"),
					new Beat(startTime + gap * 32,"S"),
					new Beat(startTime + gap * 36,"D"),
					new Beat(startTime + gap * 38,"S"),
					new Beat(startTime + gap * 40,"D"),
					new Beat(startTime + gap * 52,"Space"),
			};
		}
		else if(titleName.equals("Cjamm 신기루")&& difficulty.equals("Hard")){
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
					new Beat(startTime + gap * 2,"D"),
					new Beat(startTime + gap * 4,"S"),
					new Beat(startTime + gap * 6,"D"),
					new Beat(startTime + gap * 8,"S"),
					new Beat(startTime + gap * 10,"D"),
					new Beat(startTime + gap * 12,"S"),
					new Beat(startTime + gap * 14,"J"),
					new Beat(startTime + gap * 18,"K"),
					new Beat(startTime + gap * 20,"J"),
					new Beat(startTime + gap * 22,"K"),
					new Beat(startTime + gap * 24,"J"),
					new Beat(startTime + gap * 26,"K"),
					new Beat(startTime + gap * 28,"S"),
					new Beat(startTime + gap * 30,"D"),
					new Beat(startTime + gap * 32,"S"),
					new Beat(startTime + gap * 36,"D"),
					new Beat(startTime + gap * 38,"S"),
					new Beat(startTime + gap * 40,"D"),
					new Beat(startTime + gap * 52,"Space"),
			};
		}
		else if(titleName.equals("Postmalone Phyco")&& difficulty.equals("Hard")){
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
					new Beat(startTime + gap * 2,"D"),
					new Beat(startTime + gap * 4,"S"),
					new Beat(startTime + gap * 6,"D"),
					new Beat(startTime + gap * 8,"S"),
					new Beat(startTime + gap * 10,"D"),
					new Beat(startTime + gap * 12,"S"),
					new Beat(startTime + gap * 14,"J"),
					new Beat(startTime + gap * 18,"K"),
					new Beat(startTime + gap * 20,"J"),
					new Beat(startTime + gap * 22,"K"),
					new Beat(startTime + gap * 24,"J"),
					new Beat(startTime + gap * 26,"K"),
					new Beat(startTime + gap * 28,"S"),
					new Beat(startTime + gap * 30,"D"),
					new Beat(startTime + gap * 32,"S"),
					new Beat(startTime + gap * 36,"D"),
					new Beat(startTime + gap * 38,"S"),
					new Beat(startTime + gap * 40,"D"),
					new Beat(startTime + gap * 52,"Space"),
			};
		}
		int i =0;
		gameMusic.start();
		while(i<beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {//beats에 time을 얻은값이 gameMusic의 geTime보다 작다면
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);//노트리스트에 방금만든 노트를 추가해서 정상적으로 출력될수 있도록함.
				i++;
				dropped = true;
			}
			if(!dropped){
				try {
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
		public void judge(String input) {//큐처럼구현됨
			for(int i = 0; i < noteList.size(); i++) {
				Note note = noteList.get(i);
				if(input.equals(note.getNoteType())) {
					judgeEvent(note.judge());
					break;
				}
			}		
	}
		public void judgeEvent(String judge) {
			if(!judge.equals("None")) {
				blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
			}
			if(judge.equals("Miss")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			else if(judge.equals("Late")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
			}
			else if(judge.equals("Good")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
			}
			else if(judge.equals("Great")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
			}
			else if(judge.equals("Perfect")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
			}
			else if(judge.equals("Early")) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
			}
		}
}

