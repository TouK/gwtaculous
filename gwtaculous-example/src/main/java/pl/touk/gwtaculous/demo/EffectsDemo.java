package pl.touk.gwtaculous.demo;

import pl.touk.gwtaculous.effects.Effects;
import pl.touk.gwtaculous.effects.effect.Effect;
import pl.touk.gwtaculous.effects.effect.EffectAppear;
import pl.touk.gwtaculous.effects.effect.EffectBlindUp;
import pl.touk.gwtaculous.effects.effect.EffectFold;
import pl.touk.gwtaculous.effects.effect.EffectMove;
import pl.touk.gwtaculous.effects.effect.EffectOpacity;
import pl.touk.gwtaculous.effects.effect.EffectParallel;
import pl.touk.gwtaculous.effects.effect.EffectScale;
import pl.touk.gwtaculous.effects.effect.EffectSlideUp;
import pl.touk.gwtaculous.effects.handler.AfterFinishHandler;
import pl.touk.gwtaculous.effects.helpers.EffectOption;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class EffectsDemo extends Composite{
	
	private FlowPanel mainPanel;
	
	private String imageUrl_1 = "images/big_colony_1.png";
	private String imageUrl_2 = "images/big_colony_2.png";
	
	private FlowPanel flowPanel_1;
	private FlowPanel flowPanel_2;
	private FlowPanel flowPanel_3;
	private FlowPanel flowPanel_4;
	private FlowPanel flowPanel_5;
	private FlowPanel flowPanel_6a;
	private FlowPanel flowPanel_6b;
	private FlowPanel flowPanel_7;
	private FlowPanel flowPanel_8;
	private FlowPanel flowPanel_9;
	
	public EffectsDemo() {
		mainPanel = new FlowPanel();
		
		flowPanel_1 = new FlowPanel();
		flowPanel_2 = new FlowPanel();
		flowPanel_3 = new FlowPanel();
		flowPanel_4 = new FlowPanel();
		flowPanel_5 = new FlowPanel();
		flowPanel_6a = new FlowPanel();
		flowPanel_6b = new FlowPanel();
		flowPanel_7 = new FlowPanel();
		flowPanel_8 = new FlowPanel();
		flowPanel_9 = new FlowPanel();
		
		initWidgets();		
	}
	
	private void initWidgets(){

		mainPanel.getElement().setId("mainPanel");
		mainPanel.add(flowPanel_1);
		mainPanel.add(flowPanel_2);
		mainPanel.add(flowPanel_3);
		mainPanel.add(flowPanel_4);
		mainPanel.add(flowPanel_5);
		mainPanel.add(flowPanel_6a);
		mainPanel.add(flowPanel_6b);
		mainPanel.add(flowPanel_7);
		mainPanel.add(flowPanel_8);
		mainPanel.add(flowPanel_9);
		
		flowPanel_1.add(new Image(imageUrl_1));
		flowPanel_1.add(new Label("1"));
		flowPanel_2.add(new Image(imageUrl_1));
		flowPanel_2.add(new Label("2"));
		flowPanel_3.add(new Image(imageUrl_1));
		flowPanel_3.add(new Label("3"));
		flowPanel_4.add(new Image(imageUrl_1));
		flowPanel_4.add(new Label("4"));
		flowPanel_5.add(new Label("5"));
		flowPanel_6a.add(new Image(imageUrl_1));
		flowPanel_6a.add(new Label("6a"));
		flowPanel_6b.add(new Image(imageUrl_2));
		flowPanel_6b.add(new Label("6b"));
		flowPanel_7.add(new Image(imageUrl_1));
		flowPanel_7.add(new Label("7"));
		flowPanel_8.add(new Image(imageUrl_1));
		flowPanel_8.add(new Label("8"));
		flowPanel_9.add(new Image(imageUrl_1));
		flowPanel_9.add(new Label("9"));
		
		flowPanel_1.setStyleName("selector cursorPointer selector_1");
		flowPanel_2.setStyleName("selector cursorPointer selector_2");
		flowPanel_3.setStyleName("selector cursorPointer selector_3");
		flowPanel_4.setStyleName("selector cursorPointer selector_4");
		flowPanel_5.setStyleName("selector cursorPointer selector_5 beforeAnimation");
		flowPanel_6a.setStyleName("selector cursorPointer selector_6");
		flowPanel_6b.setStyleName("selector cursorPointer selector_6 highlighted");
		flowPanel_7.setStyleName("selector cursorPointer selector_7");
		flowPanel_8.setStyleName("selector cursorPointer selector_8");
		flowPanel_9.setStyleName("selector cursorPointer selector_9");		
		
		flowPanel_6b.setVisible(false);
		
		addEffectClickHandlers();
		initWidget(mainPanel);
	}
	
	private void addEffectClickHandlers() {
		
		flowPanel_1.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				Effects.fade(flowPanel_1);
			}
		}, ClickEvent.getType());
		
		flowPanel_2.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				Effects.blindDown(flowPanel_2, new EffectOption("duration", 4.0));
			}
		}, ClickEvent.getType());
		
		flowPanel_3.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectBlindUp effect = new EffectBlindUp(flowPanel_3);
				effect.setDelay(3.0);
				effect.addAfterFinishHandler(new RestoreAfterFinishHandler());
				effect.initialize();
			}
		}, ClickEvent.getType());
		
		flowPanel_4.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectSlideUp effect = new EffectSlideUp(flowPanel_4);
				effect.initialize();
			}
		}, ClickEvent.getType());
		
		flowPanel_5.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				Effects.morph(flowPanel_5, new EffectOption("style", "afterAnimation"), new EffectOption("duration", 3.0));
			}
		}, ClickEvent.getType());
		
		flowPanel_6a.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectAppear effect = new EffectAppear(flowPanel_6b);
				effect.addAfterFinishHandler(new AfterFinishHandler(){
					public void onAfterFinish(Effect effect) {
						Effects.fade(flowPanel_6b, new EffectOption("delay",3.0));
					}
				});
				effect.initialize();
			}
		}, ClickEvent.getType());
		
		flowPanel_7.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectFold effect = new EffectFold(flowPanel_7);
				effect.initialize();
			}
		}, ClickEvent.getType());
		
		flowPanel_8.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				flowPanel_8.getElement().getStyle().setOverflow(Overflow.HIDDEN);
				EffectScale effect = new EffectScale(flowPanel_8, 100);
				effect.setScaleFrom(0);
				effect.setScaleX(false);
				effect.setScaleContent(false);
				effect.setDuration(5.0);
				effect.initialize();
			}
		}, ClickEvent.getType());
		
		flowPanel_9.addDomHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectMove effectMove = new EffectMove(flowPanel_9, 400, 0);
				EffectOpacity effectOpacity = new EffectOpacity(flowPanel_9);
				
				effectOpacity.setFrom(1);
				effectOpacity.setTo(0);
				
				EffectParallel effectParallel = new EffectParallel();
				effectParallel.addEffect(effectMove);
				effectParallel.addEffect(effectOpacity);
				effectParallel.setDuration(1.5);
				effectParallel.setDelay(1.5);
				effectParallel.initialize();
			}
		}, ClickEvent.getType());
		
	}
	
	private class RestoreAfterFinishHandler implements AfterFinishHandler {

		public void onAfterFinish(Effect effect) {
			Effects.appear(effect.getElement());
		}	
	}
	
}
