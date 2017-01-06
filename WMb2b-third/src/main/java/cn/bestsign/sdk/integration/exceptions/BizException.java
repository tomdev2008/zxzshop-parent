package cn.bestsign.sdk.integration.exceptions;

public class BizException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3821478350945657020L;

	private int code = 0;
	private Object content = null;
	
	public BizException(int code, String message) {
        super(message);
        this.code = code;
    }
	
	public BizException(int code, Object content) {
        super();
        this.code = code;
        this.content = content;
    }
	
	public BizException(int code, String message, Object content) {
        super(message);
        this.code = code;
        this.content = content;
    }
	
	public int getCode() {
		return this.code;
	}
	
	public Object getContent() {
		return content;
	}
}
