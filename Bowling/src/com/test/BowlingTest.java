package com.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.service.KataBowlingGame;

public class BowlingTest{
	//Cr�atin d'une instance de l'objet kataBowlingGame
	//Variable globale dans toute la classe pour l'utiliser dans tout les m�thodes
	//Une seule instance est suffisante pour le test de tout l'algorithme
	private KataBowlingGame game= new KataBowlingGame();

	@Test
	public void gutterGame() throws Exception {
		// Aucun quille n'est renvers� dans tout les coups (manches)
		rollMany(20, 0);
		// Le score doit �tre � 0
		assertEquals(0, game.score());
	}

	@Test
	public void allOnes() throws Exception {
		// Renvers� un quille pour chque quille sur 20 coups
		rollMany(20, 1);
		// Le r�sultat sera � 20
		assertEquals(20,game.score());
	}

	@Test
	public void canRollSpare() throws Exception{
		// Renvers� les quilles sur deux coups
		rollSpare();
		// Renvers� encore 3 quilles
		game.roll(3);
		// 17 coups sans aucune quille renvers�
		rollMany(17,0);
		// R�sultat 10 + bonus spare 3 + la sommes des quilles renvers� 
		// sur les deux derniers coups (3+0) = 16
		assertEquals(16, game.score());
	}
	
	@Test
	public void canRollStrike() throws Exception{
		// Renvers� les quilles sur un seul coup
		rollStrike();
		// Renvers� 5 quilles
		game.roll(5);
		// Renvers� 3 quilles
		game.roll(3);
		// 16 coups sans aucune quille renvers�e
		rollMany(16,0);
		// R�sultat 10 + bonus strike 8 + la sommes des quilles renvers�es 
		// sur les deux derniers coups (5+3) = 26
		assertEquals(26, game.score());
	}

	@Test
	public void perfectGame() throws Exception{
		//Renvers� 10 quilles pour chaque coup du 12 coups
		rollMany(12,10);
		assertEquals(300, game.score());
	}
	
	//M�thode permettant de renverser les quilles d'un seul coup
	private void rollStrike() {
		game.roll(10);
	}

	//M�thode permettant de renverser les quilles sur deux coups
	private void rollSpare(){
		game.roll(5);
		game.roll(5);
	}

	// M�thode permettant de renverser des quilles
	private void rollMany(int n, int pins){
		for(int i=0;i<n;i++){
			game.roll(pins);
		}
	}
}