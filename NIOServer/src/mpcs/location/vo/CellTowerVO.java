package mpcs.location.vo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class CellTowerVO extends TowerVO{
	/*== 格式
	"cell_towers" : [{
        "cell_id" : "cell_id",
        "location_area_code" : "lac",
        "mobile_country_code" : "mcc",
        "mobile_network_code" : "mnc",
        "timing_advance" : 5555
        "signal_strength" : 4
    }]
    */
	
	private int cell_id;
	private int location_area_code;
	private int mobile_country_code = 460;
	private int mobile_network_code = 0;
	private int timing_advance;
	
	public CellTowerVO(int mnc , int lac , int cell_id){
		this.setMobile_network_code(mnc);
		this.setLocation_area_code(lac);
		this.setCell_id(cell_id);
	}
	
	public CellTowerVO(int lac , int cell_id){
		this.setLocation_area_code(lac);
		this.setCell_id(cell_id);
	}
	
	public CellTowerVO(){
	}
	
	public int getCell_id() {
		return cell_id;
	}
	public void setCell_id(int cell_id) {
		this.cell_id = cell_id;
	}
	public int getLocation_area_code() {
		return location_area_code;
	}
	public void setLocation_area_code(int location_area_code) {
		this.location_area_code = location_area_code;
	}
	public int getMobile_country_code() {
		return mobile_country_code;
	}
	public void setMobile_country_code(int mobile_country_code) {
		this.mobile_country_code = mobile_country_code;
	}
	public int getMobile_network_code() {
		return mobile_network_code;
	}
	public void setMobile_network_code(int mobile_network_code) {
		this.mobile_network_code = mobile_network_code;
	}
	public int getTiming_advance() {
		return timing_advance;
	}
	public void setTiming_advance(int timing_advance) {
		this.timing_advance = timing_advance;
	}
}
