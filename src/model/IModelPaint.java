package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import controller.IControllerPanit;
import view.ViewPaint;

public interface IModelPaint {

	public void setThickness(int thickness);

	public void optionMagic(String s);

	public void optionMenu(String actionCommand);

	public void setCol(Color col1, Color col2);

	public void setColor(Color color);

	public void zoom(String e);

	public void setStatus(String status);

	public void setPathImage(String path);

	public void optionModel(String type);

	public Image getBackgound();

	public void setController(IControllerPanit iControllerPanit);

	public void setText(String[] list);

	public void setTtrongSuot(int trongsuot);

	public void setBoolean(String s, boolean a);

}
