package com.service;


public class KataBowlingGame{
	// Le nombre de manches � jouer
	private static final int FRAME_SIZE = 10;
	// Le nombre de balles 
	private int[] rolls = new int[21];
	//La balle courante � utilis�e
	private int currentRoll;

	//M�thode permettant de stocker le score du nombre
	//de quilles renvers�s pour chaque manche
	public void roll(int pins){
		rolls[currentRoll++] = pins;
	}

	//M�thode permettant de calculer le score du jeux
	public int score(){
		//Initialisation du score � 0
		int score = 0;
		//Initialisation du manche � 0
		int frameIndex=0;
		//Parcour de la liste des 10 maches
		for(int frame = 0; frame < FRAME_SIZE; frame++){
			//Si tout les quilles sont renvers�s du premier coup
			if(isStrike(frameIndex)){
				//Ajout de 10 points au score + un bonus de strike
				score += 10 + strikeBonus(frameIndex);
				//Incr�mentation du manche pour passer au suivnant
				frameIndex++;
			}else if(isSpare(frameIndex)){
				//Ajout de 10 points au score + un bonus de spare
				score += 10 + spareBonus(frameIndex);
				//Incr�mentation de deux manches pour passer au suivant
				//puisque ce n'est pas un strike
				frameIndex += 2;
			}else{
				//Calcul des quilles renvers�s
				score += sumOfAllInFrame(frameIndex);
				//Incr�mentation de deux manches pour passer au suivant
				//puisque ce n'est pas un strike
				frameIndex += 2;
			}

		}
		//Retourner le score de la partie enti�re
		return score;
	}

	// M�thode permettant d'ajouter un bonus 
	// �gale � la somme des quilles du manche courant et celui d'apr�s
	private int strikeBonus(int frameIndex) {
		return rolls[frameIndex + 1]+rolls[frameIndex + 2];
	}

	// M�thode permettant d'ajouter un bonus  �gale aux quilles
	// renvers�s au deuxi�me manche
	private int spareBonus(int frameIndex) {
		return rolls[frameIndex + 2];
	}

	//M�thode permettant de retourner la sommes des quilles 
	//renvers�s dans les deuc premiers manches
	private int sumOfAllInFrame(int frameIndex) {
		return rolls[frameIndex]+rolls[frameIndex+1];
	}

	// M�thode permettant de v�rifier si toutes les 10 quilles ont 
	// �t� renvers�es en 2 coups
	private boolean isSpare(int frameIndex){
		return rolls[frameIndex] + rolls[frameIndex+1]==10;
	}

	// M�thode permettant de v�rifier si toutes les quilles 
	// ont �t� renvers�es avec la premi�re boule
	private boolean isStrike(int frameIndex){
		return rolls[frameIndex]==10;
	}


}