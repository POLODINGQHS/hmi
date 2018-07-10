package com.globot.hmi.attendance.vo;

import java.util.List;

import com.globot.hmi.attendance.domain.Menu;
import org.springframework.cglib.beans.BeanCopier;

public class MenuVO extends Menu {
	
	private List<Menu> childMenu;
	
	public MenuVO(Menu menu) {
        if (menu != null) {
            BeanCopier beanCopier = BeanCopier.create(Menu.class, MenuVO.class, false);
            beanCopier.copy(menu, this, null);
        }
    }
	public List<Menu> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}
	
}
