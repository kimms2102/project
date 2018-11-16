package danamic_beat_16;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{

	@Override
	public void keyPressed(KeyEvent e) {
		if(DynamicBeat.game == null) {//현재 게임이 진행되고 있지 않다면 무시
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.PressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.PressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.PressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.PressSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.PressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.PressK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.PressL();
		}		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(DynamicBeat.game == null) {//현재 게임이 진행되고 있지 않다면 무시
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.releaseSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releaseL();
		}			
	}
}
