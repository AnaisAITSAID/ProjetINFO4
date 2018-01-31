package Element;

public class Chrono {
	static long chrono = 0 ;

	public void Go_Chrono() {
		chrono = java.lang.System.currentTimeMillis() ;
	}

	
	public long Stop_Chrono() {
		long chrono2 = java.lang.System.currentTimeMillis() ;
		long temps = chrono2 - chrono ;
		return temps;
	} 
	
}
