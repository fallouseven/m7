package services;

import java.util.ArrayList;

import manager.Music;

public interface StreamingStrategy {
	/*
	 * methode pour jouer une musique
	 */
	  public void play();
	  /*
	   * methode pour obtenir une musique via son id
	   * @param idTrack, l id de la musique
	   * @return Music, l objet musique
	   */
	  public Music get(int idTrack);
	  /*
	   * methode pour chercher de la musique via son nom
	   * @param music, le terme à chercher
	   * @return ArrayList<Music>, la liste de musique
	   */
	  public ArrayList<Music> search(String music);
	  // this fuction is only for test
	  public int doOperation(int num1, int num2);

	}