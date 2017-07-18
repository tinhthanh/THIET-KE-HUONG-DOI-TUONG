package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import model.IModelPaint;
import view.FactoryView;
import view.Interfaceview;
import view.MyJpanel;
import view.ViewPaint;

public class IControllerPanit implements ControllerPaint {
	private String status;
	private ViewPaint viewPaint;
	private IModelPaint model;
	private FactoryView factoryView;
	public IControllerPanit() {
		factoryView = new FactoryView();
	}
	@Override
	public void setBoolean(String s, boolean a) {
		model.setBoolean(s, a);
	}
	@Override
	public void zoom(String e) {
		model.zoom(e);
	}
	@Override
	public void optionMagic(String type) {
		model.optionMagic(type);
	}
	@Override
	public ViewPaint viewPaint() {
     	return viewPaint;
	}
	@Override
	public void createTool(String tybe, String style) {
		if (style.equals("Tool")) {
			setControllreToMyJpanel();
			factoryView.createTool(tybe, this);
		} else if (style.equals("theme")) {
			factoryView.createTool(tybe);
		}
	}
	@Override
	public void setTheme(Interfaceview interfaceview) {
		this.setTheme(interfaceview);
	}
	@Override
	public void setView(ViewPaint giaoDien) {
		this.viewPaint = giaoDien;
	}
	// --------SU kien Giao dien start -----
	@Override
	public void setThickness(int thickness) { // set TRONG SUOT
		model.setThickness(thickness);
	}
	@Override
	public void setCol(Color col1, Color col2) {
		model.setCol(col1, col2);
	}
	@Override
	public void setColor(Color color) {
		model.setColor(color);
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
		model.setStatus(status);
	}
	@Override
	public void setPathImage(String path) {
		model.setPathImage(path);
	}
	// -----------end-----------
       @Override
	public IModelPaint getModel() { // CotrolJpanel
		MyJpanel a = factoryView.getInterfaceview().getTab();
		model = a.sendData();
		a.setPreferredSize(new Dimension(model.getBackgound().getWidth(null) + 10, model.getBackgound().getHeight(null) + 10));
		a.repaint();
		return model;
	}
	public MyJpanel getMyJpanel() {
		return factoryView.getInterfaceview().getTab();
	}
	@Override
	public void optionModel(String type) {
		// TODO Auto-generated method stub
		setControllreToMyJpanel();
		model.optionModel(type);
	}
	@Override
	public void addNewTab() {
		factoryView.getInterfaceview().addNewTab1();
	}
	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return factoryView.getInterfaceview().getJTabbendPnane();
	}
	public void setControllreToMyJpanel() {
		model.setController(this);
	}
	public FactoryView getFactoryView() {
		return factoryView;
	}
	@Override
	public void setText(String[] list) {
		// TODO Auto-generated method stub
		model.setText(list);
	}
	@Override
	public void setTrongSuot(int trongsuot) {
		// TODO Auto-generated method stub
		model.setTtrongSuot(trongsuot);
	}
	public void updata() {
		// TODO Auto-generated method stub
		this.getMyJpanel().repaint();	
	}
}