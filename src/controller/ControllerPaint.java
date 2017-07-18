package controller;

import java.awt.Color;
import java.awt.Component;

import model.IModelPaint;
import view.Interfaceview;
import view.ViewPaint;

public interface ControllerPaint {
	public ViewPaint viewPaint();
	public void setView(ViewPaint viewPaint);
	
	public void setBoolean(String s, boolean a);
	public void setTrongSuot(int trongsuot);
	public void createTool(String tybe, String style);
	public void setThickness(int thickness);
	public void setCol(Color col1, Color col2);
	public void setColor(Color color);
	public void setPathImage(String path);
	
	public void zoom(String e);
	
	public void setStatus(String status);
	public void optionModel(String type);
	public void addNewTab();
	
	public Component getComponent();
	public void setTheme(Interfaceview interfaceview);
	public void setText(String[] list);
	public void optionMagic(String type);
	
	public IModelPaint getModel();

}
