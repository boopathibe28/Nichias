package com.nichias.interfaces;

import com.nichias.model_api.BranchViewApiResponse;
import com.nichias.model_api.BranchViewDetailsApiResponse;

import java.util.List;

public interface CategoryClick {
    void onClick(int position, List<BranchViewDetailsApiResponse.Subcategories> subCategoryList);
}
