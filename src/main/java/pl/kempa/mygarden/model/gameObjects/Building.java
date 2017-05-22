package pl.kempa.mygarden.model.gameObjects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.kempa.mygarden.model.Player;

@Entity
//@Table(name = "building")
public class Building {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int level;
	private int typeid;
	
	private Player ownplayer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "player_id")
	public Player getOwnplayer() {
		return ownplayer;
	}
   
	public int getBuildingId() {
		return id;
	}

	public void setBuildingId(int buildingId) {
		this.id = buildingId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

}
