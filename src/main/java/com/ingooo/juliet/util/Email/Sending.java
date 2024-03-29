package com.ingooo.juliet.util.Email;


public class Sending implements Runnable {

	private String to; // 收件人
	private String subject; // 主题
	private String content; // 内容
	private String fileStr; // 附件路径

	public Sending(String to, String subject, String content, String fileStr) {
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.fileStr = fileStr;
	}

	@Override
	public void run() {
		EmailSend sendEmail = new EmailSend(to, subject, content, fileStr);
		sendEmail.send();

	}
}
