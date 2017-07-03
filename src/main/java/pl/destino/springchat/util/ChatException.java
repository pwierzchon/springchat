package pl.destino.springchat.util;

public class ChatException extends Exception {

	private static final long serialVersionUID = -5645645645646452342L;

	public ChatException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChatException(String message) {
		super(message);
	}
}
