/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Vastem
 */
public class MuroState {
    private static MuroState instance;
    public static final String IN_HASHTAG = "100";
    public static final String ALL = "101";
    private String state;
    
    private MuroState(){
    }
    
    public static MuroState getInstance(){
        if(instance == null) instance = new MuroState();
        return instance;
    }

    public String getState() {
        return state;
    }

    public void setStateIN_HASHTAG() {
        this.state = IN_HASHTAG;
    }
    
    public void setStateALL() {
        this.state = ALL;
    }
}
