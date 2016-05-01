package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Document;

public class PlayStream extends javax.swing.JFrame{

	public PlayStream()
	{
		super("STREAMING SERVICE");

		WindowListener l = new WindowAdapter() {
			public void windowClosing(Window e){
				System.exit(0);
			}
			
		};
				addWindowListener(l);
				setSize(1000,700);
				setVisible(true);	
	}
			/**
			 * @param args
			 */

			public static void main(String[] args) {
				
				// TODO Auto-generated method stub
				KeepValue keepValue = new KeepValue();
				MusicManager search = new MusicManager();
				System.out.println("TEST STREAMING");
				JFrame frame = new PlayStream();
				frame.setLayout(new BorderLayout());
				//some component
				//JButton button = new JButton("EXIT");
				//button.addActionListener(this); 
				JLabel label = new JLabel("Put text for search or create playlist");
				label.setFont(new Font("Serif", Font.PLAIN,12));
				BufferedImage buttonIcon;
				JButton button2 = new JButton("PLAY");;
				JButton buttonSearch = new JButton("SEARCH");
				buttonSearch.setToolTipText("Search Music");
				JButton button4 = new JButton("CREATE");
				button4.setToolTipText("Create PLAYLIST");
				JButton buttonAdd = new JButton("ADD");
				buttonAdd.setToolTipText("Add Music to PLAYLIST");
				JButton buttonRemove = new JButton("REMOVE");
				buttonRemove.setToolTipText("Remove Music to PLAYLIST");
				JTextField textField = new JTextField(20);
				
				// set icon button
				try {
					String workingDir = System.getProperty("user.dir");
					String file = workingDir+"/images/play.png";
					buttonIcon = ImageIO.read(new File(file));
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(buttonIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
					button2 = new JButton(imageIcon);
					button2.setToolTipText("Play Music");
					
					String fileSearch = workingDir+"/images/search.png";
					buttonIcon = ImageIO.read(new File(fileSearch));
					ImageIcon imageIconSearch = new ImageIcon(new ImageIcon(buttonIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
					buttonSearch = new JButton(imageIconSearch);
					buttonSearch.setToolTipText("Search Music");
					
					String fileAdd = workingDir+"/images/add.png";
					buttonIcon = ImageIO.read(new File(fileAdd));
					ImageIcon imageIconAdd = new ImageIcon(new ImageIcon(buttonIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
					buttonAdd = new JButton(imageIconAdd);
					buttonAdd.setToolTipText("Add Music to PLAYLIST");
					
					String fileRemove = workingDir+"/images/remove.png";
					buttonIcon = ImageIO.read(new File(fileRemove));
					ImageIcon imageIconRemove = new ImageIcon(new ImageIcon(buttonIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
					buttonRemove = new JButton(imageIconRemove);
					buttonRemove.setToolTipText("Remove Music to PLAYLIST");
					
					String fileCreate = workingDir+"/images/create.png";
					buttonIcon = ImageIO.read(new File(fileCreate));
					ImageIcon imageIconCreate = new ImageIcon(new ImageIcon(buttonIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
					button4 = new JButton(imageIconCreate);
					button4.setToolTipText("Create PLAYLIST");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				JPanel pane = new JPanel();
				JPanel pane1 = new JPanel();
				JPanel pane2 = new JPanel();
				JPanel pane3 = new JPanel();
				JPanel pane4 = new JPanel();
				pane4.setLayout(new GridLayout(1,2));
				//pane.add(button);
				pane.add(buttonRemove);
				pane.add(buttonAdd);
				pane.add(button2);
				pane.add(button4);
				pane.add(buttonSearch);
				pane.add(textField);
				
				//set size
				pane4.setPreferredSize(new Dimension(300, 600));
		        pane4.setMinimumSize(new Dimension(250, 600));
		        
		        
		        pane4.add(pane1);
				pane4.add(pane3);
				pane.add(label);
				
				frame.getContentPane().add(pane, BorderLayout.NORTH);
				frame.getContentPane().add(pane4, BorderLayout.WEST);
				frame.getContentPane().add(pane2, BorderLayout.CENTER);
				//frame.getContentPane().add(pane3, BorderLayout.EAST);
				frame.pack();
		        frame.setLocationRelativeTo(null);
				
				frame.show();
				String[] columnNames = {"Title",
                        "Genre",
                        "Source",
                        "Api",
                        "Number"
                        };
				String[] columnNames1 = {"Play List"
                        };
				String[] columnNames2 = {"Play List Selected"
                };
				
				DefaultTableModel dtm1 = new DefaultTableModel(null, columnNames1);
				JTable table1 = new JTable(dtm1){
			        private static final long serialVersionUID = 1L;

			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
			    table1.setPreferredSize(new Dimension(50,600));
				table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
				pane1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
				pane1.setLayout(new BorderLayout());
				pane1.add(new JScrollPane(table1.getTableHeader()), BorderLayout.NORTH);
				pane1.add(new JScrollPane( table1 ), BorderLayout.CENTER);
				DefaultTableModel dtm2 = new DefaultTableModel(null, columnNames2);
				JTable table2 = new JTable(dtm2){
			        private static final long serialVersionUID = 1L;

			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
				pane3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
				pane3.setLayout(new BorderLayout());
				pane3.add(new JScrollPane(table2.getTableHeader()), BorderLayout.NORTH);
				pane3.add(new JScrollPane(table2), BorderLayout.CENTER);
				DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
				JTable table = new JTable(dtm){
			        private static final long serialVersionUID = 1L;

			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
				
				search.parseDir("", dtm1);
				pane2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
				pane2.add(new JScrollPane(table));
				pane2.setLayout(new BorderLayout());
				pane2.add(new JScrollPane(table.getTableHeader()), BorderLayout.PAGE_START);
				pane2.add(new JScrollPane( table), BorderLayout.CENTER);
				buttonSearch.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						DefaultTableModel dtm = (DefaultTableModel) table.getModel();
						dtm.setRowCount(0);
						
						ArrayList<Music> musi = search.search(textField.getText());
						if(musi.isEmpty()){
							JOptionPane.showMessageDialog(null, "Cette musique n'existe pas!", "Information", JOptionPane.INFORMATION_MESSAGE);
						}else{
								for(Music sound : musi){
									dtm.addRow(new Object[]{String.valueOf(sound.getName()),
										String.valueOf(sound.getGenre()),
										String.valueOf(sound.getSource()),
										String.valueOf(sound.getApi()),
										Integer.valueOf(sound.getIdTrack())});
								}
								table.setModel(dtm);
							  
							    dtm.fireTableDataChanged();
						}
					}
					
				});
				button4.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						boolean notinsert = false;
						for(int i=0;i<dtm1.getRowCount();i++){
							System.out.println(dtm1.getValueAt(i, 0).toString());
							if((textField.getText().toLowerCase()).equals(dtm1.getValueAt(i, 0).toString()))
								notinsert = true;
						}
						if(!notinsert){
							search.createNewFile(String.valueOf(textField.getText()));
							dtm1.addRow(new Object[]{String.valueOf(textField.getText())
							});
						}
					}
					
				});
				//click button add
				buttonAdd.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("rowSelected :"+keepValue.getRowSelected1());
						boolean notinsert = false;
						for(int i=0;i<dtm2.getRowCount();i++){
							System.out.println(dtm2.getValueAt(i, 0).toString());
							if((textField.getText().toLowerCase()).equals(dtm2.getValueAt(i, 0).toString()))
								notinsert = true;
						}
						if(!notinsert && keepValue.getRowSelected() != -1 && keepValue.getRowSelected1() != -1){
							Music music = search.serializeToObject(String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 0)),
									String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 1)),
											String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 2)), 
													String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 3)),
													Integer.parseInt(String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 4))));
						Document doc = search.createPlayList();
							search.updateFileXml(music,String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)), doc);
						dtm2.addRow(new Object[]{String.valueOf(dtm.getValueAt(keepValue.getRowSelected(), 0).toString())
								
						});
						
						}
						if(keepValue.getRowSelected() == -1 && keepValue.getRowSelected1() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une play liste!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected1() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une play liste!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une musique!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				});
				//click buttonRemove
				buttonRemove.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Object[] options = {"Oui",
	                    "Non"};
						// TODO Auto-generated method stub
						if(keepValue.getRowSelected2() != -1 && keepValue.getRowSelected1() != -1){
							int dialogResult = JOptionPane.showOptionDialog (null, "Voulez vous supprimer la musique?","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							if(dialogResult == JOptionPane.YES_OPTION){
							search.removePlayList(String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)),String.valueOf(dtm2.getValueAt(keepValue.getRowSelected2(), 0)));
							dtm2.removeRow(keepValue.getRowSelected2());
							
							}
						}else if(keepValue.getRowSelected1() != -1){
							int dialogResult = JOptionPane.showOptionDialog (null, "Voulez vous supprimer la play liste?","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
								    null,     //do not use a custom Icon
								    options,  //the titles of buttons
								    options[0]);
							if(dialogResult == JOptionPane.YES_OPTION){
							search.removePlayList(String.valueOf(dtm1.getValueAt(keepValue.getRowSelected1(), 0)),"");
							dtm2.setRowCount(0);
							dtm1.removeRow(keepValue.getRowSelected1());
							
							}
						}else if(keepValue.getRowSelected1() == -1 && keepValue.getRowSelected2() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une play liste!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else if(keepValue.getRowSelected2() == -1){
							JOptionPane.showMessageDialog(null, "Veuillez selectionner une musique!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				});
				table1.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					    	dtm2.setRowCount(0);
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected1(target.getSelectedRow());
					      int column = target.getSelectedColumn();
					      // do some action if appropriate column
					      String nameFile = dtm1.getValueAt(keepValue.getRowSelected1(), 0).toString();
					      search.parseFile(nameFile, dtm2);
					      
					    }
					  }
					});
				table2.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected2(target.getSelectedRow());
					      //int column = target.getSelectedColumn();
					      
					    }
					  }
					});
				table.addMouseListener(new MouseAdapter() {
					int rowSelected = -1;
					  public void mouseClicked(MouseEvent e) {
					    if (e.getClickCount() == 1) {
					      JTable target = (JTable)e.getSource();
					      keepValue.setRowSelected(target.getSelectedRow());
					      //int column = target.getSelectedColumn();
					      
					    }
					  }
					});
			}
}

