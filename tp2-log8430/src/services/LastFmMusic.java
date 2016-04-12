package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import manager.Music;

public class LastFmMusic implements StreamingStrategy{
	private final String USER_AGENT = "Mozilla/5.0";
	private final String YOUR_API_KEY = "90db3977f4262f846e289b2ff0f7a89e";
	/*
	 * methode pour jouer la musique
	 */
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	/*
	 * methode pour obtenir une musique de last fm via son id.
	 * @params le id de la musique
	 * @return Music, objet d'une  musique
	 */
	@Override
	public Music get(int idTrack) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * methode pour rechercher les musiques dans last fm.
	 * @params music, le nom de la musique
	 * @return ArrayList<Music>, une liste de musique
	 */
	@Override
	public ArrayList<Music> search(String music) {
		// TODO Auto-generated method stub
		ArrayList<Music> listMusic = new ArrayList<Music>();
		String url = "http://ws.audioscrobbler.com/2.0/?method=track.search&track="+music+"&page=1&api_key="+YOUR_API_KEY;
		try {
			sendGet(url,listMusic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMusic;
	}

	@Override
	public int doOperation(int num1, int num2) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	 * methode pour obtenir les musiques dans last fm via une requet GET.
	 * @params url,le lien 
	 * @param listMusic, la liste de musique
	 * @return rien
	 */
	private void sendGet(String url,ArrayList<Music> listMusic) throws Exception {

		//String url = "http://www.google.com/search?q=mkyong";
			
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			//create a temp file xml
			String workingDir = System.getProperty("user.dir");
			String file = workingDir+"/lastFmSearch.xml";
			File f = new File (file); 
			FileWriter fw = new FileWriter (f);
			while ((inputLine = in.readLine()) != null) {
				//response.append(inputLine);
				fw.write (inputLine);
			}
			in.close();
			fw.close();
			//parse file
			parseFile(listMusic,file);
			//print result
			//System.out.println(response.toString());
	}
	/*
	 * methode pour lire un fichier xml contenant des musiques et 
	 * mettre le contenu dans une liste de musique.
	 * @params listMusic, la liste de musique
	 * @param file, le nom du fichier
	 * @return rien
	 */
	public void parseFile(ArrayList<Music> listMusic,String file){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(file));
			NodeList list = document.getElementsByTagName("track");
			System.out.println(list.getLength());
			for(int i = 0; i < list.getLength(); i++){
	        if (list.item(i) != null) {
	            NodeList subList = list.item(i).getChildNodes();
	            if (subList != null && subList.getLength() > 0) {
	            	Music musi = new Music(subList.item(0).getTextContent(),subList.item(1).getTextContent(),subList.item(2).getTextContent(),"lastFm",Integer.parseInt(subList.item(4).getTextContent()));
		            listMusic.add(musi);
	            	//System.out.println(subList.item(0).getTextContent());
	            }
	        }
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
