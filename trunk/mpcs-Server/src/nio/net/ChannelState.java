package nio.net;

/**
 * 记录（读/写）线程信息
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class ChannelState {
	
	public ChannelState() {
		threadNum = 0;
        m_start = false;
        m_startTime = System.currentTimeMillis();
    }
	
    public boolean isStart() {
        return m_start;
    }
    public void setStart(boolean start) {
        this.m_start = start;
    }
    public long getStartTime() {
        return m_startTime;
    }
    public void setStartTime(long startTime) {
        this.m_startTime = startTime;
    }
    public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	private boolean m_start;
    private long m_startTime;
    private int threadNum;
}
