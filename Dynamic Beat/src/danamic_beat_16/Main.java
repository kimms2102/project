package danamic_beat_16;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3;//아래 슬립 타임이 없을경우 엄청빠르게 떨어짐
	public static final int SLEEP_TIME = 10;//얼마간의 주기로떨어지는지 설정
	public static final int REACH_TIME = 2;//패키지3도 같이바꿈
	
	public static void main(String[] args) {
		
		new DynamicBeat(); 
		//그냥 자바에서 띄우는것을 사용하면 버퍼링이 많이걸리기 때문에 더블버퍼링이란 기법을 사용한다.
		//버퍼에 이미지를 담아서 원할때마다 이미지를 갱신
	}
}

//프로그램 실행후 PostMalon 게임 시작 화면에서 하드와 이시가 바껴서나옴 
//JButton의 easy와 hard 객체의 파라미터를 잘못적음.
//back버튼이 화면에 보이지않았다. add함수에 back버튼을 추가하지 않았다.
//리팩토링이란 지금까지 작성됬던 코드를 점검하면서 정리하는것.
//해당 곡게임 페이지에 들어갔을때 음악이 재생되지만, 멈추지않는 현상이 발생하였음, 
//하지만Game클래스에 close메스드를만들고 backMain메소드(메인으로 백했을때 닫히도록함)를 이용하여 닫아줌
//계속 판정사진이 보이지않았다, if문의 else if문을 실수하였음.