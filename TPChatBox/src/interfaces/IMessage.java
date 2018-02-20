package interfaces;

import java.io.Serializable;

public interface IMessage extends Serializable {
	public void setContent(String content);
	public void setSource(IClient c);
}
