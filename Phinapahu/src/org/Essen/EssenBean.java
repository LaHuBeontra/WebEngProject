package org.Essen;

import java.io.*;
import java.util.*;

public class EssenBean {
	String date;
	String essen;
	int votes;

	static public EssenBean parse(String string) {
		int datel = string.indexOf(";");
		System.out.println(datel);
		String date = string.substring(0, datel);		
		System.out.println(date);
		string = string.substring(datel+1);
		System.out.println(string);
		int essenl =  string.indexOf(";");
		System.out.println(essenl);
		String essenString = string.substring(0,essenl );
		string = string.substring(essenl);
		int votes =Integer.parseInt(string.substring(1));
		try{EssenBean essenlsg = new EssenBean(date,essenString);
		essenlsg.setVotes(votes);
		return essenlsg;
		}
		catch(Exception e){
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
		if (checkDate(date)) {
			this.date = date;
			this.essen = essen;
			this.votes = 0;
			System.out.println("test zum anzeigen");

		} else {

		}
	}

	public int getVotes() {
		return this.votes;
	}
	
	public void setVotes(int votes) {
		this.votes = votes;
	}

	public void vote() {
		this.delete();
		this.votes++;
		this.save();
	}

	public void delete() {
		Set<EssenBean> dateSet = new TreeSet<EssenBean>();
		File dates = new File("dates.txt");
		if (dates.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
				while (br.ready())
					dateSet.add(this);
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
		}
		dateSet.remove(this);
	}

	public void save() {
		File essen = new File("Essen.txt");

		try (PrintWriter pw = new PrintWriter(new FileWriter(essen, true))) {
			pw.println(this);
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben: " + e.getMessage());
		}

	}

	protected boolean checkDate(String date) {
		Set<String> dateSet = new TreeSet<String>();
		File dates = new File("dates.txt");
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
}
