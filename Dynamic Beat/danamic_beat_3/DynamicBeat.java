package danamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame{//JFrame이라는 이미있는 클래스를 상속받음 눈으로 보이는 게임같은것을 만드는데는 필수적인것

	//아래 두가지는 더블버퍼링을 위해서 전체화면에 대한 이미지를 담는 두 인스턴스
	private Image screenImage; 
	private Graphics screenGraphic;
	
	private Image introBackground; //아까 저장한 introImageBackground파일을 담을수있는 객체
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);//게임창의크기
		setResizable(false);//한번만들어진 게임창은 사용자가 임의적으로 줄이거나 크게할수없다
		setLocationRelativeTo(null);//컴퓨터창이 정중앙에 뜨게해줌
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//이것이 없으면 게임창을 꺼도 종료가안됨
		setVisible(true);//우리눈에 게임창이보이게함.
	
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
		//메인클레스를 기반으로 해서 이미지를 파일을 이미지인스턴스를 인트로 백그라운드라는 이미지변수에다가 초기화한다는뜻.
		Music introMusic = new Music("introMusic.mp3", true);//음악이 직접종료 시켜주기 전까지 계속 재생된다.
		introMusic.start();
	
	}
		public void paint(Graphics g) {
			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			                          //1280과 720의 크기의 이미지를 만든다음에 screen Image에 넣어줌
			screenGraphic =screenImage.getGraphics();
										//스크린이미지를 이용해서 그래픽객체를 얻어오는것
			screenDraw(screenGraphic);
										//스크린 그래픽에 그림을 그려쥬눈것
			g.drawImage(screenImage, 0, 0, null);
										// 0,0 위치에 스크린 이미지를 그려주는것.
		}
		public void screenDraw(Graphics g) {
			g.drawImage(introBackground, 0 , 0, null);
			this.repaint();
			//전체화면 이미지를 종료될때까지 매순간마다 바꿔주는것 
		}
	
}
