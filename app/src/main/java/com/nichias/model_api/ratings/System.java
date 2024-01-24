package com.nichias.model_api.ratings;

import com.google.gson.annotations.SerializedName;

public class System {

	@SerializedName("nc")
	private String nc;

	public void setNc(String nc){
		this.nc = nc;
	}

	public String getNc(){
		return nc;
	}

	@Override
 	public String toString(){
		return 
			"System{" + 
			"nc = '" + nc + '\'' + 
			"}";
		}
}