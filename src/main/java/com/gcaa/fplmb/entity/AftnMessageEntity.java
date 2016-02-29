package com.gcaa.fplmb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AFTN_MSG")
public class AftnMessageEntity extends FplmbEntity{

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
 	private Long Id;
	private String Raw_Msg;
	private String Origin;
	private String Priority;
	private Date Filing_Time;
	private String Call_Sign;
	private String Ssr;
	private String Rules;
	private String Flight_Type;
	private Date   No_Of_Ac;
	private String Ac_Type;
	private String Wtc;
	private String Equipment;
	private String Adep;
	private String Eobt;
	private String Ades;
	private String Altn_1;
	private String Altn_2;
	private String Route;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getRaw_Msg() {
		return Raw_Msg;
	}
	public void setRaw_Msg(String raw_Msg) {
		Raw_Msg = raw_Msg;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getPriority() {
		return Priority;
	}
	public void setPriority(String priority) {
		Priority = priority;
	}
	public Date getFiling_Time() {
		return Filing_Time;
	}
	public void setFiling_Time(Date filing_Time) {
		Filing_Time = filing_Time;
	}
	public String getCall_Sign() {
		return Call_Sign;
	}
	public void setCall_Sign(String call_Sign) {
		Call_Sign = call_Sign;
	}
	public String getSsr() {
		return Ssr;
	}
	public void setSsr(String ssr) {
		Ssr = ssr;
	}
	public String getRules() {
		return Rules;
	}
	public void setRules(String rules) {
		Rules = rules;
	}
	public String getFlight_Type() {
		return Flight_Type;
	}
	public void setFlight_Type(String flight_Type) {
		Flight_Type = flight_Type;
	}
	public Date getNo_Of_Ac() {
		return No_Of_Ac;
	}
	public void setNo_Of_Ac(Date no_Of_Ac) {
		No_Of_Ac = no_Of_Ac;
	}
	public String getAc_Type() {
		return Ac_Type;
	}
	public void setAc_Type(String ac_Type) {
		Ac_Type = ac_Type;
	}
	public String getWtc() {
		return Wtc;
	}
	public void setWtc(String wtc) {
		Wtc = wtc;
	}
	public String getEquipment() {
		return Equipment;
	}
	public void setEquipment(String equipment) {
		Equipment = equipment;
	}
	public String getAdep() {
		return Adep;
	}
	public void setAdep(String adep) {
		Adep = adep;
	}
	public String getEobt() {
		return Eobt;
	}
	public void setEobt(String eobt) {
		Eobt = eobt;
	}
	public String getAdes() {
		return Ades;
	}
	public void setAdes(String ades) {
		Ades = ades;
	}
	public String getAltn_1() {
		return Altn_1;
	}
	public void setAltn_1(String altn_1) {
		Altn_1 = altn_1;
	}
	public String getAltn_2() {
		return Altn_2;
	}
	public void setAltn_2(String altn_2) {
		Altn_2 = altn_2;
	}
	public String getRoute() {
		return Route;
	}
	public void setRoute(String route) {
		Route = route;
	}


	
}
