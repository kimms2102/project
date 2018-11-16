package danamic_beat_16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;//www.javazoom.com에서 받은 라이브러리 임포트
	private boolean isLoop; //곡이 한번만되고 꺼지는지 무한 루프를 도는지를 결정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int getTime() {// 나중에 곡이 떨어지는 시간을 분석해서 겟타임 함수를 사용한다,
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {//언제든지 해당곡이 안정적으로 종료 될수있도록해줌
		isLoop = false;
		player.close();
		this.interrupt();//해당쓰레드를 중지상태로 만들어줌 
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);	//isLoop가 true값을 가진다면 해당 곡은 무한루프로 실행된다.
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
