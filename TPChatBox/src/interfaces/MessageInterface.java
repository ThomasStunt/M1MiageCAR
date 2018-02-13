package interfaces;

import java.io.Serializable;

import classes.Client;

public interface MessageInterface extends Serializable {
	public void setContent(String content);
	public void setSource(Client c);
}
