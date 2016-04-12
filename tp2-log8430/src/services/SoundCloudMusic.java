package services;

import java.util.ArrayList;

import manager.Music;
import services.StreamingStrategy;
import de.voidplus.soundcloud.SoundCloud;
import de.voidplus.soundcloud.Track;

public class SoundCloudMusic implements StreamingStrategy{
	private SoundCloud soundcloud;
	/*
	 * (non-Javadoc)
	 * @see services.StreamingStrategy#play()
	 */
	@Override
	public void play(){
		
	}
	/*
	 * (non-Javadoc)
	 * @see services.StreamingStrategy#doOperation(int, int)
	 */
	@Override
	public int doOperation(int num1, int num2) {
	      return num1 * num2;
	}
	/*
	 * contructeur
	 */
	public SoundCloudMusic(){
		connect();
	} 
	/*
	 * (non-Javadoc)
	 * @see services.StreamingStrategy#get(int)
	 */
	@Override
	public Music get(int idTrack) {
		// TODO Auto-generated method stub
		Track track = soundcloud.getTrack(idTrack);
		Music musi = new Music(track.getTitle(),track.getGenre(),"","soundcloud",track.getId());
		return musi;
	}
/*
 * (non-Javadoc)
 * @see services.StreamingStrategy#search(java.lang.String)
 */
	@Override
	public ArrayList<Music> search(String music) {
		// TODO Auto-generated method stub
		ArrayList<Music> listMusic = new ArrayList<Music>();
		ArrayList<Track> result = soundcloud.findTrack(music);
		if(result!=null){
			
		    System.out.println("Tracks: "+result.size());
		    for(Track track:result){
		    		/*System.out.println(track.getId());
		    		System.out.println(track.getUserId());
		    		System.out.println(track.getTitle());
		    		System.out.println(track.getGenre());
		    		System.out.println(track.getTrackType());*/
		            Music musi = new Music(track.getTitle(),track.getGenre(),"","soundcloud",track.getId());
		            listMusic.add(musi);
		    }
		}
		return listMusic;
	}
	/*
	 * methode pour se connecter dans sound cloud
	 */
	private void connect(){
		 soundcloud = new SoundCloud(
			    "3a9d3057e13d2d0bf2bc6d31a036f6bb",
			    "3f02d82d9b67a06ebd3ee2deca11fae5"
			);
			soundcloud.login(
			    "oussou.dieng@gmail.com",
			    "soundcloud7"
			);
	}

}

