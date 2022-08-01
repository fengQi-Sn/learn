package example.example.behavior.statemachine.state;

import example.example.behavior.statemachine.State;

public class FireMario implements IMario {
    private MarioStateMachine stateMachine;
    public FireMario(MarioStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom() {

    }

    @Override
    public void obtainCape() {

    }

    @Override
    public void obtainFireFlower() {

    }

    @Override
    public void meetMonster() {

    }
}
