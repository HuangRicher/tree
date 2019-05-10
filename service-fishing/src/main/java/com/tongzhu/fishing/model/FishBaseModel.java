package com.tongzhu.fishing.model;


import java.io.Serializable;

/**
 * 鱼基础类
 * @author 海乐乐
 * @date 2018年8月9日
 */
public class FishBaseModel implements Serializable {
	
    private String name;//名称

	private Integer id;//物品type
	
	private Integer ordinary;//在普通渔场的掉落概率
	
	//在高级渔场的掉落概率
	private Integer rare;
	
	//在稀有渔场的掉落概率
	private Integer senior;
	
	
	
	private Integer weight;//权重值(根据渔场不同，权重值不同)
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOrdinary() {
		return ordinary;
	}

	public void setOrdinary(Integer ordinary) {
		this.ordinary = ordinary;
	}

	public Integer getRare() {
		return rare;
	}

	public void setRare(Integer rare) {
		this.rare = rare;
	}

	public Integer getSenior() {
		return senior;
	}

	public void setSenior(Integer senior) {
		this.senior = senior;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	
	
}
