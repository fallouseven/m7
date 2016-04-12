package manager;

import java.util.ArrayList;

import services.StreamingStrategy;


public class ContextStrategy implements SystemCommonInterface{
	
	private StreamingStrategy strategy;
	/*
	 * methode pour definir le service utilise
	 * @param strategy, le service choisi
	 */
	public ContextStrategy(StreamingStrategy strategy){
		this.strategy = strategy;	
	}

	/*
	 * (non-Javadoc)
	 * @see manager.SystemCommonInterface#getMusic(int)
	 */
	@Override
	public Music getMusic(int idTrack) {
		// TODO Auto-generated method stub
		return strategy.get(idTrack);
	}
	/*
	 * (non-Javadoc)
	 * @see manager.SystemCommonInterface#executeSearch(java.lang.String)
	 */
	@Override
	public ArrayList<Music> executeSearch(String sound) {
		// TODO Auto-generated method stub
		return strategy.search(sound);
	}
}
