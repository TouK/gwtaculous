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
	
	private FlowPanel mainNavigationPanelWrapper;
	private FlowPanel mainNavigationPanel;
	private FlowPanel mainNavigationPanelSelectorWrapper;
	
	private Image image_1;
	private Image image_2;
	private Image image_3;
	private Image image_4;
	private Image image_5;
	private Image image_6;
	private Image image_7;
	private Image image_8;
	private Image image_9;
	
	private FlowPanel flowPanel_1;
	private FlowPanel flowPanel_2;
	private FlowPanel flowPanel_3;
	private FlowPanel flowPanel_4;
	private FlowPanel flowPanel_5;
	private FlowPanel flowPanel_6;
	private FlowPanel flowPanel_7;
	private FlowPanel flowPanel_8;
	private FlowPanel flowPanel_9;
	
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private Label label_7;
	private Label label_8;
	private Label label_9;
	
	public EffectsDemo() {
		mainNavigationPanelWrapper = new FlowPanel();
		mainNavigationPanel = new FlowPanel();
		mainNavigationPanelSelectorWrapper = new FlowPanel();
		
		flowPanel_1 = new FlowPanel();
		flowPanel_2 = new FlowPanel();
		flowPanel_3 = new FlowPanel();
		flowPanel_4 = new FlowPanel();
		flowPanel_5 = new FlowPanel();
		flowPanel_6 = new FlowPanel();
		flowPanel_7 = new FlowPanel();
		flowPanel_8 = new FlowPanel();
		flowPanel_9 = new FlowPanel();
		
		image_1 = new Image("images/navigation/big_colony_1.png");
		image_2 = new Image("images/navigation/big_colony_1.png");
		image_3 = new Image("images/navigation/big_colony_1.png");
		image_4 = new Image("images/navigation/big_colony_1.png");
		image_5 = new Image("images/navigation/big_colony_2.png");
		image_6 = new Image("images/navigation/big_colony_1.png");
		image_7 = new Image("images/navigation/big_colony_1.png");
		image_8 = new Image("images/navigation/big_colony_1.png");
		image_9 = new Image("images/navigation/big_colony_1.png");
		
		label_1 = new Label("1");
		label_2 = new Label("2");
		label_3 = new Label("3");
		label_4 = new Label("4");
		label_5 = new Label("5");
		label_6 = new Label("6");
		label_7 = new Label("7");
		label_8 = new Label("8");
		label_9 = new Label("9");
		
		initWidgets();		
	}
	
	private void initWidgets(){
		mainNavigationPanelWrapper.getElement().setId("mainViewPanelWrapper");
		mainNavigationPanelWrapper.add(mainNavigationPanel);
		mainNavigationPanel.getElement().setId("mainNavigationPanel");
		mainNavigationPanel.add(mainNavigationPanelSelectorWrapper);
		mainNavigationPanelSelectorWrapper.getElement().setId("mainNavigationPanelSelectorWrapper");		
		
		mainNavigationPanelSelectorWrapper.add(flowPanel_1);
		mainNavigationPanelSelectorWrapper.add(flowPanel_2);
		mainNavigationPanelSelectorWrapper.add(flowPanel_3);
		mainNavigationPanelSelectorWrapper.add(flowPanel_4);
		mainNavigationPanelSelectorWrapper.add(flowPanel_5);
		mainNavigationPanelSelectorWrapper.add(flowPanel_6);
		mainNavigationPanelSelectorWrapper.add(flowPanel_7);
		mainNavigationPanelSelectorWrapper.add(flowPanel_8);
		mainNavigationPanelSelectorWrapper.add(flowPanel_9);
		
		flowPanel_1.add(image_1);
		flowPanel_1.add(label_1);
		flowPanel_2.add(image_2);
		flowPanel_2.add(label_2);
		flowPanel_3.add(image_3);
		flowPanel_3.add(label_3);
		flowPanel_4.add(image_4);
		flowPanel_4.add(label_4);
		flowPanel_5.add(label_5);
		flowPanel_6.add(image_6);
		flowPanel_6.add(image_5);
		flowPanel_6.add(label_6);
		flowPanel_7.add(image_7);
		flowPanel_7.add(label_7);
		flowPanel_8.add(image_8);
		flowPanel_8.add(label_8);
		flowPanel_9.add(image_9);
		flowPanel_9.add(label_9);
		
		image_5.setVisible(false);
		
		flowPanel_1.setStyleName("selector mainNavigationSelector_1");
		flowPanel_2.setStyleName("selector mainNavigationSelector_2");
		flowPanel_3.setStyleName("selector mainNavigationSelector_3");
		flowPanel_4.setStyleName("selector mainNavigationSelector_4");
		flowPanel_5.setStyleName("selector mainNavigationSelector_5 beforeAnimation");
		flowPanel_6.setStyleName("selector mainNavigationSelector_6");
		flowPanel_7.setStyleName("selector mainNavigationSelector_7");
		flowPanel_8.setStyleName("selector mainNavigationSelector_8");
		flowPanel_9.setStyleName("selector mainNavigationSelector_9");		
		
		addEffectClickHandlers();
		initWidget(mainNavigationPanelWrapper);
	}
	
	private void addEffectClickHandlers() {
		
		label_1.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				Effects.fade(image_1);
			}
		});
		
		label_2.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				Effects.blindDown(image_2, new EffectOption("duration", 4.0));
			}
		});
		label_3.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectBlindUp effect = new EffectBlindUp (image_3);
				effect.setDelay(3.0);
				effect.addAfterFinishHandler(new RestoreAfterFinishHandler());
				effect.initialize();
			}
		});
		label_4.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectSlideUp effect = new EffectSlideUp(flowPanel_4);
				effect.initialize();
			}
		});
		label_5.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				Effects.morph(flowPanel_5, new EffectOption("style", "afterAnimation"), new EffectOption("duration", 3.0));
			}
		});
		label_6.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectAppear effect = new EffectAppear(image_5);
				effect.addAfterFinishHandler(new AfterFinishHandler(){
					public void onAfterFinish(Effect effect) {
						Effects.fade(image_5, new EffectOption("delay",5.0));
					}
				});
				effect.initialize();
			}
		});
		label_7.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				EffectFold effect = new EffectFold(flowPanel_7);
				effect.initialize();
			}
		});
		label_8.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent arg0) {
				flowPanel_8.getElement().getStyle().setOverflow(Overflow.HIDDEN);
				EffectScale effect = new EffectScale(flowPanel_8, 100);
				effect.setScaleFrom(0);
				effect.setScaleX(false);
				effect.setScaleContent(false);
				effect.setDuration(5.0);
				effect.initialize();
			}
		});
		label_9.addClickHandler(new ClickHandler(){
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
		});
		
	}
	
	private class RestoreAfterFinishHandler implements AfterFinishHandler {

		public void onAfterFinish(Effect effect) {
			Effects.appear(effect.getElement());
		}	
	}
	
}
