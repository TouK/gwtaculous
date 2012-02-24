package pl.touk.gwtaculous.dnd.utils;

import com.google.gwt.dom.client.Element;

public class DOMUtil {
	
		public static native void cancelAllDocumentSelections()
		/*-{
		    try {
		      $wnd.getSelection().removeAllRanges();
		    } catch(e) { throw new Error("unselect exception:\n" + e); }
		}-*/;
	  
	  	public static void setElementPosition(Element elem, int left, int top) {
	  		elem.getStyle().setPropertyPx("left", left);
	  		elem.getStyle().setPropertyPx("top", top);
	  	}
	  	
	  	public static void setElementPositionX(Element elem, int left){
	  		elem.getStyle().setPropertyPx("left", left);
	  	}
	  	
	  	public static void setElementPositionY(Element elem, int top){
	  		elem.getStyle().setPropertyPx("top", top);
	  	}
	  	
	  	public static void forceElementPosition(Element elem, int left, int top) {
	  		String position = elem.getStyle().getPosition();
	  		
	  		if (position.indexOf("absolute") < 0 && position.indexOf("fixed") < 0) {
	  			elem.getStyle().setProperty("position", "absolute");
	  		}
	  		
	  		elem.getStyle().setPropertyPx("left", left);
	  		elem.getStyle().setPropertyPx("top", top);
	  	}
	  	
		public static boolean isMouseCursorWithinElement(Element element, int mousePosX, int mousePosY){
			
			int elementTop = element.getAbsoluteTop();
			int elementLeft = element.getAbsoluteLeft();
			int elementBottom = element.getAbsoluteBottom();
			int elementRight = element.getAbsoluteRight();
			
			if (mousePosX > elementLeft && mousePosX < elementRight && mousePosY > elementTop && mousePosY < elementBottom) {
				return true;
			}
			return false;
		}
		
		public static int getMouseRelativePositionX(Element target, int mouseClientPositionX) {
			return mouseClientPositionX - target.getAbsoluteLeft() + target.getScrollLeft() + target.getOwnerDocument().getScrollLeft();
	    }
		
		public static int getMouseRelativePositionY(Element target, int mouseClientPositionY) {
			return mouseClientPositionY - target.getAbsoluteTop() + target.getScrollTop() + target.getOwnerDocument().getScrollTop();
	    }
	
}
