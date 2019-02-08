package org.kocofarm.domain.rentCar;

import lombok.Data;

@Data
public class RentCarVO {
	
	private String carId; 
	private String modelName;
	private String carModel;
	private String condition;
	private int price;
	private int year;
	private String oilType;
	private String regDt;
	private String upDt;
	

}
