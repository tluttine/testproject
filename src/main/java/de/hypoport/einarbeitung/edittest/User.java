package de.hypoport.einarbeitung.edittest;

import java.io.Serializable;

public class User implements Serializable {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	String name;
	String vorname;

}
