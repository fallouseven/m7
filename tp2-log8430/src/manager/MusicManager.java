package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import services.JambaseMusic;
import services.LastFmMusic;
import services.SoundCloudMusic;
import services.StreamingStrategy;

public class MusicManager{
/*
 * methode pour chercher de la musique dans tous les services
 * @param sound, le nom de la musique
 * @return ArrayList<Music>, la liste de musique trouvée
 */
	public ArrayList<Music> search(String  sound){
		ArrayList<Music> music = new ArrayList<Music>();
		ContextStrategy contextSoundCloud = new ContextStrategy(new SoundCloudMusic());
		music = contextSoundCloud.executeSearch(sound);
		ContextStrategy contextLastFm = new ContextStrategy(new LastFmMusic());
		//contextLastFm.executeSearch(sound);
		ContextStrategy contextJambase = new ContextStrategy(new JambaseMusic());
		ArrayList<Music> music2 = contextJambase.executeSearch(sound);
		if(music2 != null){
			music2.addAll(contextLastFm.executeSearch(sound));
			if( music != null)
				music.addAll(music2);
		}
		
		return music;
	}
	/*
	 * methode pour chercher de la musique dans un service donné
	 * @param sound, le nom de la musique
	 * @param strategy, le service selectionné
	 * @return ArrayList<Music>, la liste de musique trouvée
	 */
	public ArrayList<Music> search(String  sound,StreamingStrategy strategy){
		ArrayList<Music> music = new ArrayList<Music>();
		ContextStrategy contextSoundCloud = new ContextStrategy(strategy);
		music = contextSoundCloud.executeSearch(sound);
		//ContextStrategy contextSpotify = new ContextStrategy(new SpotifyMusic());
		//music.addAll(contextSpotify.executeSearch(sound));
		return music;
	}
	/*
	 * methode pour creer une play liste vide.
	 * @return Document, un document avec comme balise <playList></playList>
	 */
	public Document createPlayList(){
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.newDocument();
			         // root element
			         Element rootElement = doc.createElement("playList");
			         doc.appendChild(rootElement);
			         return doc;
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;	         
	}
	/*
	 * methode pour creer une play liste non vide.
	 * @param music, la musique à ajouter
	 * @param doc, le document xml
	 * @return rien
	 */
	public void createPlayList(Music music,Document doc){
		
    //  music element
		Element rootElement = doc.getDocumentElement();
					 Element musi = doc.createElement("music");
					 rootElement.appendChild(musi);
     // name element
					 Element name = doc.createElement("name");
					 name.appendChild(
					 doc.createTextNode(music.getName()));
					 musi.appendChild(name);
     // Genre element
					 Element genre = doc.createElement("genre");
					 genre.appendChild(
					 doc.createTextNode(music.getGenre()));
					 musi.appendChild(genre);
     // Source element
					 Element source = doc.createElement("source");
					 source.appendChild(
					 doc.createTextNode(music.getSource()));
					 musi.appendChild(source);
     // Api element
					 Element api = doc.createElement("api");
					 api.appendChild(
					 doc.createTextNode(music.getApi()));
					 musi.appendChild(api);
     // idTrack element
					 Element idTrack = doc.createElement("idTrack");
					 idTrack.appendChild(
					 doc.createTextNode(String.valueOf(music.getIdTrack())));
					 musi.appendChild(api);
	}
	/*
	 * methode pour sauvegarder le document xml dans un fichier xml.
	 * @param name, le nom du fichier xml
	 * @param doc, le document xml
	 * @return rien
	 */
	public void saveInFile(String name,Document doc){
		 // write the content into xml file
		String workingDir = System.getProperty("user.dir");
		String file = workingDir+"/files/"+name+".xml";
        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
	        StreamResult result =
	        new StreamResult(new File(file));
	        try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	/*
	 * methode pour faire le mise a jour d un fichier xml
	 * @param music, l objet de la musique
	 * @param nameFile, le nom du fichier xml
	 * @param doc, le document xml
	 * @return rien
	 */
	public void updateFileXml(Music music,String nameFile,Document doc){
		String workingDir = System.getProperty("user.dir");
		try {
			BufferedReader br = new BufferedReader(new FileReader(workingDir+"/files/"+nameFile+".xml"));     
			if (br.readLine() == null) {
				this.createPlayList(music, doc);
				saveInFile(nameFile,doc);
			    System.out.println("No errors, and file empty");
			}else{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder;
				try {
					documentBuilder = documentBuilderFactory.newDocumentBuilder();
					/* parse existing file to DOM */
					try {
						Document document = documentBuilder.parse(new File(workingDir+"/files/"+nameFile+".xml"));
						this.createPlayList(music, document);
						saveInFile(nameFile,document);
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	/*
	 * methode pour creer un objet music
	 * @param name, le nom de la musique
	 * @param genre, le genre de musique
	 * @param source, l url de la musique
	 * @param api, le nom du service de musique
	 * @param idTrack, l id de la musique
	 * @return Music, l objet musique cree
	 */
	public Music serializeToObject(String name,String genre,String source,String api,int idTrack){
		Music music = new Music(name,genre,source,api,idTrack);
		return music;
	}
	/*
	 * methode pour creer un fichier xml.
	 * @param name, le nom du fichier
	 * @return rien
	 */
	public void createNewFile(String name){
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir+"/files/"+name+".xml");
		if(!file.exists()){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(workingDir+"/files/"+name+".xml")))) {
	   
		} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	}
	/*
	 * methode lire un fichier xml et mettre son contenu dans un tableau
	 * @param nameFile, le nom du fichier xml
	 * @param dtm, le tableau
	 * @return rien
	 */
	public void parseFile(String nameFile,DefaultTableModel dtm){
		String workingDir = System.getProperty("user.dir");
	
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			/* parse existing file to DOM */
			
				try {
					BufferedReader br = new BufferedReader(new FileReader(workingDir+"/files/"+nameFile+".xml"));     
					if (br.readLine() == null) {
						
					}else{
					Document document = documentBuilder.parse(new File(workingDir+"/files/"+nameFile+".xml"));
					NodeList list = document.getElementsByTagName("music");
					System.out.println(list.getLength());
					for(int i = 0; i < list.getLength(); i++){
			        if (list.item(i) != null) {
			            NodeList subList = list.item(i).getChildNodes();
			            if (subList != null && subList.getLength() > 0) {
			            	dtm.addRow(new Object[]{subList.item(0).getTextContent()
							});
			            	System.out.println(subList.item(0).getTextContent());
			            }
			        }
					}
					}
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * methode pour lister les fichiers xml d'un repertoire; 
	 * ensuite chercher les noms des playlists et les mettre dans un tableau .
	 * @param nameDir, le nom du repertoire
	 * @param dtm, le tableau
	 * @return rien
	 */
	public void parseDir(String nameDir,DefaultTableModel dtm){
		String workingDir = System.getProperty("user.dir");
		final File folder = new File(workingDir+"/files/");
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	parseDir(fileEntry.getName(),dtm);
	        } else {
	        	String extension = "";

	        	int i = fileEntry.getName().lastIndexOf('.');
	        	if (i > 0) {
	        	    extension = fileEntry.getName().substring(i+1);
	        	}
	        	System.out.println(extension);
	        	if(extension.equals("xml"))
	        		dtm.addRow(new Object[]{fileEntry.getName().substring(0,i)
	        		});
	            System.out.println(fileEntry.getName());
	        }
	    }
	}
	/*
	 * methode pour supprimer une playlist
	 * @param nameList, le nom de la playlist
	 * @param nameFile, le nom du fichier
	 * @return rien
	 */
	public void removePlayList(String nameList, String nameFile){
		String workingDir = System.getProperty("user.dir");
		if(nameFile.isEmpty()){
			File file = new File(workingDir+"/files/"+nameList+".xml");
			file.deleteOnExit();
		}else{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			/* parse existing file to DOM */
			
				try {
					Document document = documentBuilder.parse(new File(workingDir+"/files/"+nameList+".xml"));
					NodeList list = document.getElementsByTagName("music");
					boolean find = false;
					int i = 0;
					for(i = 0; i < list.getLength() && !find; i++){
			        if (list.item(i) != null) {
			            NodeList subList = list.item(i).getChildNodes();
			            if (subList != null && subList.getLength() > 0) {
			            	if(subList.item(0).getTextContent().equals(nameFile))
			            		find = true;
			            	System.out.println(subList.item(0).getTextContent());
			            }
			        }
					}
					System.out.println(list.item(i-1).getFirstChild().getTextContent());
					list.item(i-1).getParentNode().removeChild(list.item(i-1));
					this.saveInFile(nameList, document);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public void searchLocal(){
		
	}
}

