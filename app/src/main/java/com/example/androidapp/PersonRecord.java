package com.example.androidapp;

import java.util.Arrays;
import java.util.List;

public class PersonRecord implements DataItem {
	private Integer age;
	private Integer sex;
	private Integer bp;
	private Integer cholesterol;
	private Integer na;
	private Integer k;
	private String drug;

	public PersonRecord(Integer age, Integer sex, Integer bp, Integer cholesterol, Integer na, Integer k, String drug) {
		this.age = age;
		this.sex = sex;
		this.bp = bp;
		this.cholesterol = cholesterol;
		this.na = na;
		this.k = k;
		this.drug = drug;
	}

	@Override
	public String toString() {
		return "PersonRecord{" + "age=" + age + ", sex='" + sex + '\'' + ", bp='" + bp + '\'' + ", cholesterol='"
				+ cholesterol + '\'' + ", na=" + na + ", k=" + k + ", drug='" + drug + '\'' + '}';
	}

	@Override
	public List<Integer> getCoordinates() {
		return Arrays.asList(this.age, this.sex, this.bp, this.cholesterol, this.na, this.k);
	}

	@Override
	public String getClassifier() {
		return this.drug;
	}
}
