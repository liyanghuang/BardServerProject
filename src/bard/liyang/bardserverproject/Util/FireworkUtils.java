package bard.liyang.bardserverproject.Util;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkUtils {
	 
	private static FireworkUtils fireWorks = new FireworkUtils();
	 
	//While in other classes you can now use RandomFirWorks.getManager().<METHODS>();
	
	public FireworkUtils()
	{
		addColors();
		addTypes();
	}
	 
	public static FireworkUtils getManager(){
		return fireWorks;
	}
	 
	//Make the arraylists for the colors and types
	ArrayList<Color> colors = new ArrayList<Color>();
	ArrayList<FireworkEffect.Type> types = new ArrayList<FireworkEffect.Type>();
	 

	//MAKE SURE YOU PUT THIS IN YOUR ONENABLE!!!
	public void addColors(){
		//ADD ALL THE COLORS
		colors.add(Color.WHITE);
		colors.add(Color.PURPLE);
		colors.add(Color.RED);
		colors.add(Color.GREEN);
		colors.add(Color.AQUA);
		colors.add(Color.BLUE);
		colors.add(Color.FUCHSIA);
		colors.add(Color.GRAY);
		colors.add(Color.LIME);
		colors.add(Color.MAROON);
		colors.add(Color.YELLOW);
		colors.add(Color.SILVER);
		colors.add(Color.TEAL);
		colors.add(Color.ORANGE);
		colors.add(Color.OLIVE);
		colors.add(Color.NAVY);
		colors.add(Color.BLACK);
		//I think I added them all not sure though
	}
	 
	//MAKE SURE YOU PUT THIS IN YOUR ONENABLE!!!
	public void addTypes(){
		//ADD ALL THE TYPES
		types.add(FireworkEffect.Type.BURST);
		types.add(FireworkEffect.Type.BALL);
		types.add(FireworkEffect.Type.BALL_LARGE);
		types.add(FireworkEffect.Type.CREEPER);
		types.add(FireworkEffect.Type.STAR);
		//Added all the types
	}
	 
	//Getting a random firework
	 
	public FireworkEffect.Type getRandomType(){
		int size = types.size();
		FireworkEffect.Type theType = types.get(RNGesus.rng.getRandom(size));
		 
		return theType;
	}
	 
	//Getting a random COLOR!!!
	 
	public Color getRandomColor(){
		int size = colors.size();
		Color color = colors.get(RNGesus.rng.getRandom(size));
		return color;
	}
	 
	public Firework getRandomFirework(Location loc){
		Firework fw = loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta fm = fw.getFireworkMeta();
		fm.setPower(1);
		//Adding all the effects to the firework meta
		fm.addEffects(FireworkEffect.builder().with(getRandomType()).withColor(getRandomColor()).build());
		//set the firework meta to the firework!
		fw.setFireworkMeta(fm);
		return fw;
	}
}
