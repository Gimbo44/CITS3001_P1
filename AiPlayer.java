import java.util.Random;

public class AiPlayer {
	
	private Random random;
	
	//This class is for the ai player, I'm not sure if the code will be big enough to warrant its 
	//own class, but I think it might be for the best. At the moment it is pretty empty..
	public AiPlayer() {
		random = new Random();
	}
	
	public int[] makeMove() {
		int level = random.nextInt(4) + 1; //random.nextInt(max - min + 1) + 1  https://stackoverflow.com/a/20389923
		return new int[] {level, random.nextInt(5 - level), random.nextInt(5 - level)};
	}
	
	public boolean removeTwo() {
		if (random.nextInt(2) == 1) {
			return true;
		}
		return false;
	}
}