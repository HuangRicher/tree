package com.tongzhu.fishing.service;


import com.tongzhu.fishing.model.FishingSecretOperation;

import java.util.List;


public interface FishingSecretOperationService {

	List<FishingSecretOperation> getSecretPrize(Integer begin, Integer end);
}
