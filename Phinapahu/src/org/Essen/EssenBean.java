package org.Essen;

import java.io.*;
import java.util.*;

import javax.sound.midi.ShortMessage;

public class EssenBean implements Comparable<EssenBean> {
	String date;
	String essen;
	int votes;

	public int compareTo(EssenBean o2) {
		String s1 = this.date + this.essen + this.votes;
		String s2 = o2.date + o2.essen + o2.votes;
		return s1.compareTo(s2);
	}

	public static EssenBean parse(String string) {
		// System.out.println(string + " Wird versucht zu einem essen umzzuwandeln");
		try {
			int datel = string.indexOf(";");
			String date = string.substring(0, datel);
			string = string.substring(datel + 1);
			int essenl = string.indexOf(";");
			String essenString = string.substring(0, essenl);
			string = string.substring(essenl);
			int votes = Integer.parseInt(string.substring(1));
			// System.out.println("Zum erstellen des essens wird verendet: " + date + " & "
			// + essenString + " & " + votes);
			if (date != null && essenString != null) {
				EssenBean essenlsg = new EssenBean(date, essenString);
				essenlsg.setVotes(votes);
				System.out.println(essenlsg.date + "//" + essenlsg.essen + "//" + essenlsg.votes);
				return essenlsg;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEssen() {
		return essen;
	}

	public void setEssen(String essen) {
		this.essen = essen;
	}

	public EssenBean(String date, String essen) {
		// System.out.println("Neues essen wird ertellt mit den Daten : " + essen + " am
		// " + date);
		if (essen.contains(";")) {
			System.out.println("Das Essen darf ; nicht enthalten");
		} else {
			if (checkDate(date)) {
				this.date = date;
				this.essen = essen.replace(" ", "_");
				this.votes = 0;
			} else {
				System.out.println("datum nicht gefunden");
			}
			// System.out.println("Essen erfolgreich erstellt");
		}
	}

	public EssenBean(String string) {
		// System.out.println(string);
		try {
			int datel = string.indexOf(";");
			String date = string.substring(0, datel);
			string = string.substring(datel + 1);
			int essenl = string.indexOf(";");
			String essenString = string.substring(0, essenl);
			string = string.substring(essenl);
			int votes = Integer.parseInt(string.substring(1));
			if (checkDate(date)) {
				this.date = date;
				this.essen = essenString;
				this.votes = votes;
			} else {
				System.out.println("datum nicht gefunden");
				System.exit(0);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		// System.out.println("Essen erfolgreich erstellt");
	}

	public int getVotes() {
		return this.votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public void vote(String user) {
		// Der essen string sollte keine lehrzeichen enthalten , das macht momentan
		// Fehler
		// System.out.println("Essen vor dem vote: " + this.date + this.essen +
		// this.votes + user);
		if (userVote(this.date, user)) {
			this.delete();
			this.votes++;
			this.saveThis();
		} else {
			System.out.println("Benutzer hat schon abgestimmt");
		}
		// System.out.println("Essen nach dem Vote: " + this.date + this.essen +
		// this.votes + user);
	}

	public boolean userVote(String date, String user) {
		Set<String> voteSet = new TreeSet<String>();
		File votes = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\votes.txt");
		// "..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\votes.txt"
		if (votes.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(votes))) {
				while (br.ready())
					voteSet.add(br.readLine());
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
		}
		if (voteSet.contains(user + ";" + date)) {
			System.out.println("der benutzer hat bereits abgestimmt");
			return false;
		} else {
			try (PrintWriter pw = new PrintWriter(new FileWriter(votes, true))) {
				pw.println(user + ";" + date);
			} catch (IOException e) {
				System.err.println("Fehler beim Schreiben: " + e.getMessage());
			}
			return true;
		}

	}

	public void delete() {
		Set<String> essenStringSet = new TreeSet<String>();
		File essen = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\essen.txt");
		// "..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\essen.txt"
		if (essen.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(essen))) {
				while (br.ready())
					essenStringSet.add(br.readLine());
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
		}
		essenStringSet.remove(this.date + ";" + this.essen + ";" + this.votes);
		try (PrintWriter pw = new PrintWriter(new FileWriter(essen, false))) {
			for (String s : essenStringSet)
				pw.println(s);
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben: " + e.getMessage());
		}
	}

	public void saveThis() {
		File essen = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\essen.txt");
		try (PrintWriter pw = new PrintWriter(new FileWriter(essen, true))) {
			this.essen = this.essen.replace(" ", "_");
			if (this.date != null && this.essen != null) {
				pw.println(this.date + ";" + this.essen + ";" + this.votes);
				this.essen = this.essen.replace("_", " ");
			}
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben: " + e.getMessage());
		}

	}

	protected boolean checkDate(String date) {
		Set<String> dateSet = new TreeSet<String>();
		File dates = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\dates.txt");
		if (dates.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
				while (br.ready())
					dateSet.add(br.readLine());
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
		}
		if (dateSet.contains(date)) {
			return true;
		} else {
			return false;
		}
	}

	/*public static Set<EssenBean> tagesessen() {
		Set<EssenBean> tagesEssen = new TreeSet<EssenBean>();
		Set<String> dateSet = new TreeSet<String>();
		Set<EssenBean> essenSet = new TreeSet<EssenBean>();
		File essen = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\essen.txt");
		if (essen.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(essen))) {
				while (br.ready()) {
					try {
						EssenBean test = new EssenBean(br.readLine());
						essenSet.add(test);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			} catch (Exception ee) {
				System.err.println(ee.getMessage());
			}
		}
		for (EssenBean s : essenSet) {
			dateSet.add(s.date);
		}
		for (String s : dateSet) {
			EssenBean initialEssen = new EssenBean(s, null);
			tagesEssen.add(initialEssen);
		}
		for (EssenBean s : essenSet) {
			for (EssenBean i : tagesEssen) {
				if (i.date == s.date) {
					if (s.votes > i.votes) {
						i = s;
					}
				}
			}
		}
		return tagesEssen;
	}
	*/
}
