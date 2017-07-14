package com.service;


public class KataBowlingGame{
	// Le nombre de manches à jouer
	private static final int FRAME_SIZE = 10;
	// Le nombre de balles 
	private int[] rolls = new int[21];
	//La balle courante à utilisée
	private int currentRoll;

	//Méthode permettant de stocker le score du nombre
	//de quilles renversés pour chaque manche
	public void roll(int pins){
		rolls[currentRoll++] = pins;
	}

	//Méthode permettant de calculer le score du jeux
	public int score(){
		//Initialisation du score à 0
		int score = 0;
		//Initialisation du manche à 0
		int frameIndex=0;
		//Parcour de la liste des 10 maches
		for(int frame = 0; frame < FRAME_SIZE; frame++){
			//Si tout les quilles sont renversés du premier coup
			if(isStrike(frameIndex)){
				//Ajout de 10 points au score + un bonus de strike
				score += 10 + strikeBonus(frameIndex);
				//Incrémentation du manche pour passer au suivnant
				frameIndex++;
			}else if(isSpare(frameIndex)){
				//Ajout de 10 points au score + un bonus de spare
				score += 10 + spareBonus(frameIndex);
				//Incrémentation de deux manches pour passer au suivant
				//puisque ce n'est pas un strike
				frameIndex += 2;
			}else{
				//Calcul des quilles renversés
				score += sumOfAllInFrame(frameIndex);
				//Incrémentation de deux manches pour passer au suivant
				//puisque ce n'est pas un strike
				frameIndex += 2;
			}

		}
		//Retourner le score de la partie entière
		return score;
	}

	// Méthode permettant d'ajouter un bonus 
	// égale à la somme des quilles du manche courant et celui d'aprés
	private int strikeBonus(int frameIndex) {
		return rolls[frameIndex + 1]+rolls[frameIndex + 2];
	}

	// Méthode permettant d'ajouter un bonus  égale aux quilles
	// renversés au deuxième manche
	private int spareBonus(int frameIndex) {
		return rolls[frameIndex + 2];
	}

	//Méthode permettant de retourner la sommes des quilles 
	//renversés dans les deuc premiers manches
	private int sumOfAllInFrame(int frameIndex) {
		return rolls[frameIndex]+rolls[frameIndex+1];
	}

	// Méthode permettant de vérifier si toutes les 10 quilles ont 
	// été renversées en 2 coups
	private boolean isSpare(int frameIndex){
		return rolls[frameIndex] + rolls[frameIndex+1]==10;
	}

	// Méthode permettant de vérifier si toutes les quilles 
	// ont été renversées avec la première boule
	private boolean isStrike(int frameIndex){
		return rolls[frameIndex]==10;
	}


}