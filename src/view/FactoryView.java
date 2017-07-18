package view;

import toolview.InterfaceTool;
import toolview.JFrameTool;
import toolview.MySnipTool;
import toolview.MyToolGIF;
import toolview.Resize;
import toolview.Shopstickers;
import toolview.TextAdd;
import controller.IControllerPanit;

public class FactoryView {
	private Interfaceview interfaceview;
	private InterfaceTool mySnipTool;

	public FactoryView() {
	}

	public InterfaceTool createTool(String type, IControllerPanit iControllerPanit) {
		mySnipTool = null;
		if (type.equals("picture")) {
			mySnipTool = new Shopstickers(iControllerPanit);
		} else if (type.equals("resize")) {
			mySnipTool = new Resize(iControllerPanit);
		} else if (type.equals("Chup")) {
			mySnipTool = new JFrameTool(new MySnipTool(iControllerPanit));
		} else if (type.equals("GIF")) {
			mySnipTool = new JFrameTool(new MyToolGIF(iControllerPanit));
		} else if (type.equals("Text")) {
			mySnipTool = new TextAdd(iControllerPanit);
		}
		return mySnipTool;
	}

	public Interfaceview createTool(String style) {
		interfaceview = null;
		if (style.equals("Tab")) {
			interfaceview = new MyTabbedPane();
		} else if (style.equals("Desktop")) {
			interfaceview = new Desktop();
		}
		return interfaceview;
	}

	public Interfaceview getInterfaceview() {
		return interfaceview;
	}

	public InterfaceTool getMySnipTool() {
		return mySnipTool;
	}

	public void setVisible() {
		mySnipTool.setVisible();
	}

}
