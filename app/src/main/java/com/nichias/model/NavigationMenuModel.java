package com.nichias.model;


import com.nichias.enums.NavigationMenuEnum;

public class NavigationMenuModel {

    private int navigationIcon;
    private String navigationItemName;
    private NavigationMenuEnum navigationItemId;

    public int getNavigationIcon() {
        return navigationIcon;
    }

    public void setNavigationIcon(int navigationIcon) {
        this.navigationIcon = navigationIcon;
    }

    public String getNavigationItemName() {
        return navigationItemName;
    }

    public void setNavigationItemName(String navigationItemName) {
        this.navigationItemName = navigationItemName;
    }

    public NavigationMenuEnum getNavigationItemId() {
        return navigationItemId;
    }

    public void setNavigationItemId(NavigationMenuEnum navigationItemId) {
        this.navigationItemId = navigationItemId;
    }
}
