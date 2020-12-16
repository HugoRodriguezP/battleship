package BattleShip;

import java.util.ArrayList;

public class GameView {
	
	/*Attributes*/
	
	
	/*Methods*/
	
	public GameView(){
		
	}
	
	public void requestLetter(String name) {
		System.out.println(name + " introduce a letter: ");
	}
	
	public void requestNum(String name) {
		System.out.println(name + " introduce a num: ");
	}
	
	public void requestName() {
		System.out.println("Introduce your name: ");
	}
	
	public void shipInformation(PlayerInterface player) {
		
		System.out.println(player.getName() + " has four ships: \n1- AirCraft carrier (length 5)\n" +
												"2- Destroyer (length 4)\n" +
												"3- Submarine (length 3)\n" + 
												"4- Landing craft (length 2)\n");
	}
	
	public void requestAirCraftCarrier() {
		System.out.println("Introduce AirCraft carrier(length 5) coordenates.\n");
	}
	
	public void requestDestroyer() {
		System.out.println("Introduce Destroyer (length 4) coordenates.\n");
	}
	
	public void requestSubmarine() {
		System.out.println("Introduce Submarine (length 3) coordenates.\n");
	}
	
	public void requestLandingCraft() {
		System.out.println("Introduce Landing craft (length 2) coordenates.\n");
	}
	
	public void requestFirstCoordinate(){
		System.out.println("Introduce the firsts coordinates\n");
	}
	
	public void requestLastCoordinate(){
		System.out.println("Introduce the lasts coordinates\n");
	}
	
	public void isWater(){
		System.out.println("Ups. You miss your target, is WATER.\n");
	}
	
	public void isTouched(){
		System.out.println("Whoa! We have reached de enemy, is TOUCHED.\n");
	}
	
	public void isDeath(){
		System.out.println("Congratulations! One of the enemy's ships is SUNKED.\n");
	}
	
	public void winner(PlayerInterface player){
		System.out.println("Congratulations " + player.getName() + "! You're the WINNER!!!\n");
	}
	
	public void error(){
		System.out.println("Error. Try again.");
	}
	
	public void start(){
		System.out.println("\n\nThe game has STARTED.\n\n");
	}
}
