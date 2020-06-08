import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {
	public static List<Bemutato> beolvas() {
		List<Bemutato> lista = new ArrayList<Bemutato>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get("nyitohetvege.txt"));
			for (String sor : sorok.subList(1, sorok.size())) {
				String[] split = sor.split(";");
				Bemutato o = new Bemutato(split[0], split[1],
						LocalDate.parse(split[2], DateTimeFormatter.ofPattern("yyyy.MM.dd")), split[3],
						Integer.parseInt(split[4]), Integer.parseInt(split[5]));
				lista.add(o);
			}
		} catch (Exception e) {
			System.out.println("Hiba a fájl beolvasásakor. " + e);
		}
		return lista;
	}

	public static void main(String[] args) throws IOException {
		List<Bemutato> a =beolvas();
		
		System.out.println("3. feladat: Filmek száma az állományban: "+a.size()+" db");
		
		int bev=0;
		LocalDate ettol=LocalDate.of(2016, 12, 01);
		LocalDate eddig=LocalDate.of(2016, 12, 01);
		int szamlalo=0;
		for (Bemutato b : a) {
			
			if(b.getForgalmazo().contains("UIP")) {
				if(szamlalo<1) {
					szamlalo++;
					ettol=LocalDate.of(b.getDatum().getYear(), b.getDatum().getMonth(), b.getDatum().getDayOfMonth());
					eddig=ettol.plusDays(7);
				}
				if(b.getDatum().isBefore(eddig)) {
					bev+=b.getBevetel();
				}
			}
		}

		ettol=LocalDate.of(2016, 12, 01);
		eddig=LocalDate.of(2016, 12, 01);
		szamlalo=0;
		System.out.println("4. feladat: UIP Duna Film forgalmazó 1.hetes bevételeinek összege: "+bev+" Ft");
		//5. feladat:
		System.out.println("5. feladat: Legtöbb látogató az első héten:");
		int maxL=Integer.MIN_VALUE;
		Bemutato maxAlany=null;
		for (Bemutato b : a) {
			
			if(b.getLatogato()>maxL) {
				maxL=b.getLatogato();
				maxAlany=b;
			}
		}
		int elsohetes=0;
		for (Bemutato b : a) {
			if(b.geteCim().contains(maxAlany.geteCim())) {
				if(szamlalo<1) {
					szamlalo++;
					ettol=LocalDate.of(b.getDatum().getYear(), b.getDatum().getMonth(), b.getDatum().getDayOfMonth());
					eddig=ettol.plusDays(7);
				}
				if(b.getDatum().isBefore(eddig)) {
					elsohetes+=b.getBevetel();
				}
			}
		}
		
		System.out.println("\tEredeti cím: "+maxAlany.geteCim());
		System.out.println("\tMagyar cím: "+maxAlany.getmCim());
		System.out.println("\tForgalmazó: "+maxAlany.getForgalmazo());
		System.out.println("\tBevétel az első héten: : "+elsohetes);
		System.out.println("\tLátogatók száma: "+maxAlany.getLatogato());
		
		//6. feladat:
		System.out.print("6. feladat: ");
		boolean volte=false;
		int count=0;
		for (Bemutato b : a) {
			String[] eSplit=b.geteCim().split(" ");
			String[] mSplit=b.getmCim().split(" ");
			for (int i = 0; i < eSplit.length; i++) {
				if(eSplit[i].toLowerCase().startsWith("w")) {
					count++;
				}
			}
			for (int i = 0; i < mSplit.length; i++) {
				if(mSplit[i].toLowerCase().startsWith("w")) {
					count++;
				}
			}
			if(count==eSplit.length+mSplit.length) {
				volte=true;
			}
		}
	
		if(volte) {
			System.out.println("Igen, volt. ");
		}
		
		//7. feladat
		String fajlba="forgalmazo;filmekSzama\n";
		
		HashMap<String, Integer> stat=new HashMap<String,Integer>();
		for (Bemutato b : a) {
			stat.merge(b.getForgalmazo(), 1, Integer::sum);
		}

		for (Entry<String,Integer> b : stat.entrySet()) {
			if(b.getValue()>1) {
				fajlba+=b.getKey()+";"+b.getValue()+"\n";
			}
		}
		
		//8.feladat: 
		int leghoszabb=0;
		int aktHossz=0;
		szamlalo=0;
		ettol=LocalDate.of(2016, 12, 01);
		LocalDate akt=LocalDate.of(2016, 12, 01);
		
		for (Bemutato b : a) {
			if(b.getForgalmazo().contains("InterCom")) {
				if(szamlalo<1) {
					ettol=LocalDate.of(b.getDatum().getYear(), b.getDatum().getMonth(), b.getDatum().getDayOfMonth());
					szamlalo++;
				}
				if(szamlalo>0) {
					akt=LocalDate.of(b.getDatum().getYear(), b.getDatum().getMonth(), b.getDatum().getDayOfMonth());
					aktHossz=akt.getDayOfYear()-ettol.getDayOfYear();
					if(aktHossz>leghoszabb) {
						leghoszabb=aktHossz;
					}
					ettol=LocalDate.of(b.getDatum().getYear(), b.getDatum().getMonth(), b.getDatum().getDayOfMonth());
				}
				eddig=LocalDate.of(2016, 12, 01);
			}
		}
		System.out.println("8. feladat: A leghosszabb időszak két InterCom-os bemutatü között: "+leghoszabb+" nap");
		
		Files.write(Paths.get("stat.csv"),fajlba.getBytes());
		
	}// end of main
}
