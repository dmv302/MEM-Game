package com.mem.game.utils;

public class NpcDialog {
	private String[] phrases;
	private int curr;
	
	public NpcDialog(String... texts) {
		this.phrases = texts;
		this.curr = 0;
	}
	
	public String getNextPhrase() {
		curr++;
		if (curr > phrases.length) curr = curr % phrases.length;
		return phrases[curr];
	}
	
	public String getCurrPhrase() {
		return phrases[curr];
	}
	
	public boolean isEnd() { return curr == phrases.length - 1; };
}
