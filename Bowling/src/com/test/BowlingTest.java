package com.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.service.KataBowlingGame;

public class BowlingTest{
	//Créatin d'une instance de l'objet kataBowlingGame
	//Variable globale dans toute la classe pour l'utiliser dans tout les méthodes
	//Une seule instance est suffisante pour le test de tout l'algorithme
	private KataBowlingGame game= new KataBowlingGame();

	@Test
	public void gutterGame() throws Exception {
		// Aucun quille n'est renversé dans tout les coups (manches)
		rollMany(20, 0);
		// Le score doit être à 0
		assertEquals(0, game.score());
	}

	@Test
	public void allOnes() throws Exception {
		// Renversé un quille pour chque quille sur 20 coups
		rollMany(20, 1);
		// Le résultat sera à 20
		assertEquals(20,game.score());
	}

	@Test
	public void canRollSpare() throws Exception{
		// Renversé les quilles sur deux coups
		rollSpare();
		// Renversé encore 3 quilles
		game.roll(3);
		// 17 coups sans aucune quille renversé
		rollMany(17,0);
		// Résultat 10 + bonus spare 3 + la sommes des quilles renversé 
		// sur les deux derniers coups (3+0) = 16
		assertEquals(16, game.score());
	}
	
	@Test
	public void canRollStrike() throws Exception{
		// Renversé les quilles sur un seul coup
		rollStrike();
		// Renversé 5 quilles
		game.roll(5);
		// Renversé 3 quilles
		game.roll(3);
		// 16 coups sans aucune quille renversée
		rollMany(16,0);
		// Résultat 10 + bonus strike 8 + la sommes des quilles renversées 
		// sur les deux derniers coups (5+3) = 26
		assertEquals(26, game.score());
	}

	@Test
	public void perfectGame() throws Exception{
		//Renversé 10 quilles pour chaque coup du 12 coups
		rollMany(12,10);
		assertEquals(300, game.score());
	}
	
	//Méthode permettant de renverser les quilles d'un seul coup
	private void rollStrike() {
		game.roll(10);
	}

	//Méthode permettant de renverser les quilles sur deux coups
	private void rollSpare(){
		game.roll(5);
		game.roll(5);
	}

	// Méthode permettant de renverser des quilles
	private void rollMany(int n, int pins){
		for(int i=0;i<n;i++){
			game.roll(pins);
		}
	}
}