package com.example.demo.model;

public class Plant {
	public final static String KEY_ROOT_ARRAY = "plants";
	public final static String KEY_ID = "id";
	public final static String KEY_GENUS = "genus";
	public final static String KEY_SPECIES = "species";
	public final static String KEY_CULTIVAR = "cultivar";
	public final static String KEY_COMMON = "common";

	private int id;
	private String genus;
	private String species;
	private String cultivar;
	private String common;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenus() {
		return genus;
	}
	public void setGenus(String genus) {
		this.genus = genus;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getCultivar() {
		return cultivar;
	}
	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}

	@Override
	public String toString() {
		return "Plant{" +
				"id=" + id +
				", genus='" + genus + '\'' +
				", species='" + species + '\'' +
				", cultivar='" + cultivar + '\'' +
				", common='" + common + '\'' +
				'}';
	}
}
