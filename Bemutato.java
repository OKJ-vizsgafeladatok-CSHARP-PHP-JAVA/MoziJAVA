import java.time.LocalDate;
import java.time.LocalDate;

public class Bemutato {
	private String eCim;
	private String mCim;
	private LocalDate datum; 
	private String forgalmazo;
	private int bevetel;
	private int latogato;
	public String geteCim() {
		return eCim;
	}
	public void seteCim(String eCim) {
		this.eCim = eCim;
	}
	public String getmCim() {
		return mCim;
	}
	public void setmCim(String mCim) {
		this.mCim = mCim;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public String getForgalmazo() {
		return forgalmazo;
	}
	public void setForgalmazo(String forgalmazo) {
		this.forgalmazo = forgalmazo;
	}
	public int getBevetel() {
		return bevetel;
	}
	public void setBevetel(int bevetel) {
		this.bevetel = bevetel;
	}
	public int getLatogato() {
		return latogato;
	}
	public void setLatogato(int latogato) {
		this.latogato = latogato;
	}
	public Bemutato(String eCim, String mCim, LocalDate datum, String forgalmazo, int bevetel, int latogato) {
		super();
		this.eCim = eCim;
		this.mCim = mCim;
		this.datum = datum;
		this.forgalmazo = forgalmazo;
		this.bevetel = bevetel;
		this.latogato = latogato;
	}
	@Override
	public String toString() {
		return "Bemutato [eCim=" + eCim + ", mCim=" + mCim + ", datum=" + datum + ", forgalmazo=" + forgalmazo
				+ ", bevetel=" + bevetel + ", latogato=" + latogato + "]";
	}
	
}
