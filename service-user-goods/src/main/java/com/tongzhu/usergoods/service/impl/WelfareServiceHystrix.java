package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.service.WelfareService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class WelfareServiceHystrix implements WelfareService {



	@Override
	public boolean addMall(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingCoffersSetting(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingWelfareSetting(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingChurchSetting(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingSmithySetting(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingWineshopSetting(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingStatueSetting(String fileName, MultipartFile file) {
		return false;
	}

	@Override
	public boolean addBuildingPetshopSetting(String fileName, MultipartFile file) {
		return false;
	}
}
