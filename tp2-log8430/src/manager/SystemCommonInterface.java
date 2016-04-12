package manager;

import java.util.ArrayList;


public interface SystemCommonInterface {
	/*
	 * methode pour recherche une musique via son id
	 * @param idTrack, l id de la musique
	 * @return Music, l objet musique
	 */
	public Music getMusic(int idTrack);
	/*
	 * methode pour rechercher de la musique via son nom
	 * @param sound, le nom de la musique
	 * @return ArrayList<Music>, la liste de musique
	 */
	public ArrayList<Music> executeSearch(String sound);
}
