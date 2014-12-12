package netty.in.action.one;

public interface Fetcher {
	
	void fetchData(FetcherCallback callback);
	
}
