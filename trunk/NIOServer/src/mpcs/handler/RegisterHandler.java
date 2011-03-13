package mpcs.handler;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.vo.UserVO;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 用户注册处理器</p>
 * <p>Description: 处理用户注册事件</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class RegisterHandler extends ListenAdapter {
	
	private UserVO vo = null;
	
	public RegisterHandler(){
	}
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
        if (command == GlobalConst.C_USER_REGISTER) {
        	vo = new UserVO();
        	vo.setEmail(request.getPacket().readString());
        	vo.setPassword(request.getPacket().readString());
        	//执行数据库操作 查询用户
        	if (isExist(vo.getEmail())) {
        		//该邮箱已被注册
        		BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_REGISTER, GlobalErrorConst.E_USER_REGISTER);
        		cmd.writeString(LangUtil.get("20001") + vo.getEmail());
        		response.send(cmd);
        		return;
			}else {
				// 添加用户
				if (addUser(vo.getEmail(), vo.getPassword()) == 1) {
					// 注册成功 返回
					BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_REGISTER);
					cmd.writeString(LangUtil.get("20003") + vo.getEmail());
					response.send(cmd);
					return;
				}else {
					// 执行数据库操作，添加用户时失败
					BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_REGISTER, GlobalErrorConst.E_ADD_USER_FAILED);
	        		cmd.writeString(LangUtil.get("20002") + vo.getEmail());
					response.send(cmd);
				}
			}
        }
	}
	
	/**
     * 添加用户
     * @param email
     * @param pwd
     * @return 返回1则成功添加，0则添加失败
     */
    private int addUser(String email, String pwd){
    	return ExeSQL.addUser(email, pwd);
    }
    
    /**
     * 检查此邮箱是否可用
     * @param email
     * @return
     */
    private boolean isExist(String email){
    	String pwd = ExeSQL.selectByEmail(email);
    	if (pwd.equals("")) {
    		return false;
		}
    	return true;
    }

}
