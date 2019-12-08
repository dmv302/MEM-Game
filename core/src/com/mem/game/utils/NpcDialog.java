package com.mem.game.utils;

public class NpcDialog {
	private String[] phrases;
	private int curr;
	
	public NpcDialog(String... texts) {
		this.phrases = texts;
		this.curr = 0;
	}
	
	public void reset() {
		curr = 0;
	}
	
	public void nextPhrase() {
		curr++;
		if (curr >= phrases.length) curr = curr % phrases.length;
	}
	
	public String getCurrPhrase() {
		return phrases[curr];
	}
	
	public boolean isEnd() { return curr == phrases.length - 1; };
}
