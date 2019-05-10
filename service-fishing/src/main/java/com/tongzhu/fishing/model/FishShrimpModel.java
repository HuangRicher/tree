package com.tongzhu.fishing.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**鱼种相关配置
 * 海虾
 * @author 海乐乐
 * @date 2018年8月9日
 */
@Component
@ConfigurationProperties(prefix="fishing.shrimp")
public class FishShrimpModel extends FishBaseModel{
	
    private String name;//名称

	private Integer id;//物品type
	
	private Integer ordinary;//在普通渔场的掉落概率
	
	//在高级渔场的掉落概率
	private Integer rare;
	
	//在稀有渔场的掉落概率
	private Integer senior;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
}
