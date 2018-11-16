package danamic_beat_3;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	public static void main(String[] args) {
		
		new DynamicBeat(); 
		//그냥 자바에서 띄우는것을 사용하면 버퍼링이 많이걸리기 때문에 더블버퍼링이란 기법을 사용한다.
		//버퍼에 이미지를 담아서 원할때마다 이미지를 갱신
	}
}
