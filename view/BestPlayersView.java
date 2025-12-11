package ticTacToe.view;

import ticTacToe.model.BestPlayersModel;
import ticTacToe.util.Console;

public class BestPlayersView {
	
	private final BestPlayersModel model;
	
	public BestPlayersView(BestPlayersModel model) {
		
		super();
		this.model = model;
	}

	public void print() {
		
		Console.println("""
		************************************************
		| G R A N D E S - V E N C E D O R E S          |
		|----------------------------------------------|
		| Nome Venceu Perdeu Empate                    |
		|----------------------------------------------|		
				""");
		
		String format = "|  %-20s %3d %3d %3d            |\n";
		
		model.asStream().forEach(bp -> {
			
			String name = bp.name();
			if(name.length() > 20) 
				name = name.substring(0, 20);
				
			Console.printf(format, name,
							bp.wonTimes(), bp.lostTimes(), bp.drawTimes());
			
		});
		Console.println("************************************************");
	}
	
	static public void main(String[] args) {
		
		BestPlayersModel bpm = new BestPlayersModel(10);
		BestPlayersView bpv = new BestPlayersView(bpm);
		
		bpm.addBestPlayer("Maria Cristina", 5, 4, 7);
		bpm.addBestPlayer("Joao", 1, 4, 7);
		bpm.addBestPlayer("José Rodrigo", 5, 3, 7);
		
		bpv.print();
	}
}
