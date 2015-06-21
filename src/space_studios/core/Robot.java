package space_studios.core;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;

public class Robot { //Mecha-ship!
	public StateMachine<Robot> stateMachine;
	public Robot() {
		stateMachine = new DefaultStateMachine<Robot>(this);
	}
	public enum RobotState implements State<Robot> {
		//TODO replace pseudocode with real code
		IDLE() {
			@Override
			public void update(Robot robot) {
				/*
				if (is threatened) {
					robot.stateMachine.changeState(DEFENSIVE);
				} else {
					do a thing();
				}
				*/
			}
		},
		DEFENSIVE() {
			@Override
			public void update(Robot arg0) {
				/*
				if (player_is_broke) {
					 robot.stateMachine.changeState(AGGRESSIVE);
				} else {
					if (has_enough_money && player_built_ship) {
						 if (built_ship == type1) {
							 if (has_enough_money(type1_counter)) {
								 buy_ship(player_current_lane, type1_counter);
							 }
						 }
						 if (built_ship == type2) {
							 if (has_enough_money(type2_counter)) {
								 buy_ship(player_current_lane, type2_counter);
							 }
						 }
						 if (built_ship == type3) {
							 if (has_enough_money(type3_counter)) {
								 buy_ship(player_current_lane, type3_counter);
							 }
						 }
					}
				}
				*/
			}
		},
		AGGRESSIVE() {
			@Override
			public void update(Robot arg0) {
				/*
				if (health < 20%) {
					state = "defensive";
				} else {
					if (lane_is_empty) {
						random_int = new random(between 1 and 3);
						if (random_int == 1) {
							buy_ship(type1);
						}
						if (random_int == 2) {
							buy_ship(type2);
						}
						if (random_int == 3) {
							buy_ship(type3);
						}
					}
				}
				*/
			}
		};
		
		@Override public boolean onMessage(Robot robot, Telegram msg) {return false;}
		@Override public void enter(Robot robot) {}
		@Override public void exit(Robot robot) {}
	}
}