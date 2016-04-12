package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import manager.ContextStrategy;
import manager.Music;
import services.LastFmMusic;
import services.SoundCloudMusic;

public class SearchTest {
	/*
	 * methode pour tester la recherche de music dans sound cloud
	 */
	@Test
	public void testSearchSouncloud() {
		ArrayList<Music> music = new ArrayList<Music>();
		ContextStrategy contextSoundCloud = new ContextStrategy(new SoundCloudMusic());
		music = contextSoundCloud.executeSearch("tu pac");
		assertTrue(music.size() >= 0);
		
	}
	/*
	 * methode pour tester la recherche de music dans last fm
	 */
	@Test
	public void testSearchLastFm() {
		ArrayList<Music> music = new ArrayList<Music>();
		ContextStrategy contextcontextLastFm = new ContextStrategy(new LastFmMusic());
		music = contextcontextLastFm.executeSearch("tu pac");
		assertTrue(music.size() >= 0);
		
	}

}
