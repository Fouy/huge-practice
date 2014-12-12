package netty.in.action.one;

public interface FetcherCallback {
	/** 
	 * 接收数据时被调用 
	 * @param data
	 * @throws Exception
	 */
	void onData(Data data) throws Exception;
	/**
	 * 发生错误时被调用
	 * @param cause
	 */
	void onError(Throwable cause);
	
}