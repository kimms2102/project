package danamic_beat_16;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class DynamicBeat extends JFrame{//JFrame이라는 이미있는 클래스를 상속받음 눈으로 보이는 게임같은것을 만드는데는 필수적인것

	//아래 두가지는 더블버퍼링을 위해서 전체화면에 대한 이미지를 담는 두 인스턴스
	private Image screenImage; 
	private Graphics screenGraphic;
		
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));	
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	
	private Image background =new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(); //아까 저장한 introImageBackground파일을 담을수있는 객체
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	
	private int mouseX, mouseY; //마우스 좌표를 의미한다.
	
	private boolean isMainScreen = false;//처음에는 시작화면이 아닌 메인화면이기때문에 false 메인화면으로 이동했을때 true값으로 바뀐다
	private boolean isGameScreen = false;
	
	ArrayList<Track> trackList =new ArrayList<Track>();//각각 넣었었던 셀렉티드 뮤직자체를 하나의 객체로만든다.
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	Music introMusic = new Music("introMusic.mp3", true);//음악이 직접종료 시켜주기 전까지 계속 재생된다.
	private int nowSelected = 0;//현재선택된 트랙의번호 인덱스이기때문에 0부터시작
	
	public static Game game;
	
	public DynamicBeat() {
		
		trackList.add(new Track("Mighty Love Title Image.png", "Mighty Love Start Image.png",
				"Mighty Love Game Image.png", "IU Pallete Selected.mp3", "IU Pallete.mp3","IU Pallete"));
		trackList.add(new Track("Wild Flower Title Image.png", "Wild Flower Start Image.png",
				"Wild Flower Game Image.png", "Cjamm 신기루 Selected.mp3", "Cjamm 신기루.mp3","Cjamm 신기루"));
		trackList.add(new Track("Energy Title Image.png", "Energy Start Image.png",
				"Energy Game Image.png", "Postmalone Phyco Selected.mp3", "Postmalone Phyco.mp3","Postmalone Phyco"));
		
		
		setUndecorated(true);//실행을 했을때 기본적으로 보이는 메뉴바가 보이지않게된다.
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);//게임창의크기
		setResizable(false);//한번만들어진 게임창은 사용자가 임의적으로 줄이거나 크게할수없다
		setLocationRelativeTo(null);//컴퓨터창이 정중앙에 뜨게해줌
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//이것이 없으면 게임창을 꺼도 종료가안됨
		setVisible(true);//우리눈에 게임창이보이게함.
		setBackground(new Color(0, 0, 0, 0));//페인트 컴포넌트를 했을때 전부 배경색이 휜색이된다.
		setLayout(null);//버튼이나 제이라벨같은것을 넣었을때 그위치그대로 꽃힘
		
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);				
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				try {//System exit에의해 바로 종료되기 때문에 효과음이 들리게 한후 종료시킨다.
					Thread.sleep(1000);
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(exitButton);
		
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);				
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				enterMain();
			}
		});//버튼이 자연스럽게 적용되도록 하는것.
		add(startButton);
		
		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);				
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				try {//System exit에의해 바로 종료되기 때문에 효과음이 들리게 한후 종료시킨다.
					Thread.sleep(1000);
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);				
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				selectLeft();
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);				
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				selectRight();		
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(rightButton);
		
		menuBar.setBounds( 0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//실제로 이벤트가 발생했을때 x,y좌표를 가져오는것
				mouseX = e.getX();
				mouseY = e.getY();
			}			
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
/*여기서 큰실수하였음!!*/	setLocation(x - mouseX, y - mouseY);//드래그할때마다 순간순간 x와y좌표를 가져와서 현제 jFrame의 위치를 바꿔주는것.
				}
		});
		add(menuBar);// JFrame에 메뉴바가 추가가됨
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);				
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				gameStart(nowSelected, "Easy");//난이도 쉬움 이벤트
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);				
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				gameStart(nowSelected, "Hard");//난이도 어려움 이벤트
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);				
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				
				//마우스가 범위안에 들어갔을때 손가락 모양으로 바뀜.
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();	
				backMain();
				// 메인 화면으로 돌아가는 이벤트
			}
		});
		//버튼이 자연스럽게 적용되도록 하는것.
	
		add(backButton);
	
		
		
		//메인클레스를 기반으로 해서 이미지를 파일을 이미지인스턴스를 인트로 백그라운드라는 이미지변수에다가 초기화한다는뜻.

	
	}
		public void paint(Graphics g) {
			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			                          //1280과 720의 크기의 이미지를 만든다음에 screen Image에 넣어줌
			screenGraphic =screenImage.getGraphics();
										//스크린이미지를 이용해서 그래픽객체를 얻어오는것
			screenDraw((Graphics2D)screenGraphic);
										//스크린 그래픽에 그림을 그려쥬눈것
			g.drawImage(screenImage, 0, 0, null);
										// 0,0 위치에 스크린 이미지를 그려주는것.
		}
		public void screenDraw(Graphics2D g) {
			g.drawImage(background, 0 , 0, null);//이미지를 단순히 화면에 출력해줄때
			if(isMainScreen)
			{
				g.drawImage(selectedImage, 340, 180, null);
				g.drawImage(titleImage, 340, 50, null);				
			
			}
			if(isGameScreen)
			{		
				game.screenDraw(g);
			}
			paintComponents(g);
			//add라고 적혀있는것은 패인트 컴포넌트에 의해 그려진다
			//getimage를 단순하게 screenImage라는 변수안에 그려주는것이외에 따로 menubar ,jrabel 같은것을 jFRame안에 넣어주는 그것을 그려주는것
			//항상 존재하기 때문에  draw이미지가아닌 페인트 컴포넌트를 이용
			try {
				Thread.sleep(5);
			}catch(Exception e) {
				e.printStackTrace();
			}
			this.repaint();//전체화면 이미지를 종료될때까지 매순간마다 바꿔주는것 
			}
			public void selectTrack(int nowSelected) {
				if(selectedMusic != null)
					selectedMusic.close();
				titleImage=new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage();
				selectedImage=new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage();
				selectedMusic=new Music(trackList.get(nowSelected).getStartMusic(),true);
				selectedMusic.start();
			}
			
			public void selectLeft() {//곡 트랙에 관한 이벤트처리
				if(nowSelected ==0)
					nowSelected = trackList.size() - 1;
				else
					nowSelected--;
				selectTrack(nowSelected);
			}
			public void selectRight() {//곡 트랙에 관한 이벤트처리
				if(nowSelected == trackList.size() - 1)
					nowSelected = 0;
				else
					nowSelected++;
				selectTrack(nowSelected);
			}
			public void gameStart(int nowSelected,String difficulty ) {
				if(selectedMusic != null)
					selectedMusic.close(); //현재 선택화면에서 재생되고 있는 뮤직을 닫음.
				isMainScreen = false; //메인화면이 아니라는것을 변수로서 알려줌
				leftButton.setVisible(false);
				rightButton.setVisible(false);
				hardButton.setVisible(false);
				easyButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
						.getImage();
				backButton.setVisible(true);
				isGameScreen = true;//배경은 투루 또는 펄스 버튼은 셋비시블을이용.
				game = new Game(trackList.get(nowSelected).getTitleName(),difficulty,trackList.get(nowSelected).getGameMusic());
				game.start();
				setFocusable(true);	
			}
			public void backMain() {
				//backButton을  누르면 다시 메인으로 들어와서 각종 버튼과 배경화면을 다시 바꿔라
				isMainScreen = true;
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				easyButton.setVisible(true);
				hardButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
						.getImage();
				backButton.setVisible(false);
				selectTrack(nowSelected);//현재 선택된 트랙을보여주고 음악 재생시켜라
				isGameScreen = false;
				game.close();
			}
			public void enterMain() {
					startButton.setVisible(false);
					quitButton.setVisible(false);
					background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
					isMainScreen = true;
					leftButton.setVisible(true);
					rightButton.setVisible(true);
					easyButton.setVisible(true);
					hardButton.setVisible(true);	
					introMusic.close();
					selectTrack(0);
			}
}

