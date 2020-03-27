package fop.view.gui;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fop.controller.GameController;
import fop.model.interfaces.GameConstants;
import fop.model.interfaces.GameMethods;
import fop.model.interfaces.MessagesConstants;
import fop.model.player.ScoreEntry;
import fop.view.components.View;
import fop.view.components.gui.Resources;

/**
 * HighScore Area
 *
 */
public class HighscoreView extends View {

	private JButton btnBack;
	private JButton btnClear;
	private JTable scoreTable;
	private JLabel lblTitle;
	private JScrollPane scrollPane;

	public HighscoreView(GameWindow gameWindow) {
		super(gameWindow);
	}

	@Override
	public void onResize() {
		int offsetY = 25;
		lblTitle.setLocation((getWidth() - lblTitle.getWidth()) / 2, offsetY);
		offsetY += lblTitle.getSize().height + 25;
		scrollPane.setLocation(25, offsetY);
		
		
		scrollPane.setSize(getWidth() - 50, getHeight() - 50 - BUTTON_SIZE.height - offsetY);
		
		btnBack.setLocation((getWidth() / 3) - (BUTTON_SIZE.width / 2), getHeight() - BUTTON_SIZE.height - 25);
		btnClear.setLocation((2 * (getWidth() / 3) - (BUTTON_SIZE.width / 2)), getHeight() - BUTTON_SIZE.height - 25);
	}

	@Override
	protected void onInit() {
		btnBack = createButton("Back");
		btnClear = createButton("Delete");
		lblTitle = createLabel("Highscores", 45, true);
		
		Resources resources = Resources.getInstance();
		// TODO
		String[] colummName= {"Date", "Name","Points"};
		
		
		
		if(resources.getScoreEntries().size()!=0) {
			String[][] data= new String[resources.getScoreEntries().size()][3];
		for(int i=0;i<resources.getScoreEntries().size();i++) {
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			data[i][0]=df.format(resources.getScoreEntries().get(i).getDate().getTime());
			data[i][1]=resources.getScoreEntries().get(i).getName();
			data[i][2]=resources.getScoreEntries().get(i).getScore()+"";			
		}
		scoreTable=new JTable(data,colummName);
		
		add(scoreTable.getTableHeader());
		add(scoreTable);
		scrollPane = new JScrollPane(scoreTable);
		}
		else {
			scrollPane = new JScrollPane();
			
		}
		
	  
		
		
		
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(btnBack)) {
			GameMethods.GoToMainMenu();

		} else {
				int message = MessagesConstants.deleteHighScore();
				GameMethods.deleteHighScoreEntries(message);
		}
	}
}
