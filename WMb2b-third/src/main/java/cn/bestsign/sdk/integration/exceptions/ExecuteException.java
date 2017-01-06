package cn.bestsign.sdk.integration.exceptions;

public class ExecuteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3821478350945657020L;

	private Object data = null;
	
	public ExecuteException(String message) {
        super(message);
    }
	
	public ExecuteException(String message, Object data) {
        super(message);
        this.data = data;
    }
	
	public Object getData() {
		return data;
	}
}
